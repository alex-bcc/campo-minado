import logo from '../assets/logo.svg';
import style from './navbar.module.scss'

const Navbar = () => {
  return (
    <div className={`${style.nav} mx-auto max-w-screen-xl px-6 py-3`} >
      
      <img src={logo} alt="" />
    </div>
  )
}

export default Navbar