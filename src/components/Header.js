import { NavLink } from "react-router-dom"
import logo from "./assets/logo.jpg"

const Header = () => {
    return (
        <header>
            <img className='logo' src={logo} alt='Logo'/>
            <nav className='header__nav'>
                <div className='header__nav-link'><NavLink exact to='/'>Home</NavLink></div>
                <div className='header__nav-link'><NavLink to='/catalog'>Catalog</NavLink></div>
                <div className='header__nav-link'><NavLink to='/creating_application'>Create application</NavLink></div>
            </nav>
        </header>
    )
}

export default Header
