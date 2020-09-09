package com.jigowatts.monolith.api.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jigowatts.monolith.api.exception.BadRequestException;
import com.jigowatts.monolith.api.exception.NotFoundException;
import com.jigowatts.monolith.api.service.AddressService;
import com.jigowatts.monolith.domain.model.Address;
import com.jigowatts.monolith.domain.model.AddressCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("address")
public class AddressApiController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public AddressResponse search(@RequestParam Map<String, String> queryParameters) {
        AddressCriteria criteria;
        try {
            criteria = AddressCriteria.buildCriteria(queryParameters);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
        List<Address> addressList = addressService.search(criteria);
        return AddressResponse.buildResponse(addressList, criteria);
    }

}
