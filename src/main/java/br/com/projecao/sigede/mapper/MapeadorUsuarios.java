package br.com.projecao.sigede.mapper;

import br.com.projecao.sigede.dto.CadastroDto;
import br.com.projecao.sigede.dto.UsuarioDto;
import br.com.projecao.sigede.modelos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapeadorUsuarios {

    UsuarioDto toUsuarioDto(Usuario usuario);

    @Mapping(target = "senha", ignore = true)
    Usuario cadastroDtoToUsuario(CadastroDto cadastroDto);

}
