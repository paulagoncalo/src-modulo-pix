package br.com.banco.controller;

import br.com.banco.exceptions.ChaveNaoEncontradaException;
import br.com.banco.exceptions.ChavePixException;
import br.com.banco.dto.ChavePixDTO;
import br.com.banco.exceptions.ClienteServiceException;
import br.com.banco.service.ChavePixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChavePixController {

    @Autowired
    private ChavePixService chavePixService;

    @PostMapping("/cadastrar/chave")
    public ResponseEntity<Object> cadastrarChavePix(@RequestBody ChavePixDTO chavePixDTO) throws ClienteServiceException, ChavePixException {
        if(chavePixService.isChaveValida(chavePixDTO)){
           return ResponseEntity.ok(chavePixService.cadastrarChavePix(chavePixDTO));
        }
        return ResponseEntity.unprocessableEntity().body(null);
    }
    @PostMapping("/deletar/chave/{id}")
    public ResponseEntity<Object> removerChavePix(@PathVariable String id) throws ChaveNaoEncontradaException, ChavePixException {
        return ResponseEntity.ok(chavePixService.inativarChavePix(id));
    }
    @GetMapping("/consultar/chave/{id}")
    public ResponseEntity<Object> consultarChavePixPorId(@PathVariable String id) throws ChaveNaoEncontradaException, ChavePixException {
        return ResponseEntity.ok(chavePixService.consultarChavePorId(id));
    }
}
