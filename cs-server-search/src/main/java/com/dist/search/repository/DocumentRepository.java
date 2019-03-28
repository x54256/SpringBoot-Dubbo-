package com.dist.search.repository;

import com.dist.model.search.DocumentEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DocumentRepository extends ElasticsearchRepository<DocumentEntity, String> {

    List<DocumentEntity> findAllByReviewTaskIdAndContent(Long reviewTaskId, String keyword);

}