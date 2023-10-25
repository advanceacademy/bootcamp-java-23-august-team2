package com.aacademy.moonlight.custom_deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CustomHourDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String hourString = jsonParser.getValueAsString();
        try {
            return LocalTime.parse(hourString, DateTimeFormatter.ofPattern("HH"));
        } catch (Exception e) {
            // Handle parsing errors
            return null; // or throw an exception as needed
        }
    }
}
