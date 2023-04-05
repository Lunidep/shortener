package project.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(Integer.toHexString(hashCode()), ".tmp");
            path.toFile().deleteOnExit();

            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {

        }
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {

        }
        return 0;
    }

    public void putEntry(project.strategy.Entry entry) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(entry);
        } catch (Exception e) {

        }
    }

    public project.strategy.Entry getEntry() {
        if (getFileSize() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
                return (project.strategy.Entry) ois.readObject();
            } catch (Exception e) {

            }
        }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {

        }
    }
}
