import { useSearchParams } from 'react-router-dom';

export default function ListPage() {
  const [queryParams] = useSearchParams();

  const page = queryParams.get('page')
    ? parseInt(queryParams.get('page') as string)
    : 1;

  const size = queryParams.get('size')
    ? parseInt(queryParams.get('size') as string)
    : 10;

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
        Todo List Page Component --- {page} --- {size}
      </div>
    </div>
  );
}
