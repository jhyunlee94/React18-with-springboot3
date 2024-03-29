import { postAdd } from '@/api/ProductApi';
import FetchingModal from '@/common/FetchingModal';
import ResultModal from '@/common/ResultModal';
import useCustomMove from '@/hooks/useCustomMove';
import React, { ChangeEventHandler, useRef, useState } from 'react';

type MyObject = {
  [key: string]: any;
};

const initState: MyObject = {
  pname: '',
  pdesc: '',
  price: 0,
  files: [],
};

// new FormData() -> POST , PUT
const AddComponent = () => {
  const [product, setProduct] = useState(initState);
  const uploadRef = useRef<HTMLInputElement>(null);
  // const uploadRef = useRef(null);

  const [fetching, setFetching] = useState(false);
  const [result, setResult] = useState(null);

  const { moveToList } = useCustomMove();

  const handleChangeProduct: any = (e: any) => {
    product[e.target.name] = e.target.value;
    setProduct({ ...product });
  };

  const handleClickAdd = () => {
    console.log(product);

    const formData = new FormData();

    const files = uploadRef.current?.files!;
    for (let i = 0; i < files.length; i++) {
      // console.log('i', i);
      // console.log('files.length', files.length);
      // console.log('files[i]', files[i]);
      formData.append('files', files[i]);
    }
    formData.append('pname', product.pname);
    formData.append('pdesc', product.pdesc);
    formData.append('price', product.price);

    console.log(formData);

    setFetching(true);
    postAdd(formData).then((data) => {
      setFetching(false);
      setResult(data.RESULT);
    });
  };

  const closeModal = () => {
    setResult(null);
    moveToList({ page: 1 });
  };
  return (
    <div
      style={{
        border: '1px solid black',
        marginTop: '10px',
        margin: '10px',
        padding: '10px',
      }}
    >
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
          <div
            style={{
              width: '20%',
              fontWeight: 'bold',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            Product Name
          </div>
          <input
            style={{
              width: '80%',
              padding: '10px',
              borderRadius: '24px',
              border: 'none',
            }}
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
          <div
            style={{
              width: '20%',
              fontWeight: 'bold',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            DESC
          </div>
          <textarea
            style={{
              width: '80%',
              padding: '10px',
              borderRadius: '24px',
              border: 'none',
            }}
            name="pdesc"
            rows={4}
            value={product.pdesc}
            onChange={handleChangeProduct}
          >
            {product.pdesc}
          </textarea>
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
          <div
            style={{
              width: '20%',
              fontWeight: 'bold',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            Price
          </div>
          <input
            style={{
              width: '80%',
              padding: '10px',
              borderRadius: '24px',
              border: 'none',
            }}
            name="price"
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
          <div
            style={{
              width: '20%',
              fontWeight: 'bold',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            Files
          </div>
          <input
            style={{
              width: '80%',
              padding: '10px',
              borderRadius: '24px',
              border: 'none',
            }}
            ref={uploadRef}
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
          <div
            style={{
              width: '20%',
              fontWeight: 'bold',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            <button
              type="button"
              style={{
                padding: '10px',
                backgroundColor: 'blue',
                color: 'white',
              }}
              onClick={handleClickAdd}
            >
              ADD
            </button>
          </div>
        </div>
      </div>
      {fetching ? <FetchingModal /> : <></>}
      {result ? (
        <ResultModal
          callbackFn={closeModal}
          title={'Product Add Result'}
          content={`${result}번 상품 등록 완료`}
        ></ResultModal>
      ) : (
        <></>
      )}
    </div>
  );
};

export default AddComponent;
