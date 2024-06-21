// ClientesServicoDto.java
package br.com.projecao.sigede.servicos;

import br.com.projecao.sigede.dto.ClienteDto;
import br.com.projecao.sigede.mapper.MapeadorClientes;
import br.com.projecao.sigede.modelos.Cliente;
import br.com.projecao.sigede.repositorios.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Gerencia as regras de negócio dos clientes
 */
@Service
public class ClientesServico {

    private final ClientesRepositorio repositorio;
    private final MapeadorClientes mapeadorClientes;

    /**
     * Construtor
     *
     * @param repositorio - Repositório de clientes
     * @param mapeadorClientes - Mapeador de clientes
     */
    @Autowired
    public ClientesServico(ClientesRepositorio repositorio, MapeadorClientes mapeadorClientes) {
        this.repositorio = repositorio;
        this.mapeadorClientes = mapeadorClientes;
    }

    /**
     * Lista todos os clientes
     *
     * @return List<ClienteDto> - Lista de clientes
     */
    public List<ClienteDto> listar() {
        return repositorio.findAll().stream()
                .map(mapeadorClientes::toClienteDto)
                .collect(Collectors.toList());
    }

    /**
     * Buscar um cliente pelo id
     *
     * @param id
     * @return ClienteDto - Cliente com o id informado
     */
    public ClienteDto buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(mapeadorClientes::toClienteDto)
                .orElse(null);
    }

    /**
     * Salvar um cliente
     *
     * @param clienteDto
     * @return ClienteDto - Cliente salvo
     */
    public ClienteDto salvar(ClienteDto clienteDto) {
        Cliente cliente = mapeadorClientes.toCliente(clienteDto);
        Cliente clienteSalvo = repositorio.save(cliente);
        return mapeadorClientes.toClienteDto(clienteSalvo);
    }

    /**
     * Atualiza um cliente
     *
     * @param clienteDto
     * @return ClienteDto - Cliente atualizado
     */
    public ClienteDto atualizar(ClienteDto clienteDto) {
        Cliente cliente = mapeadorClientes.toCliente(clienteDto);
        Cliente clienteAtualizado = repositorio.save(cliente);
        return mapeadorClientes.toClienteDto(clienteAtualizado);
    }

    /**
     * Exclui um cliente
     *
     * @param id
     */
    public void excluir(Long id) {
        repositorio.deleteById(id);
    }
}