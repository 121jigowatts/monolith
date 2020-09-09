package com.jigowatts.monolith.api.controller;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jigowatts.monolith.domain.model.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressJson {
    @JsonProperty("prefecture_code")
    private String prefectureCode;
    @JsonProperty("prefecture")
    private String prefecture;
    @JsonProperty("prefecture_kana")
    private String prefectureKana;
    @JsonProperty("prefecture_roma")
    private String prefectureRoma;
    @JsonProperty("city_code")
    private String cityCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("city_kana")
    private String cityKana;
    @JsonProperty("city_roma")
    private String cityRoma;
    @JsonProperty("street_code")
    private String streetCode;
    @JsonProperty("street")
    private String street;

    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("create_at")
    private String createAt;
    @JsonProperty("create_by")
    private String createBy;
    @JsonProperty("update_at")
    private String updateAt;
    @JsonProperty("update_by")
    private String updateBy;

    public static AddressJson build(Address address) {

        String format = "yyyy/MM/dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String createAt = address.getCreateAt().format(dateTimeFormatter);
        String updateAt = address.getUpdateAt().format(dateTimeFormatter);

        return AddressJson.builder().prefectureCode(address.getPrefectureCode()).prefecture(address.getPrefecture())
                .prefectureKana(address.getPrefectureKana()).prefectureRoma(address.getPrefectureRoma())
                .cityCode(address.getCityCode()).city(address.getCity()).cityKana(address.getCityKana())
                .cityRoma(address.getCityRoma()).streetCode(address.getStreetCode()).street(address.getStreet())
                .latitude(address.getLatitude()).longitude(address.getLongitude()).createAt(createAt)
                .createBy(address.getCreateBy()).updateAt(updateAt).updateBy(address.getUpdateBy()).build();
    }
}
