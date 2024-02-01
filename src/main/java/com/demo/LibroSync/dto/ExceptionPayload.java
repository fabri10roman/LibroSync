package com.demo.LibroSync.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionPayload {
    private final int status;
    private final String title;
    private final String message;
}
