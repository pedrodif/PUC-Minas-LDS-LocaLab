package com.localab.api.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.localab.api.DTO.AuthDTO;
import com.localab.api.DTO.UsuarioDTO;
import com.localab.api.Model.Entity.Usuario;
import com.localab.api.Service.UserService;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody Usuario usuario) {
        UsuarioDTO created = service.create(usuario);
        return ResponseEntity.created(URI.create("/users/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.update(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@Valid @RequestBody AuthDTO authDTO) {
        return ResponseEntity.ok(service.authenticate(authDTO));
    }
}