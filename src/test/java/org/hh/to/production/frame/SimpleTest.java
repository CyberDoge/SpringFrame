package org.hh.to.production.frame;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class SimpleTest {
    @Test
    public void mkdir() throws IOException {
        String folderName = "image/" + UUID.randomUUID().toString();
        var path = Paths.get(folderName);
        Files.createDirectories(path);
        System.out.println(path.toAbsolutePath().toString());
    }
}