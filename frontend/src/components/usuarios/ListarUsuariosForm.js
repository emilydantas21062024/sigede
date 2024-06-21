import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {Link} from 'react-router-dom';
import {request} from "../../helpers/axios_helper";

class ListarUsuariosForm extends Component {

    constructor(props) {
        super(props);
        this.state = {users: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        request('GET', '/usuarios')
            .then(response => this.setState({users: response.data}))
            .catch(error => console.error('Error:', error));
    }

    async remove(id) {
        request('delete', `/usuarios/${id}`)
            .then(() => {
                let updatedUsers = [...this.state.users].filter(i => i.id !== id);
                this.setState({users: updatedUsers});
            });
    }

    render() {
        const {users, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const usersList = users.map(user => {
            return <tr key={user.id}>
                <td style={{whiteSpace: 'nowrap'}}>{user.primeiroNome}</td>
                <td>{user.ultimoNome}</td>
                <td>{user.login}</td>
                <td>{user.papel}</td>
                <td>
                    <ButtonGroup>
                        {/*<Button size="sm" color="primary" tag={Link} to={"/users/" + user.id}>Edit</Button>*/}
                        <Button size="sm" color="danger" onClick={() => this.remove(user.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h3>Usuários</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Nome</th>
                            <th width="30%">Sobrenome</th>
                            <th width="20%">Login</th>
                            <th width="30%">Papel</th>
                        </tr>
                        </thead>
                        <tbody>
                        {usersList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        {/*<Button color="success" tag={Link} to="/usuarios/new">Adicionar</Button>*/}
                    </div>
                </Container>

            </div>
        );
    }
}

export default ListarUsuariosForm;  // This is the end of the file after the last line of code.