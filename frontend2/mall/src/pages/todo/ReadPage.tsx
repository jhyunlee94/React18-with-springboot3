import { useCallback } from 'react';
import {
  createSearchParams,
  useNavigate,
  useParams,
  useSearchParams,
} from 'react-router-dom';

export default function ReadPage() {
  const navigate = useNavigate();
  const { tno } = useParams(); // 구조분해

  const [queryParams] = useSearchParams();

  const page: any = queryParams.get('page')
    ? parseInt(queryParams.get('page') as string)
    : 1;

  const size: any = queryParams.get('size')
    ? parseInt(queryParams.get('size') as string)
    : 10;

  const queryStr = createSearchParams({ page, size }).toString();

  console.log('queryStr', queryStr);
  //   type Props = {
  //     tno: any;
  //   };
  const moveToModify = (tno: any) => {
    navigate({ pathname: `/todo/modify/${tno}`, search: queryStr });
  };

  const moveToList = useCallback(() => {
    navigate({
      pathname: `/todo/list`,
      search: queryStr,
    });
  }, []);
  return (
    <div
      style={{
        fontSize: '14px',
      }}
    >
      Todo Read Page {tno}
      <div>
        <button type="button" onClick={() => moveToModify(tno)}>
          Test Modify
        </button>
        <button type="button" onClick={moveToList}>
          Test List
        </button>
      </div>
    </div>
  );
}
