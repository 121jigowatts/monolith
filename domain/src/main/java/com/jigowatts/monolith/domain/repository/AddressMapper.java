package com.jigowatts.monolith.domain.repository;

import java.util.List;

import com.jigowatts.monolith.domain.model.Address;
import com.jigowatts.monolith.domain.model.AddressCriteria;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
    public List<Address> search(AddressCriteria criteria);
    public int save(Address address);
    public int delete();
}
