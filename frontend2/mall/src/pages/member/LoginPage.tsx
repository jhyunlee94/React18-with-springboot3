import BasicMenu from '@components/menus/BasicMenu';

export default function LoginPage() {
  return (
    <div
      style={{
        position: 'fixed',
        top: 0,
        left: 0,
        zIndex: 1055,
        display: 'flex',
        flexDirection: 'column',
        width: '100%',
        height: '100%',
      }}
    >
      <BasicMenu />
      <div
        style={{
          display: 'flex',
          flexWrap: 'wrap',
          width: '100%',
          height: '100%',
          justifyContent: 'center',
          alignItems: 'center',
          border: '1px solid black',
        }}
      >
        <div style={{ fontSize: '24px' }}>Login Page</div>
      </div>
    </div>
  );
}
