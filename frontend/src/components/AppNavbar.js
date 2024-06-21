import React, {Component} from 'react';
import {Navbar, NavbarBrand, Nav, NavItem, NavLink} from 'reactstrap';
import {Link} from 'react-router-dom';


export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    handleLogout() {
        // Remove the token from local storage
        localStorage.removeItem('auth_token');

        // Redirect to login page
        window.location.href = "/";
    }

    render() {
        const isLoggedIn = localStorage.getItem('auth_token');

        return (
            <Navbar color="dark" dark expand="md">
                <NavbarBrand tag={Link} to="/home">Home</NavbarBrand>
                <Nav className="ml-auto" navbar>
                    {isLoggedIn && (
                        <NavItem>
                            <NavLink onClick={this.handleLogout}>Logout</NavLink>
                        </NavItem>
                    )}
                </Nav>
            </Navbar>
        );
    }
}