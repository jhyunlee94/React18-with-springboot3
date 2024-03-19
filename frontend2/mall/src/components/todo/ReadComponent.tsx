import { getOne } from '@/api/TodoApi';
import { useEffect, useState } from 'react';

const initState = {
  tno: 0,
  title: '',
  content: '',
  dueDate: '',
  complete: false,
};
export default function ReadComponent({ tno }: any) {
  const [todo, setTodo] = useState(initState);

  useEffect(() => {
    getOne(tno).then((data) => {
      console.log('data', data);
      setTodo(data);
    });
  }, [tno]);
  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '10px',
        margin: '5px',
        padding: '7px',
      }}
    >
      {makeDive('Tno', todo.tno)}
      {makeDive('Content', todo.content)}
      {makeDive('Title', todo.title)}
      {makeDive('Due Date', todo.dueDate)}
      {makeDive('Complete,', todo.complete ? 'Completed' : 'Not Yet')}
    </div>
  );
}

const makeDive = (title: any, value: any) => (
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
      <div
        style={{
          width: '100px',
          padding: '10px',
          right: 0,
          fontWeight: 'bold',
        }}
      >
        {title}
      </div>
      <div
        style={{
          width: '500px',
          padding: '10px',
          border: '1px solid black',
          borderRadius: '24px',
        }}
      >
        {value}
      </div>
    </div>
  </div>
);
