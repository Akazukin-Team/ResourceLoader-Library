package org.akazukin.resource.resource;

import org.akazukin.resource.identifier.IResourceIdentifier;

import java.io.Closeable;
import java.io.InputStream;

public interface IResource extends Closeable {
    String getType();

    IResourceIdentifier getIdentifier();

    InputStream getInputStream();
}
