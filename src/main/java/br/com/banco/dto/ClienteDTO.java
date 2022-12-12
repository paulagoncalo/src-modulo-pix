package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nomeCliente;
    private String sobrenomeCliente;
    private String tipoConta;
    private Long numeroAgencia;
    private Long NumeroConta;
    private String cpfCnpj;
    private String tipoCliente;
}
