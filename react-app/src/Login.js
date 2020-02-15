import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam]: val});
    }

    handleSubmit(event) {
        axios.post(
            'http://127.0.0.1:8080/api/auth/login/', 
            {
                username: this.state.username,
                password: this.state.password,
            }
        ).then(
            res => {
                alert('Successfully logged in.')
                sessionStorage.setItem('jwttoken', res.headers['authorization']);
            }
        ).catch((error) => {
            if (error.response) {
                console.log(error.response.data);
                console.log(error.response.status);
                alert('Error while logging in.')
            }
        });
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit} >
            <p>
            Nombre: <input type="text" name="username" value={this.state.username} onChange={this.handleChange} /><br />
            Password: <input type="password" name="password" value={this.state.password} onChange={this.handleChange} /><br />
            <input type="submit" value="Ingresar" />
            </p>
            </form>
        )
    }

}

ReactDOM.render(
    <Login />,
    document.getElementById('root')
);

export default Login