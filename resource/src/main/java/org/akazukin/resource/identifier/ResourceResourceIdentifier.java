package org.akazukin.resource.identifier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.resource.IResource;
import org.akazukin.resource.resource.InputStreamResource;

import java.io.InputStream;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class ResourceResourceIdentifier implements IResourceIdentifier {
    @Getter
    String identifier;
    private final ClassLoader classLoader;

    public ResourceResourceIdentifier(final String uri, final ClassLoader classLoader) {
        this.identifier = uri;
        this.classLoader = classLoader;
    }

    public ResourceResourceIdentifier(final String uri) {
        this.identifier = uri;
        this.classLoader = this.getClass().getClassLoader();
    }

    @Override
    public String getType() {
        return "resource";
    }

    @Override
    public IResource getResource() throws ResourceFetchException {
        try {
            final InputStream is = this.classLoader.getResourceAsStream(this.identifier);
            if (is == null) {
                throw new ResourceFetchException(ResourceFetchException.Type.NOT_FOUND, this);
            }

            return new InputStreamResource(this, is) {
                @Override
                public String getType() {
                    return ResourceResourceIdentifier.this.getType();
                }
            };
        } catch (final Throwable t) {
            if (t instanceof ResourceFetchException) {
                throw (ResourceFetchException) t;
            }
            throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR, t, this);
        }
    }
}
