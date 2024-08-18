package com.tinqinacademy.bff.api.restapiroutes;

public class RestApiRoutes {
    private final static String ROOT = "/api/v1";
    public final static String HOTEL = ROOT + "/hotel";
    public final static String SYSTEM = ROOT + "/system";
    public static final String AUTH = ROOT + "/auth";

    public static final String ROOM_ID = "/{roomId}";
    public static final String ROOM = "/room";
    public static final String REGISTER = "/register";
    public static final String BOOKING_ID = "/{bookingId}";
    public final static String COMMENT = "/comment";
    public final static String COMMENT_ID = "/{commentId}";


    public final static String RETRIEVE_ALL_COMMENTS = HOTEL + ROOM_ID + COMMENT;
    public final static String LEAVE_COMMENT = HOTEL + ROOM_ID + COMMENT;
    public final static String UPDATE_COMMENT_CONTENT = HOTEL + COMMENT_ID+ COMMENT;
    public static final String UPDATE_COMMENT = SYSTEM + COMMENT + COMMENT_ID;
    public static final String DELETE_COMMENT = SYSTEM + COMMENT + COMMENT_ID;

    public final static String CHECK_AVAILABLE_ROOMS = HOTEL + "/rooms";
    public final static String RETRIEVE_BASIC_INFO = HOTEL + ROOM_ID;
    public final static String BOOK_ROOM = HOTEL + ROOM_ID;
    public final static String DELETE_RESERVATION = HOTEL + BOOKING_ID;
    public final static String REGISTER_NEW_GUEST = SYSTEM + REGISTER;
    public final static String REPORT_VISITORS = SYSTEM + REGISTER;
    public final static String CREATE_ROOM = SYSTEM + ROOM;
    public final static String UPDATE_ROOM = CREATE_ROOM + ROOM_ID;
    public final static String REMOVE_ROOM = CREATE_ROOM + ROOM_ID;
    public final static String PART_UPDATE_ROOM =   CREATE_ROOM + ROOM_ID;

    public static final String LOGIN = AUTH + "/login";
    public static final String REGISTER_NEW_USER = AUTH + REGISTER;
    public static final String RECOVER_PASSWORD = AUTH + "/recover-password";
    public static final String CONFIRM_REGISTRATION = AUTH + "/confirm-registration";
    public static final String CHANGE_PASSWORD = AUTH + "/change-password";
    public static final String CHANGE_PASSWORD_USING_RECOVERY_CODE = AUTH + "/change-password/recover-code";
    public static final String VALIDATE_TOKEN = AUTH + "/validate-token";
    public static final String PROMOTE = AUTH + "/promote";
    public static final String DEMOTE = AUTH + "/demote";
    public static final String LOGOUT = AUTH + "/logout";
}
