package org.delivery.api.commom.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements  ErrorCodeIfs{

    USER_NOT_FOUND(400,1404,"사용자를 찾을수 없음");




    private  Integer httpStatusCode;

    private  Integer errorCode;


    private  String description;

}
