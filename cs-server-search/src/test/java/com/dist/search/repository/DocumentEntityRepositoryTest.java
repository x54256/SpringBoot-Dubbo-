package com.dist.search.repository;

import com.dist.model.search.DocumentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yujx
 * @date 2019/03/20 11:03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DocumentEntityRepositoryTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void testCreate(){
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(DocumentEntity.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(DocumentEntity.class);
    }

    @Test
    public void func() {

        List<DocumentEntity> list = documentRepository.findAllByReviewTaskIdAndContent(123L, "滴滴");

        System.out.println(list);

    }

}