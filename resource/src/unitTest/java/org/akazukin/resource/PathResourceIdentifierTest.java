package org.akazukin.resource;

import org.akazukin.resource.identifier.IResourceIdentifier;
import org.akazukin.resource.identifier.PathResourceIdentifier;
import org.akazukin.resource.identifier.UriResourceIdentifier;
import org.akazukin.resource.resource.IResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.misc.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;

public class PathResourceIdentifierTest {
    @Test
    public void testFetch() throws Exception {

        final IResourceIdentifier uri = new PathResourceIdentifier("pathTest.txt");
        try (final IResource res = uri.getResource();
             final InputStream is = res.getInputStream()) {
            Assertions.assertArrayEquals(Constants.TEST_MSG.getBytes(), IOUtils.readAllBytes(is), "The resource was not fetched correctly.");
        }
    }

    @Test
    public void testWrite() throws Exception {
        final String result = "User-agent: *\r\n" +
                "Allow: /\r\n" +
                "Disallow: /public/";

        final IResourceIdentifier uri = new UriResourceIdentifier("https://examplefile.com/robots.txt", true);
        try (final IResource res = uri.getResource();
             final OutputStream os = res.getOutputStream()) {
            try {
                os.write(result.getBytes());
                os.flush();

                try (final InputStream is = res.getInputStream()) {
                    Assertions.assertArrayEquals(result.getBytes(), IOUtils.readAllBytes(is), "The resource was not fetched correctly.");
                }
            } finally {
                os.flush();
                os.write(result.getBytes());
                os.flush();
            }
        }
    }
}
