package org.akazukin.resource;

import org.akazukin.resource.identifier.IResourceIdentifier;
import org.akazukin.resource.identifier.UriResourceIdentifier;
import org.akazukin.resource.resource.IResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.misc.IOUtils;

import java.io.InputStream;

public class UriResourceIdentifierTest {
    @Test
    public void testFetch() throws Exception {
        final String result = "User-agent: *\r\n" +
                "Allow: /\r\n" +
                "Disallow: /public/";

        final IResourceIdentifier uri = new UriResourceIdentifier("https://examplefile.com/robots.txt", true);
        try (final IResource res = uri.getResource();
             final InputStream is = res.getInputStream()) {
            Assertions.assertArrayEquals(result.getBytes(), IOUtils.readAllBytes(is), "The resource was not fetched correctly.");
        }
    }
}
