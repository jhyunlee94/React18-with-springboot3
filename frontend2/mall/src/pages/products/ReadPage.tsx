import ReadComponent from '@components/products/ReadComponent';
import { useParams } from 'react-router-dom';

export default function ReadPage() {
  const { pno } = useParams();
  //   console.log('pno', pno);
  return (
    <div style={{ padding: '10px', width: '100%', backgroundColor: 'white' }}>
      <div style={{ fontSize: '24px', fontWeight: 'bold' }}>
        Products Read Page
      </div>
      <ReadComponent pno={pno} />
    </div>
  );
}
