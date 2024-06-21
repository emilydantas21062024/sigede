import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {Link} from 'react-router-dom';
import {request} from "../../helpers/axios_helper";

class ListarBoletosForm extends Component {

    componentDidMount() {
        request('GET', '/boletos')
            .then(response => this.setState({debts: response.data}))
            .catch(error => console.error('Error:', error));
    }
    constructor(props) {
        super(props);
        this.state = {debts: []};
        this.remove = this.remove.bind(this);
    }
    async remove(id) {
        request('delete', `/boletos/${id}`).then(() => {
            let updatedDebts = [...this.state.debts].filter(i => i.id !== id);
            this.setState({debts: updatedDebts});
        });
    }

    render() {
        const {debts, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }
        const debtsList = debts.map(debt => {
            return <tr key={debt.id}>
                <td style={{whiteSpace: 'nowrap'}}>{debt.numero}</td>
                <td>{debt.nomeCliente}</td>
                <td>{debt.cpfCnpjCliente}</td>
                <td>{debt.dataVencimento}</td>
                <td>{parseFloat(debt.valor).toFixed(2)}</td>
                <td>{parseFloat(debt.juros).toFixed(2)}</td>
                <td>{parseFloat(debt.multa).toFixed(2)}</td>
                <td>{parseFloat(debt.valorFinal).toFixed(2)}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/boletos/" + debt.id}>Editar</Button>
                        <Button size="sm" color="secondary" tag={Link}
                                to={`/boletos/${debt.id}/cobrancas`}>Cobranca</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h3>Boletos</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="10%">Número</th>
                            <th width="20%">Nome Cliente</th>
                            <th width="20%">CPF/CNPJ Cliente</th>
                            <th width="10%">Vencimento</th>
                            <th width="10%">R$ Valor</th>
                            <th width="10%">R$ Juros (1% dia)</th>
                            <th width="10%">R$ Multa (7%)</th>
                            <th width="10%">R$ Final</th>
                        </tr>
                        </thead>
                        <tbody>
                        {debtsList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/boletos/new">Adicionar</Button>
                    </div>
                </Container>
            </div>
        );
    }
}
export default ListarBoletosForm;