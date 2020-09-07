package com.jigowatts.monolith.domain.repository;

import java.util.List;

import com.jigowatts.monolith.domain.model.Address;
import com.jigowatts.monolith.domain.model.AddressCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> search(AddressCriteria criteria) {
        return addressMapper.search(criteria);
    }

}
