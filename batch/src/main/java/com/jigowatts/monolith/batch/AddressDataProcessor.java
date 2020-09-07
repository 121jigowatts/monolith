package com.jigowatts.monolith.batch;

import java.time.LocalDateTime;

import com.jigowatts.monolith.batch.domain.model.InputAddress;
import com.jigowatts.monolith.domain.model.Address;

import org.springframework.batch.item.ItemProcessor;

public class AddressDataProcessor implements ItemProcessor<InputAddress, Address> {
    private static final String OPERATOR = "SYSTEM";

    private LocalDateTime processDateTime;

    public AddressDataProcessor(LocalDateTime processDateTime) {
        this.processDateTime = processDateTime;
    }

    @Override
    public Address process(InputAddress input) throws Exception {
        return Address.builder().prefectureCode(input.getPrefectureCode()).prefecture(input.getPrefecture())
                .prefectureKana(input.getPrefectureKana()).prefectureRoma(input.getPrefectureRoma())
                .cityCode(input.getCityCode()).city(input.getCity()).cityKana(input.getCityKana())
                .cityRoma(input.getCityRoma()).streetCode(input.getStreetCode()).street(input.getStreet())
                .latitude(input.getLatitude()).longitude(input.getLongitude()).createAt(processDateTime)
                .createBy(OPERATOR).updateAt(processDateTime).updateBy(OPERATOR).build();
    }

}
