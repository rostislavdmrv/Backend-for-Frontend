package com.tinqinacademy.bff.rest.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.restexport.CommentsClient;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {

    private static final String COMMENTS_CLIENT_URL = "http://localhost:8086";
    private static final String HOTEL_CLIENT_URL = "http://localhost:8080";
    private final ObjectMapper objectMapper;

    @Bean
    public CommentsClient getCommentsClient() {
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(CommentsClient.class, COMMENTS_CLIENT_URL);
    }

    @Bean
    public HotelClient getHotelsClient() {
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(HotelClient.class, HOTEL_CLIENT_URL);
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

}
