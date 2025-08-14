package com.snipper.snipper_snippets.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.snipper.snipper_snippets.JwtUtil;
import com.snipper.snipper_snippets.models.AuthenticationRequest;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipper.snipper_snippets.models.Snippet;
import com.snipper.snipper_snippets.models.User;
import com.snipper.snipper_snippets.repositories.SnippetRepository;
import com.snipper.snipper_snippets.repositories.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("")
public class CrudController {

    private final SnippetRepository snippetRepository;
    private final StringEncryptor stringEncryptor;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    

    public CrudController(SnippetRepository snippetRepository, StringEncryptor stringEncryptor, UserRepository userRepository, JwtUtil jwtUtil) {
        this.snippetRepository = snippetRepository;
        this.userRepository = userRepository;
        this.stringEncryptor = stringEncryptor;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/snippets")
    public ResponseEntity<Iterable<Snippet>> createSnippet(@RequestBody Snippet snippet) {
        
        String hashedCodeSnippet = stringEncryptor.encrypt(snippet.getCode());
        snippet.setCode(hashedCodeSnippet);
        snippetRepository.save(snippet);
        var snippets = snippetRepository.findAll();

        return ResponseEntity.status(200).body(snippets);
    }

    @GetMapping("/snippets")
    public ResponseEntity<Iterable<String>> getSnippets(@RequestParam(name = "language") Optional<String> codingLanaguge) {
        Iterable<Snippet> snippets;

        if (codingLanaguge.isPresent()) {
            snippets = snippetRepository.findByLanguage(codingLanaguge.get());
            
        } else {
            snippets = snippetRepository.findAll();

        }
        
        var decryptedCodeSnippets = new ArrayList<String>();

        for (var codeSnippet : snippets) {
            var decryptedCode = stringEncryptor.decrypt(codeSnippet.getCode());
            decryptedCodeSnippets.add(decryptedCode);
        }

        return ResponseEntity.status(200).body(decryptedCodeSnippets);
    }

    @GetMapping("/snippets/{id}")
    public ResponseEntity<Snippet> getMethodName(@PathVariable Long id) {
        var snippet = snippetRepository.findById(id);

        return ResponseEntity.status(200).body(snippet.get());

    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        
        var findUser = userRepository.findByUsername(user.getUsername());

        if (findUser.isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        } else {
            var hashedPassword = BCrypt.withDefaults().hashToString(10, user.getPassword().toCharArray());
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return ResponseEntity.status(201).body("User registered Successfully");
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> getUser(@RequestBody AuthenticationRequest authenticationRequest) {

        var foundUser = userRepository.findByUsername(authenticationRequest.getUsername());

        if (foundUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Could not find user");
        } else {
            var correctPassword = BCrypt.verifyer().verify(authenticationRequest.getPassword().toCharArray(), foundUser.get().getPassword().toCharArray());

            if (correctPassword.verified) {
                return ResponseEntity.status(200).body(jwtUtil.generateToken(foundUser.get().getUsername()));
            } else {
                return ResponseEntity.status(400).body("Invalid Password");
            }
        }
    }


}
