package br.com.banco.services;

import java.time.LocalDateTime;
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

    public List<Transferencia> findAllTransferenciaByData(LocalDateTime data) {
        return transferenciaRepository.findAllByDataTransferencia(data);
    }

    public List<Transferencia> findAllTransferenciaByContaNomeResponsavel(String nome) {
        return transferenciaRepository.findAllByContaNomeResponsavel(nome);
    }

    public List<Transferencia> findAllTransferenciaByContaNomeResponsavelAndData(String nome, LocalDateTime data) {
        return transferenciaRepository.findAllByContaNomeResponsavelAndDataTransferencia(nome, data);
    }

}
