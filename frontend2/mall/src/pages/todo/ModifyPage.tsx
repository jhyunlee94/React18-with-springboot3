import ModifyComponent from '@components/todo/ModifyComponent';
import { useNavigate, useParams } from 'react-router-dom';

const ModifyPage = () => {
  const { tno } = useParams();
  const navigate = useNavigate();

  const moveToRead = () => {
    navigate({ pathname: `/todo/read/${tno}` });
  };

  const moveToList = () => {
    navigate({ pathname: `/todo/list` });
  };

  return (
    <div
      style={{
        padding: '10px',
        width: '100%',
        backgroundColor: 'white',
      }}
    >
      <div style={{ fontSize: '24px', fontWeight: 'bold' }}>
        Todo Modify Page
      </div>
      <ModifyComponent tno={tno} />
    </div>
  );
};

export default ModifyPage;
