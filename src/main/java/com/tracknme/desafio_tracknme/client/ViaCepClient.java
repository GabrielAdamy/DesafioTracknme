package com.tracknme.desafio_tracknme.client;

import com.tracknme.desafio_tracknme.domain.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@EnableCaching
public class ViaCepClient {

    public ResponseEntity<ViaCepDTO> cep(@PathVariable(name = "cep") String cep) {

        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://viacep.com.br/ws/{cep}/json/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("cep", cep);

        //faz um GET, converte a resposta HTTP em um tipo de objeto da minha escolha e retorna esse objeto
        ViaCepDTO viaCepDTO = restTemplate.getForObject(uri, ViaCepDTO.class, params);

        return new ResponseEntity<ViaCepDTO>(viaCepDTO, HttpStatus.OK);
    }
}
