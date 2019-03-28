package com.dist.model.search.dto;

/**
 * @author yujx
 * @date 2019/03/20 15:39
 */
public class DocumentDTO {

    private String id;

    // 一份文档使用同一个UUID
    private String fileId;

    // 页码数
    private Integer pageIndex;

    // 每一页文档内容
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
