package org.akazukin.resource.identifier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.resource.IResource;
import org.akazukin.resource.resource.UriResource;

import javax.net.ssl.SSLSocketFactory;
import java.net.HttpURLConnection;
import java.net.URL;

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
    public String toString() {
        return "ResourceResourceIdentifier{"
                + "type='" + this.getType() + "', "
                + "identifier='" + this.identifier + "',"
                + "ssl=" + this.ssl
                + '}';
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

            if (this.ssl) {
                if (!(con instanceof javax.net.ssl.HttpsURLConnection)) {
                    throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR,
                            new IllegalArgumentException("SSL is enabled but the connection is not HTTPS"), this);
                } else {
                    ((javax.net.ssl.HttpsURLConnection) con).setSSLSocketFactory(this.createSocketFactory());
                }
            }

            con.setDoInput(true);
            con.setDoOutput(true);

            this.configureConnection(con);
            con.connect();

            return new UriResource(this, con);
        } catch (final Throwable t) {
            if (con != null) {
                con.disconnect();
            }
            throw new ResourceFetchException(ResourceFetchException.Type.FETCH_ERROR, t, this);
        }
    }

    protected void configureConnection(final HttpURLConnection con) {
    }

    protected SSLSocketFactory createSocketFactory() {
        return (SSLSocketFactory) SSLSocketFactory.getDefault();
    }
}
