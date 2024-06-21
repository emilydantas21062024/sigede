package br.com.projecao.sigede.servicos;

import br.com.projecao.sigede.dto.CobrancaDto;
import br.com.projecao.sigede.mapper.MapeadorCobrancas;
import br.com.projecao.sigede.modelos.Boleto;
import br.com.projecao.sigede.modelos.Cobranca;
import br.com.projecao.sigede.repositorios.BoletosRepositorio;
import br.com.projecao.sigede.repositorios.CobrancasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CobrancasServico {

    private final CobrancasRepositorio cobrancasRepositorio;

    private final MapeadorCobrancas mapeadorCobrancas;

    private final BoletosRepositorio boletosRepositorio;

    @Autowired
    public CobrancasServico(CobrancasRepositorio cobrancasRepositorio, MapeadorCobrancas mapeadorCobrancas, BoletosRepositorio boletosRepositorio) {
        this.cobrancasRepositorio = cobrancasRepositorio;
        this.mapeadorCobrancas = mapeadorCobrancas;
        this.boletosRepositorio = boletosRepositorio;
    }

    public List<CobrancaDto> list() {
        List<Cobranca> cobrancas = cobrancasRepositorio.findAll();
        return cobrancas.stream()
                .map(mapeadorCobrancas::toChargeDto)
                .collect(Collectors.toList());
    }

    public List<CobrancaDto> recuperarPorDescricao(String description) {
        List<Cobranca> byDescriptionContaining = cobrancasRepositorio.findByDescricaoContaining(description);
        return byDescriptionContaining.stream()
                .map(mapeadorCobrancas::toChargeDto)
                .collect(Collectors.toList());
    }

    public CobrancaDto recuperar(Long id) {
        return cobrancasRepositorio.findById(id)
                .map(mapeadorCobrancas::toChargeDto)
                .orElse(null);
    }

    public CobrancaDto salvar(CobrancaDto cobrancaDto) {
        Cobranca cobranca = mapeadorCobrancas.toCharge(cobrancaDto);
        Cobranca savedCobranca = cobrancasRepositorio.save(cobranca);
        return mapeadorCobrancas.toChargeDto(savedCobranca);
    }

    public void deletar(Long id) {
        cobrancasRepositorio.deleteById(id);
    }

    public List<CobrancaDto> recuperarPorBoleto(Long debtId) {
        List<Cobranca> byDebtId = cobrancasRepositorio.findByBoleto_Id(debtId);
        return byDebtId.stream()
                .map(mapeadorCobrancas::toChargeDto)
                .collect(Collectors.toList());
    }
    public CobrancaDto adicionarCobranca(Long debtId, CobrancaDto charge) {
        Boleto boleto = boletosRepositorio.findById(debtId).orElseThrow(() -> new RuntimeException("Boleto nao encontrado"));
        Cobranca cobrancaEntity = mapeadorCobrancas.toCharge(charge);
        cobrancaEntity.setBoleto(boleto);
        return mapeadorCobrancas.toChargeDto(cobrancasRepositorio.save(cobrancaEntity));
    }

}