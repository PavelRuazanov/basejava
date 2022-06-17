package com.pavelryazanov;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File dir = new File(".\\src\\com\\pavelryazanov");
        walk(dir.getCanonicalPath());

    }

    public static void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
    }

}
