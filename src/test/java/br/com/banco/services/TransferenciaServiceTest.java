package br.com.banco.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.banco.models.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TransferenciaServiceTest {
    @Mock
    private TransferenciaRepository transferenciaRepository;
    @InjectMocks
    private TransferenciaService transferenciaService;

    @Test
    void findTransferenciaByContaBancaria() {
        Long id = 1L;
        Transferencia transferencia = new Transferencia();
        transferencia.setId(id);
        Mockito.when(transferenciaRepository.findById(id)).thenReturn(Optional.of(transferencia));

        Transferencia result = transferenciaService.findTransferenciaByContaBancaria(id);

        Assertions.assertEquals(transferencia, result);
        Mockito.verify(transferenciaRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void testFindAllTransferencias() {

        List<Transferencia> transferencias = new ArrayList<>();
        transferencias.add(new Transferencia());
        Mockito.when(transferenciaRepository.findAll()).thenReturn(transferencias);

        List<Transferencia> result = transferenciaService.findAllTransferencias();

        Assertions.assertEquals(transferencias, result);
        Mockito.verify(transferenciaRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindAllTransferenciaByData() {

        LocalDate data = LocalDate.now();
        List<Transferencia> transferencias = new ArrayList<>();
        transferencias.add(new Transferencia());
        Mockito.when(transferenciaRepository.findAllByDataTransferencia(data.atStartOfDay()))
                .thenReturn(transferencias);

        List<Transferencia> result = transferenciaService.findAllTransferenciaByData(data);

        Assertions.assertEquals(transferencias, result);
        Mockito.verify(transferenciaRepository, Mockito.times(1)).findAllByDataTransferencia(data.atStartOfDay());
    }

    @Test
    public void testFindAllTransferenciaByContaNomeResponsavel() {
        String nome = "Jon Snow";
        List<Transferencia> transferencias = new ArrayList<>();
        transferencias.add(new Transferencia());
        Mockito.when(transferenciaRepository.findAllByContaNomeResponsavel(nome)).thenReturn(transferencias);

        List<Transferencia> result = transferenciaService.findAllTransferenciaByContaNomeResponsavel(nome);

        Assertions.assertEquals(transferencias, result);
        Mockito.verify(transferenciaRepository, Mockito.times(1)).findAllByContaNomeResponsavel(nome);
    }

    @Test
    public void testFindAllTransferenciaByContaNomeResponsavelAndData() {
        // Arrange
        String nome = "Jon Snow";
        LocalDate data = LocalDate.now();
        List<Transferencia> transferencias = new ArrayList<>();
        transferencias.add(new Transferencia());
        Mockito.when(
                transferenciaRepository.findAllByContaNomeResponsavelAndDataTransferencia(nome, data.atStartOfDay()))
                .thenReturn(transferencias);

        List<Transferencia> result = transferenciaService.findAllTransferenciaByContaNomeResponsavelAndData(nome, data);

        Assertions.assertEquals(transferencias, result);
        Mockito.verify(transferenciaRepository, Mockito.times(1))
                .findAllByContaNomeResponsavelAndDataTransferencia(nome, data.atStartOfDay());
    }
}