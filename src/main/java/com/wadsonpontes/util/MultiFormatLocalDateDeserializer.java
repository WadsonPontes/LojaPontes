package com.wadsonpontes.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MultiFormatLocalDateDeserializer extends JsonDeserializer<LocalDate> {

	private static final List<DateTimeFormatter> FORMATTERS = new ArrayList<>();

    static {
        // Adicionando vários formatos de data aceitos
        FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        FORMATTERS.add(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        FORMATTERS.add(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        FORMATTERS.add(DateTimeFormatter.ofPattern("MM/dd/yy"));
        FORMATTERS.add(DateTimeFormatter.ofPattern("MM-dd-yy"));
        FORMATTERS.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        FORMATTERS.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        FORMATTERS.add(DateTimeFormatter.ofPattern("dd/MM/yy"));
        FORMATTERS.add(DateTimeFormatter.ofPattern("dd-MM-yy"));
    }
    
    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText();

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // Tenta o próximo formato
            }
        }

        throw new RuntimeException("Failed to parse date: " + date);
    }
}
