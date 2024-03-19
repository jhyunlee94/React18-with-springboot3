import ListComponent from '@components/todo/ListComponent';
import { useSearchParams } from 'react-router-dom';

export default function ListPage() {
  return (
    <div
      style={{
        padding: '20px',
        width: '100%',
        background: 'white',
      }}
    >
      <div
        style={{
          fontSize: '36px',
          fontWeight: 'bold',
        }}
      >
        Todo List Page Component
      </div>
      <ListComponent />
    </div>
  );
}
