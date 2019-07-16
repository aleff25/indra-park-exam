package com.indraparkapi.domain.commons.exception;

public class ParkException extends RuntimeException {

    private final ParkError parkError;
    private final int httpStatusCode;

    public ParkException(String code, String message, int httpStatusCode) {
        this(new ParkError.Builder(code, message).build(), httpStatusCode);
    }

    public ParkException(ParkError error, int httpStatusCode) {
        super(error.getFormattedMessage());
        this.parkError = error;
        this.httpStatusCode = httpStatusCode;
    }

    public ParkException(ParkError error, Throwable ex, int httpStatusCode) {
        super(error.getFormattedMessage(), ex);
        this.parkError = error;
        this.httpStatusCode = httpStatusCode;
    }

    public ParkError getParkError() {
        return parkError;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
