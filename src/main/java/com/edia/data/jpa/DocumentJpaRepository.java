package com.edia.data.jpa;

import com.edia.data.DocumentEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by hadi on 7/25/2016.
 */
public interface DocumentJpaRepository extends JpaRepository<DocumentEntity, Long> {
}
