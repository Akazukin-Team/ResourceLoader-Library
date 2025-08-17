package org.akazukin.resource.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.exception.ResourceNotFoundException;
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
    public InputStream getInputStream() throws ResourceNotFoundException, ResourceFetchException {
        final Path path = Paths.get(this.identifier.getIdentifier());
        if (!path.toFile().exists()) {
            throw new ResourceNotFoundException(this.getIdentifier());
        }

        try {
            return Files.newInputStream(path);
        } catch (final Throwable t) {
            log.error("Failed to get input stream for resource: {}", this.getIdentifier(), t);
            throw new ResourceFetchException(this.getIdentifier(), t);
        }
    }

    @Override
    public OutputStream getOutputStream() throws ResourceNotFoundException, ResourceFetchException {
        final Path path = Paths.get(this.identifier.getIdentifier());
        if (!path.toFile().exists()) {
            throw new ResourceNotFoundException(this.getIdentifier());
        }

        try {
            return Files.newOutputStream(path);
        } catch (final Throwable t) {
            log.error("Failed to get output stream for resource: {}", this.getIdentifier(), t);
            throw new ResourceFetchException(this.getIdentifier(), t);
        }
    }
}
