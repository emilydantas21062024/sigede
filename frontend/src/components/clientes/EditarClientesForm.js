import React, {useState, useEffect} from 'react';
import {Link, useParams, useNavigate} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {request, updateItemValue} from "../../helpers/axios_helper";

/**
 *
 * @returns {Element}
 * @constructor
 */
const EditarClientesForm = () => {
    const emptyItem = {
        nome: '',
        email: '',
        endereco: '',
        telefone: '',
        estado: '',
        cidade: '',
        dataNascimento: '',
        cpfCnpj: ''
    };

    const [item, setItem] = useState(emptyItem);
    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchItem = async () => {
            if (id !== 'new') {
                request('GET', `/clientes/${id}`)
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
        // await request((item.id) ? 'PUT' : 'POST', '/clientes' + (item.id ? '/' + item.id : ''), item);
        await request((item.id) ? 'PUT' : 'POST', '/clientes', item);
        navigate('/clientes');
    };

    const title = <h2>{item.id ? 'Editar Ciente' : 'Adicionar Cliente'}</h2>;
    return <div>
        <AppNavbar/>
        <Container>
            {title}
            <Form onSubmit={handleSubmit}>
                <FormGroup>
                    <Label for="nome">Nome</Label>
                    <Input type="text" name="nome" id="nome" value={item.nome || ''}
                           onChange={handleChange} autoComplete="name"/>
                </FormGroup>
                <FormGroup>
                    <Label for="email">Email</Label>
                    <Input type="email" name="email" id="email" value={item.email || ''}
                           onChange={handleChange} autoComplete="email"/>
                </FormGroup>
                <FormGroup>
                    <Label for="cpf">Cpf</Label>
                    <Input type="number" name="cpfCnpj" id="cpfCnpj" value={item.cpfCnpj || ''}
                           onChange={handleChange} autoComplete="cpfCnpj"/>
                </FormGroup>
                <FormGroup>
                    <Label for="dataNascimento">Data Nascimento</Label>
                    <Input type="date" name="dataNascimento" id="dataNascimento" value={item.dataNascimento || ''}
                           onChange={handleChange} autoComplete="birthDate"/>
                </FormGroup>
                <FormGroup>
                    <Label for="endereco">Endereco</Label>
                    <Input type="text" name="endereco" id="endereco" value={item.endereco || ''}
                           onChange={handleChange} autoComplete="endereco"/>
                </FormGroup>
                <FormGroup>
                    <Label for="telefone">Telefone</Label>
                    <Input type="number" name="telefone" id="telefone" value={item.telefone || ''}
                           onChange={handleChange} autoComplete="telefone"/>
                </FormGroup>
                <FormGroup>
                    <Label for="cep">CEP</Label>
                    <Input type="number" name="cep" id="cep" value={item.cep || ''}
                           onChange={handleChange} autoComplete="cep"/>
                </FormGroup>
                <FormGroup>
                    <Label for="email">Estado</Label>
                    <Input type="text" name="estado" id="estado" value={item.estado || ''}
                           onChange={handleChange} autoComplete="state"/>
                </FormGroup>
                <FormGroup>
                    <Label for="email">Cidade</Label>
                    <Input type="text" name="cidade" id="cidade" value={item.cidade || ''}
                           onChange={handleChange} autoComplete="city"/>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary" tag={Link} to="/clientes">Voltar</Button>
                </FormGroup>
            </Form>
        </Container>
    </div>
};

export default EditarClientesForm;