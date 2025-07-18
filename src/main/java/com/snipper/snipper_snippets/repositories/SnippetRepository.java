package com.snipper.snipper_snippets.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.snipper.snipper_snippets.models.Snippet;

@Repository
public interface SnippetRepository extends CrudRepository<Snippet, Long> {

    Iterable<Snippet> findByLanguage(String language);
}
