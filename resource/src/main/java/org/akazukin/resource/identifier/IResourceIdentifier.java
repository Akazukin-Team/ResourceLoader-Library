package org.akazukin.resource.identifier;

import org.akazukin.resource.exception.ResourceFetchException;
import org.akazukin.resource.resource.IResource;

public interface IResourceIdentifier {
    String getType();

    String getIdentifier();

    IResource getResource() throws ResourceFetchException;
}
