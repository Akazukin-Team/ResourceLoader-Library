package org.akazukin.resource.identifier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.resource.IResource;
import org.akazukin.resource.resource.InputStreamResource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class PathResourceIdentifier implements IResourceIdentifier {
    @Getter
    String identifier;

    public PathResourceIdentifier(final String path) {
        this.identifier = path;
    }

    @Override
    public String getType() {
        return "path";
    }

    @Override
    public IResource getInputStream() throws ResourceFetchException {
        try {
            final InputStream is = Files.newInputStream(Paths.get(this.identifier));
            return new InputStreamResource(this, is) {
                @Override
                public String getType() {
                    return PathResourceIdentifier.this.getType();
                }
            };
        } catch (final Throwable t) {
            throw new ResourceFetchException(ResourceFetchException.RESOURCE_FETCH_ERROR, t, this);
        }
    }
}
