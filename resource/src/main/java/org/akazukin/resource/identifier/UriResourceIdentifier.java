package org.akazukin.resource.identifier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.resource.IResource;
import org.akazukin.resource.resource.InputStreamResource;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UriResourceIdentifier implements IResourceIdentifier {
    @Getter
    String identifier;
    boolean ssl;

    public UriResourceIdentifier(final String uri, final boolean ssl) {
        this.identifier = uri;
        this.ssl = ssl;
    }

    @Override
    public String getType() {
        return "uri";
    }

    @Override
    public IResource getResource() throws ResourceFetchException {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL(this.identifier).openConnection();
            this.configureConnection(con);
            con.connect();

            final AtomicReference<HttpURLConnection> atomicCon = new AtomicReference<>(con);
            final InputStream is = con.getInputStream();
            return new InputStreamResource(this, is) {
                @Override
                public String getType() {
                    return UriResourceIdentifier.this.getType();
                }

                @Override
                public void close() {
                    synchronized (this) {
                        final HttpURLConnection con = atomicCon.getAndSet(null);
                        if (con != null) {
                            System.out.println(con.getContentEncoding());
                            con.disconnect();
                        }
                    }
                    super.close();
                }
            };
        } catch (final Throwable t) {
            if (con != null) {
                con.disconnect();
            }
            throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR, t, this);
        }
    }

    protected void configureConnection(final HttpURLConnection con) {
    }
}
