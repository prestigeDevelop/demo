package com.example.demo.controller;

import com.example.demo.util.Errors;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime time;
            private Errors error;
        private String description;
        private String message;
}
