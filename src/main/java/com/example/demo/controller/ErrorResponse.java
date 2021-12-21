package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime time;
            private String name;
        private String description;
        private String message;
}
