import { useParams } from 'react-router-dom';

export default function ReadPage() {
  const { tno } = useParams(); // 구조분해

  return (
    <div
      style={{
        fontSize: '14px',
      }}
    >
      Todo Read Page
    </div>
  );
}
