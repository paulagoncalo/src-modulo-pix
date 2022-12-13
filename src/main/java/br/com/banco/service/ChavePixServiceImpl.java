package br.com.banco.service;

import br.com.banco.AppConstants;
import br.com.banco.dto.ChavePixDTO;
import br.com.banco.entities.ChavePixEntity;
import br.com.banco.enums.TipoClienteEnum;
import br.com.banco.enums.TipoContaEnum;
import br.com.banco.exceptions.ChaveNaoEncontradaException;
import br.com.banco.exceptions.ChavePixException;
import br.com.banco.exceptions.ClienteServiceException;
import br.com.banco.repository.ChavePixRepository;
import br.com.banco.service.rest.ClienteRestService;
import br.com.banco.utils.ValidadorChaveUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static br.com.banco.AppConstants.LIMITE_DIGITOS_NUMERO_AGENCIA;
import static br.com.banco.AppConstants.LIMITE_DIGITOS_NUMERO_CONTA;

@Service
@RequiredArgsConstructor
public class ChavePixServiceImpl implements ChavePixService{

    private final ChavePixRepository chavePixRepository;
    private final ClienteRestService clienteRestService;

    @Override
    public String cadastrarChavePix(ChavePixDTO chavePixDTO) throws ChavePixException {
        var entity= ChavePixEntity.builder().build();
        BeanUtils.copyProperties(chavePixDTO, entity);

        entity.setId(String.valueOf(UUID.randomUUID()));
        entity.setCreated(LocalDateTime.now());
        entity.setUpdated(LocalDateTime.now());
        entity.setIsActive(Boolean.TRUE);

        try {
            return chavePixRepository.save(entity).getId();
        } catch (Exception e){
            throw new ChavePixException(null);
        }
    }

    @Override
    public ChavePixDTO inativarChavePix(String id) throws ChaveNaoEncontradaException, ChavePixException {
        var entity = chavePixRepository.findById(id).orElseThrow(() -> new ChaveNaoEncontradaException("Não foi encontrada chave para valor informado"));
        if (entity.getIsActive()){
            entity.setIsActive(Boolean.FALSE);
            entity.setUpdated(LocalDateTime.now());
            chavePixRepository.save(entity);

            return ChavePixDTO.builder().id(entity.getId())
                    .tipoChave(entity.getTipoChave()).valorChave(entity.getValorChave())
                    .tipoConta(entity.getTipoConta()).numeroAgencia(entity.getNumeroAgencia())
                    .numeroConta(entity.getNumeroConta()).nomeCorrentista(entity.getNomeCorrentista())
                    .sobrenomeCorrentista(Objects.requireNonNull(entity.getSobrenomeCorrentista()))
                    .dataHoraInclusaoChave(entity.getCreated()).dataHoraInativacaoChave(String.valueOf(entity.getUpdated())).build();
        }
        throw new ChavePixException("Chave já removida!");
    }

    @Override
    public ChavePixDTO consultarChavePorId(String id) throws ChaveNaoEncontradaException {
        var entity = chavePixRepository.findById(id).orElseThrow(() -> new ChaveNaoEncontradaException("Não foi encontrada chave para valor informado"));
        return mapToDto(entity);
    }

    @Override
    public ArrayList<ChavePixDTO> consultarChavePorAgenciaEConta(ChavePixDTO chavePixDTO) throws ChaveNaoEncontradaException {
        var entity = chavePixRepository.findByNumeroAgenciaAndNumeroConta(chavePixDTO.getNumeroAgencia(), chavePixDTO.getNumeroConta()).orElseThrow(() -> new ChaveNaoEncontradaException("Não foi encontrada chave para valor informado"));
        ArrayList<ChavePixDTO> chavePixdtoList = new ArrayList<>();
        entity.forEach(chave -> chavePixdtoList.add(mapToDto(chave)));

        return chavePixdtoList;
    }

    @Override
    public ArrayList<ChavePixDTO> consultarChavePorTipoChave(String tipoChave) throws ChaveNaoEncontradaException {
        var entity = chavePixRepository.findByTipoChave(tipoChave).orElseThrow(() -> new ChaveNaoEncontradaException("Não foi encontrada chave para valor informado"));
        ArrayList<ChavePixDTO> chavePixdtoList = new ArrayList<>();
        entity.forEach(chave -> chavePixdtoList.add(mapToDto(chave)));

        return chavePixdtoList;
    }

    @Override
    public Boolean isChaveValida(ChavePixDTO chavePixDTO) throws ClienteServiceException, ChavePixException {

        validaInformacoesRequest(chavePixDTO);

        switch (chavePixDTO.getTipoChave()){
            case (AppConstants.CHAVE_CPF):
                return ValidadorChaveUtil.isCpfValido(chavePixDTO.getValorChave());
            case (AppConstants.CHAVE_CELULAR):
                return ValidadorChaveUtil.isCelularValido(chavePixDTO.getValorChave());
            case (AppConstants.CHAVE_EMAIL):
                return ValidadorChaveUtil.isEmailValido(chavePixDTO.getValorChave());
            default:
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, null);

        }
    }

    private void validaInformacoesRequest(ChavePixDTO chavePixDTO) throws ClienteServiceException, ChavePixException {
        if(chavePixRepository.existsByValorChave(chavePixDTO.getValorChave())
                || isQuantidadeChaveMaiorQueLimite(chavePixDTO.getNumeroConta())
                || !isTipoContaValida(chavePixDTO.getTipoConta())
                || chavePixDTO.getNumeroAgencia() >= LIMITE_DIGITOS_NUMERO_AGENCIA
                || chavePixDTO.getNumeroConta() >= LIMITE_DIGITOS_NUMERO_CONTA)
            throw new ChavePixException(null);
    }

    private Boolean isTipoContaValida(String tipoConta){
        if(Objects.nonNull(tipoConta))
            return TipoContaEnum.CORRENTE.getValue().equalsIgnoreCase(tipoConta)
                || TipoContaEnum.POUPANCA.getValue().equalsIgnoreCase(tipoConta);

        return Boolean.FALSE;
    }

    private boolean isQuantidadeChaveMaiorQueLimite(Long numeroConta) throws ClienteServiceException {
        var cliente = clienteRestService.consultarClientePorNumeroConta(numeroConta);

        return (TipoClienteEnum.PF.name().equalsIgnoreCase(cliente.getTipoCliente())
                && chavePixRepository.findByNumeroConta(numeroConta).size()> LIMITE_DIGITOS_NUMERO_AGENCIA)
                ||(TipoClienteEnum.PJ.name().equalsIgnoreCase(cliente.getTipoCliente())
                && chavePixRepository.findByNumeroConta(numeroConta).size()> LIMITE_DIGITOS_NUMERO_CONTA);
    }

    private ChavePixDTO mapToDto(ChavePixEntity entity){
        return ChavePixDTO.builder()
                .id(entity.getId())
                .tipoChave(entity.getTipoChave())
                .valorChave(entity.getValorChave())
                .tipoConta(entity.getTipoConta())
                .numeroAgencia(entity.getNumeroAgencia())
                .numeroConta(entity.getNumeroConta())
                .nomeCorrentista(entity.getNomeCorrentista())
                .sobrenomeCorrentista(Objects.nonNull(entity.getSobrenomeCorrentista()) ? entity.getSobrenomeCorrentista() : "")
                .dataHoraInclusaoChave(entity.getCreated())
                .dataHoraInativacaoChave(entity.getIsActive() ? "" : String.valueOf(entity.getUpdated())).build();
    }
}
