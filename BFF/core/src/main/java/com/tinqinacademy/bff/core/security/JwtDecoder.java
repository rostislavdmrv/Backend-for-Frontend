package com.tinqinacademy.bff.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtDecoder {
    private final ObjectMapper objectMapper;

    public Map<String, Object> getPayloadFromJwt(String authorizationHeader) throws IOException {
        String token = authorizationHeader.substring(7);

        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        return objectMapper.readValue(payload, Map.class);
    }
}
