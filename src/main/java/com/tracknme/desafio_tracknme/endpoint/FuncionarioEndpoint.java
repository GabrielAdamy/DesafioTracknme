package com.tracknme.desafio_tracknme.endpoint;

import com.tracknme.desafio_tracknme.domain.Funcionario;
import com.tracknme.desafio_tracknme.repository.FuncionarioRepository;
import com.tracknme.desafio_tracknme.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioEndpoint {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FuncionarioService service;

    public FuncionarioEndpoint(FuncionarioRepository repository, FuncionarioService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("{id}")
    @Cacheable()
    public Optional<Funcionario> getById(@PathVariable long id) {
        return repository.findById(id);
    }

    @GetMapping(path = "cep/{cep}")
    @Cacheable()
    public List<Funcionario> getById(@PathVariable String cep) {
        return repository.findByCep(cep);
    }

    @GetMapping
    @Cacheable
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(path = "/create")
    public Funcionario create(@RequestBody @Validated Funcionario funcionario) throws Exception {
        return service.create(funcionario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Funcionario> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<Funcionario>(NO_CONTENT);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
        repository.findById(id).map(f -> {
            funcionario.setId(f.getId());
            service.update(id, funcionario);
            return f;
        });
    }

    @PatchMapping(path = "update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Funcionario> updatee(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
        Funcionario resource = service.update(id, funcionario);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
