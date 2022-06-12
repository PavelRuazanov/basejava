package com.pavelryazanov;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File file = new File(".\\src\\com\\pavelryazanov");
        System.out.println(file.getCanonicalPath());

        for (File name : file.listFiles()){
            System.out.println(name.getAbsolutePath());
        }
    }
}
