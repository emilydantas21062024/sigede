package br.com.projecao.sigede.controladores;

import br.com.projecao.sigede.modelos.Boleto;
import br.com.projecao.sigede.servicos.BoletosServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de dívidas
 *
 * @version 1.0
 * @see BoletosServico
 * @see Boleto
 * @since 1.0
 */
@RestController
@RequestMapping("/boletos")
public class BoletosController {

    private final BoletosServico boletosServico;

    /**
     * Construtor
     *
     * @param boletosServico - Serviço de dívidas
     */
    @Autowired
    public BoletosController(BoletosServico boletosServico) {
        this.boletosServico = boletosServico;
    }

    /**
     * Incluir um débito
     *
     * @param boleto - Débito a ser salvo
     * @return Boleto - Débito salvo
     */
    @PostMapping
    public Boleto save(@RequestBody Boleto boleto) {
        return boletosServico.salvar(boleto);
    }

    /**
     * Atualizar um débito
     *
     * @param boleto - Débito a ser atualizado
     * @return Boleto - Débito atualizado
     */
    @PutMapping
    public Boleto update(@RequestBody Boleto boleto) {
        return boletosServico.atualizar(boleto);
    }

    /**
     * Excluir um débito
     *
     * @param id - Id do débito a ser excluído
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boletosServico.deletar(id);
    }

    /**
     * Recuperar um débito pelo Id
     *
     * @param id - Id do débito
     * @return Boleto - Débito recuperado
     */
    @GetMapping("/{id}")
    public Boleto findById(@PathVariable Long id) {
        return boletosServico.buscarPorId(id);
    }

    /**
     * Listar todos os débitos
     *
     * @return List<Boleto> - Lista de débitos
     */
    @GetMapping
    public List<Boleto> list() {
        return boletosServico.listar();
    }

    /**
     * Listar todos os boletos sem cobranca vinculada
     *
     * @return List<Boleto> - Lista de débitos
     */
    @GetMapping("/sem-cobranca")
    public List<Boleto> listarBoletosSemCobranca() {
        return boletosServico.listarBoletosSemCobranca();
    }


}
