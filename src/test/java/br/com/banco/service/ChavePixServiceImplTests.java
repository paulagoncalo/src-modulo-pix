package br.com.banco.service;

import br.com.banco.dto.ChavePixDTO;
import br.com.banco.dto.ClienteDTO;
import br.com.banco.exceptions.ChavePixException;
import br.com.banco.exceptions.ClienteServiceException;
import br.com.banco.repository.ChavePixRepository;
import br.com.banco.service.rest.ClienteRestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ChavePixServiceImplTests {

    @InjectMocks
    private ChavePixServiceImpl chavePixService;
    @Mock
    private ChavePixRepository chavePixRepository;
    @Mock
    private ClienteRestService clienteRestService;

    @Test
    void verifica_chave_valida() throws ClienteServiceException, ChavePixException {

        var chavePixDTO = ChavePixDTO.builder().tipoChave("cpf").valorChave("09192458467").tipoConta("corrente").numeroAgencia(1234L)
                .numeroConta(87654321L).nomeCorrentista("Maria").sobrenomeCorrentista("Teste")
                .build();
        var clienteDTO = ClienteDTO.builder().nomeCliente("Maria").sobrenomeCliente("Teste")
                        .tipoCliente("pf").tipoConta("corrente").cpfCnpj("09192458467").build();

        when(clienteRestService.consultarClientePorNumeroConta(chavePixDTO.getNumeroConta())).thenReturn(clienteDTO);
        var chaveValida = chavePixService.isChaveValida(chavePixDTO);

        assertTrue(chaveValida);
    }
}
