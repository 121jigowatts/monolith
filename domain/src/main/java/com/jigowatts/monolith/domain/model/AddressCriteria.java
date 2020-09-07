package com.jigowatts.monolith.domain.model;

import java.util.Map;

import lombok.Getter;

@Getter
public class AddressCriteria {
    public static AddressCriteria buildCriteria(Map<String, String> queryParameters) {
        return new AddressCriteria(queryParameters);
    }

    private AddressCriteria(Map<String, String> queryParameters) {
        this.prefectureCode = queryParameters.get("prefectureCode");
        this.prefecture = queryParameters.get("prefecture");
        this.prefectureKana = queryParameters.get("prefectureKana");
        this.prefectureRoma = queryParameters.get("prefectureRoma");
        this.cityCode = queryParameters.get("cityCode");
        this.city = queryParameters.get("city");
        this.cityKana = queryParameters.get("cityKana");
        this.cityRoma = queryParameters.get("cityRoma");
        this.streetCode = queryParameters.get("streetCode");
        this.street = queryParameters.get("street");
        this.latitude = queryParameters.get("latitude");
        this.longitude = queryParameters.get("longitude");
    }

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
