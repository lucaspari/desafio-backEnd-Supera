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

import static org.mockito.Mockito.when;

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
        Mockito.when(transferenciaRepository.findAllByNomeOperadorTransacao(nome)).thenReturn(transferencias);

        List<Transferencia> result = transferenciaService.findAllTransferenciaByNomeOperadorResponsavel(nome);

        Assertions.assertEquals(transferencias, result);
        Mockito.verify(transferenciaRepository, Mockito.times(1)).findAllByNomeOperadorTransacao(nome);
    }
        @Test
    public void testFindAllTransferenciasByDataBetweenAndNomeOp() {
        String nome = "João";
        LocalDate data1 = LocalDate.now();
        LocalDate data2 = LocalDate.now().plusDays(1);
        List<Transferencia> transferencias = new ArrayList<>();
        Transferencia transferencia1 = new Transferencia();
        transferencia1.setId(1L);
        transferencia1.setNomeOperadorTransacao(nome);
        Transferencia transferencia2 = new Transferencia();
        transferencia2.setId(2L);
        transferencia2.setNomeOperadorTransacao("Maria");
        transferencias.add(transferencia1);
        transferencias.add(transferencia2);
        when(transferenciaRepository.findAllByDataTransferenciaBetween(data1.atStartOfDay(), data2.atStartOfDay())).thenReturn(transferencias);
        List<Transferencia> result = transferenciaService.findAllTransferenciasByDataBetweenAndNomeOp(nome, data1, data2);
        Assertions.assertEquals(result.get(0).getNomeOperadorTransacao(),"João");
    }

}