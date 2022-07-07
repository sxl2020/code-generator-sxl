package com.sac.common.entity.dto;
/**
 * @author qiancheng-su
 * @description: 后台返回的统一消息格式
 * @date 2022/1/12 15:32
 */
public class ResponseMsg<T> {

    private int code;

    private String msg;

    private T data;

    public ResponseMsg(int code,String msg,T data){
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public ResponseMsg (int code,T data){
        this.code = code;
        this.data = data;
    }

    public static <T> ResponseMsg<T> ok(T data){
        return new ResponseMsg<T>(200,"操作成功",data);
    }


    public static <T> ResponseMsg<T> ok(int code,T data){
        return new ResponseMsg<T>(200,"操作成功",data);
    }

    public static <T> ResponseMsg<T> failed(T data){
        return new ResponseMsg<T>(-1,"操作失败",data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    @Override
    public String toString() {
        return "ResponseMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public void setData(T data) {
        this.data = data;
    }
}
