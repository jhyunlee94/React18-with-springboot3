import { getOne } from '@/api/ProductApi';
import { API_SERVER_HOST } from '@/api/TodoApi';
import FetchingModal from '@/common/FetchingModal';
import { ChangeEventHandler, useEffect, useRef, useState } from 'react';

type MyObject = {
  [key: string]: any;
};

const initState: MyObject = {
  pno: 0,
  pname: '',
  pdesc: '',
  price: 0,
  delFlag: false,
  uploadFileNames: [],
};

const host = API_SERVER_HOST;

export default function ModifyComponent({ pno }: any) {
  const [product, setProduct] = useState(initState);
  const [fetching, setFetching] = useState(false);
  const uploadRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    setFetching(true);
    getOne(pno).then((data) => {
      setProduct(data);
      setFetching(false);
    });
  }, [pno]);

  const handleChangeProduct = (e: any) => {
    product[e.target.name] = e.target.value;
    setProduct({ ...product });
  };

  const deleteOldImages = (imageName: any) => {
    const resultFileNames = product.uploadFileNames.filter(
      (fileName: any) => fileName !== imageName,
    );

    product.uploadFileNames = resultFileNames;
    setProduct({ ...product });
  };

  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '10px',
        margin: '20px',
        padding: '20px',
      }}
    >
      Product Modify Component
      {fetching && <FetchingModal />}
      <div style={{ display: 'flex', justifyContent: 'center' }}>
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            Product Name
          </div>
          <input
            style={{ width: '80%', padding: '10px', border: '1px solid black' }}
            name="pname"
            type={'text'}
            value={product.pname}
            onChange={handleChangeProduct}
          />
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            Desc
          </div>
          <textarea
            style={{ width: '80%', padding: '10px', border: '1px solid black' }}
            name="pname"
            value={product.pdesc}
            rows={4}
            onChange={handleChangeProduct}
          />
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            Price
          </div>
          <input
            style={{ width: '80%', padding: '10px', border: '1px solid black' }}
            name="pname"
            type={'number'}
            value={product.price}
            onChange={handleChangeProduct}
          />
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            DELETE
          </div>
          <select
            name="delFlag"
            value={product.delFlag}
            onChange={handleChangeProduct}
            style={{ width: '80%', padding: '10px', border: '1px solid black' }}
          >
            <option value="false">사용</option>
            <option value="true">삭제</option>
          </select>
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            Files
          </div>
          <input
            ref={uploadRef}
            style={{ width: '80%', padding: '10px', border: '1px solid black' }}
            type={'file'}
            multiple={true}
          />
        </div>
      </div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
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
          <div style={{ width: '20%', padding: '10px', fontWeight: 'bold' }}>
            Images
          </div>
          <div
            style={{
              width: '80%',
              display: 'flex',
              justifyContent: 'center',
              flexWrap: 'wrap',
              alignItems: 'start',
            }}
          >
            {product.uploadFileNames.map((imgFile: string, i: number) => (
              <div
                style={{
                  display: 'flex',
                  justifyContent: 'center',
                  flexDirection: 'column',
                  width: '30%',
                  margin: '5px',
                }}
                key={i}
              >
                <button
                  style={{
                    backgroundColor: 'blue',
                    fontSize: '20px',
                    color: 'white',
                  }}
                  onClick={() => deleteOldImages(imgFile)}
                >
                  DELETE
                </button>
                <img alt="img" src={`${host}/api/products/view/s_${imgFile}`} />
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
