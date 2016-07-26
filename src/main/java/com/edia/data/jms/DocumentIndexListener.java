package com.edia.data.jms;

import com.edia.data.DocumentEntity;
import com.edia.data.elasticsearch.DocumentElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by hadi on 7/26/2016.
 */
@Component
public class DocumentIndexListener {

    public static final String QUEUE = "index-queue";

    @Autowired
    DocumentElasticRepository documentElasticRepository;

    @JmsListener(destination = QUEUE, containerFactory = "indexJmsContainerFactory")
    public void receiveMessage(DocumentEntity documentEntity) {
        documentElasticRepository.index(documentEntity);
    }
}
