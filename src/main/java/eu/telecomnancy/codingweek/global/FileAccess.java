package eu.telecomnancy.codingweek.global;

import java.io.IOException;
import java.util.Objects;

public class FileAccess {

    private final String absoluteResourceFolderPath;

    public FileAccess() throws IOException {
        String myStr = Objects.requireNonNull(getClass().getResource("")).getPath();
        String newStr = myStr.replaceFirst("^file:/*", "/");
        newStr = newStr.replaceFirst("/[^/]*.jar.*", "/files/");
        this.absoluteResourceFolderPath = newStr;
    }

    public String getAbsoluteResourceFolderPath() {
        return absoluteResourceFolderPath;
    }

    public String getPathOf(String fileName) throws IOException {
        return this.absoluteResourceFolderPath + fileName;
    }

}
