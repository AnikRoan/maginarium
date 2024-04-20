package com.aimaginarium.exception;

public class PictureNotFoundException extends RuntimeException {
    public PictureNotFoundException(final String message) {
        super(message);
    }
}
