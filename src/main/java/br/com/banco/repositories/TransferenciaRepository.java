package br.com.banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.models.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}
