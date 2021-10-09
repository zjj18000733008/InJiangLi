package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/4
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String code;
    private String message;
    private T data;


    public static <T> ApiResponse success(T data){
        return ApiResponse.builder().code(ApiResultCode.SUCCESS.getCode())
                .message(ApiResultCode.SUCCESS.getMessage())
                .data(data).build();
    }

    public static <T> ApiResponse error(String code,String message){
        return ApiResponse.builder().code(code).message(message).build();
    }
    public static <T> ApiResponse error(){
        return ApiResponse.builder().code(ApiResultCode.SERVER_ERROR.getCode())
                .message(ApiResultCode.SERVER_ERROR.getMessage()).build();
    }


}
