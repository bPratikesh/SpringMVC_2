package com.example.SpringMVCweek2.SpringMVC.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;


@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String msg;
    private List<String> subErrors;
}
