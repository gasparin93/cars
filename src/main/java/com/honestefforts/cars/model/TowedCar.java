package com.honestefforts.cars.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;

public record TowedCar(
     @JsonProperty("Tow Date")
     @JsonDeserialize(using = DateDeserializer.class)
     LocalDate date,
     @JsonProperty("Make")
     String make,
     @JsonProperty("Style")
     String style,
     @JsonProperty("Model")
     String model,
     @JsonProperty("Color")
     String color,
     @JsonProperty("Plate")
     String plate,
     @JsonProperty("State")
     String state,
     @JsonProperty("Towed to Address")
     String facilityAddress,
     @JsonProperty("Tow Facility Phone")
     String facilityPhone,
     @JsonProperty("Inventory Number")
     String inventoryNumber
) { }
