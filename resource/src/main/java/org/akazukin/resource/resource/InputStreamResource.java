package org.akazukin.resource.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.akazukin.resource.identifier.IResourceIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Slf4j
public class InputStreamResource implements IResource {
    final IResourceIdentifier identifier;
    InputStream inputStream;

    public InputStreamResource(@Nullable final IResourceIdentifier identifier, @NotNull final InputStream inputStream) {
        this.identifier = identifier;
        this.inputStream = inputStream;
    }

    @Override
    public String getType() {
        return "input-stream";
    }

    @Override
    public void close() {
        synchronized (this) {
            if (this.inputStream != null) {
                try {
                    this.inputStream.close();
                } catch (final IOException e) {
                    log.error(e.getMessage(), e);
                }
                this.inputStream = null;
            }
        }
    }
}
