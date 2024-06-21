import React, {Component} from 'react';
import '../assets/App.css';
import Home from './Home';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ListarClientesForm from './clientes/ListarClientesForm';
import EditarClientesForm from "./clientes/EditarClientesForm";
import ListarUsuariosForm from './usuarios/ListarUsuariosForm';
import EditarUsuariosForm from "./usuarios/EditarUsuariosForm";
import ListarBoletosForm from "./boletos/ListarBoletosForm";
import EditarBoletosForm from "./boletos/EditarBoletosForm";
import LoginForm from "./usuarios/LoginForm";
import CadastrarUsuarioForm from "./usuarios/CadastrarUsuarioForm";
import WelcomeContent from "./WelcomeContent";
import PrivateRoutes from "./PrivateRoutes";
import EditarCobrancasForm from "./cobrancas/EditarCobrancasForm";
import ListarCobrancasForm from "./cobrancas/ListarCobrancasForm";
import ListarBoletosSemCobrancaForm from "./boletos/ListarBoletosSemCobrancaForm";

class App extends Component {
    render() {
        return (
            <Router>
                <Routes>
                    <Route path='/' element={<WelcomeContent />} />
                    <Route path='/login' element={<LoginForm />} />
                    <Route path='/registrar' element={<CadastrarUsuarioForm />} />
                    <Route element={<PrivateRoutes />}>
                        <Route path='/home' element={<Home />} />
                        <Route path='/clientes' element={<ListarClientesForm />} />
                        <Route path='/clientes/:id' element={<EditarClientesForm />} />
                        <Route path='/usuarios' element={<ListarUsuariosForm />} />
                        <Route path='/usuarios/:id' element={<EditarUsuariosForm />} />
                        <Route path='/boletos' element={<ListarBoletosForm />} />
                        <Route path='/boletos/:id' element={<EditarBoletosForm />} />
                        <Route path='/boletos/:id/cobrancas' element={<ListarCobrancasForm />} />
                        <Route path='/boletos/sem-cobranca' element={<ListarBoletosSemCobrancaForm />} />
                        <Route path='/boletos/:debtid/cobrancas/:id' element={<EditarCobrancasForm />} />
                    </Route>
                </Routes>
            </Router>
        )
    }
}

export default App;
