package com.aimaginarium.exception;

import static java.lang.String.format;

public class GalleryExistsException extends RuntimeException {
    public GalleryExistsException(final String message, final Object o) {
        super(format(message, o));
    }
}
