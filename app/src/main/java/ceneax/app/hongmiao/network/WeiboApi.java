package ceneax.app.hongmiao.network;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ceneax.app.hongmiao.bean.WeiboBean;
import ceneax.app.hongmiao.bean.WeiboCommentResultBean;
import ceneax.app.hongmiao.bean.WeiboGroupResultBean;
import ceneax.app.hongmiao.bean.WeiboGroupStatusesResultBean;
import ceneax.app.hongmiao.bean.WeiboGsidBean;
import ceneax.app.hongmiao.bean.WeiboGsidResultBean;
import ceneax.app.hongmiao.bean.WeiboHotwordCardBean;
import ceneax.app.hongmiao.bean.WeiboProfileStatusesCardBean;
import ceneax.app.hongmiao.bean.WeiboProfileStatusesResultBean;
import ceneax.app.hongmiao.bean.WeiboReplyResultBean;
import ceneax.app.hongmiao.bean.WeiboResultBean;
import ceneax.app.hongmiao.bean.WeiboTopicResultBean;
import ceneax.app.hongmiao.bean.WeiboUserResultBean;
import ceneax.app.hongmiao.util.HttpUtil;
import ceneax.app.hongmiao.util.L;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Response;

public class WeiboApi {

    // 单例对象
    private static WeiboApi instance;

    private static final String baseUrl = "https://api.weibo.cn/2/";

    /**
     * 获取单例
     * @return 返回单例对象
     */
    public static WeiboApi getInstance() {
        if(instance == null)
            return new WeiboApi();
        return instance;
    }

    /**
     * 构造函数
     */
    public WeiboApi() {
    }

    /**
     * 登录
     * @param username username
     * @param password password
     * @param listener 回调接口
     */
    public void login(String username, String password, final Listener listener) {
        HttpUtil.getInstance().post("https://passport.weibo.cn/sso/login", new Headers.Builder()
                .add("Referer", "https://passport.weibo.cn/signin/login?entry=mweibo&res=wel&wm=3349&r=https%3A%2F%2Fm.weibo.cn%2F")
                .build(), new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("savestate", "1")
                .add("r", "https://m.weibo.cn/")
                .add("ec", "0")
                .add("pagerefer", "https://m.weibo.cn/login?backURL=https%253A%252F%252Fm.weibo.cn%252F")
                .add("entry", "mweibo")
                .add("mainpageflag", "1")
                .build(),new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboGsidResultBean gsidResultBean = new Gson().fromJson(response.body().string(), WeiboGsidResultBean.class);
                WeiboGsidBean gsidBean = gsidResultBean.getData();

                if(gsidResultBean.getRetcode() == 20000000) {
                    String gsid = "";
                    String expires = "";

                    for(String tmpCookie : response.headers().values("Set-Cookie")) {
                        if(tmpCookie.contains("SUB=")) {
                            gsid = tmpCookie.substring(tmpCookie.indexOf("SUB="), tmpCookie.indexOf(";")).replace("SUB=", "");
                            expires = tmpCookie.substring(tmpCookie.indexOf("Expires="), tmpCookie.lastIndexOf(";")).replace("Expires=", "");
                            break;
                        }
                    }

                    if(!TextUtils.isEmpty(gsid)) {
                        gsidBean.setGsid(gsid);
                        gsidBean.setExpires(expires);
                        listener.success(gsidBean);
                    } else {
                        listener.fail(new IOException("Gsid is null"));
                    }
                } else {
                    listener.fail(new IOException(gsidResultBean.getMsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 获取个人资料
     * @param gsid gsid
     * @param uid uid
     * @param listener 回调接口
     */
    public void getProfile(String gsid, long uid, final Listener listener) {
        HttpUtil.getInstance().get(baseUrl + "profile?uid=" + uid + "&gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN", new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboUserResultBean userResultBean = new Gson().fromJson(response.body().string(), WeiboUserResultBean.class);

                if(TextUtils.isEmpty(userResultBean.getErrmsg()) && userResultBean.getUserInfo() != null && userResultBean.getTabsInfo() != null) {
                    listener.success(userResultBean);
                } else {
                    listener.fail(new IOException(userResultBean.getErrmsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 获取个人资料
     * @param gsid gsid
     * @param nickname nickname
     * @param listener 回调接口
     */
    public void getProfile(String gsid, String nickname, final Listener listener) {
        HttpUtil.getInstance().get(baseUrl + "profile?nick=" + nickname + "&gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN", new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboUserResultBean userResultBean = new Gson().fromJson(response.body().string(), WeiboUserResultBean.class);

                if(TextUtils.isEmpty(userResultBean.getErrmsg()) && userResultBean.getUserInfo() != null && userResultBean.getTabsInfo() != null) {
                    listener.success(userResultBean);
                } else {
                    listener.fail(new IOException(userResultBean.getErrmsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 获取指定页码的关注的微博
     * @param gsid gsid
     * @param page 页码
     * @param listener 回调接口
     */
    public void getWeibo(String gsid, final String groupId, int page, final Listener listener) {
        String url;

        if(groupId != null)
            url = baseUrl + "groups/timeline?gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN&page=" + page + "&list_id=" + groupId;
        else
            url = baseUrl + "statuses/friends_timeline?gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN&page=" + page;

        HttpUtil.getInstance().get(url, new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                String errMsg = null;
                List<WeiboBean> lists = new ArrayList<>();

                if(groupId == null) {
                    WeiboResultBean resultBean = new Gson().fromJson(response.body().string(), WeiboResultBean.class);
                    errMsg = resultBean.getErrmsg();
                    lists.addAll(resultBean.getStatuses());
                } else {
                    WeiboGroupStatusesResultBean resultBean = new Gson().fromJson(response.body().string(), WeiboGroupStatusesResultBean.class);
                    errMsg = resultBean.getErrmsg();
                    lists.addAll(resultBean.getStatuses());
                }

                if(TextUtils.isEmpty(errMsg)) {
                    List<WeiboBean> list = new ArrayList<>();

                    // 去除广告
                    for(WeiboBean bean : lists) {
                        if(bean.getAdState() != 1) {
                            list.add(bean);
                        }
                    }

                    listener.success(list);
                } else {
                    listener.fail(new IOException(errMsg));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 获取微博完整内容
     * @param gsid gsid
     * @param id id
     * @param listener listener
     */
    public void getWeiboLongText(String gsid, String id, final Listener listener) {
        HttpUtil.getInstance().get(baseUrl + "statuses/show?gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN&id=" + id + "&isGetLongText=1", new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboBean weiboBean = new Gson().fromJson(response.body().string(), WeiboBean.class);

                if(TextUtils.isEmpty(weiboBean.getErrmsg())) {
                    listener.success(weiboBean);
                } else {
                    listener.fail(new IOException(weiboBean.getErrmsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 获取微博评论
     * @param gsid gsid
     * @param id id
     * @param maxId 页码
     * @param listener listener
     */
    public void getWeiboComment(String gsid, String id, String maxId, final Listener listener) {
        HttpUtil.getInstance().get(baseUrl + "comments/build_comments?gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN&id=" + id + "&is_append_blogs=1&is_show_bulletin=2&max_id=" + maxId, new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboCommentResultBean commentResultBean = new Gson().fromJson(response.body().string(), WeiboCommentResultBean.class);

                if(TextUtils.isEmpty(commentResultBean.getErrmsg())) {
                    listener.success(commentResultBean);
                } else {
                    listener.fail(new IOException(commentResultBean.getErrmsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 获取评论的回复
     * @param gsid gsid
     * @param id id
     * @param maxId maxId
     * @param listener listener
     */
    public void getWeiboReply(String gsid, String id, String maxId, final Listener listener) {
        HttpUtil.getInstance().get(baseUrl + "comments/build_comments?gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN&id=" + id + "&is_append_blogs=1&is_show_bulletin=2&max_id=" + maxId + "&fetch_level=1", new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboReplyResultBean replyResultBean = new Gson().fromJson(response.body().string(), WeiboReplyResultBean.class);

                if(TextUtils.isEmpty(replyResultBean.getErrmsg())) {
                    listener.success(replyResultBean);
                } else {
                    listener.fail(new IOException(replyResultBean.getErrmsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 获取热搜
     * @param listener 回调接口
     */
    public void getHotword(String gsid, final Listener listener) {
        HttpUtil.getInstance().get(baseUrl + "page?gsid=" + gsid + "&containerid=106003%26type=25%26t=3%26disable_hot=1%26filter_type=realtimehot&c=android&from=10A5095010&lang=zh_CN&s=e298a9ce", new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboHotwordCardBean cardBean = new Gson().fromJson(response.body().string(), WeiboHotwordCardBean.class);

                if(TextUtils.isEmpty(cardBean.getErrmsg()) && cardBean.getCards() != null) {
                    listener.success(cardBean.getCards().get(0).getCardGroup());
                } else {
                    listener.fail(new IOException(cardBean.getErrmsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 获取分组
     * @param gsid gsid
     * @param listener listener
     */
    public void getGroup(String gsid, final Listener listener) {
        HttpUtil.getInstance().get(baseUrl + "groups?gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN", new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboGroupResultBean groupResultBean = new Gson().fromJson(response.body().string(), WeiboGroupResultBean.class);

                if(TextUtils.isEmpty(groupResultBean.getErrmsg())) {
                    listener.success(groupResultBean.getLists());
                } else {
                    listener.fail(new IOException(groupResultBean.getErrmsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 获取自己的微博列表
     * @param gsid gsid
     * @param uid uid
     * @param containerid containerid
     * @param page page
     * @param listener listener
     */
    public void getProfileStatuses(String gsid, String uid, String containerid, int page, final Listener listener) {
        HttpUtil.getInstance().get(baseUrl + "profile/statuses?gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN&page=" + page + "&uid=" + uid + "&containerid=" + containerid + "_-_WEIBO_SECOND_PROFILE_WEIBO", new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboProfileStatusesResultBean resultBean = new Gson().fromJson(response.body().string(), WeiboProfileStatusesResultBean.class);

                if(TextUtils.isEmpty(resultBean.getErrmsg())) {
                    List<WeiboBean> list = new ArrayList<>();

                    for(WeiboProfileStatusesCardBean cardBean : resultBean.getCards()) {
                        if(cardBean.getMblog() != null) {
                            list.add(cardBean.getMblog());
                        }
                    }

                    listener.success(list);
                } else {
                    listener.fail(new IOException(resultBean.getErrmsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    public void getSearchAll(String gsid, String kw, int page, final Listener listener) {
        HttpUtil.getInstance().get(baseUrl + "searchall?gsid=" + gsid + "&s=e298a9ce&c=android&from=10A5095010&lang=zh_CN&page=" + page + "&containerid=100103%26type=1%26q=" + kw + "%26t=0", new HttpUtil.Listener() {
            @Override
            public void success(Response response) throws IOException {
                WeiboTopicResultBean resultBean = new Gson().fromJson(response.body().string(), WeiboTopicResultBean.class);

                if(TextUtils.isEmpty(resultBean.getErrmsg())) {
                    listener.success(resultBean);
                } else {
                    listener.fail(new IOException(resultBean.getErrmsg()));
                }
            }

            @Override
            public void fail(IOException e) {
                listener.fail(e);
            }
        });
    }

    /**
     * 回调接口
     * @param <T>
     */
    public interface Listener<T> {
        void success(T res);
        void fail(IOException e);
    }

}
