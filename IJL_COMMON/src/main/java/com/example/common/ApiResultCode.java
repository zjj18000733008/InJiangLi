package com.example.common;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/5
 */
public enum ApiResultCode {

    SUCCESS("0","成功"),
    SERVER_ERROR("5000","服务端异常");

    private String code;
    private String message;

    ApiResultCode(String code,String message){
        this.code=code;
        this.message=message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

}
