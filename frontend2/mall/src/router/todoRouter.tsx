import { Suspense, lazy } from 'react';
import { Navigate } from 'react-router-dom';

const Loading = <div style={{ color: 'red' }}>Loading...</div>;
const TodoList = lazy(() => import('@/pages/todo/ListPage'));

const TodoRead = lazy(() => import('@/pages/todo/ReadPage'));
const TodoAdd = lazy(() => import('@/pages/todo/AddPage'));
const todoRouter = () => {
  return [
    {
      path: 'list',
      element: (
        <Suspense fallback={Loading}>
          <TodoList />
        </Suspense>
      ),
    },
    {
      path: '', // '/' 는 생략 가능
      element: <Navigate replace={true} to={'list'} />,
    },
    {
      path: 'read/:tno',
      element: (
        <Suspense fallback={Loading}>
          <TodoRead />
        </Suspense>
      ),
    },
    {
      path: 'add', // '/' 는 생략 가능
      element: (
        <Suspense fallback={Loading}>
          <TodoAdd />
        </Suspense>
      ),
    },
  ];
};

export default todoRouter;
