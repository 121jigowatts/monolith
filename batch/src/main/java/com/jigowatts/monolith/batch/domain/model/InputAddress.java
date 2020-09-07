package com.jigowatts.monolith.batch.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputAddress {
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
}