package ceneax.app.hongmiao.bean;

/**
 * note:不能继承BaseWeiboBean
 */
public class WeiboGsidResultBean {

    private int retcode;
    private String msg;
    private WeiboGsidBean data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WeiboGsidBean getData() {
        return data;
    }

    public void setData(WeiboGsidBean data) {
        this.data = data;
    }

}
