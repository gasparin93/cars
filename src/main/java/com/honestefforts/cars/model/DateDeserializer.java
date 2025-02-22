package com.honestefforts.cars.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateDeserializer extends StdDeserializer<LocalDate> {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

  public DateDeserializer() {
    super(LocalDate.class);
  }

  @Override
  public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    return LocalDate.parse(parser.getText(), FORMATTER);
  }
}