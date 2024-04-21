package com.aimaginarium.exception;

import static java.lang.String.format;

public class GalleryNotFoundException extends RuntimeException {
    public GalleryNotFoundException(final String message, final Object o) {
        super(format(message, o));
    }
}
