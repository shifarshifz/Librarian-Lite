package com.shifz.librarian.utils;

import java.io.File;

/**
 * Created by Shifar Shifz on 11/16/2015.
 */
public class FileMan {

    private static final String[] videoExts = "webm,mkv,flv,vob,ogv,ogg,drc,mng,avi,mov,qt,wmv,rm,rmvb,asf,mp4,m4p,m4v,mpg,mp2,mpeg,mpe,mpv,mpg,mpeg,m2v,m4v,svi,3gp,3g2,mxf,roq,nsv".split(",");
    private static final CharSequence DOT_SRT = ".srt";
    private static final CharSequence DOT_STRING = ".";
    private static final char DOT_CHAR = '.';

    public static boolean isVideoFile(final File file) {

        if (file.isFile()) {
            final String fileName = file.getName();
            final String fileExt = fileName.substring(fileName.lastIndexOf(DOT_CHAR) + 1).toLowerCase();
            for (final String okExt : videoExts) {
                if (okExt.equals(fileExt)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean isSubtitleFile(final File file) {
        if (file.isFile()) {
            final String fileName = file.getName();
            return fileName.contains(DOT_SRT) && fileName.substring(fileName.length() - DOT_SRT.length()).equals(DOT_SRT);
        }
        return false;
    }


    public static String getFileNameWithOutExtension(String name) {
        if (name.contains(DOT_STRING)) {
            return name.substring(0, name.lastIndexOf(DOT_CHAR));
        }
        return name;
    }
}
