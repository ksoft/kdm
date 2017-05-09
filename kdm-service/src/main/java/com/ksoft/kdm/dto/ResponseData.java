package com.ksoft.kdm.dto;
import java.io.Serializable;


public class ResponseData<T> implements Serializable {
    private boolean success;
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private RespCode code;
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private PageVO<T> data;

    private ResponseData(boolean success, RespCode code, String message, PageVO<T> data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseData<T> success(String rspMsg) {
        return new ResponseData<T>(true, RespCode.ACK, rspMsg, null);
    }

    public static <T> ResponseData<T> success(String rspMsg, PageVO<T> data) {
        return new ResponseData<T>(true, RespCode.ACK, rspMsg, data);
    }



    public static <T> ResponseData<T> fail(String rspMsg) {
        return new ResponseData<T>(false, RespCode.NACK, rspMsg, null);
    }

    public static <T> ResponseData<T> unAuthorized(String rspMsg) {
        return new ResponseData<T>(false, RespCode.UNAUTHORIZED, rspMsg, null);
    }


    public enum RespCode{
        ACK,
        NACK,
        UNAUTHORIZED
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RespCode getCode() {
        return code;
    }

    public void setCode(RespCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageVO<T> getData() {
        return data;
    }

    public void setData(PageVO<T> data) {
        this.data = data;
    }
}
