package br.com.banco.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_chaves_pix")
public class ChavePixEntity {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(name = "tipo_chave", nullable = false)
    private String tipoChave;

    @Column(name = "valor_chave", nullable = false)
    private String valorChave;

    @Column(name = "tipo_conta", nullable = false)
    private String tipoConta;

    @Column(name = "numero_agencia", nullable = false)
    private Long numeroAgencia;

    @Column(name = "numero_conta", nullable = false)
    private Long numeroConta;

    @Column(name = "nome_correntista", nullable = false)
    private String nomeCorrentista;

    @Column(name = "sobrenome_correntista", nullable = true)
    private String sobrenomeCorrentista;

    @Column(name = "ativo", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated;

}
