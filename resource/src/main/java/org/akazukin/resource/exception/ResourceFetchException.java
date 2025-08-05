package org.akazukin.resource.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
public final class ResourceFetchException extends Exception {
    private static final long serialVersionUID = 6684038629235688861L;

    @Getter
    IResourceIdentifier identifier;
    @Getter
    Type type;

    public ResourceFetchException(@NotNull final Type type, @Nullable final Throwable cause, @NotNull final IResourceIdentifier identifier) {
        this(type.message, cause, type, identifier);
    }

    private ResourceFetchException(@Nullable final String message, @Nullable final Throwable cause, @NotNull final Type type, @NotNull final IResourceIdentifier identifier) {
        super(message, cause);
        this.type = type;
        this.identifier = identifier;
    }

    public ResourceFetchException(@Nullable final String message, @NotNull final Type type, @Nullable final Throwable cause, @NotNull final IResourceIdentifier identifier) {
        this(message, cause, type, identifier);
    }

    public ResourceFetchException(@NotNull final Type type, @NotNull final IResourceIdentifier identifier) {
        this(type.message, null, type, identifier);
    }

    public ResourceFetchException(@Nullable final String message, @NotNull final Type type, @NotNull final IResourceIdentifier identifier) {
        this(message, null, type, identifier);
    }

    /**
     * Defines constants representing error types that can occur when fetching a resource.
     * Each constant also holds a predefined error message.
     * Commonly used in {@link ResourceFetchException} to specify the type of error.
     */
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum Type {
        NOT_FOUND("The requested resource was not found."),
        FETCH_ERROR("An error occurred while fetching the resource."),
        UNKNOWN("An unknown error occurred while fetching the resource.");

        String message;
    }
}
