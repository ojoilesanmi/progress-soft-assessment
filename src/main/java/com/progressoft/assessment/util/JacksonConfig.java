package com.progressoft.assessment.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        SimpleModule strictModule = new SimpleModule();
        strictModule.addDeserializer(String.class, new StrictStringDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(strictModule);

        return mapper;
    }

    public static class StrictStringDeserializer extends StringDeserializer {
        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonToken token = p.currentToken();
            if (token.isBoolean() || token.isNumeric() || !token.toString().equalsIgnoreCase("VALUE_STRING")) {
                ctxt.reportInputMismatch(String.class, "%s is not a `String` value!", token.toString());
                return null;
            }
            return super.deserialize(p, ctxt);
        }
    }

    public static class StrictBigDecimalDeserializer extends StdDeserializer<BigDecimal> {

        public StrictBigDecimalDeserializer() {
            super(BigDecimal.class);
        }

        @Override
        public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonToken token = p.currentToken();
            if (token.isBoolean() || token.isNumeric()) {
                String value = p.getValueAsString();
                ctxt.reportInputMismatch(BigDecimal.class, "%s is not a valid `BigDecimal` value!", value);
                return null;
            }
            return new BigDecimal(p.getValueAsString());
        }
    }
}

