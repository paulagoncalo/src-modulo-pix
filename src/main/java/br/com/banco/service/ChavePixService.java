package br.com.banco.service;

import br.com.banco.exceptions.ChaveNaoEncontradaException;
import br.com.banco.exceptions.ChavePixException;
import br.com.banco.dto.ChavePixDTO;
import br.com.banco.exceptions.ClienteServiceException;
import org.springframework.stereotype.Service;

@Service
public interface ChavePixService {

    String cadastrarChavePix(ChavePixDTO chavePixDTO) throws ChavePixException;
    ChavePixDTO inativarChavePix(String id) throws ChaveNaoEncontradaException, ChavePixException;
    ChavePixDTO consultarChavePorId(String id) throws ChaveNaoEncontradaException;
    Boolean isChaveValida(ChavePixDTO chavePixDTO) throws ClienteServiceException, ChavePixException;
}
