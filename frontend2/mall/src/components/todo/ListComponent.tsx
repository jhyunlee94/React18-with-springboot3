import { getList } from '@/api/TodoApi';
import PageComponent from '@/common/PageComponent';
import useCustomMove from '@/hooks/useCustomMove';
import { useEffect, useState } from 'react';

const initState = {
  dtoList: [],
  pageNumList: [],
  pageRequestDTO: null,
  prev: false,
  next: false,
  totalCount: 0,
  prevPage: 0,
  nextPage: 0,
  totalPage: 0,
  current: 0,
};

export default function ListComponent() {
  const { page, size, moveToList, refresh } = useCustomMove();
  const [serverData, setServerData] = useState(initState);

  useEffect(() => {
    getList({ page, size }).then((data) => {
      console.log(data);
      setServerData(data);
    });
  }, [page, size, refresh]);

  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '10px',
        marginRight: '10px',
        marginLeft: ' 10px',
      }}
    >
      <div
        style={{
          display: 'flex',
          flexWrap: 'wrap',
          justifyContent: ' center',
          padding: '6px',
        }}
      >
        {serverData.dtoList.map((todo: any) => (
          <div
            key={todo.tno}
            style={{
              width: '100%',
              minWidth: '400px',
              padding: ' 5px',
              margin: '5px',
              borderRadius: '50px',
              border: '1px solid black',
            }}
          >
            <div style={{ display: 'flex' }}>
              <div
                style={{
                  fontWeight: 'bold',
                  fontSize: '20px',
                  padding: '5px',
                  width: '8%',
                }}
              >
                {todo.tno}
              </div>
              <div
                style={{
                  fontSize: '18px',
                  padding: '5px',
                  width: '60%',
                  fontWeight: 'bold',
                }}
              >
                {todo.title}
              </div>
              <div
                style={{
                  fontSize: '18px',
                  padding: '5px',
                  width: '60%',
                  fontWeight: 'bold',
                }}
              >
                {todo.dueDate}
              </div>
            </div>
          </div>
        ))}
      </div>
      <PageComponent serverData={serverData} movePage={moveToList} />
    </div>
  );
}
