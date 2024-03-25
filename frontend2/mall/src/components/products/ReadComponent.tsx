import { getOne } from '@/api/ProductApi';
import { API_SERVER_HOST } from '@/api/TodoApi';
import FetchingModal from '@/common/FetchingModal';
import useCustomMove from '@/hooks/useCustomMove';
import { useEffect, useState } from 'react';

const initState = {
  pno: 0,
  pname: '',
  pdesc: '',
  price: 0,
  uploadFileNames: [],
};

const host = API_SERVER_HOST;

export default function ReadComponent({ pno }: any) {
  const [product, setProduct] = useState(initState);
  const [fetching, setFetching] = useState(false);

  const { moveToModify, moveToList, page, size } = useCustomMove();

  useEffect(() => {
    setFetching(true);
    getOne(pno).then((data: any) => {
      console.log(data);
      setProduct(data);
      setFetching(false);
    });
  }, [pno]);
  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '10px',
        margin: '20px',
        padding: '20px',
      }}
    >
      {fetching && <FetchingModal />}
      <div
        style={{ display: 'flex', justifyContent: 'center', marginTop: '10px' }}
      >
        <div
          style={{
            position: 'relative',
            marginBottom: '10px',
            display: 'flex',
            width: '100%',
            flexWrap: 'wrap',
            alignItems: 'stretch',
          }}
        >
          <div
            style={{
              width: '20%',
              padding: '20px',
              right: 0,
              fontWeight: 'bold',
            }}
          >
            PNO
          </div>
          <div
            style={{
              width: '80%',
              padding: '20px',
              border: '1px solid black',
              borderRadius: '24px',
            }}
          >
            {product.pno}
          </div>
        </div>
      </div>
      <div
        style={{ display: 'flex', justifyContent: 'center', marginTop: '10px' }}
      >
        <div
          style={{
            position: 'relative',
            marginBottom: '10px',
            display: 'flex',
            width: '100%',
            flexWrap: 'wrap',
            alignItems: 'stretch',
          }}
        >
          <div
            style={{
              width: '20%',
              padding: '20px',

              fontWeight: 'bold',
            }}
          >
            PNAME
          </div>
          <div
            style={{
              width: '80%',
              padding: '20px',
              border: '1px solid black',
              borderRadius: '24px',
            }}
          >
            {product.pname}
          </div>
        </div>
      </div>
      <div
        style={{ display: 'flex', justifyContent: 'center', marginTop: '10px' }}
      >
        <div
          style={{
            position: 'relative',
            marginBottom: '10px',
            display: 'flex',
            width: '100%',
            flexWrap: 'wrap',
            alignItems: 'stretch',
          }}
        >
          <div
            style={{
              width: '20%',
              padding: '20px',
              right: 0,
              fontWeight: 'bold',
            }}
          >
            PRICE
          </div>
          <div
            style={{
              width: '80%',
              padding: '20px',
              border: '1px solid black',
              borderRadius: '24px',
            }}
          >
            {product.price}
          </div>
        </div>
      </div>
      <div
        style={{ display: 'flex', justifyContent: 'center', marginTop: '10px' }}
      >
        <div
          style={{
            position: 'relative',
            marginBottom: '10px',
            display: 'flex',
            width: '100%',
            flexWrap: 'wrap',
            alignItems: 'stretch',
          }}
        >
          <div
            style={{
              width: '20%',
              padding: '20px',
              right: 0,
              fontWeight: 'bold',
            }}
          >
            PDESC
          </div>
          <div
            style={{
              width: '80%',
              padding: '20px',
              border: '1px solid black',
              borderRadius: '24px',
            }}
          >
            {product.pdesc}
          </div>
        </div>
      </div>
      <div
        style={{
          width: '100%',
          display: 'flex',
          justifyContent: 'center',
          flexDirection: 'column',
          margin: '0 auto',
          alignItems: 'center',
        }}
      >
        {product.uploadFileNames.map((imgFile, i) => (
          <img
            alt="product"
            key={i}
            style={{ padding: '20px', width: '50%' }}
            src={`${host}/api/products/view/${imgFile}`}
          />
        ))}
      </div>
      <div style={{ display: 'flex', justifyContent: 'end', padding: '20px' }}>
        <button
          type="button"
          style={{
            display: 'inline-block',
            padding: '10px',
            margin: '20px',
            fontSize: '18px',
            color: 'white',
            backgroundColor: 'red',
            width: '80px',
          }}
          onClick={() => moveToModify(pno)}
        >
          Modify
        </button>
        <button
          type="button"
          style={{
            display: 'inline-block',
            padding: '10px',
            margin: '20px',
            fontSize: '18px',
            color: 'white',
            backgroundColor: 'blue',
            width: '80px',
          }}
          onClick={() => moveToList({ page, size })}
        >
          List
        </button>
      </div>
    </div>
  );
}
