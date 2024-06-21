package br.com.projecao.sigede.mapper;

import br.com.projecao.sigede.dto.CobrancaDto;
import br.com.projecao.sigede.modelos.Cobranca;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapeadorCobrancas {

    CobrancaDto toChargeDto(Cobranca cobranca);

    Cobranca toCharge(CobrancaDto cobrancaDto);
}