package com.example.AuthorService.utils;

/**
 * A class that holds constant values used throughout the application.
 */
public class Constants {

    /**
     * The default page number used in pagination.
     */
    public static final String DEFAULT_PAGE_NUMBER = "0";

    /**
     * The default page size used in pagination.
     */
    public static final String DEFAULT_PAGE_SIZE = "10";

    /**
     * The date format pattern used for date parsing and formatting.
     */
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    /**
     * Logging message for data access exceptions.
     */
    public static final String LOG_DATA_ACCESS_EXCEPTION = "Data access exception occurred: {}";

    /**
     * Logging message for general exceptions.
     */
    public static final String LOG_EXCEPTION = "Exception occurred: {}";

    /**
     * Logging message for resource not found exceptions.
     */
    public static final String LOG_RESOURCE_NOT_FOUND_EXCEPTION="Resource not found occurred: {}";

      /**
     * Basename for the resource bundle message source.
     */
    public static final String MESSAGE_SOURCE_BASENAME = "i18n.messages";

    /**
     * Default encoding for the resource bundle message source.
     */
    public static final String MESSAGE_SOURCE_DEFAULT_ENCODING = "UTF-8";

        /**
     * The base package to scan for API controllers.
     */
    public static final String BASE_PACKAGE = "com.example.controllers";

    /**
     * The regex pattern to match API paths.
     */
    public static final String PATH_REGEX = "/.*";

    /**
     * The title for the API documentation.
     */
    public static final String API_TITLE = "Author Service API";

    /**
     * The description for the API documentation.
     */
    public static final String API_DESCRIPTION = "API documentation for the Author Service application.";

    /**
     * The version of the API.
     */
    public static final String API_VERSION = "1.0.0";

}
