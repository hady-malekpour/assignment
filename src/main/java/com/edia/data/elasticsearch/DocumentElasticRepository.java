package com.edia.data.elasticsearch;

import com.edia.data.DocumentEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by hadi on 7/25/2016.
 */
public interface DocumentElasticRepository extends ElasticsearchRepository<DocumentEntity, Long> {
    List<DocumentEntity> findByText(String text);
}
