import { loginPost } from '@/api/MemberApi';
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';

const initState = {
  email: '',
};

export const loginPostAsync = createAsyncThunk('loginPostAsync', (param) =>
  loginPost(param),
);

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
  extraReducers: (builder) => {
    builder
      .addCase(loginPostAsync.fulfilled, (state, action) => {
        console.log('fulfilled');
        const payload = action.payload;

        return payload;
      })
      .addCase(loginPostAsync.pending, (state, action) => {
        console.log('pending');
      })
      .addCase(loginPostAsync.rejected, (state, action) => {
        console.log('rejected');
      });
  },
});

export const { login, logout } = loginSlice.actions;

export default loginSlice.reducer;
