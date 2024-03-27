import { createSlice } from '@reduxjs/toolkit';

const initState = {
  email: '',
};

const loginSlice = createSlice({
  name: 'loginSlice',
  initialState: initState,
  reducers: {
    login: (state, action) => {
      // state 기존 상태, action 은 내가 처리하고 싶은 데이터
      console.log('login..........', action);
      return {
        email: action.payload.email,
      };
    },
    logout: () => {
      console.log('logout..........');

      return {
        ...initState,
      };
    },
  },
});

export const { login, logout } = loginSlice.actions;

export default loginSlice.reducer;
