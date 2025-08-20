package org.akazukin.resource;

import org.akazukin.resource.identifier.IResourceIdentifier;
import org.akazukin.resource.identifier.PathResourceIdentifier;
import org.akazukin.resource.resource.IResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.misc.IOUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class PathResourceIdentifierTest {
    private static final byte[] EMPTY_BYTES = new byte[0];

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
        try (final FileOutputStream fos = new FileOutputStream("pathTest.txt")) {
            fos.write(EMPTY_BYTES);
        }
        final IResourceIdentifier uri = new PathResourceIdentifier("pathTest.txt");
        try (final IResource res = uri.getResource();
             final OutputStream os = res.getOutputStream()) {
            final byte[] bytes2 = Constants.TEST_MSG.getBytes();
            os.write(bytes2);

            try (final InputStream is = res.getInputStream()) {
                Assertions.assertArrayEquals(bytes2, IOUtils.readAllBytes(is), "The resource was not fetched correctly.");
            }
        }
    }
}
