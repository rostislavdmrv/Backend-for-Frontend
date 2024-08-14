package com.tinqinacademy.bff.rest.config.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.authentication.restexport.AuthClient;
import com.tinqinacademy.comments.restexport.CommentsClient;
import com.tinqinacademy.myhotel.restexport.HotelClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {

    @Value("${comment.client.url}")
    private String COMMENTS_CLIENT_URL;

    @Value("${hotel.client.url}")
    private String HOTEL_CLIENT_URL;

    @Value("${auth.client.url}")
    private String AUTH_CLIENT_URL;


    private final ObjectMapper objectMapper;

    @Bean
    public CommentsClient getCommentsClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(CommentsClient.class, COMMENTS_CLIENT_URL);
    }

    @Bean
    public HotelClient getHotelsClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(HotelClient.class, HOTEL_CLIENT_URL);
    }

    @Bean
    public AuthClient getAuthsClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(AuthClient.class, AUTH_CLIENT_URL);
    }


}
