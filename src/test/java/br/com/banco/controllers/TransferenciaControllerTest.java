package br.com.banco.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.banco.models.Transferencia;
import br.com.banco.services.TransferenciaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TransferenciaControllerTest {

    @Mock
    private TransferenciaService transferenciaService;

    @InjectMocks
    private TransferenciaController transferenciaController;

    @Test
    public void testListar() {
        LocalDate dateTime = LocalDate.now();
        LocalDate dateTime2 = LocalDate.now().plusDays(1);
        String nome = "Jon Snow";
        List<Transferencia> transferencias = new ArrayList<>();
        transferencias.add(new Transferencia());
        Mockito.when(transferenciaService.findAllTransferenciasByDataBetweenAndNomeOp(nome, dateTime,dateTime2))
                .thenReturn(transferencias);
        Mockito.when(transferenciaService.findAllTransferenciaByNomeOperadorResponsavel(nome)).thenReturn(transferencias);
        Mockito.when(transferenciaService.findAllTransferencias()).thenReturn(transferencias);

        ResponseEntity<List<Transferencia>> result = transferenciaController.listar(dateTime, dateTime2, nome);
        ResponseEntity<List<Transferencia>> result2 = transferenciaController.listar(null, null, null);
        ResponseEntity<List<Transferencia>> result3 = transferenciaController.listar(dateTime, null, null);
        ResponseEntity<List<Transferencia>> result4 = transferenciaController.listar(null, null, nome);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(transferencias, result.getBody());
        Assertions.assertEquals(HttpStatus.OK, result2.getStatusCode());
        Assertions.assertEquals(transferencias, result2.getBody());
        Assertions.assertEquals(HttpStatus.OK, result3.getStatusCode());
        Assertions.assertEquals(transferencias, result3.getBody());
        Assertions.assertEquals(HttpStatus.OK, result4.getStatusCode());
        Assertions.assertEquals(transferencias, result4.getBody());
        Mockito.verify(transferenciaService, Mockito.times(1)).findAllTransferenciasByDataBetweenAndNomeOp(nome, dateTime, dateTime2);
    }
}
