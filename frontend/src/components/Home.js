import React, { Component } from 'react';
import '../assets/App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';


class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid >
                    <Button color="link"><Link to="/clientes">Clientes</Link></Button>
                    <Button color="link"><Link to="/usuarios">Usuários</Link></Button>
                    <Button color="link"><Link to="/boletos">Boletos</Link></Button>
                    <Button color="link"><Link to="/boletos/sem-cobranca">Boletos sem Cobrança</Link></Button>
                </Container>
            </div>
        );
    }
}
export default Home;
