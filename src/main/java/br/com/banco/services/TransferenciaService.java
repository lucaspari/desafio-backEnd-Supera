package br.com.banco.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banco.models.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransferenciaService {

    private TransferenciaRepository transferenciaRepository;

    public Transferencia findTransferenciaByContaBancaria(Long id) {
        return transferenciaRepository.findById(id).orElse(null);
    }

    public List<Transferencia> findAllTransferencias() {
        return transferenciaRepository.findAll();
    }

    public List<Transferencia> findAllTransferenciaByData(LocalDate data) {
        return transferenciaRepository.findAllByDataTransferencia(data.atStartOfDay());
    }

    public List<Transferencia> findAllTransferenciaByDataBetween(LocalDate d1, LocalDate d2) {
        return transferenciaRepository.findAllByDataTransferenciaBetween(d1.atStartOfDay(), d2.atStartOfDay());

    }

    public List<Transferencia> findAllTransferenciaByNomeOperadorResponsavel(String nome) {
        return transferenciaRepository.findAllByNomeOperadorTransacao(nome);
    }

    public List<Transferencia> findAllTransferenciasByDataBetweenAndNomeOp(String nome, LocalDate data,LocalDate data2) {
        var transferencias = transferenciaRepository.
        findAllByDataTransferenciaBetween(data.atStartOfDay(), data2.atStartOfDay());
        transferencias.forEach(t -> System.out.println(t.getNomeOperadorTransacao()));
        return transferencias.stream()
    .filter(t -> {
        String nomeOperadorTransacao = t.getNomeOperadorTransacao();
        return nomeOperadorTransacao != null && nomeOperadorTransacao.equals(nome);
    })
    .toList();
        
    }

}
