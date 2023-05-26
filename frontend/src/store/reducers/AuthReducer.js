import * as auth from '../actions/AuthAction';
import { decodeJwtWithArg, delToken, isNotBlank } from '../../utils';

const initalState = {
    isLogined: false,
    token: "",

    id: 0,
    nickname: "",
    role: "",
};

const AuthReducer = (state = initalState, action) => {
    const token = action.payload?.token;
    const jsonWithToken = decodeJwtWithArg(token);

    switch (action.type) {
        case auth.LOGIN:
            return {
                ...state,

                isLogined: isNotBlank(token),
                token: token,

                id: jsonWithToken.ID,
                nickname: jsonWithToken.NICKNAME,
                role: jsonWithToken.ROLE,
            };
        case auth.LOGOUT:
            delToken();
            return {
                ...state,
                ...initalState
            };
  
      default:
        return state;
    }
};

export default AuthReducer;
