import React, {Component} from "react";
import {Button, ButtonGroup} from "reactstrap";
import {Link} from "react-router-dom";

class WelcomeContent extends Component {
    render() {
        return (
            <div className="row justify-content-md-center">
                <div className="jumbotron jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-4">Sigede</h1>
                        <p className="lead">Bem-Vindo! Faça Login para ter acesso as funcionalidades do Sistema</p>
                        <ButtonGroup>
                            <Button tag={Link} to="/login" color="primary">Já tenho uma conta</Button>
                            <Button tag={Link} to="/registrar" color="secondary">Criar uma conta</Button>
                        </ButtonGroup>
                    </div>
                    <div className="container">
                    </div>
                </div>
            </div>
        );
    }
}

export default WelcomeContent;