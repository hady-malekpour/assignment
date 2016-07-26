package com.edia.service;

import com.edia.data.DocumentEntity;
import com.edia.data.elasticsearch.DocumentElasticRepository;
import com.edia.data.jms.DocumentIndexListener;
import com.edia.data.jpa.DocumentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.jms.core.JmsTemplate;
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

    @Autowired
    JmsTemplate jmsTemplate;

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
        DocumentEntity inserted = documentJpaRepository.save(entity);
        index(inserted);
        return inserted;
    }

    @Override
    public DocumentEntity update(DocumentEntity entity) {
        Assert.notNull(entity);
        DocumentEntity updated = documentJpaRepository.save(entity);
        index(updated);
        return updated;
    }

    private void index(DocumentEntity entity) {
        jmsTemplate.convertAndSend(DocumentIndexListener.QUEUE, entity);
    }
}
