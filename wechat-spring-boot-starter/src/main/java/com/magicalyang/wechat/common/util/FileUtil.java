package com.magicalyang.wechat.common.util;

import java.io.*;

/**
 * @author Constanline
 */
public class FileUtil {
    public static File getFile(String dirName, String fileName) {
        File dir = new File(dirName);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                return null;
            }
        }
        File file = new File(dirName + "/" + fileName);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return null;
                }
            } catch (IOException ioException) {
                return null;
            }
        }
        return file;
    }

    private static void closeFile(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }

    }

    public static String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            char[] cArr = new char[1024];
            while (true) {
                int read = fileReader.read(cArr);
                if (read < 0) {
                    break;
                }
                sb.append(cArr, 0, read);
            }
        } catch (IOException ignored) {
        } finally {
            closeFile(fileReader);
        }
        return sb.toString();
    }

    public static boolean writeFile(File file, String msg) {
        return writeFile(file, msg, false);
    }

    public static boolean writeFile(File file, String msg, Boolean isAppend) {
        if (file == null) {
            return false;
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, isAppend);
            fileWriter.write(msg);
            fileWriter.flush();
            return true;
        } catch (IOException ioException) {
            return false;
        } finally {
            closeFile(fileWriter);
        }
    }
}
