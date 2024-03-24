import ListComponent from '@components/products/ListComponent';
import React from 'react';

const ListPage = () => {
  return (
    <div style={{ padding: '10px', width: '100%', backgroundColor: 'white' }}>
      <div style={{ fontSize: '24px', fontWeight: 'bold' }}>
        Products List Page
      </div>
      <ListComponent />
    </div>
  );
};

export default ListPage;
