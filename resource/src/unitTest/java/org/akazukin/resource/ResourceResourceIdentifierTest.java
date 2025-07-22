package org.akazukin.resource;

import org.akazukin.resource.identifier.IResourceIdentifier;
import org.akazukin.resource.identifier.ResourceResourceIdentifier;
import org.akazukin.resource.resource.IResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.misc.IOUtils;

public class ResourceResourceIdentifierTest {
    @Test
    public void testFetch() throws Exception {
        final String result = "This is a test file.";

        final IResourceIdentifier uri = new ResourceResourceIdentifier("resourceTest.txt");
        try (final IResource res = uri.getInputStream()) {
            Assertions.assertArrayEquals(result.getBytes(), IOUtils.readAllBytes(res.getInputStream()), "The resource was not fetched correctly.");
        }
    }
}
