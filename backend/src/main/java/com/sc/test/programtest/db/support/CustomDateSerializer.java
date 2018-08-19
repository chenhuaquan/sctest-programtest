package com.sc.test.programtest.db.support;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(final Date value, final JsonGenerator generator, final SerializerProvider serializeProvider)
            throws IOException, JsonProcessingException {
        generator.writeString(formatter.format(value));
    }

}
