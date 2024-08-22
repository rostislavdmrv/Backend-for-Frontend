package com.tinqinacademy.bff.rest.controllers.hotel;

import com.tinqinacademy.bff.api.models.usertoken.CustomUser;
import com.tinqinacademy.bff.api.operations.hotel.booksroomspecified.BookRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.createsnewroomsbyadmin.CreateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.createsnewroomsbyadmin.CreateRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.deletesroomsbyadmin.DeleteRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.deletesroomsbyadmin.DeleteRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.isroomavailable.IsRoomFreeOperation;
import com.tinqinacademy.bff.api.operations.hotel.isroomavailable.RoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.registersvisitors.RegisterVisitorOperation;
import com.tinqinacademy.bff.api.operations.hotel.registersvisitors.RegisterVisitorRequest;
import com.tinqinacademy.bff.api.operations.hotel.removesroomreservation.UnbookRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.removesroomreservation.UnbookRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.retrivesreports.ReportOperation;
import com.tinqinacademy.bff.api.operations.hotel.retrivesreports.ReportRequest;
import com.tinqinacademy.bff.api.operations.hotel.returnsbasicinfoforroom.BasicInfoRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.returnsbasicinfoforroom.BasicInfoRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.updatescertainroomsbyadmin.UpdateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.updatescertainroomsbyadmin.UpdateRoomRequest;
import com.tinqinacademy.bff.api.operations.hotel.updatespartialroomsbyadmin.PartialUpdateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.updatespartialroomsbyadmin.PartialUpdateRoomRequest;
import com.tinqinacademy.bff.rest.controllers.base.BaseController;
import com.tinqinacademy.bff.api.operations.hotel.booksroomspecified.BookRoomRequest;

import com.tinqinacademy.bff.api.restapiroutes.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Tag(name = "MyHotel Client - REST APIs")
public class HotelController  extends BaseController {


    private final BasicInfoRoomOperation basicInfoRoomOperation;
    private final BookRoomOperation bookRoomOperation;
    private final IsRoomFreeOperation isRoomFreeOperation;
    private final RegisterVisitorOperation registerVisitorOperation;
    private final ReportOperation reportOperation;
    private final UnbookRoomOperation unbookRoomOperation;
    private final CreateRoomOperation createRoomOperation;
    private final DeleteRoomOperation deleteRoomOperation;
    private final PartialUpdateRoomOperation partialUpdateRoomOperation;
    private final UpdateRoomOperation updateRoomOperation;


    @Operation(summary = "Checks for available room for certain period",
            description = "Checks the availability of a room based on the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room availability retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to check for available this  room"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @GetMapping(RestApiRoutes.CHECK_AVAILABLE_ROOMS)
    public ResponseEntity<?> isRoomAvailable(
            @RequestParam(required = false) LocalDate start,
            @RequestParam(required = false) LocalDate end,
            @RequestParam(required = false) Integer bedCount,
            @RequestParam(required = false) String bathroomType,
            @RequestParam(required = false) String bedSize) {


        RoomRequest input = RoomRequest.builder()
                .startDate(start)
                .endDate(end)
                .bedCount(bedCount)
                .bedSize(bedSize)
                .bathroomType(bathroomType)
                .build();

        return handle(isRoomFreeOperation.process(input));

    }

    @Operation(summary = "Retrieves basic information for the specified room by specific ID",
            description = "Retrieves basic information for the specified room on the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved information successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to view this room's information"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @GetMapping(RestApiRoutes.RETRIEVE_BASIC_INFO)
    public ResponseEntity<?> infoForRoom(@PathVariable String roomId) {

        BasicInfoRoomRequest input = BasicInfoRoomRequest.builder()
                .roomId(roomId)
                .build();

        return handle(basicInfoRoomOperation.process(input));

    }


    @Operation(summary = "Books a new room with the provided details",
            description = "Reserves a specified room for a user within the provided information, ensuring the room is available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully booked a room"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to book this room"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @PostMapping(RestApiRoutes.BOOK_ROOM)
    @PreAuthorize("hasRole('USER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> bookRoom(@PathVariable String roomId, @RequestBody BookRoomRequest input) {

        CustomUser user = getUserFromContext();

        BookRoomRequest updatedInput = input.toBuilder()
                .roomId(roomId)
                .userId(user.getUserId().toString())
                .build();

        return handleWithStatus(bookRoomOperation.process(updatedInput), HttpStatus.CREATED);

    }

    @Operation(summary = "Remove room reservation by specific ID",
            description = "Cancels a reservation for a specified room and date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation removed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to cancel this reservation"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @DeleteMapping(RestApiRoutes.DELETE_RESERVATION)
    @PreAuthorize("hasRole('USER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> removeBookedRoom(@PathVariable String bookingId) {

        CustomUser user = getUserFromContext();

        UnbookRoomRequest unbookRoomInput = UnbookRoomRequest.builder()
                .bookingId(bookingId)
                .userId(user.getUserId().toString())
                .build();

        return handleWithStatus(unbookRoomOperation.process(unbookRoomInput),HttpStatus.OK);

    }
    @Operation(summary = "Registers a new renter ",
            description = "Registers a new renter in the system with specific details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered renter"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to register a new renter")

    })
    @PostMapping(RestApiRoutes.REGISTER_NEW_GUEST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerVisitorAsRenter(@RequestBody RegisterVisitorRequest input) {

        return handleWithStatus(registerVisitorOperation.process(input),HttpStatus.CREATED);

    }


    @Operation(summary = "Creates a report based on specified criteria",
            description = "Creates a detailed report by evaluating various criteria related to room occupancies and visitor information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to generate the report")

    })
    @GetMapping(RestApiRoutes.REPORT_VISITORS)
    public ResponseEntity<?> getVisitorReports(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String phoneNo,
            @RequestParam(required = false) String idCardNo,
            @RequestParam(required = false) LocalDate idCardValidity,
            @RequestParam(required = false) String idCardIssueAuthority,
            @RequestParam(required = false) LocalDate idCardIssueDate,
            @RequestParam(required = false) String roomNo) {

        ReportRequest input = ReportRequest.builder()
                .startDate(startDate)
                .endDate(endDate)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNo(phoneNo)
                .idCardNo(idCardNo)
                .idCardValidity(idCardValidity)
                .idCardIssueAuthority(idCardIssueAuthority)
                .idCardIssueDate(idCardIssueDate)
                .roomNo(roomNo)
                .build();

        return handle(reportOperation.process(input));

    }

    @Operation(summary = "Creates a new room with the provided details",
            description = "Creates a room with specific parameters that will later serve as a template to save a room from a user ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully create a room"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to create this room")
    })
    @PostMapping(RestApiRoutes.CREATE_ROOM)
    public ResponseEntity<?> createNewRoomInSystem(@RequestBody CreateRoomRequest input) {

        return handle(createRoomOperation.process(input));
    }

    @Operation(summary = "Updates a room with the provided details",
            description = "Updates the specified room with the provided information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the room"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to update this room"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })

    @PutMapping(RestApiRoutes.UPDATE_ROOM)
    public ResponseEntity<?> updateAlreadyCreatedRoomInSystem(@PathVariable("roomId") String roomId, @RequestBody UpdateRoomRequest input) {

        UpdateRoomRequest updated = input.toBuilder().roomId(roomId).build();
        return handle(updateRoomOperation.process(updated));

    }

    @Operation(summary = "Partially updates a room with the provided details", description = "Updates specified fields of the room with the provided information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully partially updated the room"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to update this room"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })

    @PatchMapping(value=RestApiRoutes.PART_UPDATE_ROOM,consumes = "application/json-patch+json")
    public ResponseEntity<?> updateRoomPartially(@PathVariable("roomId") String roomId,@RequestBody PartialUpdateRoomRequest input) {

        PartialUpdateRoomRequest updated = input.toBuilder().roomId(roomId).build();
        return handle(partialUpdateRoomOperation.process(updated));
    }

    @Operation(summary = "Deletes room by specific ID",
            description = "Deletes a reservation for a specified room and date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Forbidden: You don't have permission to delete this reservation"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @DeleteMapping(RestApiRoutes.REMOVE_ROOM)
    public ResponseEntity<?> deleteRooms(@PathVariable String roomId) {

        DeleteRoomRequest roomForDelete = DeleteRoomRequest.builder()
                .roomId(roomId)
                .build();

        return handleWithStatus(deleteRoomOperation.process(roomForDelete), HttpStatus.OK);

    }
}
