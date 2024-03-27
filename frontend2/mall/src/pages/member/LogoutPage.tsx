import LogoutComponent from '@components/member/LogoutComponent';
import BasicMenu from '@components/menus/BasicMenu';

export default function LogoutPage() {
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
          width: '100%',
          display: 'flex',
          flexWrap: 'wrap',
          height: '100%',
          justifyContent: 'center',
          alignItems: 'center',
          border: '1px solid black',
        }}
      >
        <LogoutComponent></LogoutComponent>
      </div>
    </div>
  );
}
