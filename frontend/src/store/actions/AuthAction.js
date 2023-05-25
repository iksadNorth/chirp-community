export const LOGIN = 'AUTH/LOGIN';
export const LOGOUT = 'AUTH/LOGOUT';

export function login(token) {
    return {
        type: LOGIN,
        payload: {
            token: token,
        },
    };
}

export function logout(token) {
    return {
        type: LOGOUT,
    };
}
