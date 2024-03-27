import { login } from '@/slices/loginSlice';
import { useState } from 'react';
import { useDispatch } from 'react-redux';

type MyObject = {
  [key: string]: string;
};

const initState: MyObject = {
  email: '',
  pw: '',
};

export default function LoginComponent() {
  const [loginParam, setLoginParam] = useState({ ...initState });

  const dispatch = useDispatch();

  const handleChange = (e: any) => {
    loginParam[e.target.name] = e.target.value;
    setLoginParam({ ...loginParam });
  };

  const handleClickLogin = (e: any) => {
    dispatch(login(loginParam));
  };

  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '10px',
        margin: '20px',
        padding: '20px',
        width: '400px',
        height: '400px',
        backgroundColor: '#fff',
      }}
    >
      <div
        style={{
          display: 'flex',
          justifyContent: 'center',
          fontSize: '24px',
          margin: '20px',
          padding: '20px',
          fontWeight: 'bold',
          color: 'black',
        }}
      >
        Login Component
      </div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <div
          style={{
            position: 'relative',
            marginBottom: '10px',
            display: 'flex',
            width: '100%',
            flexWrap: 'wrap',
            alignItems: 'stretch',
          }}
        >
          <div style={{ color: 'black', marginBottom: '10px' }}>Email</div>
          <input
            style={{
              width: '100%',
              padding: '10px',
              borderRadius: '14px',
              border: 'none',
            }}
            name="email"
            type={'text'}
            value={loginParam.email}
            onChange={handleChange}
          />
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <div
          style={{
            position: 'relative',
            marginBottom: '10px',
            display: 'flex',
            width: '100%',
            flexWrap: 'wrap',
            alignItems: 'stretch',
          }}
        >
          <div style={{ color: 'black', marginBottom: '10px' }}>Password</div>
          <input
            style={{
              width: '100%',
              padding: '10px',
              borderRadius: '14px',
              border: 'none',
            }}
            name="pw"
            type={'password'}
            value={loginParam.pw}
            onChange={handleChange}
          />
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
              padding: '10px',
              display: 'flex',
              justifyContent: 'center',
              fontWeight: 'bold',
            }}
          >
            <button
              style={{
                backgroundColor: 'blue',
                color: 'white',
                borderRadius: '14px',
                border: 'none',
                width: '80px',
                height: '40px',
              }}
              onClick={handleClickLogin}
            >
              LOGIN
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
