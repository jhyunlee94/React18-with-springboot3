export default function ResultModal({ title, content, callbackFn }: any) {
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
            justifyContent: 'center',
            marginTop: '10px',
            marginBottom: '10px',
            fontSize: '26px',
            borderBottom: '1px solid black',
          }}
        >
          {title}
        </div>
        <div
          style={{
            fontSize: '34px',
            borderBottom: '1px solid black',
            paddingTop: '10px',
            paddingBottom: '10px',
          }}
        >
          {content}
        </div>
        <div style={{ display: 'flex', justifyContent: 'end' }}>
          <button
            style={{
              border: 'none',
              borderRadius: '36px',
              margin: '10px',
              padding: '10px',
              color: 'white',
            }}
            onClick={() => {
              if (callbackFn) {
                callbackFn();
              }
            }}
          >
            Close Modal
          </button>
        </div>
      </div>
    </>
  );
}
