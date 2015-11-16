package com.shifz.librarian;

import com.shifz.librarian.utils.FileMan;

import java.io.File;

public class Main {

    private static final String SUPER_PATH = ".";

    public static void main(String[] args) {
        
        if (args.length == 1) {
            String path = args[0];

            //Checking if it's a super path
            if (path.equals(SUPER_PATH)) {
                path = System.getProperty("user.dir");
            }

            final File rootDir = new File(path);
            if (rootDir.exists() && rootDir.isDirectory()) {

                final File[] files = rootDir.listFiles();

                if (files != null && files.length > 0) {
                    for (final File file : files) {

                        if (FileMan.isVideoFile(file) || FileMan.isSubtitleFile(file)) {
                            final String fileNameWithOutExt = FileMan.getFileNameWithOutExtension(file.getName());
                            final File dir = new File(String.format("%s/%s", file.getParent(), fileNameWithOutExt));
                            if (!dir.exists() || !dir.isDirectory()) {
                                if (dir.mkdir()) {
                                    System.out.println("*New directory : " + dir.getAbsolutePath());
                                } else {
                                    System.out.println("\t#Failed to make dir : " + dir.getAbsolutePath());
                                }
                            }
                            final String destFileAbsPath = String.format("%s/%s", dir.getAbsolutePath(), file.getName());
                            final File destFile = new File(destFileAbsPath);
                            final boolean isMoved = file.renameTo(destFile);
                            if (isMoved) {
                                System.out.println("*File moved: " + destFileAbsPath);
                            } else {
                                System.out.println("\t#Failed to move the file : " + file.getName());
                            }
                        }

                    }

                    System.out.println("*Finished!");
                } else {
                    System.out.println("\t#No files found!");
                }

            } else {
                //ERROR
                System.out.println(String.format("\t#%s not exist", rootDir.getAbsolutePath()));
            }

        } else {
            System.out.println("\t#Syntax error");
        }
    }
}
