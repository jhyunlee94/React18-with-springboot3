import { logout } from '@/slices/loginSlice';
import { useDispatch } from 'react-redux';

export default function LogoutComponent() {
  const dispatch = useDispatch();

  const handleClickLogout = () => {
    dispatch(logout());
  };
  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '20px',
        margin: '10px',
        padding: '10px',
      }}
    >
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <div
          style={{
            fontSize: '36px',
            margin: '20px',
            padding: '20px',
            fontWeight: 'bold',
            color: 'red',
          }}
        >
          Logout Component
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <div
          style={{
            position: 'relative',
            marginBottom: '10px',
            display: 'flex',
            width: '100%',
            justifyContent: 'center',
          }}
        >
          <div
            style={{
              width: '40%',
              padding: '20px',
              display: 'flex',
              justifyContent: 'center',
              fontWeight: 'bold',
            }}
          >
            <button
              style={{
                border: 'none',
                borderRadius: '14px',
                backgroundColor: 'red',
                color: 'white',
              }}
              onClick={handleClickLogout}
            >
              LOGOUT
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
