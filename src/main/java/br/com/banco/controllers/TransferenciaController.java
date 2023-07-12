package br.com.banco.controllers;

import java.time.LocalDate;
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
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d1,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d2,
            @RequestParam(value = "nome", required = false) String nome) {
        List<Transferencia> transferencias;

        if ((d1 != null || d2 != null) && nome != null) {
            transferencias = transferenciaService.findAllTransferenciaByContaNomeResponsavelAndData(nome,
                    (d1 == null) ? d2 : d1);
        } else if (d1 != null && d2 != null) {
            transferencias = transferenciaService.findAllTransferenciaByDataBetween(d1, d2);
        } else if (nome != null) {
            transferencias = transferenciaService.findAllTransferenciaByContaNomeResponsavel(nome);
        } else {
            transferencias = transferenciaService.findAllTransferencias();
        }

        return ResponseEntity.ok(transferencias);
    }
}
