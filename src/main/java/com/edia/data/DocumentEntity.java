package com.edia.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * Created by hadi on 7/25/2016.
 */
@Entity
@Document(indexName = "documents", type = "DocumentEntity", shards = 1, replicas = 0, refreshInterval = "-1")
public class DocumentEntity implements Serializable{
    private static final long serialVersionUID = 1473894793874893799L;

    @Id
    @javax.persistence.Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    public DocumentEntity() {
    }

    public DocumentEntity(String title, String text) {
        this.setTitle(title);
        this.setText(text);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("DocumentEntity[id=%s, title='%s', text='%s']", this.id,
                this.title, this.text);
    }
}
