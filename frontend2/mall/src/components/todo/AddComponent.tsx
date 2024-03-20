import { postAdd } from '@/api/TodoApi';
import ResultModal from '@/common/ResultModal';
import useCustomMove from '@/hooks/useCustomMove';
import { ChangeEventHandler, useCallback, useState } from 'react';

type MyObject = {
  [key: string]: string;
};

const initState: MyObject = {
  title: '',
  content: '',
  dueDate: '',
};

export default function AddComponent() {
  const [todo, setTodo] = useState({ ...initState });
  const [result, setResult] = useState(null);

  const { moveToList } = useCustomMove();

  const handleChangeTodo: ChangeEventHandler<HTMLInputElement> = useCallback(
    (e) => {
      // todo[title] => 자바에서는 이런 처리가 안되는데 자바스크립트는 동적 처리가 잘되어있음
      console.log(e.target.name, e.target.value);
      todo[e.target.name] = e.target.value;

      setTodo({ ...todo });
    },
    [],
  );

  const handleClickAdd = () => {
    console.log('todoObjzzz', todo);
    postAdd(todo).then((result: any) => {
      // data = {TNO: 104}
      console.log('result', result);
      setResult(result.TNO);
      setTodo({ ...initState });
    });
  };

  const closeModal = () => {
    setResult(null);
    moveToList();
  };
  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '10px',
        margin: '5px',
        padding: '5px',
      }}
    >
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <div
          style={{
            position: 'relative',
            marginBottom: '5px',
            display: 'flex',
            flexWrap: 'wrap',
            alignItems: 'stretch',
            width: '100%',
          }}
        >
          <div
            style={{
              width: '20%',
              fontWeight: 'bold',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            Title
          </div>
          <input
            style={{
              width: '80%',
              padding: '10px',
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
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <div
          style={{
            width: '100%',
            position: 'relative',
            marginBottom: '5px',
            display: 'flex',
            flexWrap: 'wrap',
            alignItems: 'stretch',
          }}
        >
          <div
            style={{
              width: '20%',
              fontWeight: 'bold',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            Content
          </div>
          <input
            style={{
              width: '80%',
              padding: '10px',
              borderRadius: '24px',
              border: '1px solid black',
            }}
            name="content"
            type={'text'}
            value={todo.content}
            onChange={handleChangeTodo}
          />
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <div
          style={{
            position: 'relative',
            marginBottom: '5px',
            display: 'flex',
            flexWrap: 'wrap',
            alignItems: 'stretch',
            width: '100%',
          }}
        >
          <div
            style={{
              width: '20%',
              fontWeight: 'bold',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            DUEDATE
          </div>
          <input
            style={{
              width: '80%',
              padding: '10px',
              borderRadius: '24px',
              border: '1px solid black',
            }}
            name="dueDate"
            type={'date'}
            value={todo.dueDate}
            onChange={handleChangeTodo}
          />
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'end' }}>
        <div
          style={{
            position: 'relative',
            marginBottom: '5px',
            display: 'flex',
            padding: '5px',
            flexWrap: 'wrap',
            alignItems: 'stretch',
          }}
        >
          <button
            type="button"
            style={{
              padding: '5px',
              width: '70px',
              backgroundColor: 'blue',
              fontSize: '15px',
              color: 'white',
            }}
            onClick={handleClickAdd}
          >
            ADD
          </button>
        </div>
      </div>
      {result && (
        <ResultModal
          title={'Add Result'}
          content={`New ${result} Added`}
          callbackFn={closeModal}
        />
      )}
      {/* {result ? <ResultModal /> : <></>} */}
    </div>
  );
}
