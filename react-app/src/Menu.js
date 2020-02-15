import React from 'react';
import ReactDOM from 'react-dom';

class Menu extends React.Component {

    render() {
        return (
            <ul>
				<li><a title="Home" id="home" href="/">Home</a></li><li>|</li>
				<li><a title="Ingresar" id="login" href="http://test">Ingresar</a></li><li>|</li>
				<li><a title="Saludos" id="hello" href="http://test">Saludos</a></li><li>|</li>
				<li><a title="Muro" id="wall" href="http://test">Muro</a></li><li>|</li>
				<li><a title="Imagenes" id="images" href="http://test">Imagenes</a></li><li>|</li>
				<li><a title="Usuarios" id="users" href="http://test">Usuarios</a></li><li>|</li>
				<li><a title="Cuenta" id="account" href="http://test">Cuenta</a></li><li>|</li>
				<li><a title="Salir" id="exit" href="http://test">Salir</a></li>
			</ul>
        )
    }

}

ReactDOM.render(
    <Menu />,
    document.getElementById('menu')
);

export default Menu