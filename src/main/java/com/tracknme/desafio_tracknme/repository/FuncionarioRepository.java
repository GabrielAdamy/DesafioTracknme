package com.tracknme.desafio_tracknme.repository;

import com.tracknme.desafio_tracknme.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    //Funcionario findById(long id);


    List<Funcionario> findByCep(String cep);
}
