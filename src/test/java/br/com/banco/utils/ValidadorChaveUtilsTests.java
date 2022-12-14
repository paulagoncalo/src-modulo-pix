package br.com.banco.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class ValidadorChaveUtilsTests {

    @Test
    void verifica_chave_celular_valida() {
        var celular = "+5581982747458";
        var chaveValida = ValidadorChaveUtils.isCelularValido(celular);

        assertTrue(chaveValida);
    }

    @Test
    void verifica_chave_celular_invalida() {
        var celular = "+558198274-7458";
        var chaveValida = ValidadorChaveUtils.isCelularValido(celular);

        assertFalse(chaveValida);
    }

    @Test
    void verifica_chave_email_valido() {
        var email = "email@email.com";
        var chaveValida = ValidadorChaveUtils.isEmailValido(email);

        assertTrue(chaveValida);
    }

    @Test
    void verifica_chave_email_invalido() {
        var email = "email@com";
        var chaveValida = ValidadorChaveUtils.isEmailValido(email);

        assertFalse(chaveValida);
    }

    @Test
    void verifica_chave_cpf_valida() {
        var cpf = "75745437049";
        var chaveValida = ValidadorChaveUtils.isCpfValido(cpf);

        assertTrue(chaveValida);
    }

    @Test
    void verifica_chave_cpf_invalida() {
        var cpf = "75745437041";
        var chaveValida = ValidadorChaveUtils.isCpfValido(cpf);

        assertFalse(chaveValida);
    }
}
