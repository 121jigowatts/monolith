package com.jigowatts.monolith.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jigowatts.monolith.domain.model.Address;
import com.jigowatts.monolith.domain.model.AddressCriteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    // 該当件数
    @JsonProperty("total_hit_count")
    private int totalHitCount;
    // 表示件数
    @JsonProperty("hit_per_page")
    private int hitPerPage;
    // 表示ページ
    @JsonProperty("page_offset")
    private int pageOffset;
    
    @JsonProperty("address")
    private List<AddressJson> addressList;

    public static AddressResponse buildResponse(List<Address> addressList, AddressCriteria criteria) {

        // 表示件数50件、表示ページが２ページ目からの場合はskip(50 * (2-1)).limit(50)
        var skipPage = criteria.getHitPerPage().getValue() * (criteria.getPageOffset().getValue() - 1);
        var limitValue = criteria.getHitPerPage().getValue();
        List<AddressJson> resultList = addressList.stream().skip(skipPage).limit(limitValue).map(AddressJson::build)
                .collect(Collectors.toList());

        return AddressResponse.builder().totalHitCount(addressList.size())
                .hitPerPage(criteria.getHitPerPage().getValue()).pageOffset(criteria.getPageOffset().getValue())
                .addressList(resultList).build();
    }
}
