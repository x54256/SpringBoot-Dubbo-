package com.dist.model.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 文档实体（每页一个实体）
 */
@Document(indexName = "document", type = "pdf", refreshInterval = "-1")
public class DocumentEntity {

    @Id
    private String id;

    // 一份文档使用同一个UUID
    @Field(type = FieldType.Keyword)
    private String fileId;

    // 审查任务id
    @Field(type = FieldType.Long)
    private Long reviewTaskId;

    // 页码数
    @Field(type = FieldType.Integer)
    private Integer pageIndex;

    // 每一页文档内容
    @Field(searchAnalyzer = "ik_max_word", analyzer = "ik_max_word", type = FieldType.Text)
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

    public Long getReviewTaskId() {
        return reviewTaskId;
    }

    public void setReviewTaskId(Long reviewTaskId) {
        this.reviewTaskId = reviewTaskId;
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