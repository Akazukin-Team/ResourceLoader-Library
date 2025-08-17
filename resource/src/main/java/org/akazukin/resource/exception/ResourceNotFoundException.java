package org.akazukin.resource.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.akazukin.resource.identifier.IResourceIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an exception that occurs when a resource cannot be fetched.
 * It provides details about the error type and the resource identifier related to the failure.
 * This exception is often used in conjunction with implementations of the {@link IResourceIdentifier} interface.
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ResourceNotFoundException extends Exception {
    public static final String MESSAGE = "The requested resource was not found; Identifier:";
    private static final long serialVersionUID = 6684038629235688862L;

    @Getter
    IResourceIdentifier identifier;

    public ResourceNotFoundException(@NotNull final IResourceIdentifier identifier, @Nullable final Throwable cause) {
        super(MESSAGE + identifier, cause);
        this.identifier = identifier;
    }

    public ResourceNotFoundException(@NotNull final IResourceIdentifier identifier) {
        super(MESSAGE + identifier);
        this.identifier = identifier;
    }
}
