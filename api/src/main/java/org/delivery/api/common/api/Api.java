package org.delivery.api.commom.api;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.commom.error.ErrorCodeIfs;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private  Result result;


    @Valid
    private  T body; //http 바디 넣어놈


        public  static <T> Api<T> OK(T data){
            var api = new Api<T>();
            api.result=Result.OK();
            api.body=data;
            return  api;

        }

    public  static  Api<Object> ERROR(Result result){
        var api = new Api<Object>();
        api.result=result;
        return  api;

    }
    public  static  Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){
        var api = new Api<Object>();
        api.result=Result.ERROR(errorCodeIfs);
        return  api;

    }

    public  static  Api<Object> ERROR(ErrorCodeIfs errorCodeIfs,Throwable tx){
        var api = new Api<Object>();
        api.result=Result.ERROR(errorCodeIfs,tx);
        return  api;

    }

    public  static  Api<Object> ERROR(ErrorCodeIfs errorCodeIfs,String Description){
        var api = new Api<Object>();
        api.result=Result.ERROR(errorCodeIfs,Description);
        return  api;

    }
}
