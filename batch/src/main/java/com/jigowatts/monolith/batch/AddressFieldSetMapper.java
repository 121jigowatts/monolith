package com.jigowatts.monolith.batch;

import com.jigowatts.monolith.batch.domain.model.InputAddress;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class AddressFieldSetMapper implements FieldSetMapper<InputAddress> {

    @Override
    public InputAddress mapFieldSet(FieldSet fieldSet) throws BindException {

        return InputAddress.builder().prefectureCode(fieldSet.readString(0)).prefecture(fieldSet.readString(1))
                .prefectureKana(fieldSet.readString(2)).prefectureRoma(fieldSet.readString(3))
                .cityCode(fieldSet.readString(4)).city(fieldSet.readString(5)).cityKana(fieldSet.readString(6))
                .cityRoma(fieldSet.readString(7)).streetCode(fieldSet.readString(8)).street(fieldSet.readString(9))
                .latitude(fieldSet.readString(10)).longitude(fieldSet.readString(11)).build();
    }

}
