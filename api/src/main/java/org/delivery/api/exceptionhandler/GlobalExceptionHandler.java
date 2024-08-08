package org.delivery.api.exceptionhandler;


import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //예외를 여기다 모음
@Order(value = Integer.MAX_VALUE) // 순서를 지정해줌, //가장 마지막에 실행 적용
public class GlobalExceptionHandler {


    //예외를 다 잡음
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(

        Exception exception
    ){
        log.error("",exception);

        return  ResponseEntity
                .status(500)
                .body(Api.ERROR(ErrorCode.SERVER_ERROR));


    }
}
