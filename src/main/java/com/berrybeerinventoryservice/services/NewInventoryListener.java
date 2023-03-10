package com.berrybeerinventoryservice.services;

import com.berrybeerinventoryservice.domain.BeerInventory;
import com.berrybeerinventoryservice.repositories.BeerInventoryRepository;
import com.common.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import static com.berrybeerinventoryservice.config.JmsConfig.NEW_INVENTORY_QUEUE;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewInventoryListener {
    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent newInventoryEvent){
        log.debug("Got Inventory: " + newInventoryEvent.toString());

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(newInventoryEvent.getBeerDto().getId())
                .upc(newInventoryEvent.getBeerDto().getUpc())
                .quantityOnHand(newInventoryEvent.getBeerDto().getQuantityOnHand())
                .build());
    }
}
