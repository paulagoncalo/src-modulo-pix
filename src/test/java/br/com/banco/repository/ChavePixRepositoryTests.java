package br.com.banco.repository;

import br.com.banco.entities.ChavePixEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQLDB)
class ChavePixRepositoryTests {

    @Autowired
    private ChavePixRepository chavePixRepository;

    @Test
    void salva_chave_pix_com_sucesso() {
        var chavePix = ChavePixEntity.builder().id("3953a028-7b45-11ed-a1eb-0242ac120002")
                .tipoChave("cpf").valorChave("09192458469").tipoConta("corrente").numeroAgencia(4321L)
                .numeroConta(12345678L).nomeCorrentista("Maria").sobrenomeCorrentista("Teste")
                .created(LocalDateTime.now()).updated(LocalDateTime.now()).isActive(true).build();
        chavePixRepository.save(chavePix);
        var registro = chavePixRepository.findAll().size();
        assertEquals(1, registro);
    }

    @Test
    void consulta_chave_ja_existe() {
        var chavePix = ChavePixEntity.builder().id("3953a028-7b45-11ed-a1eb-0242ac120002")
                .tipoChave("cpf").valorChave("09192458469").tipoConta("corrente").numeroAgencia(4321L)
                .numeroConta(12345678L).nomeCorrentista("Maria").sobrenomeCorrentista("Teste")
                .created(LocalDateTime.now()).updated(LocalDateTime.now()).isActive(true).build();
        chavePixRepository.save(chavePix);
        var registro = chavePixRepository.findAll().size();
        assertEquals(1, registro);

        var hasChavePix = chavePixRepository.existsByValorChave(chavePix.getValorChave());
        assertTrue(hasChavePix);

    }

}
