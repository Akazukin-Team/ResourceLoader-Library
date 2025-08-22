package org.akazukin.resource.identifier;

import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.exception.ResourceNotFoundException;
import org.akazukin.resource.resource.IResource;

/**
 * Represents a contract for identifying and retrieving a resource.
 * An implementation defines a specific method to resolve and fetch a resource based on a unique identifier.
 */
public interface IResourceIdentifier {
    /**
     * Retrieves the type of the resource associated with this identifier.
     * The type can provide context or classification for the resource being resolved and fetched.
     *
     * @return a {@link String} representing the type of the resource.
     * depending on the implementation of {@link IResourceIdentifier}.
     */
    String getType();

    /**
     * Retrieves the unique identifier for the resource.
     * The identifier provides a reference to locate and interact with the resource.
     *
     * @return a {@link String} representing the unique identifier associated with the resource.
     */
    String getIdentifier();

    /**
     * Converts the specified relative path into a new {@link IResourceIdentifier} instance.
     * The resulting identifier represents the resource relative to the current identifier.
     * The behavior and implementation of this method depend on the specific type of {@link IResourceIdentifier}.
     *
     * @param relativePath a {@link String} representing the relative path to the target resource.
     *                     It should be relative to the current resource identifier.
     * @return a new instance of {@link IResourceIdentifier} corresponding to the given relative path.
     */
    IResourceIdentifier toRelativeIdentifier(String relativePath);

    /**
     * Resolves and retrieves the associated resource.
     * The retrieval process may vary based on the specific implementation of the {@link IResourceIdentifier}.
     *
     * @return an instance of {@link IResource} representing the retrieved resource.
     * @throws ResourceNotFoundException if the resource cannot be resolved.
     * @throws ResourceFetchException    if the resource cannot be fetched, or if an error occurs during the retrieval process.
     */
    IResource getResource() throws ResourceNotFoundException, ResourceFetchException;

    @Override
    String toString();
}
