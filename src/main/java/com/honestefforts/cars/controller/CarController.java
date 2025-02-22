package com.honestefforts.cars.controller;

import ch.qos.logback.core.util.StringUtil;
import com.honestefforts.cars.model.TowedCar;
import com.honestefforts.cars.repository.EmbeddedCarDb;
import java.util.List;
import lombok.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CarController {

  //this really should be in a service, but this app is too simple, it would be a waste of time
  @NonNull
  private final EmbeddedCarDb repository;

  //lombok was having issues on my local, did not want to debug for now
  public CarController(EmbeddedCarDb repository) {
    this.repository = repository;
  }

  @GetMapping("/getTowedCar")
  public List<TowedCar> receivePayment(@RequestParam(required = false) String plate,
      @RequestParam(required = false) String state) {
    if(plate == null && state == null) {
      return repository.findAll();
    }
    return repository.findByPlateAndState(StringUtil.nullStringToEmpty(plate),
        StringUtil.nullStringToEmpty(state));
  }

}