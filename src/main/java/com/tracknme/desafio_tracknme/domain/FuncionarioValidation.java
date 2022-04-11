package com.tracknme.desafio_tracknme.domain;

import javax.validation.constraints.NotNull;

public class FuncionarioValidation {

    public static boolean isValid(@NotNull Funcionario fun) throws Exception {
        if (fun.getNome() == null)
            throw new Exception("Informe o nome do Funcionario.");

        if (fun.getIdade() == null)
            throw new Exception("Informe a idade do Funcionario.");

        if (fun.getCep() == null)
            throw new Exception("Informe o Cep do Funcionario.");

        return true;
    }
}

