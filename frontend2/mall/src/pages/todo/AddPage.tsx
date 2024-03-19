import AddComponent from '@components/todo/AddComponent';

export default function AddPage() {
  return (
    <div style={{ padding: '10px', width: '100%', backgroundColor: 'white' }}>
      <div style={{ fontSize: '20px', fontWeight: 'bold' }}>Add Page</div>
      <AddComponent />
    </div>
  );
}
