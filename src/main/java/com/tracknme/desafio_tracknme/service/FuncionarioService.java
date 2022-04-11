package com.tracknme.desafio_tracknme.service;

import com.tracknme.desafio_tracknme.client.ViaCepClient;
import com.tracknme.desafio_tracknme.domain.Funcionario;
import com.tracknme.desafio_tracknme.domain.FuncionarioValidation;
import com.tracknme.desafio_tracknme.domain.ViaCepDTO;
import com.tracknme.desafio_tracknme.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FuncionarioService {

    @Autowired
    private ViaCepClient client;

    @Autowired
    private FuncionarioRepository repository;

    public Funcionario create(Funcionario fun) throws Exception {
        Funcionario funcionario = new Funcionario();
        ResponseEntity<ViaCepDTO> cepDTO = client.cep(fun.getCep());

        if (fun.getNome() == null || fun.getNome().isBlank()) {
            throw new Error("O nome deve ser preenchido!");
        } else {
            funcionario.setNome(fun.getNome());
        }
        if (fun.getIdade() == null) {
            throw new Exception("A idade deve ser preenchida!");
        } else {
            funcionario.setIdade(fun.getIdade());
        }
        if (fun.getCep() == null || fun.getCep().isBlank()) {
            throw new Exception("O cep deve ser preenchido!");
        } else {
            funcionario.setCep(fun.getCep());
        }
        funcionario.setSexo(fun.getSexo());
        funcionario.setCidade(cepDTO.getBody().getLocalidade());
        funcionario.setBairro(cepDTO.getBody().getBairro());
        funcionario.setEndereco(cepDTO.getBody().getLogradouro());
        funcionario.setEstado(cepDTO.getBody().getUf());
        return repository.save(funcionario);
    }

    public Funcionario update(Long id, Funcionario fun) {
        Funcionario funcionario = repository.getById(id);

        if (fun.getCep() != null) {
            ResponseEntity<ViaCepDTO> cepDTO = findCep(fun.getCep());
            funcionario.setCep(cepDTO.getBody().getCep());
            funcionario.setEndereco(cepDTO.getBody().getLogradouro());
            funcionario.setCidade(cepDTO.getBody().getLocalidade());
            funcionario.setBairro(cepDTO.getBody().getBairro());
            funcionario.setEstado(cepDTO.getBody().getUf());
        }
        if (fun.getNome() != null) {
            funcionario.setNome(fun.getNome());
        }
        if (fun.getIdade() != null) {
            funcionario.setIdade(fun.getIdade());
        }
        if (fun.getSexo() != null) {
            funcionario.setSexo(fun.getSexo());
        }
        return repository.save(funcionario);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ResponseEntity<ViaCepDTO> findCep(String cep) {
        return client.cep(cep);
    }

}
