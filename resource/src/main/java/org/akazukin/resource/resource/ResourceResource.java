package org.akazukin.resource.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.identifier.ResourceResourceIdentifier;

import java.io.InputStream;
import java.io.OutputStream;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@Slf4j
public class ResourceResource implements IResource {
    public static final String NOT_SUPPORT_OUTPUT_STREAMS = "Thr Resource implementation does not support output streams.";

    ResourceResourceIdentifier identifier;

    public ResourceResource(final ResourceResourceIdentifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getType() {
        return "input-stream";
    }

    @Override
    public InputStream getInputStream() throws ResourceFetchException {
        final InputStream is = this.identifier.getClassLoader().getResourceAsStream(this.getIdentifier().getIdentifier());
        if (is == null) {
            throw new ResourceFetchException(ResourceFetchException.Type.NOT_FOUND, this.identifier);
        }
        return is;
    }

    @Override
    public OutputStream getOutputStream() {
        throw new UnsupportedOperationException(NOT_SUPPORT_OUTPUT_STREAMS);
    }
}
