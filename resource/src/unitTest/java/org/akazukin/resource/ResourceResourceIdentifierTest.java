package org.akazukin.resource;

import org.akazukin.resource.identifier.IResourceIdentifier;
import org.akazukin.resource.identifier.ResourceResourceIdentifier;
import org.akazukin.resource.resource.IResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.misc.IOUtils;

import java.io.InputStream;

public class ResourceResourceIdentifierTest {
    @Test
    public void testFetch() throws Exception {
        final IResourceIdentifier uri = new ResourceResourceIdentifier("resourceTest.txt");
        try (final IResource res = uri.getResource();
             final InputStream is = res.getInputStream()) {
            Assertions.assertArrayEquals(Constants.TEST_MSG.getBytes(), IOUtils.readAllBytes(is), "The resource was not fetched correctly.");
        }
    }
}
