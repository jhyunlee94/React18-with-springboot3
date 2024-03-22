import axios from 'axios';
import { API_SERVER_HOST } from './TodoApi';

const host = `${API_SERVER_HOST}/api/products`;

export const postAdd = async (product: any) => {
  const header = {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  };

  const res = await axios.post(`${host}/`, product, header);

  return res.data;
};
