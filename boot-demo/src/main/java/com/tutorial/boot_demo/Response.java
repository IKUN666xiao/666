package com.tutorial.boot_demo;
//判断调用情况
public class Response<T> {
    private  T data;
    private  boolean success;
    private  String errorMsg;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public static  <K> Response<K> newSuccess(K data) {
        Response<K> response =new Response<>();
        response.setData(data);

        response.setSuccess(true);
        return response;
    }
    public static  Response<Void> newFail(String errorMsg) {
        Response<Void> response =new Response<>();
        response.setErrorMsg(errorMsg);

        response.setSuccess(false);
        return response;
    }
    public static <T> Response<T> newError(String message) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
