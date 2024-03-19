import axios from 'axios';

export const API_SERVER_HOST = 'http://localhost:8080';

const prefix = `${API_SERVER_HOST}/api/todo`;

export const getOne = async (tno: any) => {
  const res = await axios.get(`${prefix}/${tno}`);
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
