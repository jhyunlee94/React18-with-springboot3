import style from '@/components/menus/BasicMenu.module.css';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
export default function BasicMenu() {
  const loginState = useSelector((state: any) => state.loginSlice);
  // console.log('loginState....' + JSON.stringify(loginState));
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

          {loginState.email ? (
            <>
              <li>
                <Link to={'/todo/'}>Todo</Link>
              </li>
              <li>
                <Link to={'/products/'}>Products</Link>
              </li>
            </>
          ) : (
            <></>
          )}
        </ul>
      </div>
      <div className={style.loginContainer}>
        {!loginState.email ? (
          <div className={style.login}>
            <Link to={'/member/login'}>Login</Link>
          </div>
        ) : (
          <div className={style.login}>
            <Link to={'/member/logout'}>Logout</Link>
          </div>
        )}
      </div>
    </nav>
  );
}
