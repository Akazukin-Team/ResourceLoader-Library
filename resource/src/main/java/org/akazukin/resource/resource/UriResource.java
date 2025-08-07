package org.akazukin.resource.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.identifier.UriResourceIdentifier;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@Slf4j
public class UriResource implements IResource {
    UriResourceIdentifier identifier;
    HttpURLConnection connection;

    public UriResource(final UriResourceIdentifier identifier, final HttpURLConnection con) {
        this.identifier = identifier;
        this.connection = con;
    }

    @Override
    public String getType() {
        return this.identifier.getType();
    }

    @Override
    public InputStream getInputStream() throws ResourceFetchException {
        try {
            return this.connection.getInputStream();
        } catch (final Throwable e) {
            log.error("Failed to get input stream for resource: {}", this.getIdentifier(), e);
            throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR, e, this.getIdentifier());
        }
    }

    @Override
    public OutputStream getOutputStream() throws ResourceFetchException {
        try {
            return this.connection.getOutputStream();
        } catch (final Throwable e) {
            log.error("Failed to get output stream for resource: {}", this.getIdentifier(), e);
            throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR, e, this.getIdentifier());
        }
    }
}
