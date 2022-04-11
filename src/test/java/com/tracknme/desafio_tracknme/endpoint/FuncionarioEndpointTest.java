package com.tracknme.desafio_tracknme.endpoint;

import com.tracknme.desafio_tracknme.domain.Funcionario;
import com.tracknme.desafio_tracknme.repository.FuncionarioRepository;
import com.tracknme.desafio_tracknme.service.FuncionarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FuncionarioEndpointTest {

    @InjectMocks
    private FuncionarioEndpoint endpoint;

    @Mock
    private FuncionarioRepository repository;

    @Mock
    private FuncionarioService service;

    private Funcionario funcionario = new Funcionario();

    @Before
    public void setUp() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("Gabriel");
        funcionario.setIdade(26L);
        funcionario.setCep("93110270");
        funcionario.setSexo("Masculino");
        funcionario.setEndereco("Rua Fernando Abott");
        funcionario.setBairro("Rio dos Sinos");
        funcionario.setCidade("São Leopoldo");
        funcionario.setEstado("RS");

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setId(2L);
        funcionario2.setNome("Adamy");
        funcionario2.setIdade(10L);
        funcionario2.setCep("93110270");
        funcionario2.setSexo("Masculino");
        funcionario2.setEndereco("Rua Fernando Abott");
        funcionario2.setBairro("Rio dos Sinos");
        funcionario2.setCidade("São Leopoldo");
        funcionario2.setEstado("RS");

    }

    @Test
    public void testEndPoint() throws Exception {
        endpoint.create(funcionario);
        verify(service, times(1)).create(funcionario);
    }

    @Test
    public void testEndPointDelete(){
        endpoint.delete(anyLong());
        verify(service, times(1)).delete(anyLong());
    }

    @Test
    public void testEndPointUpdate(){
        endpoint.updatee(1L, funcionario);
        verify(service, times(1)).update(1L, funcionario);
    }

}
