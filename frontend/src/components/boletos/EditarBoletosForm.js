import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, InputGroup, InputGroupText, Label} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {request} from "../../helpers/axios_helper"; // Add this line


const EditarBoletosForm = () => {
    const emptyItem = {
        numero: '',
        valor: '',
        desconto: '',
        valorFinal: '',

        descricao: '',
        dataVencimento: '',
        dataPagamento: '',
        status: 'Nao Pago', // Defina o valor inicial como  Nao pago
        observacao: '',
        parcela: '',
        metodoPagamento: '',
        nomeCliente: '',
        cpfCnpjCliente: '',
        diasAtraso: '',
    };

    const [item, setItem] = useState(emptyItem);
    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchItem = async () => {
            if (id !== 'new') {
                request('GET', `/boletos/${id}`)
                    .then(response => setItem(response.data))
                    .catch(error => console.error('Error:', error));
            }
        };
        fetchItem();
    }, [id]);
    const handleChange = (event) => {
        const target = event.target;
        const value = target.type === 'number' ? parseFloat(target.value) : target.value;
        const name = target.name;
        setItem({...item, [name]: value});
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        await request((item.id) ? 'PUT' : 'POST', '/boletos' , item);
        navigate('/boletos');
    };

    const title = <h2>{item.id ? 'Editar Boleto' : 'Cadastrar Boleto'}</h2>;

    return <div>
        <AppNavbar/>
        <Container>
            {title}
            <Form onSubmit={handleSubmit}>
                <FormGroup>
                    <Label for="number">Número</Label>
                    <Input type="number" name="numero" id="numero" value={item.numero || ''}
                           onChange={handleChange} autoComplete="number" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="number">Dias atraso</Label>
                    <Input type="number" name="diasAtraso" id="diasAtraso" value={item.diasAtraso || ''}
                           onChange={handleChange} autoComplete="lateDays" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="amount">Valor </Label>
                    <InputGroup>
                        <InputGroupText>R$</InputGroupText>
                        <Input type="number" step="0.01" name="valor" id="valor" value={item.valor || ''}
                               onChange={handleChange} autoComplete="valor" required/>
                    </InputGroup>
                </FormGroup>
                <FormGroup>
                    <Label for="amount">Desconto </Label>
                    <InputGroup>
                        <InputGroupText>R$</InputGroupText>
                        <Input type="number" step="0.01" name="desconto" id="desconto" value={item.desconto || ''}
                               onChange={handleChange} autoComplete="desconto"/>
                    </InputGroup>
                </FormGroup>
                <FormGroup>
                    <Label for="amount">Valor final </Label>
                    <InputGroup>
                        <InputGroupText>R$</InputGroupText>
                        <Input type="number" step="0.01" name="valorFinal" id="valorFinal" value={item.valorFinal || ''}
                               onChange={handleChange} autoComplete="valorFinal" required readOnly/>
                    </InputGroup>
                </FormGroup>
                <FormGroup>
                    <Label for="observation">Nome Cliente</Label>
                    <Input type="text" name="nomeCliente" id="nomeCliente" value={item.nomeCliente || ''}
                           onChange={handleChange} autoComplete="nomeCliente" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="observation">Cpf/CNPJ Cliente</Label>
                    <Input type="text" name="cpfCnpjCliente" id="cpfCnpjCliente" value={item.cpfCnpjCliente || ''}
                           onChange={handleChange} autoComplete="cpfCnpjCliente" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="dueDate">Vencimento</Label>
                    <Input type="date" name="dataVencimento" id="dataVencimento" value={item.dataVencimento || ''}
                           onChange={handleChange} autoComplete="dataVencimento" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="paymentDate">Data Pagamento</Label>
                    <Input type="date" name="dataPagamento" id="dataPagamento" value={item.dataPagamento || ''}
                           onChange={handleChange} autoComplete="dataPagamento" required/>
                </FormGroup>
                <FormGroup>
                    <Label for="status">Situação</Label>
                    <Input type="select" name="status" id="status" value={item.status || ''}
                           onChange={handleChange} autoComplete="status" required>
                        <option value="">Selecione...</option>
                        <option value="Pago">Pago</option>
                        <option value="Nao Pago">Não Pago</option>
                    </Input>
                </FormGroup>
                <FormGroup>
                    <Label for="observation">Observação</Label>
                    <Input type="text" name="observacao" id="observacao" value={item.observacao || ''}
                           onChange={handleChange} autoComplete="observacao" required/>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Salvar</Button>{' '}
                    <Button color="secondary" tag={Link} to="/boletos">Voltar</Button>
                </FormGroup>
            </Form>
        </Container>
    </div>;

};
export default EditarBoletosForm;