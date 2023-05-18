const header_jwt = 'jwt_token'

export function getToken() {
    const token = localStorage.getItem(header_jwt);
    return token;
}

export function setToken(token) {
    if(isNotBlank(token)) {
        localStorage.setItem(header_jwt, token);
    }
}

export function isNotBlank(token) {
    return (token !== null && token !== undefined)
}

// 쿠키에서 값을 가져오는 함수
export function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) {
        return parts.pop().split(';').shift();
    }
}

export function pageRequest(page, size, sort_field=null, isAsc=true) {
    const query = {
        size: size,
        page: page
    };
    if(sort_field != null) {
        query.sort = [`${sort_field},${isAsc ? 'asc' : 'desc'}`];
    }

    return query;
}

export function addParams(url, params) {
    let url_obj = new URL(addBaseUrl(url));
    Object.keys(params).forEach(key => url_obj.searchParams.append(key, params[key]));
    return url_obj.pathname + url_obj.search;
}
  
export function addBaseUrl(url) {
    return `http://localhost:8080${url}`;
}
