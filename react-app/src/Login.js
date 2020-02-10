import React from 'react';
import ReactDOM from 'react-dom';

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
        alert('A login was submitted: ' + this.state.username);
        //event.preventDefault();
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