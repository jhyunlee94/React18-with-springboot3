import BasicLayout from '@/layouts/BasicLayout';
import { useCallback } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';

export default function IndexPage() {
  const navigate = useNavigate();

  const handleClickList = useCallback(() => {
    navigate({ pathname: 'list' });
  }, []);

  const handleClickAdd = useCallback(() => {
    navigate({ pathname: 'add' });
  }, []);
  return (
    <BasicLayout>
      <div
        style={{
          width: '100%',
          display: 'flex',
          margin: '20px',
          padding: '20px',
        }}
      >
        <div
          style={{
            fontSize: '24px',
            margin: '10px',
            padding: '20px',
            fontWeight: 'bold',
            width: '50px',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            cursor: 'pointer',
          }}
          onClick={handleClickList}
        >
          List
        </div>
        <div
          style={{
            fontSize: '24px',
            margin: '10px',
            padding: '20px',
            fontWeight: 'bold',
            width: '50px',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            cursor: 'pointer',
          }}
          onClick={handleClickAdd}
        >
          ADD
        </div>
      </div>
      <div style={{ display: 'flex', flexWrap: 'wrap' }}>
        <Outlet />
      </div>
    </BasicLayout>
  );
}
