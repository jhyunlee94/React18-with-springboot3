export default function PageComponent({ serverData, movePage }: any) {
  // serverData.prev, pageNumList, next
  return (
    <div style={{ margin: '10px', display: 'flex', justifyContent: 'center' }}>
      {serverData.prev ? (
        <div
          style={{
            margin: '5px',
            padding: '5px',
            width: '100px',
            textAlign: 'center',
            fontWeight: 'bold',
            color: 'blue',
          }}
          onClick={() => movePage({ page: serverData.prevPage })}
        >
          Prev
        </div>
      ) : null}
      {serverData.pageNumList.map((pageNum: any) => (
        <div
          key={pageNum}
          style={{
            margin: '5px',
            padding: '5px',
            width: '35px',
            textAlign: 'center',
            borderRadius: '24px',
            color: 'black',
          }}
          onClick={() => movePage({ page: pageNum })}
        >
          {pageNum}
        </div>
      ))}

      {serverData.next ? (
        <div
          style={{
            margin: '5px',
            padding: '5px',
            width: ' 80px',
            textAlign: 'center',
            fontWeight: 'bold',
            color: 'blue',
          }}
          onClick={() => movePage({ page: serverData.nextPage })}
        >
          Next
        </div>
      ) : null}
    </div>
  );
}
