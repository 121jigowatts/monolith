package com.jigowatts.monolith.domain.repository;

import java.util.List;

import com.jigowatts.monolith.domain.model.Address;
import com.jigowatts.monolith.domain.model.AddressCriteria;

public interface AddressRepository {
    List<Address> search(AddressCriteria criteria);
}
