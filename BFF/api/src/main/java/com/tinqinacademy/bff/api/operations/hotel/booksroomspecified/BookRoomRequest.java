package com.tinqinacademy.bff.api.operations.hotel.booksroomspecified;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.bff.api.base.OperationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookRoomRequest implements OperationRequest {


    @JsonIgnore
    @NotBlank
    @UUID
    private String roomId;

    @Schema(example = "2024-08-01")
    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @Schema(example = "2025-08-01")
    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @Schema(example = "Rostislav")
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2,max = 30, message = "First name cannot exceed 30 characters")
    private String firstName;

    @Schema(example = "Demirov")
    @NotBlank(message = "Last name cannot be blank")
    @Size(min =2,max = 30, message = "Last name cannot exceed 30 characters")
    private String lastName;

    @Schema(example = "+359898545312")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^\\+[1-9]{1}[0-9]{3,14}$",
            message = "Phone number must start with a '+' followed by the country code and subscriber number digits"
    )
    private String phoneNo;

    @JsonIgnore
    @NotBlank
    @UUID
    private String userId;
}
