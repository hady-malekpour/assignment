package com.edia.service;

import com.edia.data.DocumentEntity;
import com.edia.data.elasticsearch.DocumentElasticRepository;
import com.edia.data.jpa.DocumentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by hadi on 7/25/2016.
 */
@Service
@Transactional
public class DefaultDocumentService implements DocumentService {

    @Autowired
    DocumentJpaRepository documentJpaRepository;

    @Autowired
    DocumentElasticRepository documentElasticRepository;

    @Override
    public DocumentEntity load(Long id) {
        Assert.notNull(id);
        return documentJpaRepository.getOne(id);
    }

    @Override
    public List<DocumentEntity> search(String query) {
        if (StringUtils.hasText(query)) {
            return documentElasticRepository.findByText("*" + query + "*");
        }
        return documentJpaRepository.findAll();
    }

    @Override
    public DocumentEntity insert(DocumentEntity entity) {
        Assert.notNull(entity);
        Assert.isNull(entity.getId());
        DocumentEntity updated = documentJpaRepository.save(entity);
        index(updated);
        return updated;
    }

    @Override
    public DocumentEntity update(DocumentEntity entity) {
        Assert.notNull(entity);
        DocumentEntity inserted = documentJpaRepository.save(entity);
        index(inserted);
        return inserted;
    }

    private void index(DocumentEntity updated) {
        documentElasticRepository.index(updated);
    }
}
