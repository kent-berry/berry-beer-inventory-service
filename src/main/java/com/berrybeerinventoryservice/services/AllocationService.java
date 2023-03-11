package com.berrybeerinventoryservice.services;

import com.brewery.model.BeerOrderDto;

public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);
}
