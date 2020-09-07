package com.jigowatts.monolith.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String prefectureCode;
    private String prefecture;
    private String prefectureKana;
    private String prefectureRoma;
    private String cityCode;
    private String city;
    private String cityKana;
    private String cityRoma;
    private String streetCode;
    private String street;

    private String latitude;
    private String longitude;

    private LocalDateTime createAt;
    private String createBy;
    private LocalDateTime updateAt;
    private String updateBy;
}