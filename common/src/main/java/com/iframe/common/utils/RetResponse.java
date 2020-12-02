package com.iframe.common.utils;

import com.iframe.common.enums.RetCodeEnum;

public class RetResponse {
    private final static String SUCCESS = "SUCCESS";

    public static <T> ResponseResult<T> makeOKRsp() {
        return new ResponseResult<T>().setCode(RetCodeEnum.SUCCESS).setMsg(SUCCESS);
    }

    public static <T> ResponseResult<T> makeOKRsp(T data) {
        return new ResponseResult<T>().setCode(RetCodeEnum.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> ResponseResult<T> makeErrRsp(String message) {
        return new ResponseResult<T>().setCode(RetCodeEnum.FAIL).setMsg(message);
    }

    public static <T> ResponseResult<T> makeErrUnauisp(String message) {
        return new ResponseResult<T>().setCode(RetCodeEnum.UNAUTHORIZED).setMsg(message);
    }

    public static <T> ResponseResult<T> makeRsp(int code, String msg) {
        return new ResponseResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> ResponseResult<T> makeRsp(int code, String msg, T data) {
        return new ResponseResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}
