import { Suspense, lazy } from 'react';

const Loading = <div>Loading....</div>;
const Login = lazy(() => import('../pages/member/LoginPage'));

const Logout = lazy(() => import('../pages/member/LogoutPage'));
const memberRouter = () => {
  return [
    {
      path: 'login',
      element: (
        <Suspense fallback>
          <Login />
        </Suspense>
      ),
    },
    {
      path: 'logout',
      element: (
        <Suspense fallback>
          <Logout />
        </Suspense>
      ),
    },
  ];
};

export default memberRouter;
