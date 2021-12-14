package ar.com.zsoft.client.api.controller;

import java.util.concurrent.ExecutionException;

import ar.com.zsoft.client.api.entity.Document;
import ar.com.zsoft.client.api.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/{id}")
    public Document findOneDocument(@PathVariable String id) throws InterruptedException, ExecutionException {
        return documentService.findOneDocument(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Document document) throws InterruptedException, ExecutionException {
        return documentService.create(document);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return documentService.delete(id);
    }

    @PutMapping("/{id}")
    public String updateDocument(@RequestBody Document document) throws InterruptedException, ExecutionException {
        return documentService.update(document);
    }

}

