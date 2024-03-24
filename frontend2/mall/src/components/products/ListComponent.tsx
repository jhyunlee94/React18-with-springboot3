import { getList } from '@/api/ProductApi';
import { API_SERVER_HOST } from '@/api/TodoApi';
import FetchingModal from '@/common/FetchingModal';
import PageComponent from '@/common/PageComponent';
import useCustomMove from '@/hooks/useCustomMove';
import { useEffect, useState } from 'react';

const initState = {
  dtoList: [],
  pageNumList: [],
  pageRequestDTO: null,
  prev: false,
  next: false,
  totalCount: 0,
  prevPage: 0,
  nextPage: 0,
  totalPage: 0,
  current: 0,
};

const host = API_SERVER_HOST;

export default function ListComponent() {
  const { moveToList, moveToRead, page, size, refresh } = useCustomMove();

  const [serverData, setServerData] = useState(initState);

  const [fetching, setFetching] = useState(false);

  useEffect(() => {
    setFetching(true);
    getList({
      page,
      size,
    }).then((data: any) => {
      setFetching(false);
      setServerData(data);
    });
  }, [page, size, refresh]);

  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '10px',
        marginRight: '10px',
        marginLeft: '10px',
      }}
    >
      {fetching && <FetchingModal />}
      <div style={{ display: 'flex', flexWrap: 'wrap', padding: '10px' }}>
        {serverData.dtoList.map((product: any) => (
          <div
            key={product.pno}
            style={{
              width: '50%',
              padding: '10px',
              border: '1px solid black',
            }}
            onClick={() => moveToRead(product.pno)}
          >
            <div
              style={{
                display: 'flex',
                flexDirection: 'column',
                height: '100%',
              }}
            >
              <div
                style={{
                  fontWeight: 'bold',
                  fontSize: '10px',
                  padding: '5px',
                  width: '100%',
                }}
              >
                {product.pno}
              </div>
              <div
                style={{
                  fontSize: '10px',
                  margin: '10px',
                  padding: '10px',
                  width: '100%',
                  display: 'flex',
                  flexDirection: 'column',
                }}
              >
                <div
                  style={{ width: '100%', overflow: 'hidden', display: 'flex' }}
                >
                  <img
                    alt="product"
                    style={{
                      margin: '0 auto',
                      width: '150px',
                    }}
                    src={`${host}/api/products/view/s_${product.uploadFileNames[0]}`}
                  />
                </div>
                <div
                  style={{
                    bottom: '0',
                    fontWeight: 'bold',
                    backgroundColor: 'white',
                  }}
                >
                  <div
                    style={{
                      textAlign: 'center',
                      padding: '3px',
                    }}
                  >
                    이름: {product.pname}
                  </div>
                  <div
                    style={{
                      textAlign: 'center',
                      padding: '3px',
                    }}
                  >
                    가격: {product.price}
                  </div>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
      <PageComponent serverData={serverData} movePage={moveToList} />
    </div>
  );
}
