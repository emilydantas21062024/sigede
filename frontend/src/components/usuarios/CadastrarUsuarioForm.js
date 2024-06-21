import React, {useState, useEffect} from 'react';
import {Link, useParams, useNavigate} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {request, setAuthHeader, updateItemValue} from '../../helpers/axios_helper';

const CadastrarUsuarioForm = () => {
    const emptyItem = {
        primeiroNome: '',
        ultimoNome: '',
        login: '',
        senha: '',
        papel: ''
    };

    const [item, setItem] = useState(emptyItem);
    const {id} = useParams();
    const navigate = useNavigate();

    const handleChange = (event) => {
        updateItemValue(event, item, setItem);
    };
    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await request('post', '/registrar', JSON.stringify(item));
            alert("Usuário cadastrado com sucesso");
            setAuthHeader(response.data.token);
            navigate('/');
        } catch (error) {
            if (error.response) {
                // The request was made and the serve    private final UsuariosServico usuariosServico;r responded with a status code
                // that falls out of the range of 2xx
                console.log(error.response.data);
                console.log(error.response.status);
                console.log(error.response.headers);
            } else if (error.request) {
                // The request was made but no response was received
                console.log(error.request);
            } else {
                // Something happened in setting up the request that triggered an Error
                console.log('Error', error.message);
            }
            console.log(error.config);
        }
    };

    const title = <h2>{item.id ? 'Editar Usuário' : 'Cadastrar Usuário'}</h2>;

    return <div>
        <AppNavbar/>
        <Container>
            {title}
            <Form onSubmit={handleSubmit}>
                <FormGroup>
                    <Label for="primeiroNome">Nome</Label>
                    <Input type="text" name="primeiroNome" id="primeiroNome" value={item.primeiroNome || ''}
                           onChange={handleChange} autoComplete="firstName" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="ultimoNome">Sobrenome</Label>
                    <Input type="text" name="ultimoNome" id="ultimoNome" value={item.ultimoNome || ''}
                           onChange={handleChange} autoComplete="lastName" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="login">Login</Label>
                    <Input type="text" name="login" id="login" value={item.login || ''}
                           onChange={handleChange} autoComplete="login" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="senha">Senha</Label>
                    <Input type="password" name="senha" id="senha" value={item.senha || ''}
                           onChange={handleChange} autoComplete="new-password" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="papel">Papel</Label>
                    <Input type="select" name="papel" id="papel" value={item.papel || ''}
                           onChange={handleChange} required>
                        <option value="GERENTE">Gerente</option>
                        <option value="ASSISTENTE">Assistente</option>
                    </Input>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary" tag={Link} to="/">Voltar</Button>
                </FormGroup>
            </Form>
        </Container>
    </div>
};

export default CadastrarUsuarioForm;