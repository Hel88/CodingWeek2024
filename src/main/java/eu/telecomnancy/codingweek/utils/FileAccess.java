package eu.telecomnancy.codingweek.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

public class FileAccess {

    private String absoluteResourceFolderPath;

    public FileAccess() throws IOException {
        String myStr = getClass().getResource("").getPath().toString();
        String newStr = myStr.replaceFirst("^file:/*", "/");
        newStr = newStr.replaceFirst("/[^/]*.jar.*","/files/");
        this.absoluteResourceFolderPath = newStr;

        try {
            String path = this.absoluteResourceFolderPath + "users.json";
            File file = new File(path);
            String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("There is no 'files' folder in the project directory");
            e.printStackTrace();
        }
    }

    public String getAbsoluteResourceFolderPath() {
        return absoluteResourceFolderPath;
    }

    public String getPathOf(String fileName) throws IOException {
        return this.absoluteResourceFolderPath + fileName;
    }

}
