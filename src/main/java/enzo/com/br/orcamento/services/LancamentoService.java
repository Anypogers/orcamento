package enzo.com.br.orcamento.services;

import enzo.com.br.orcamento.model.Lancamento;
import enzo.com.br.orcamento.repositories.LancamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento){
        return lancamentoRepository.save(lancamento);
    }

    public Lancamento atualizar(Long id, Lancamento lancamento) {
        Lancamento lancamentoSalva = buscarLancamentoExistente(id);
        BeanUtils.copyProperties(lancamento, lancamentoSalva, "id");
        return lancamentoRepository.save(lancamentoSalva);
    }
    private Lancamento buscarLancamentoExistente(Long id){
        Optional<Lancamento> lancamentoSalva = lancamentoRepository.findById(id);
        if (!lancamentoSalva.isPresent()){
            throw new IllegalArgumentException();
        }
        return lancamentoSalva.get();
    }
}
