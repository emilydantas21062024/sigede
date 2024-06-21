package br.com.projecao.sigede.controladores;

import br.com.projecao.sigede.dto.CobrancaDto;
import br.com.projecao.sigede.servicos.CobrancasServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controlador de cobranças
 */
@RestController
@RequestMapping("/cobrancas")
public class CobrancasController {

    /**
     * Serviço de cobranças
     */
    private final CobrancasServico cobrancasServico;

    /**
     * Construtor
     *
     * @param cobrancasServico - Serviço de cobranças
     */
    public CobrancasController(CobrancasServico cobrancasServico) {
        this.cobrancasServico = cobrancasServico;
    }

    @GetMapping
    public ResponseEntity<List<CobrancaDto>> listar() {
        return ResponseEntity.ok(cobrancasServico.list());
    }

    @GetMapping("/descricoes/{descricoes}")
    public ResponseEntity<List<CobrancaDto>> recuperarPorDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(cobrancasServico.recuperarPorDescricao(descricao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CobrancaDto> recuperar(@PathVariable Long id) {
        return ResponseEntity.ok(cobrancasServico.recuperar(id));
    }

    @GetMapping("/boletos/{idBoleto}")
    public ResponseEntity<List<CobrancaDto>> recuperarPorBoleto(@PathVariable Long idBoleto) {
        return ResponseEntity.ok(cobrancasServico.recuperarPorBoleto(idBoleto));
    }

    @PostMapping
    public ResponseEntity<CobrancaDto> salvar(@RequestBody CobrancaDto cobranca) {
        CobrancaDto cobrancaDto = cobrancasServico.salvar(cobranca);
        return ResponseEntity.created(URI.create("/cobrancas/" + cobrancaDto.getId())).body(cobrancaDto);
    }

    @PostMapping("/boletos/{idBoleto}")
    public ResponseEntity<CobrancaDto> adicionarCobranca(@PathVariable Long idBoleto, @RequestBody CobrancaDto cobranca) {
        CobrancaDto cobrancaDto = cobrancasServico.adicionarCobranca(idBoleto, cobranca);
        return ResponseEntity.created(URI.create("/cobrancas/" + cobrancaDto.getId())).body(cobrancaDto);
    }

    @PutMapping
    public ResponseEntity<CobrancaDto> atualizar(@RequestBody CobrancaDto cobranca) {
        return ResponseEntity.ok(cobrancasServico.salvar(cobranca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cobrancasServico.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
