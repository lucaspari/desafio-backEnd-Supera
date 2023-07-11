package br.com.banco.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/listar")
    public ResponseEntity<List<Transferencia>> listar() {
        return ResponseEntity.ok(transferenciaService.findAllTransferencias());
    }
}
