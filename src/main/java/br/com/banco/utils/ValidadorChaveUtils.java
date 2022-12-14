package br.com.banco.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
@UtilityClass
public class ValidadorChaveUtils {


    public Boolean isCelularValido(String celular){
        var pattern = Pattern.compile(
                "^(\\+\\d{1,2}( )?)?((\\(\\d{1,3}\\))|\\d{1,9})?\\d{3,4}?\\d{4}$");
        var matcher = pattern.matcher(celular);
        return matcher.matches();
    }

    public static boolean isEmailValido(String email) {
        var expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        var pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        var matcher = pattern.matcher(email);
        return matcher.matches() && email.length() < 77;
    }

    public boolean isCpfValido(String cpf){
        log.info("Iniciando validação de cpf");
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") ||
                cpf.equals("33333333333") ||
                cpf.equals("44444444444") ||
                cpf.equals("55555555555") ||
                cpf.equals("66666666666") ||
                cpf.equals("77777777777") ||
                cpf.equals("88888888888") ||
                cpf.equals("99999999999") ||
                cpf.length() != 11)
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // Calculo do primeiro Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char)(r + 48);

            // Calculo do segundo Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char)(r + 48);

            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                log.info("Validação de cpf finalizada com status {}", true);
                return (true);
            } else{
                log.info("Validação de cpf finalizada com status {}", false);
                return(false);
            }
        } catch(Exception e) {
            return(false);
        }
    }
}
