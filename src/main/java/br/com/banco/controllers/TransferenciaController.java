package br.com.banco.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.models.Transferencia;
import br.com.banco.services.TransferenciaService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {
    private TransferenciaService transferenciaService;

    @GetMapping("/listar/search/")
    public ResponseEntity<List<Transferencia>> listar(
            @RequestParam(value = "dateTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime,
            @RequestParam(value = "nome", required = false) String nome) {
        List<Transferencia> transferencias;

        if (dateTime != null && nome != null) {
            transferencias = transferenciaService.findAllTransferenciaByContaNomeResponsavelAndData(nome, dateTime);
        } else if (dateTime != null) {
            transferencias = transferenciaService.findAllTransferenciaByData(dateTime);
        } else if (nome != null) {
            transferencias = transferenciaService.findAllTransferenciaByContaNomeResponsavel(nome);
        } else {
            transferencias = transferenciaService.findAllTransferencias();
        }

        return ResponseEntity.ok(transferencias);
    }
}
