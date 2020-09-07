package com.jigowatts.monolith.api.service;

import java.util.List;

import com.jigowatts.monolith.domain.model.Address;
import com.jigowatts.monolith.domain.model.AddressCriteria;
import com.jigowatts.monolith.domain.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Address> search(AddressCriteria criteria) {
        return addressRepository.search(criteria);
    }
}
