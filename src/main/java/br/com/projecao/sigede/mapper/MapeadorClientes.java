package br.com.projecao.sigede.mapper;

import br.com.projecao.sigede.dto.ClienteDto;
import br.com.projecao.sigede.modelos.Cliente;
import org.mapstruct.Mapper;
/**
 * Mapeador de clientes
 */
@Mapper(componentModel = "spring")
public interface MapeadorClientes {

/**
     * Converte um cliente para um DTO de cliente.
     *
     * @param cliente Cliente a ser convertido.
     * @return DTO de cliente.
     */
    ClienteDto toClienteDto(Cliente cliente);

    /**
     * Converte um DTO de cliente para um cliente.
     *
     * @param clienteDto DTO de cliente a ser convertido.
     * @return Cliente.
     */
    Cliente toCliente(ClienteDto clienteDto);
}
