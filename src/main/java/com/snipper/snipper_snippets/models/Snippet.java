package com.snipper.snipper_snippets.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "snippet")
public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String language;

    private String code;

    public Snippet() {
    }

    public Snippet(Long id, String language, String code) {
        this.id = id;
        this.language = language;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
