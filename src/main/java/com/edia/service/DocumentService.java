package com.edia.service;

import com.edia.data.DocumentEntity;

import java.util.List;

/**
 * Created by hadi on 7/25/2016.
 */
public interface DocumentService {
    DocumentEntity load(Long id);

    List<DocumentEntity> search(String text);

    DocumentEntity insert(DocumentEntity entity);

    DocumentEntity update(DocumentEntity entity);
}
