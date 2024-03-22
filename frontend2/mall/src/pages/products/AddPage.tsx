import AddComponent from '@components/products/AddComponent';

export default function AddPage() {
  return (
    <div style={{ padding: '10px', width: '100%', backgroundColor: 'white' }}>
      <div style={{ fontSize: '24px', fontWeight: 'bold' }}>
        Products Add Page
      </div>
      <AddComponent />
    </div>
  );
}
