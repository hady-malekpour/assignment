package com.edia.controller;

import com.edia.AssignmentApplication;
import com.edia.data.DocumentEntity;
import com.edia.data.jpa.DocumentJpaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URI;
import java.util.List;

/**
 * Created by hadi on 7/26/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AssignmentApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class DocumentControllerTest {

    @Autowired
    private DocumentJpaRepository jpaRepository;

    @Value("${local.server.port}")
    int port;

    private String urlPrefix;

    private RestTemplate template = new TestRestTemplate();

    private DocumentEntity documentEntity1;
    private DocumentEntity documentEntity2;

    @BeforeClass
    public static void clean() {
        new File("target/elastic").deleteOnExit();
    }

    @Before
    public void setup() {
        urlPrefix = "http://localhost:" + port + "/api/documents/";

        documentEntity1 = new DocumentEntity();
        documentEntity1.setTitle("TITLE" + +Math.random());
        documentEntity1.setText("TEXT" + +Math.random());

        documentEntity2 = new DocumentEntity();
        documentEntity2.setTitle("TITLE" + Math.random());
        documentEntity2.setText("TEXT" + +Math.random());

        jpaRepository.save(documentEntity1);
    }

    @Test
    public void get() throws Exception {
        DocumentEntity documentEntity = template.getForEntity(urlPrefix + documentEntity1.getId(), DocumentEntity.class).getBody();
        Assert.assertEquals(documentEntity1, documentEntity);
    }

    @Test
    public void search() throws Exception {
        ResponseEntity<List> responseEntity = template.getForEntity(urlPrefix + "?query=" + documentEntity1.getText(), List.class);
        Assert.assertEquals(0, responseEntity.getBody().size());
        DocumentEntity result = template.postForEntity(urlPrefix, documentEntity2, DocumentEntity.class).getBody();
        responseEntity = template.getForEntity(urlPrefix + "?query=" + result.getText(), List.class);
        Thread.sleep(2L);
        Assert.assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    public void insert() throws Exception {
        documentEntity2 = template.postForEntity(urlPrefix, documentEntity2, DocumentEntity.class).getBody();
        documentEntity2 = template.postForEntity(urlPrefix, documentEntity2, DocumentEntity.class).getBody();
        ResponseEntity<DocumentEntity> responseEntity1 = template.getForEntity(urlPrefix + documentEntity2.getId(), DocumentEntity.class);
        ResponseEntity<DocumentEntity> responseEntity2 = template.getForEntity(urlPrefix + documentEntity2.getId(), DocumentEntity.class);
        Assert.assertNotNull(responseEntity1.getBody());
        Assert.assertNotNull(responseEntity2.getBody());
    }

    @Test
    public void update() throws Exception {
        RequestEntity<DocumentEntity> httpEntity = new RequestEntity<DocumentEntity>(documentEntity1, HttpMethod.PUT, URI.create(urlPrefix + documentEntity1.getId()));
        ResponseEntity<DocumentEntity> responseEntity = template.exchange(httpEntity, DocumentEntity.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}