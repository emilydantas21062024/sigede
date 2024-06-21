import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import { ToastContainer, toast } from 'react-custom-alert';
import 'react-custom-alert/dist/index.css'; // import css file from root.

import {
    Button,
    ButtonGroup,
    Container,
    Form,
    FormGroup,
    Input,
    InputGroup,
    InputGroupText,
    Label,
    Table
} from 'reactstrap';
import AppNavbar from '../AppNavbar';
import {request} from "../../helpers/axios_helper"; // Add this line


const ListarCobrancasForm = () => {
    const emptyItem = {
        number: '',
        amount: '',
        discount: '',
        finalValue: '',
        description: '',
        dueDate: '',
        paymentDate: '',
        status: 'Nao Pago', // Defina o valor inicial como  Nao pago
        observation: '',
        installment: '',
        paymentMethod: '',
        customerName: '',
        customerCpfCnpj: '',
        lateDays: '',
        customerId: '',
    };

    const [chargesList, setChargesList] = useState([]);
    const [item, setItem] = useState(emptyItem);
    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCharges = async () => {
            request('GET', `cobrancas/boletos/${id}`)
                .then(response => {
                    setChargesList(response.data)
                    if (response.data.length > 2) {
                        alert("Boleto deve ser protestado!", "warning");
                    }
                })
                .catch(error => console.error('Error:', error));
        };
        fetchCharges();
    }, [id]);

    // const handleChange = (event) => {
    //     const target = event.target;
    //     const value = target.type === 'number' ? parseFloat(target.value) : target.value;
    //     const name = target.name;
    //     setItem({...item, [name]: value});
    // };

    // const handleSubmit = async (event) => {
    //     event.preventDefault();
    //     await request((item.id) ? 'PUT' : 'POST', '/debts' + (item.id ? '/' + item.id : ''), item);
    //     navigate('/debts');
    // };


    return <div>
        <AppNavbar/>
        <Container fluid>
            <h3>Cobranças</h3>
            <Table className="mt-4">
                <thead>
                <tr>
                    <th width="80%">Descrição</th>
                    <th width="20%">Data</th>
                </tr>
                </thead>
                <tbody>
                {chargesList.map(charge => {
                    return <tr key={charge.id}>
                        <td>{charge.descricao}</td>
                        <td>{charge.data}</td>
                        <td>
                            {/*<ButtonGroup>*/}
                            {/*    <Button size="sm" color="primary" tag={Link}*/}
                            {/*            to={`/debts/${id}/charges/${charge.id}`}>Editar</Button>*/}
                            {/*</ButtonGroup>*/}
                        </td>
                    </tr>
                })}
                </tbody>
            </Table>
            <div className="float-right">

                <Button color="success" tag={Link} to={"/boletos/" + id + "/cobrancas/new"}>Adicionar</Button>
                <Button color="secondary" tag={Link} to="/boletos">Voltar</Button>
            </div>
        </Container>
    </div>;

};
export default ListarCobrancasForm;
