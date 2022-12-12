package br.com.banco.repository;

import br.com.banco.entities.ChavePixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePixEntity, String> {

    Boolean existsByValorChave(String valorChave);
    List<ChavePixEntity> findByNumeroConta(Long numeroConta);
}
