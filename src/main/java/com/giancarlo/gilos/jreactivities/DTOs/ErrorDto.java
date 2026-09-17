package com.giancarlo.gilos.jreactivities.DTOs;

public class ErrorDto {

    private String status;
    private String title;

    private String source;
    private String detail;

    public ErrorDto(String status, String title, String source, String detail) {
        this.status = status;
        this.title = title;
        this.source = String.format("/data/attributes/%s", source);
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getDetail() {
        return detail;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "{" +
                "\"status\":\"" + status + "\"," +
                "\"title\":\"" + title + "\"," +
                "\"source\":\"" + source + "\"," +
                "\"detail\":\"" + detail + "\"" +
                "}";
    }

}
