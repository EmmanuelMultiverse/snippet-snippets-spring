package com.snipper.snipper_snippets.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipper.snipper_snippets.models.Snippet;
import com.snipper.snipper_snippets.repositories.SnippetRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/snippets")
public class CrudController {

    private final SnippetRepository snippetRepository;

    public CrudController(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;

    }

    @PostMapping()
    public ResponseEntity<Iterable<Snippet>> createSnippet(@RequestBody Snippet snippet) {
        snippetRepository.save(snippet);
        var snippets = snippetRepository.findAll();

        return ResponseEntity.status(200).body(snippets);

    }

    @GetMapping()
    public ResponseEntity<Iterable<Snippet>> getSnippets(@RequestParam(name = "language") Optional<String> codingLanaguge) {
        Iterable<Snippet> snippets;

        if (codingLanaguge.isPresent()) {
            snippets = snippetRepository.findByLanguage(codingLanaguge.get());

        } else {
            snippets = snippetRepository.findAll();

        }
        
        return ResponseEntity.status(200).body(snippets);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Snippet> getMethodName(@PathVariable Long id) {
        var snippet = snippetRepository.findById(id);

        return ResponseEntity.status(200).body(snippet.get());

    }

}
