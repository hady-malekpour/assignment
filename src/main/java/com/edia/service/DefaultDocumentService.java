package com.edia.service;

import com.edia.data.DocumentEntity;
import com.edia.data.elasticsearch.DocumentElasticRepository;
import com.edia.data.jpa.DocumentJpaRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private JpaContext jpaContext;

    @Override
    public DocumentEntity load(Long id) {
        DocumentEntity entity = documentJpaRepository.getOne(id);
        deatach(entity);
        return entity;
    }

    @Override
    public List<DocumentEntity> search(String text) {
        return null;
    }

    @Override
    public DocumentEntity insert(DocumentEntity entity) {
        return documentJpaRepository.save(entity);
    }

    @Override
    public DocumentEntity update(DocumentEntity entity) {
        return documentJpaRepository.save(entity);
    }

    private void deatach(DocumentEntity entity) {
        jpaContext.getEntityManagerByManagedType(DocumentEntity.class).detach(entity);
    }
}
