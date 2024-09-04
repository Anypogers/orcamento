package enzo.com.br.orcamento.controllers;

import enzo.com.br.orcamento.model.Lancamento;
import enzo.com.br.orcamento.repositories.LancamentoRepository;
import enzo.com.br.orcamento.services.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService;
    @Autowired
    private LancamentoRepository lancamentoRepository;

    @GetMapping
    public List<Lancamento> listarTodasLancamento() {
        return lancamentoRepository.findAll(Sort.by("datalancamento").ascending());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long id){
        Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
        return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<Lancamento> inserir(@RequestBody Lancamento lancamento){
        Lancamento lancamentoSalva = lancamentoService.salvar(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
    }
}
