package com.dist.base.utils;

import java.io.*;

/**
 * java.io.file工具
 */
public abstract class FileUtil {

    //合法文件类型
    public static final String[] LEGAL_FILE_TYPE = new String[]{"jpg", "png", "jpeg", "pdf"};

    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    public static final String getFileSuffix(File file) {
        String fileName = file.getName();
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1)
                .toLowerCase();
        return suffixName;
    }

    public static String getFileSuffix(String filePath) {
        return getFileSuffix(new File(filePath));
    }

    public static String getFileName(String filePath) {
        String fileName = new File(filePath).getName();
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * 检查文件类型是否合法
     *
     * @param file
     * @return
     */
    public static final boolean isLegalFileType(File file) {
        boolean isLegal = false;
        if (null == file) {
            return isLegal;
        }
        String fileSuffix = getFileSuffix(file);
        for (String s : LEGAL_FILE_TYPE) {
            if (s.equalsIgnoreCase(fileSuffix)) {
                isLegal = true;
                break;
            }
        }
        return isLegal;
    }

    /**
     * 将二进制 转出 java.io.file
     *
     * @param srcFile
     * @param path
     */
    public static final boolean byte2File(byte[] srcFile, String path) {
        FileOutputStream out = null;
        String targetFolder = path.substring(0, path.lastIndexOf(File.separator));
        try {
            File folder = new File(targetFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            out = new FileOutputStream(path);
            out.write(srcFile);
            out.flush();
        } catch (Exception e) {
            return false;
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * file 转 byte[]
     *
     * @param filePath
     * @return
     */
    public static byte[] File2byte(String filePath) {
        byte[] buffer = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;

        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != bos) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }

    /**
     * InputStream 转 byte[]
     *
     * @param inStream
     * @return
     */
    private byte[] readStream(InputStream inStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outStream.toByteArray();
    }


    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static boolean copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newDir = new File(newPath.replace('\\', '/').substring(0,
                    newPath.replace('\\', '/').lastIndexOf("/")));
            if (!newDir.exists()) {
                newDir.mkdirs();
            }
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.flush();
                fs.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 根据文件路径检测文件的合法性
     *
     * @param filePath
     * @return
     */
    public static boolean checkFile(String filePath) {

        File file = new File(filePath);
        FileInputStream fis = null;
        try {
            if (file.exists() && file.isFile()) {
                fis = new FileInputStream(file);
                if (fis.available() > 0) {
                    return true;
                } else {
                    file.delete();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 修改文件的后缀
     *
     * @param fileName      文件名
     * @param replaceSuffix 要修改的后缀名，不带.
     * @return
     */
    public static String replaceFileSuffix(String fileName, String replaceSuffix) {
        if (fileName.contains(".")) {
            int i = fileName.lastIndexOf(".");
            String substring = fileName.substring(0, i);
            fileName = substring + "." + replaceSuffix;
        }
        return fileName;
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 创建目录
     *
     * @param filePath
     */
    public static void createDirs(String filePath) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

}
