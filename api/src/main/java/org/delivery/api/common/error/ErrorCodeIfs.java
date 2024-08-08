package org.delivery.api.commom.error;

public interface ErrorCodeIfs  {

    Integer getHttpStatusCode();

    Integer getErrorCode();


    String getDescription();
}
