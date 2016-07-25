package com.edia.controller;

import com.edia.data.DocumentEntity;
import com.edia.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hadi on 7/25/2016.
 */
@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public DocumentEntity get(@PathVariable(value = "id") Long id) {
        DocumentEntity d = this.documentService.load(id);
        return d;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DocumentEntity> search(@RequestParam(value = "search") String text) {
        return this.documentService.search(text);
    }

    @RequestMapping(method = RequestMethod.POST)
    public DocumentEntity insert(@RequestBody DocumentEntity entity) {
        return this.documentService.insert(entity);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public DocumentEntity update(@RequestBody DocumentEntity entity) {
        return this.documentService.update(entity);
    }
}
