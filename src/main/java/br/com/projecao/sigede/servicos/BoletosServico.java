// BoletosServico.java
package br.com.projecao.sigede.servicos;

import br.com.projecao.sigede.modelos.Boleto;
import br.com.projecao.sigede.repositorios.BoletosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gerencia as regras de negócio dos boletos
 */
@Service
public class BoletosServico {

    /**
     * Valor percentual da multa
     */
    @Value("${sigede.multa}")
    private Double multa;

    /**
     * Valor percentual do juros
     */
    @Value("${sigede.juros}")
    private Double juros;
    private final BoletosRepositorio repositorio;

    @Autowired
    public BoletosServico(BoletosRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Salva um boleto
     *
     * @param boleto
     * @return Boleto - Boleto salvo
     */
    public Boleto salvar(Boleto boleto) {
        calcularValorFinal(boleto);
        return repositorio.save(boleto);
    }

    /**
     * Atualiza um boleto
     *
     * @param boleto
     * @return Boleto - Boleto atualizado
     */
    public Boleto atualizar(Boleto boleto) {
        calcularValorFinal(boleto);
        return repositorio.save(boleto);
    }

    /**
     * Deleta um boleto
     *
     * @param id
     */
    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    /**
     * Busca um boleto pelo id
     *
     * @param id
     * @return Boleto - Boleto encontrado
     */
    public Boleto buscarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    /**
     * Lista todos os boletos
     *
     * @return List<Boleto> - Lista de boletos
     */
    public List<Boleto> listar() {
        return repositorio.findAll();
    }


    /**
     * Calcula o valor de juros e multa de um boleto antes de salvar
     *
     * @param boleto
     */
    public void calcularValorFinal(Boleto boleto) {
        Double valorMulta = boleto.getValor() * (this.multa / 100);
        Double valorJuros = (boleto.getValor() * (this.juros / 100)) * boleto.getDiasAtraso();
        Double valorFinal = (boleto.getValor() + valorJuros + valorMulta) - boleto.getDesconto();

        boleto.setMulta(valorMulta);
        boleto.setJuros(valorJuros);
        boleto.setValorFinal(valorFinal);
    }

    /**
     * Lista todos os boletos sem cobrança vinculada
     *
     * @return List<Boleto> - Lista de boletos
     */
    public List<Boleto> listarBoletosSemCobranca() {
        return repositorio.findAllByCobrancasIsNull();
    }
}
