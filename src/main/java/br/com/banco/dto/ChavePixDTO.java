package br.com.banco.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChavePixDTO {
    private String id;
    @NonNull
    private String tipoChave;
    @NonNull
    private String valorChave;
    @NonNull
    private String tipoConta;
    @NonNull
    private Long numeroAgencia;
    @NonNull
    private Long numeroConta;
    @NonNull
    private String nomeCorrentista;
    private String sobrenomeCorrentista;
    private LocalDateTime dataHoraInclusaoChave;
    private String dataHoraInativacaoChave;
}
