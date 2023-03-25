package com.wordle.wordlebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
public class Response<T> {
    private Integer status;
    private String message;
    private T data;
}
