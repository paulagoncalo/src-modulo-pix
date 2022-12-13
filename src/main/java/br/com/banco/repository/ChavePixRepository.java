package br.com.banco.repository;

import br.com.banco.entities.ChavePixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePixEntity, String> {

    Boolean existsByValorChave(String valorChave);
    List<ChavePixEntity> findByNumeroConta(Long numeroConta);
    Optional<ChavePixEntity> findByIdAndIsActive(String id, Boolean ativo);
    Optional<ArrayList<ChavePixEntity>> findByNumeroAgenciaAndNumeroConta(Long numeroAgencia, Long numeroConta);
    Optional<ArrayList<ChavePixEntity>> findByTipoChave(String tipoChave);
}
