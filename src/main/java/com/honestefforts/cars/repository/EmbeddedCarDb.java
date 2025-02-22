package com.honestefforts.cars.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import com.honestefforts.cars.model.TowedCar;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class EmbeddedCarDb {

  private final Map<String, List<TowedCar>> carsByPlate;

  public EmbeddedCarDb() throws IOException {
    File file = new File("src/main/resources/files/Towed_Vehicles.csv");

    CsvSchema schema = CsvSchema.emptySchema().withHeader();

    CsvMapper mapper = new CsvMapper();

    MappingIterator<TowedCar> it = mapper
        .readerFor(TowedCar.class)
        .with(schema)
        .readValues(file);

    carsByPlate = it.readAll()
        .stream()
        .collect(Collectors.groupingBy(TowedCar::plate));
  }

  public List<TowedCar> findByPlateAndState(String plate, String state) {
    return Optional.ofNullable(state)
        .filter(s -> !s.isEmpty())
        .map(_ -> carsByPlate.getOrDefault(plate, List.of()).stream()
            .filter(towedCar -> state.equals(towedCar.state()))
            .toList()
        )
        .orElse(carsByPlate.getOrDefault(plate, List.of()));
  }

  public List<TowedCar> findAll() {
    return carsByPlate.values().stream().flatMap(List::stream).toList();
  }

}

