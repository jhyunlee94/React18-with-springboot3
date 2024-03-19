import ReadComponent from '@components/todo/ReadComponent';

import { useParams } from 'react-router-dom';

export default function ReadPage() {
  const { tno } = useParams(); // 구조분해

  return (
    <div
      style={{
        fontSize: '14px',
        width: '100%',
        marginTop: '10px',
      }}
    >
      Todo Read Page {tno}
      {/* <div>
        <button type="button" onClick={() => moveToModify(tno)}>
          Test Modify
        </button>
        <button type="button" onClick={moveToList}>
          Test List
        </button>
      </div> */}
      <ReadComponent tno={tno} />
    </div>
  );
}
