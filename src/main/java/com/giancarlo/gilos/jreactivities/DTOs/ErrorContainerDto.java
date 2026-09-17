package com.giancarlo.gilos.jreactivities.DTOs;

import java.util.List;

public class ErrorContainerDto {

    public List<Object> errors;

    private ErrorContainerDto(List<Object> errorDtos) {
        this.errors = errorDtos;
    }

    public static ErrorContainerDto wrap(Object errorDto) {
        return new ErrorContainerDto(List.of(errorDto));
    }

    public static ErrorContainerDto wrap(List<Object> errorDtos) {
        return new ErrorContainerDto(errorDtos);
    }

    @Override 
    public String toString() {
        return "{ errors:" + errors + "}";
    }

}
