import axios from 'axios';

export const API_SERVER_HOST = 'http://localhost:8080';

const prefix = `${API_SERVER_HOST}/api/todo`;

export const getOne = async (tno: any) => {
  const res = await axios.get(`${prefix}/${tno}`);
  console.log('res', res);
  return res.data;
};

export const getList = async (pageParam: any) => {
  const { page, size } = pageParam;

  const res = await axios.get(`${prefix}/list`, {
    params: {
      //   ...pageParam,
      page,
      size,
    },
  });

  return res.data;
};

export const postAdd = async (todoObj: any) => {
  // JSON.stringify(obj) => JSON 문자열
  console.log('todoObj-axios', todoObj);
  const res = await axios.post(`${prefix}/`, todoObj);

  return res.data;
};

export const deleteOne = async (tno: any) => {
  const res = await axios.delete(`${prefix}/${tno}`);
  return res.data;
};

export const putOne = async (todo: any) => {
  const res = await axios.put(`${prefix}/${todo.tno}`, todo);

  return res.data;
};
