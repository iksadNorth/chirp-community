const header_jwt = 'jwt_token'

export function getToken() {
    const token = localStorage.getItem(header_jwt);
    return token;
}

export function setToken(token) {
    localStorage.setItem(header_jwt, token);
}
  