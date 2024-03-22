import style from '@/components/menus/BasicMenu.module.css';
import { Link } from 'react-router-dom';
export default function BasicMenu() {
  return (
    <nav className={style.navContainer}>
      <div className={style.menuContainer}>
        <div className={style.normalButton}>Home</div>
        <ul>
          <li>
            <Link to={'/'}>Main</Link>
          </li>
          <li>
            <Link to={'/about'}>About</Link>
          </li>
          <li>
            <Link to={'/todo/'}>Todo</Link>
          </li>
          <li>
            <Link to={'/products/'}>Products</Link>
          </li>
        </ul>
      </div>
      <div className={style.loginContainer}>
        <div className={style.login}>Login</div>
      </div>
    </nav>
  );
}
