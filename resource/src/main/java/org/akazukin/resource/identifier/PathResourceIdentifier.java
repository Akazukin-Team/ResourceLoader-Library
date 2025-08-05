package org.akazukin.resource.identifier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.resource.IResource;
import org.akazukin.resource.resource.PathResource;

import java.nio.file.Path;
import java.nio.file.Paths;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class PathResourceIdentifier implements IResourceIdentifier {
    @Getter
    String identifier;

    public PathResourceIdentifier(final String path) {
        this.identifier = path;
    }

    @Override
    public String toString() {
        return "PathResourceIdentifier{"
                + "type='" + this.getType() + "', "
                + "identifier='" + this.identifier + "'"
                + '}';
    }

    @Override
    public String getType() {
        return "path";
    }

    @Override
    public IResource getResource() throws ResourceFetchException {
        try {
            final Path path = Paths.get(this.identifier);
            if (!path.toFile().exists()) {
                throw new ResourceFetchException(ResourceFetchException.Type.NOT_FOUND, this);
            }

            return new PathResource(this);
        } catch (final Throwable t) {
            if (t instanceof ResourceFetchException) {
                throw (ResourceFetchException) t;
            }
            throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR, t, this);
        }
    }


}
