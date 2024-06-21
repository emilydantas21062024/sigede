import React, {useState, useEffect} from 'react';
import {Link, useParams, useNavigate} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {request, updateItemValue} from "../../helpers/axios_helper";

const EditarUsuariosForm = () => {
    const emptyItem = {
        primeirNome: '',
        ultimoNome: '',
        login: '',
        papel: '',
    };

    const [item, setItem] = useState(emptyItem);
    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchItem = async () => {
            if (id !== 'new') {
                request('GET', `/usuarios/${id}`)
                    .then(response => setItem(response.data))
                    .catch(error => console.error('Error:', error));
            }
        };
        fetchItem();
    }, [id]);

    const handleChange = (event) => {
        updateItemValue(event, item, setItem);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        // request((item.id) ? 'PUT' : 'POST', '/usuarios' + (item.id ? '/' + item.id : ''), JSON.stringify(item));
        request((item.id) ? 'PUT' : 'POST', '/usuarios' , JSON.stringify(item));

        navigate('/usuarios');
    };

    const title = <h2>{item.id ? 'Editar Usuário' : 'Adicionar Usuário'}</h2>;

    return <div>
        <AppNavbar/>
        <Container>
            {title}
            <Form onSubmit={handleSubmit}>
                <FormGroup>
                    <Label for="name">Nome</Label>
                    <Input type="text" name="primeiroNome" id="primeiroNome" value={item.primeiroNome || ''}
                           onChange={handleChange} autoComplete="primeiroNome" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="email">Sobrenome</Label>
                    <Input type="text" name="ultimoNome" id="ultimoNome" value={item.ultimoNome || ''}
                           onChange={handleChange} autoComplete="ultimoNome" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="email">Login</Label>
                    <Input type="email" name="login" id="login" value={item.login || ''}
                           onChange={handleChange} autoComplete="login" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="role">Papel</Label>
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

export default EditarUsuariosForm;