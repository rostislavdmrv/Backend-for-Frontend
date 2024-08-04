package com.tinqinacademy.bff.api.models.errors;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorsResponse {
    private String field;
    private String message;
}
