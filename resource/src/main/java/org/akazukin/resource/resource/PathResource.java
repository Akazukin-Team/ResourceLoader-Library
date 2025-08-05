package org.akazukin.resource.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.identifier.PathResourceIdentifier;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@Slf4j
public class PathResource implements IResource {
    PathResourceIdentifier identifier;

    public PathResource(final PathResourceIdentifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getType() {
        return this.identifier.getType();
    }

    @Override
    public InputStream getInputStream() throws ResourceFetchException {
        final Path path = Paths.get(this.identifier.getIdentifier());
        if (!path.toFile().exists()) {
            throw new ResourceFetchException(ResourceFetchException.Type.NOT_FOUND, this.getIdentifier());
        }

        try {
            return Files.newInputStream(path);
        } catch (final Throwable e) {
            log.error("Failed to get input stream for resource: {}", this.getIdentifier(), e);
            throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR, e, this.getIdentifier());
        }
    }

    @Override
    public OutputStream getOutputStream() throws ResourceFetchException {
        final Path path = Paths.get(this.identifier.getIdentifier());
        if (!path.toFile().exists()) {
            throw new ResourceFetchException(ResourceFetchException.Type.NOT_FOUND, this.getIdentifier());
        }

        try {
            return Files.newOutputStream(path);
        } catch (final Throwable e) {
            log.error("Failed to get output stream for resource: {}", this.getIdentifier(), e);
            throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR, e, this.getIdentifier());
        }
    }
}
