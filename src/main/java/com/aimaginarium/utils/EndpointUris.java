package com.aimaginarium.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EndpointUris {
    public final String ROOT_USER = "/api/v1/users";
    public final String GET_USER = "/get/{id}";
    public final String GET_PROFILE = "/getProfile/{id}";
    public final String DELETE_USER = "/delete/{id}";
    public final String CHANGE_EMAIL = "/changeEmail/{id}";
    public final String CHANGE_NUMBER = "/changePhoneNumber/{id}";
    public final String CHANGE_USERNAME = "/changeUsername/{id}";
    public final String CHANGE_PASSWORD = "/changePassword/{id}";
}
