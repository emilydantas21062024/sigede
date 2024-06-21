import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {request, updateItemValue} from "../../helpers/axios_helper";

const EditarCobrancasForm = () => {
    const emptyItem = {
        descricao: '',
        data: '',
    };

    const [item, setItem] = useState(emptyItem);
    const {id, debtid} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchItem = async () => {
            if (id !== 'new') {
                request('GET', `/cobrancas/${id}`)
                    .then(response => setItem(response.data))
                    .catch(error => console.error('Error:', error));
            }
        };
        fetchItem();
    }, [id, debtid]);

    const handleChange = (event) => {
        updateItemValue(event, item, setItem);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        await request((item.id) ? 'PUT' : 'POST', '/cobrancas/boletos/' +debtid, item);
        navigate(`/boletos/${debtid}/cobrancas`);
    };

    const title = <h2>{item.id ? 'Editar Cobrança' : 'Adicionar Cobrança'}</h2>;
    return <div>
        <AppNavbar/>
        <Container>
            {title}
            <Form onSubmit={handleSubmit}>
                <FormGroup>
                    <Label for="name">Descrição</Label>
                    <Input type="textarea" name="descricao" id="descricao" value={item.descricao || ''}
                           onChange={handleChange} autoComplete="descricao"/>
                </FormGroup>
                <FormGroup>
                    <Label for="date">Data</Label>
                    <Input type="date" name="data" id="data" value={item.data || ''}
                           onChange={handleChange} autoComplete="data" />
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary" tag={Link}  to={`/boletos/${debtid}/cobrancas`}>Voltar</Button>
                </FormGroup>
            </Form>
        </Container>
    </div>
};

export default EditarCobrancasForm;