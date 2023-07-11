package br.com.banco.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.models.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findAllByDataTransferencia(LocalDateTime data);

    List<Transferencia> findAllByContaNomeResponsavel(String nome);

    List<Transferencia> findAllByContaNomeResponsavelAndDataTransferencia(String nome, LocalDateTime data);

}
