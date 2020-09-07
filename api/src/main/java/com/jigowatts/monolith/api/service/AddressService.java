package com.jigowatts.monolith.api.service;

import java.util.List;

import com.jigowatts.monolith.domain.model.Address;
import com.jigowatts.monolith.domain.model.AddressCriteria;

public interface AddressService {
    List<Address> search(AddressCriteria criteria);
}
