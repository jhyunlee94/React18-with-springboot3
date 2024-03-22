export default function FetchingModal() {
  return (
    <>
      <div
        style={{
          position: 'fixed',
          top: '0',
          left: '0',
          //   display: 'flex',
          width: '100dvw',
          height: '100dvh',
          //   justifyContent: 'center',
          backgroundColor: 'black',
          opacity: '0.6',
        }}
      ></div>
      <div
        style={{
          position: 'absolute',
          backgroundColor: 'white',
          opacity: '1',
          zIndex: '1055',
          width: '500px',
          height: '240px',
          top: '0',
          left: 'calc(50% - 240px)',
          marginTop: '100px',
          marginBottom: '40px',
          padding: '40px',
          minWidth: '400px',
          borderRadius: '36px',
        }}
      >
        <div
          style={{
            fontSize: '34px',
            borderBottom: '1px solid black',
            paddingTop: '10px',
            paddingBottom: '10px',
          }}
        >
          Loading...
        </div>
      </div>
    </>
  );
}
