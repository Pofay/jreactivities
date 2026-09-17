package com.giancarlo.gilos.jreactivities.DTOs;

public class ErrorDto {

    private String status;
    private String source;
    private String message;

    public ErrorDto(String status, String source, String message) {
        this.status = status;
        this.source = String.format("/data/attributes/%s", source);
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    @Override 
    public String toString() {
        return "{" +
               "\"status\":\"" + status + "\"," +
               "\"source\":\"" + source + "\"," +
               "\"message\":\"" + message + "\"" +
               "}";
    }

}
