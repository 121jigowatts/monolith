package com.jigowatts.monolith.api.controller;

import java.util.List;
import java.util.stream.Collectors;

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
    private int totalHitCount;
    // 表示件数
    private int hitPerPage;
    // 表示ページ
    private int pageOffset;
    private List<Address> addressList;

    public static AddressResponse buildResponse(List<Address> addressList, AddressCriteria criteria) {

        // 表示件数50件、表示ページが２ページ目からの場合はskip(50 * (2-1)).limit(50)
        var skipPage = criteria.getHitPerPage().getValue() * (criteria.getPageOffset().getValue() - 1);
        var limitValue = criteria.getHitPerPage().getValue();
        List<Address> resultList = addressList.stream().skip(skipPage).limit(limitValue).collect(Collectors.toList());

        return AddressResponse.builder()
        .totalHitCount(addressList.size())
        .hitPerPage(criteria.getHitPerPage().getValue())
        .pageOffset(criteria.getPageOffset().getValue())
        .addressList(resultList)
        .build();
    }
}
