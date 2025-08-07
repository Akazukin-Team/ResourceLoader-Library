package org.akazukin.resource;

import org.akazukin.resource.identifier.IResourceIdentifier;
import org.akazukin.resource.identifier.PathResourceIdentifier;
import org.akazukin.resource.resource.IResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.misc.IOUtils;

public class PathResourceIdentifierTest {
    @Test
    public void testFetch() throws Exception {

        final IResourceIdentifier uri = new PathResourceIdentifier("pathTest.txt");
        try (final IResource res = uri.getResource()) {
            Assertions.assertArrayEquals(Constants.TEST_MSG.getBytes(), IOUtils.readAllBytes(res.getInputStream()), "The resource was not fetched correctly.");
        }
    }
}
