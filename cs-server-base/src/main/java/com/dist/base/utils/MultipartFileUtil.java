package com.dist.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * springmvc MultipartFile类型文件工具
 *
 *  @author yinxp@dist.com.cn
 */
@Slf4j
public abstract class MultipartFileUtil {

    //合法文件类型
    private static final String[] LEGAL_FILE_TYPE = new String[]{"zip"};

    //允许的文件最大字节
    private static final long LEGAL_MAX_FILE_SIZE = 3 * 1024 * 1024;

    /**
     * 获取文件后缀
     * @param file
     * @return
     */
    public static final String getFileSuffix(MultipartFile file) {
        String filename =file.getOriginalFilename();
        String suffix = filename.substring(filename.indexOf(".") + 1);
        return suffix;

    }

    /**
     * 获取文件类型
     * @param file
     * @return
     */
    public static final String getContentType(MultipartFile file) {
        String contentType = file.getContentType();
        String type = contentType.substring(contentType.indexOf("/") + 1);
        return type;

    }

    /**
     * 计算文件大小
     * @param file
     * @return
     */
    public static final long getFileSize(MultipartFile file) {
        if (null == file) {
            return 0;
        }
        if (file.isEmpty()) {
            return 0;
        }
        return file.getSize();
    }

    /**
     * 检查文件类型是否合法
     * @param file
     * @return
     */
    public static final boolean isLegalFileType(MultipartFile file) {
        boolean isLegal = false;
        if (null == file) {
            return isLegal;
        }
        String fileType = getContentType(file);
        for (String s : LEGAL_FILE_TYPE) {
            if (s.equalsIgnoreCase(fileType)) {
                isLegal = true;
                break;
            }
        }
        return isLegal;
    }

    /**
     * 检查文件大小是否合法
     * @param file
     * @return
     */
    public static final boolean isLegalFileSize(MultipartFile file) {
        boolean isLegal = false;
        if (null == file) {
            return isLegal;
        }
        long fileSize = getFileSize(file);
        if (fileSize > 0 && fileSize < LEGAL_MAX_FILE_SIZE) {
            isLegal = true;
        }
        return isLegal;
    }
}
