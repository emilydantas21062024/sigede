import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {Link} from 'react-router-dom';

import {request} from "../../helpers/axios_helper";

/**
 * Listar clientes
 */
class ListarClientesForm extends Component {

    constructor(props) {
        super(props);
        this.state = {customers: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        request('GET', '/clientes')
            .then(response => this.setState({customers: response.data}))
            .catch(error => console.error('Error:', error));
    }

    async remove(id) {
        await fetch(`/customers/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedCustomers = [...this.state.customers].filter(i => i.id !== id);
            this.setState({customers: updatedCustomers});
        });
    }

    render() {
        const {customers, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const customerList = customers.map(customer => {
            return <tr key={customer.id}>
                <td style={{whiteSpace: 'nowrap'}}>{customer.nome}</td>
                <td>{customer.email}</td>
                <td>{customer.telefone}</td>
                <td>{customer.cpfCnpj}</td>
                <td>{customer.cep}</td>
                <td>{customer.cidade}</td>
                <td>{customer.endereco}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/clientes/" + customer.id}>Edit</Button>
                        {/*<Button size="sm" color="danger" onClick={() => this.remove(customer.id)}>Delete</Button>*/}
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h3>Clientes</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Nome</th>
                            <th width="10%">Email</th>
                            <th width="10%">Telefone</th>
                            <th width="10%">Cpf</th>
                            <th width="10%">Cep</th>
                            <th width="10%">Cidade</th>
                            <th width="20%">Endereco</th>
                        </tr>
                        </thead>
                        <tbody>
                        {customerList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/clientes/new">Adicionar</Button>
                    </div>
                </Container>

            </div>
        );
    }
}

export default ListarClientesForm;  // This is the end of the file after the last line of code.