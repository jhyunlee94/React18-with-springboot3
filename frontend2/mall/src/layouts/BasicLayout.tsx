import React from 'react';
import style from '@/layouts/BasicLayout.module.css';
import BasicMenu from '@components/menus/BasicMenu';
type Props = {
  children: React.ReactNode;
};

const BasicLayout = ({ children }: Props) => {
  return (
    <>
      <BasicMenu />

      <div className={style.mainContainer}>
        <main className={style.main}>{children}</main>
        <aside className={style.aside}>
          <h1>Sidebar</h1>
        </aside>
      </div>
    </>
  );
};

export default BasicLayout;
