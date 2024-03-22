import BasicLayout from '@/layouts/BasicLayout';
import { Outlet, useNavigate } from 'react-router-dom';

export default function IndexPage() {
  const navigate = useNavigate();

  const handleClickList = () => {
    navigate('list');
  };
  const handleClickAdd = () => {
    navigate('add');
  };
  return (
    <BasicLayout>
      <div style={{ color: 'black', fontWeight: 'bold', marginTop: '10px' }}>
        Products Menus
      </div>
      <div
        style={{
          width: '100%',
          display: 'flex',
          margin: '10px',
          padding: '10px',
        }}
      >
        <div
          style={{
            fontSize: '24px',
            margin: '10px',
            padding: '10px',
            width: '40px',
            display: 'flex',
            justifyContent: 'center',
            cursor: 'pointer',
          }}
          onClick={handleClickList}
        >
          LIST
        </div>
        <div
          style={{
            fontSize: '24px',
            margin: '10px',
            padding: '10px',
            width: '40px',
            display: 'flex',
            justifyContent: 'center',
            cursor: 'pointer',
          }}
          onClick={handleClickAdd}
        >
          ADD
        </div>
      </div>
      <div style={{ display: 'flex', flexWrap: 'wrap', width: '100%' }}>
        <Outlet />
      </div>
    </BasicLayout>
  );
}
