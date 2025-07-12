package org.akazukin.resource.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.akazukin.resource.identifier.IResourceIdentifier;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ResourceFetchException extends Exception {
    public static final String RESOURCE_NOT_FOUND = "The resource was not found.";
    public static final String RESOURCE_FETCH_ERROR = "Resource fetch error.";

    private static final long serialVersionUID = 6684038629235688861L;

    @Getter
    IResourceIdentifier identifier;

    public ResourceFetchException(final String message, final IResourceIdentifier identifier) {
        super(message);
        this.identifier = identifier;
    }

    public ResourceFetchException(final String message, final Throwable cause, final IResourceIdentifier identifier) {
        super(message, cause);
        this.identifier = identifier;
    }
}
