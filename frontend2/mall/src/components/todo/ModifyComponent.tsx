import { deleteOne, getOne, putOne } from '@/api/TodoApi';
import ResultModal from '@/common/ResultModal';
import useCustomMove from '@/hooks/useCustomMove';
import { ChangeEventHandler, useEffect, useState } from 'react';

type MyObject = {
  [key: string]: any;
};

const initState: MyObject = {
  tno: 0,
  title: '',
  content: '',
  dueDate: '',
  complete: false,
};

export default function ModifyComponent({ tno }: any) {
  const [todo, setTodo] = useState(initState);
  const [result, setResult] = useState('');

  const { moveToList, moveToRead } = useCustomMove();
  // 수정 -> 조회화면
  // 삭제 -> 1페이지(목록으로)

  useEffect(() => {
    getOne(tno).then((data: any) => {
      console.log(data);
      setTodo(data);
    });
  }, [tno]);

  const handleChangeTodo = (e: any) => {
    console.log(e.target.name, e.target.value);
    todo[e.target.name] = e.target.value;
    setTodo({ ...todo });
  };

  const handleChangeTodoComplete = (e: any) => {
    const value = e.target.value;
    todo.complete = value === 'Y';
    setTodo({ ...todo });
  };

  const handleClickDelete = () => {
    deleteOne(tno).then((data: any) => {
      console.log('delete result: ' + JSON.stringify(data)); // {RESULT: SUCCESS}
      setResult('Deleted');
    });
  };

  const handleClickModify = () => {
    putOne(todo).then((data: any) => {
      console.log('modify result: ' + JSON.stringify(data)); // {RESULT: SUCCESS}
      setResult('Modified');
    });
  };

  const closeModal = () => {
    if (result === 'Deleted') {
      moveToList();
    } else {
      moveToRead(tno);
    }
  };

  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '10px',
        margin: '20px',
        padding: '20px',
      }}
    >
      <div
        style={{
          display: 'flex',
          justifyContent: 'center',
          marginTop: '10px',
        }}
      >
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            TNO
          </div>
          <div
            style={{
              width: '80%',
              padding: '10px',
              borderRadius: '24px',
              border: '1px solid black',
            }}
          >
            {todo.tno}
          </div>
        </div>
      </div>
      <div
        style={{
          display: 'flex',
          justifyContent: 'center',
          marginTop: '10px',
        }}
      >
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            Content
          </div>
          <div
            style={{
              width: '80%',
              padding: '10px',
              borderRadius: '24px',
              border: '1px solid black',
            }}
          >
            {todo.content}
          </div>
        </div>
      </div>
      <div
        style={{
          display: 'flex',
          justifyContent: 'center',
          marginTop: '10px',
        }}
      >
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            Title
          </div>
          <input
            style={{
              width: '80%',
              borderRadius: '24px',
              border: '1px solid black',
            }}
            name="title"
            type={'text'}
            value={todo.title}
            onChange={handleChangeTodo}
          />
        </div>
      </div>
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
        <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
          DUEDATE
        </div>
        <input
          style={{
            width: '80%',
            borderRadius: '24px',
            border: '1px solid black',
          }}
          name="dueDate"
          type={'date'}
          value={todo.dueDate}
          onChange={handleChangeTodo}
        />
      </div>

      <div
        style={{
          display: 'flex',
          justifyContent: 'center',
          marginTop: '10px',
        }}
      >
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            Complete
          </div>
          <select
            style={{
              width: '80%',
              borderRadius: '24px',
              border: '1px solid black',
            }}
            name="status"
            value={todo.complete ? 'Y' : 'N'}
            onChange={handleChangeTodoComplete}
          >
            <option value="Y">Completed</option>
            <option value="N">Not Yet</option>
          </select>
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'end', padding: '10px' }}>
        <button
          type="button"
          style={{
            display: 'inline-block',
            padding: '10px',
            margin: '10px',
            fontSize: '12px',
            width: '60px',
            color: 'white',
          }}
          onClick={handleClickDelete}
        >
          Delete
        </button>
        <button
          type="button"
          style={{
            display: 'inline-block',
            padding: '10px',
            margin: '10px',
            fontSize: '12px',
            width: '60px',
            color: 'white',
          }}
          onClick={handleClickModify}
        >
          Modify
        </button>
      </div>
      {result && (
        <ResultModal
          title={'처리결과'}
          content={result}
          callbackFn={closeModal}
        ></ResultModal>
      )}
    </div>
  );
}
