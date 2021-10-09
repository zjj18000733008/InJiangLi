package com.example.common.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.common.ApiResponse;
import com.example.common.exception.BusinessRuntimeException;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/4
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse handleException(Exception e){
        e.printStackTrace();
        return ApiResponse.error();
    }

    @ExceptionHandler(value = BusinessRuntimeException.class)
    @ResponseBody
    public ApiResponse handleException(BusinessRuntimeException e){
        e.printStackTrace();
        return ApiResponse.error(ApiResponse.error().getCode(), e.getMessage());
    }
}
