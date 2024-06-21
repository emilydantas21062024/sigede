// ControladorClientesDto.java
package br.com.projecao.sigede.controladores;

import br.com.projecao.sigede.dto.ClienteDto;
import br.com.projecao.sigede.servicos.ClientesServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controlador para a entidade Cliente que utiliza o serviço ClientesServicoDto
 */
@RestController
@RequestMapping("/clientes")
public class ClientesController {

    private final ClientesServico clientesServico;

    /**
     * Construtor para injeção do serviço ClientesServicoDto
     *
     * @param clientesServico serviço de clientes
     */
    public ClientesController(ClientesServico clientesServico) {
        this.clientesServico = clientesServico;
    }

    /**
     * Endpoint para listar todos os clientes
     *
     * @return ResponseEntity com a lista de clientes
     */
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listar() {
        return ResponseEntity.ok(clientesServico.listar());
    }

    /**
     * Endpoint para buscar um cliente pelo id
     *
     * @param id id do cliente
     * @return ResponseEntity com o cliente encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clientesServico.buscarPorId(id));
    }

    /**
     * Endpoint para salvar um novo cliente
     *
     * @param clienteDto dados do cliente para salvar
     * @return ResponseEntity com o cliente salvo
     */
    @PostMapping
    public ResponseEntity<ClienteDto> salvar(@RequestBody ClienteDto clienteDto) {
        ClienteDto clienteSalvo = clientesServico.salvar(clienteDto);
        return ResponseEntity.created(URI.create("/clientes/" + clienteSalvo.getId())).body(clienteSalvo);
    }

    /**
     * Endpoint para atualizar um cliente existente
     *
     * @param clienteDto dados do cliente para atualizar
     * @return ResponseEntity com o cliente atualizado
     */
    @PutMapping
    public ResponseEntity<ClienteDto> atualizar(@RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clientesServico.atualizar(clienteDto));
    }

    /**
     * Endpoint para excluir um cliente pelo id
     *
     * @param id id do cliente para excluir
     * @return ResponseEntity sem conteúdo após a exclusão bem-sucedida
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clientesServico.excluir(id);
        return ResponseEntity.noContent().build();
    }
}