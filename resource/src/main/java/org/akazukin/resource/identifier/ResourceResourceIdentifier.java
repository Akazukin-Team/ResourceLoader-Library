package org.akazukin.resource.identifier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.resource.IResource;
import org.akazukin.resource.resource.ResourceResource;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ResourceResourceIdentifier implements IResourceIdentifier {
    @Getter
    String identifier;
    @Getter
    ClassLoader classLoader;

    public ResourceResourceIdentifier(final String uri, final ClassLoader classLoader) {
        this.identifier = uri;
        this.classLoader = classLoader;
    }

    public ResourceResourceIdentifier(final String uri) {
        this.identifier = uri;
        this.classLoader = this.getClass().getClassLoader();
    }

    @Override
    public String toString() {
        return "ResourceResourceIdentifier{"
                + "type='" + this.getType() + "', "
                + "identifier='" + this.identifier + "',"
                + "classLoader=" + this.classLoader
                + '}';
    }

    @Override
    public String getType() {
        return "resource";
    }

    @Override
    public IResource getResource() throws ResourceFetchException {
        try {
            return new ResourceResource(this);
        } catch (final Throwable t) {
            throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR, t, this);
        }
    }
}
