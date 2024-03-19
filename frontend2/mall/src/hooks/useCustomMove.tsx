import React from 'react';
import {
  createSearchParams,
  useNavigate,
  useSearchParams,
} from 'react-router-dom';

const getNum = (param: any, defaultValue: any) => {
  if (!param) {
    return defaultValue;
  }
  return parseInt(param);
};

const useCustomMove = () => {
  const navigate = useNavigate();

  // 페이지 이동할때마다 써줘야하기에 싫어서 hooks 를 만드는중
  const [queryParams] = useSearchParams(); // 파라미터 값 쿼리스트링 처리
  // 화면상에 있는 페이지 번호
  const page = getNum(queryParams.get('page'), 1);
  const size = getNum(queryParams.get('size'), 10);

  // page=3&size=10

  const queryDefault = createSearchParams({ page, size }).toString();

  const moveToList = (pageParam: any) => {
    let queryStr = '';

    if (pageParam) {
      const pageNum = getNum(pageParam.page, 1);
      const sizeNum = getNum(pageParam.size, 10);

      queryStr = createSearchParams({
        page: pageNum,
        size: sizeNum,
      }).toString();
    } else {
      queryStr = queryDefault;
    }
    navigate({ pathname: '../list', search: queryStr });
  };

  return { moveToList };
};

export default useCustomMove;
