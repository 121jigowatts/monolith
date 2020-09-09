package com.jigowatts.monolith.api.controller;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int status;
    private String error;
    private String message;

}
