package com.indraparkapi.domain.commons.exception;

import java.io.Serializable;

public class ParkError implements Serializable {

    private String code;
    private String message;

    public ParkError() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFormattedMessage(){
        return String.format("%s - %s", getCode(), getMessage());
    }

    public static class Builder {

        private final ParkError parkError;

        public Builder(String code, String message) {
            this.parkError = new ParkError();
            parkError.code = code;
            parkError.message = message;
        }


        public  ParkError build() {
            return parkError;
        }
    }
}
