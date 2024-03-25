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

export const getList = async (pageParam: any) => {
  const { page, size } = pageParam;
  const res = await axios.get(`${host}/list`, {
    params: {
      page,
      size,
    },
  });

  return res.data;
};

export const getOne = async (pno: any) => {
  const res = await axios.get(`${host}/${pno}`);

  return res.data;
};

export const deleteOne = async (pno: any) => {
  const res = await axios.delete(`${host}/${pno}`);
  return res.data;
};

export const putOne = async (pno: any, product: any) => {
  const header = {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  };

  const res = await axios.put(`${host}/${pno}`, product, header);

  return res.data;
};
