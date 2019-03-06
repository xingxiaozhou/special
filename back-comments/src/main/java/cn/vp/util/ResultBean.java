package cn.vp.util;

import java.util.List;

/**
 * 接口返回参数封装
 */
public class ResultBean {
    //状态码（1000成功）
    private String  resultCode;

    //提示信息
    private String  message;

    //返回信息
    private Object  dataInfo;

    @Override
    public String toString() {
        return "ResultBean{" +
                "resultCode='" + resultCode + '\'' +
                ", message='" + message + '\'' +
                ", dataInfo='" + dataInfo + '\'' +
                '}';
    }

    public Object getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(Object dataInfo) {
        this.dataInfo = dataInfo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
