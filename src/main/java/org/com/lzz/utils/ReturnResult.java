package org.com.lzz.utils;
import java.io.Serializable;

/**
 * Created by cp on 2016/8/20.
 *
 * 返回给前端的结果对象
 *
 */
public class ReturnResult implements Serializable{

    private int errCode;

    private String status;

    private String message;

    private long total;

    private Object data;

    public ReturnResult() {
    }

    public ReturnResult(String status, String message) {
        this.setStatus(status);
        this.setMessage(message);
    }

    public ReturnResult(String status, String message, Object data) {
        this(status, message);
        this.setData(data);
    }

    public void setContent(String status, String message) {
        this.setStatus(status);
        this.setMessage(message);
    }

    public void setContent(String status, String message, Object data) {
        setContent(status, message);
        this.setData(data);
    }

    public void setContent(String status, String message, Object data,long total) {
        setContent(status, message,data);
        this.setTotal(total);
    }

    public void setContent(int errCode, String status, String message, Object data) {
        setContent(status, message, data);
        this.setErrCode(errCode);
    }

    public void setContent(int errCode, String status, String message, Object data,long total) {
        setContent(errCode,status, message, data);
        this.setTotal(total);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * 返回代码,默认为0
     */
    public int getErrCode() {
        return errCode;
    }

    /**
     * 返回的状态标识
     */
    public String getStatus() {
        if (status == null) {
            return Constants.FAIL;
        }
        return status;
    }

    /**
     * 返回的消息文本
     */
    public String getMessage() {
        if (message == null) {
            return "";
        }
        return message;
    }

    /**
     * 返回的结果数据
     */
    public Object getData() {
        if (data == null) {
            return "";
        }

        return data;
    }
}
