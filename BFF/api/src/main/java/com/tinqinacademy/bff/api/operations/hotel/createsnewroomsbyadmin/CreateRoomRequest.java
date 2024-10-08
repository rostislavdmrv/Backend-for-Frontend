package com.tinqinacademy.bff.api.operations.hotel.createsnewroomsbyadmin;

import com.tinqinacademy.bff.api.base.OperationRequest;
import com.tinqinacademy.bff.api.validations.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.bff.api.validations.bedsize.BedSizeValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoomRequest implements OperationRequest {

    @NotNull(message = "Bed sizes cannot be null")
    @BedSizeValidation
    private List<String> beds;

    @Schema(example = "private")
    @BathroomTypeValidation
    @Size(min = 5, max = 30, message = "Bathroom type cannot exceed 30 characters")
    private String bathroomType;


    @Schema(example = "1")
    @NotNull(message = "Floor cannot be blank")
    @Min(value = 0, message = "Floor must be 0 (ground floor) or a positive integer")
    private Integer floor;

    @Schema(example = "12A")
    @NotBlank(message = "Room number cannot be blank")
    @Size(min = 2, max = 10, message = "Room number cannot exceed 10 characters" )
    @Pattern(regexp = "[0-9]{1,10}[A-Z]?", message = "Valid room numbers start with numbers and could end with a letter")
    private String roomNumber;

    @Schema(example = "89.99")
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Price must be a numeric value with up to 10 integer digits and 2 fractional digits")
    private BigDecimal price;

}
