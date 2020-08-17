package ceneax.app.hongmiao.bean;

/**
 * note: 不能继承BaseWeiboBean
 */
public class WeiboGsidBean {

    private String uid;
    private String gsid;
    private String expires;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGsid() {
        return gsid;
    }

    public void setGsid(String gsid) {
        this.gsid = gsid;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

}
