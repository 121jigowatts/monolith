package com.jigowatts.monolith.domain.model;

import java.util.Map;

import com.jigowatts.monolith.domain.model.criteria.HitPerPage;
import com.jigowatts.monolith.domain.model.criteria.PageOffset;

import lombok.Getter;

@Getter
public class AddressCriteria {
    public static AddressCriteria buildCriteria(Map<String, String> queryParameters) {
        return new AddressCriteria(queryParameters);
    }

    private AddressCriteria(Map<String, String> queryParameters) {
        this.hitPerPage = new HitPerPage(queryParameters.get("hit_per_page"));
        this.pageOffset = new PageOffset(queryParameters.get("page_offset"));

        this.prefectureCode = queryParameters.get("prefecture_code");
        this.prefecture = queryParameters.get("prefecture");
        this.prefectureKana = queryParameters.get("prefecture_kana");
        this.prefectureRoma = queryParameters.get("prefecture_roma");
        this.cityCode = queryParameters.get("city_code");
        this.city = queryParameters.get("city");
        this.cityKana = queryParameters.get("city_kana");
        this.cityRoma = queryParameters.get("city_roma");
        this.streetCode = queryParameters.get("street_code");
        this.street = queryParameters.get("street");
        this.latitude = queryParameters.get("latitude");
        this.longitude = queryParameters.get("longitude");
    }

    // 表示件数
    private HitPerPage hitPerPage;
    // 表示ページ
    private PageOffset pageOffset;

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
