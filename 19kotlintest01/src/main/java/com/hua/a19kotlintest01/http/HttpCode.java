package com.hua.a19kotlintest01.http;

/**
 * @author Yu, Yang
 */
public class HttpCode {
    /**
     * Success.
     */
    public static final int SUCCESS = 200;
    /**
     * Internal service error.
     */
    public static final int INTERNAL_SERVICE_ERROR = 500;
    /**
     * Unauthorized login.
     */
    public static final int UNAUTHORIZED_LOGIN = 401;
    /**
     * No permission.
     */
    public static final int NO_PERMISSION = 1401;
    /**
     * Parameter error.
     */
    public static final int PARAMETER_ERROR = 3001;
    /**
     * Condition not satisfied.
     */
    public static final int CONDITION_NOT_SATISFIED = 3002;
    /**
     * Query user pay status.
     */
    public static final int QUERY_USER_PAY_STATUS = 6001;
}