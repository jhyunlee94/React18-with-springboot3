import ModifyComponent from '@components/products/ModifyComponent';
import { useParams } from 'react-router-dom';

export default function ModifyPage() {
  const { pno } = useParams();
  return (
    <div style={{ padding: '10px', width: '100%', backgroundColor: 'white' }}>
      <div style={{ fontSize: '24px', fontWeight: 'bold' }}>
        Products Modify Page
      </div>
      <ModifyComponent pno={pno} />
    </div>
  );
}
