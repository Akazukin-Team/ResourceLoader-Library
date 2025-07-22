package org.akazukin.resource.resource;

import org.akazukin.resource.identifier.IResourceIdentifier;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents a general contract for working with resources, including reading and writing data,
 * as well as identifying the resource by an associated {@link IResourceIdentifier}.
 * Implementations may vary depending on the type of resource being represented.
 * The caller is responsible for closing the instance to release any associated resources.
 */
public interface IResource extends Closeable {
    /**
     * Retrieves the type of resource represented by the current implementation.
     *
     * @return a {@link String} indicating the type of the resource.
     */
    String getType();

    /**
     * Retrieves the identifier associated with the resource.
     *
     * @return an instance of {@link IResourceIdentifier} representing the unique identifier of the resource.
     */
    IResourceIdentifier getIdentifier();

    /**
     * Provides an {@link InputStream} for reading data from the resource.
     *
     * @return an {@link InputStream} instance associated with the resource.
     * @throws UnsupportedOperationException if the resource implementation does not support input streams.
     * @throws IllegalStateException         if the input stream is already closed or not available.
     */
    InputStream getInputStream();

    /**
     * Provides an {@link OutputStream} for writing data to the resource.
     *
     * @return an {@link OutputStream} instance associated with the resource.
     * @throws UnsupportedOperationException if the resource implementation does not support output streams.
     * @throws IllegalStateException         if the output stream is already closed or not available.
     */
    OutputStream getOutputStream();
}
