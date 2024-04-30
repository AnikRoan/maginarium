package com.aimaginarium.exception;

import static java.lang.String.format;

public class PictureNotFoundException extends RuntimeException {
    public PictureNotFoundException(final String message, final Object o) {
        super(format(message, o));
    }
}
