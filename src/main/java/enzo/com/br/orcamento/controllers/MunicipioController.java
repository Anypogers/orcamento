package enzo.com.br.orcamento.controllers;

import enzo.com.br.orcamento.model.Municipio;
import enzo.com.br.orcamento.repositories.MunicipioRepository;
import enzo.com.br.orcamento.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {
    @Autowired
    private MunicipioService municipioService;
    @Autowired
    private MunicipioRepository municipioRepository;

    @GetMapping
    public List<Municipio> listarTodasMunicipio() {
        return municipioRepository.findAll(Sort.by("nome").ascending());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Municipio> buscarPeloCodigo(@PathVariable int id){
        Optional<Municipio> municipio = municipioRepository.findById(id);
        return municipio.isPresent() ? ResponseEntity.ok(municipio.get()) : ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<Municipio> inserir(@RequestBody Municipio municipio){
        Municipio municipioSalva = municipioService.salvar(municipio);
        return ResponseEntity.status(HttpStatus.CREATED).body(municipioSalva);
    }
}
