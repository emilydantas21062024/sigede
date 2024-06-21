import React, {useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {request, setAuthHeader} from '../../helpers/axios_helper';

const LoginForm = () => {
    const emptyItem = {
        login: '',
        senha: ''
    };

    const [item, setItem] = useState(emptyItem);
    const {id} = useParams();
    const navigate = useNavigate();

    const handleChange = (event) => {
        const target = event.target;
        const name = target.name;
        const value = target.value;
        setItem({...item, [name]: value});
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const response = await fetch('/login' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        if (response.ok) {
            const data = await response.json();
            setAuthHeader(data.token);
            navigate('/home');
        } else {
            const error = await response.json();
            console.log(response,error);
            alert("Usuário ou senha inválidos");
        }
    };


    const title = <h2>{'Login'}</h2>;

    return <div>
        <AppNavbar/>
        <Container>
            {title}
            <Form onSubmit={handleSubmit}>
                <FormGroup>
                    <Label for="email">Login</Label>
                    <Input type="text" name="login" id="login" value={item.login || ''}
                           onChange={handleChange} autoComplete="login"/>
                </FormGroup>
                <FormGroup>
                    <Label for="password">Senha</Label>
                    <Input type="password" name="senha" id="senha" value={item.senha || ''}
                           onChange={handleChange} autoComplete="new-password"/>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Login</Button>{' '}
                    <Button color="secondary" tag={Link} to="/">Cancel</Button>
                </FormGroup>
            </Form>
        </Container>
    </div>
};

export default LoginForm;