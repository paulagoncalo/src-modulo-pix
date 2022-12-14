package br.com.banco.controller;

import br.com.banco.dto.ChavePixDTO;
import br.com.banco.entities.ChavePixEntity;
import br.com.banco.exceptions.ChaveNaoEncontradaException;
import br.com.banco.repository.ChavePixRepository;
import br.com.banco.service.ChavePixService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static br.com.banco.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ChavePixController.class)
class ChavePixControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ChavePixService chavePixService;

    @Test
    void cadastrar_chave() throws Exception {
        var chavePixDTO = ChavePixDTO.builder().tipoChave("cpf").valorChave("09192458467").tipoConta("corrente").numeroAgencia(1234L)
                .numeroConta(87654321L).nomeCorrentista("Maria").sobrenomeCorrentista("Teste")
                .build();
        var id = "08a39420-6bfa-4a58-876a-a63a663098d5";

        when(chavePixService.isChaveValida(chavePixDTO)).thenReturn(true);
        when(chavePixService.cadastrarChavePix(chavePixDTO)).thenReturn(id);
        this.mockMvc.perform(post("/cadastrar/chave")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(chavePixDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("08a39420-6bfa-4a58-876a-a63a663098d5")));
    }
}
