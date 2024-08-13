package enzo.com.br.orcamento.services;

import enzo.com.br.orcamento.model.Municipio;
import enzo.com.br.orcamento.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipioService {
    @Autowired
    private MunicipioRepository municipioRepository;

    public Municipio salvar(Municipio municipio){
        return municipioRepository.save(municipio);
    }
}
