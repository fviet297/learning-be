package com.learningapp.constants;

public final class CoreConstants {

    public interface RESPONSE_STATUS {
        String SUCCESS = "Success";
        String ERROR = "Error";
    }

    public interface MESSAGE_ERROR {
        String NOT_FOUND_ENTITY = "%s with ID %s not found or has been deleted";
        String NO_DATA = "The list of %s has no data";
    }

    public interface MESSAGE_AUTH {
        String NOT_FOUND = "User not found";
        String USER_EXISTS = "Username already exists";
        String INVALID_CREDENTIALS = "Invalid credentials";
    }

    public interface QUERY {
        String PAGE = "page";
        String SIZE = "size";
        String SORT = "sort";
        String PAGE_DEFAULT = "0";
        String SIZE_DEFAULT = "10";
        String UPDATE_ORDER = "updatedAt";
    }
}
