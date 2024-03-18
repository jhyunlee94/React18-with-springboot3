import BasicLayout from '@/layouts/BasicLayout';
import { Outlet } from 'react-router-dom';

export default function IndexPage() {
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
          }}
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
          }}
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
