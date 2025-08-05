package org.akazukin.resource;

import org.akazukin.resource.identifier.IResourceIdentifier;
import org.akazukin.resource.identifier.UriResourceIdentifier;
import org.akazukin.resource.resource.IResource;
import org.junit.jupiter.api.Assertions;
import sun.misc.IOUtils;

public class UriResourceIdentifierTest {
    //@Test
    public void testFetch() throws Exception {
        final String result = "User-agent: *\r\n" +
                "Allow: /\r\n" +
                "Disallow: /public/";

        final IResourceIdentifier uri = new UriResourceIdentifier("https://examplefile.com/robots.txt", true);
        try (final IResource res = uri.getResource()) {
            Assertions.assertArrayEquals(result.getBytes(), IOUtils.readAllBytes(res.getInputStream()), "The resource was not fetched correctly.");
        }
    }
}
