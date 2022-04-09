package dev.benkyou.weblog;

public class Rsp<T> {
    private Integer code = 200;
    private String msg;
    private T data;

    public Rsp(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public Rsp(T data) {
        this.data = data;
    }

    public static <T> Rsp<T> ok() {
        return new Rsp<>(null, null);
    }

    public static <T> Rsp<T> ok(T data) {
        return new Rsp<>(data, null);
    }

    public static <T> Rsp<T> ok(T data, String msg) {
        return new Rsp<>(data, msg);
    }

    public static <T> Rsp<T> fail() {
        return Rsp.fail(null, null);
    }

    public static <T> Rsp<T> fail(String msg) {
        return Rsp.fail(null, msg);
    }

    public static <T> Rsp<T> fail(T data, String msg) {
        Rsp<T> rsp = new Rsp<>(data, msg);
        rsp.code = 500;
        return rsp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
