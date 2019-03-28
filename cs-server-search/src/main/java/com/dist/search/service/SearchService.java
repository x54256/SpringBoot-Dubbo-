package com.dist.search.service;

import com.dist.base.utils.dozer.IGenerator;
import com.dist.model.search.DocumentEntity;
import com.dist.model.search.dto.DocumentDTO;
import com.dist.search.repository.DocumentRepository;
import com.dist.search.utils.DocumentParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yujx
 * @date 2019/03/20 14:59
 */
@Service
public class SearchService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private IGenerator dozer;


    /**
     * 创建文件的索引
     *
     * @param filePath
     * @param reviewTaskId
     */
    public void createFileIndex(String filePath, Long reviewTaskId) {

        try {

            // 解析pdf文件的内容
            List<DocumentDTO> documentDTOS = DocumentParserUtils.parsePDF(filePath);

            List<DocumentEntity> documentEntities = dozer.convert(documentDTOS, DocumentEntity.class);

            for (DocumentEntity documentEntity : documentEntities) {
                documentEntity.setReviewTaskId(reviewTaskId);
            }

            // 保存
            documentRepository.saveAll(documentEntities);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
