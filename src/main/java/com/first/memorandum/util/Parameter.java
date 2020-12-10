package com.first.memorandum.util;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

public class Parameter {

    public static final String REDIS_SEPERATE = ":";

    public static final String VERIFY_CODE_COUNT = "VERIFY_CODE_COUNT";

    public static final String LOGIN_PREFIX = "LOGIN:";

    public static final String TOKEN_PREFIX = "TOKEN:";

    public static final String REQUEST_INFO_HEADER_TOKEN_NAME="token";

    public static final int LOGIN_TIMEOUT_NUM =1;

    public static final TimeUnit LOGIN_TIMEOUT_UNIT = TimeUnit.DAYS;
}
