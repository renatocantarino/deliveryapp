package com.cantarino.delivery.delivery.controllers;

import com.cantarino.delivery.delivery.exception.ResourceNotFoundException;
import com.cantarino.delivery.delivery.models.Delivery;
import com.cantarino.delivery.delivery.repositories.DeliveryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DeliveryController {

    private final DeliveryRepository _deliveryRepository;
    public DeliveryController(DeliveryRepository deliveryRepository) {
        this._deliveryRepository = deliveryRepository;
    }

    @GetMapping("/deliveries")
    public List<Delivery> getAll()
    {
        return _deliveryRepository.findAll();
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable(value = "id") Long deliveryId) throws ResourceNotFoundException
    {
        Delivery delivery = _deliveryRepository.findById(deliveryId)
                           .orElseThrow(() -> new ResourceNotFoundException("Delivery not found : " + deliveryId));

        return ResponseEntity.ok().body(delivery);
    }

    @PostMapping("/delivery")
    public ResponseEntity<Delivery> create(@RequestBody Delivery delivery)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(_deliveryRepository.saveAndFlush(delivery));
    }

    @PutMapping("/delivery/{id}")
    public ResponseEntity<Delivery> update(@PathVariable(value = "id") Long deliveryId,
                                           @Valid @RequestBody Delivery deliveryDetails)
            throws ResourceNotFoundException
    {
        Delivery _delivery = _deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found : " + deliveryId));

        _delivery.setCustomer_id(deliveryDetails.getCustomer_id());
        _delivery.setAddress(deliveryDetails.getAddress());
        _delivery.setFee(deliveryDetails.getFee());
        _delivery.setOrder_id(deliveryDetails.getOrder_id());

        final Delivery updated = _deliveryRepository.saveAndFlush(_delivery);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delivery/{id}")
    public ResponseEntity<Delivery> delete(@PathVariable(value = "id") Long deliveryId)
            throws ResourceNotFoundException
    {
        Delivery _delivery = _deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found : " + deliveryId));

       _deliveryRepository.delete(_delivery);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
