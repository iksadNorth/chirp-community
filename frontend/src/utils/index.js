import { format } from 'date-fns';
import { v4 as uuidv4 } from 'uuid';

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

export function delToken() {
    localStorage.removeItem(header_jwt);
}

export function isNotBlank(token) {
    return (token !== null && token !== undefined)
}

export function hasSomethingInString(str) {
    return (isNotBlank(str) && str.trim()!=='');
};

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
    return `http://localhost:80${url}`;
}

export function decodeBase64Url(str) {
    // Base64Url을 Base64로 변환
  str = str.replace(/-/g, "+").replace(/_/g, "/");
  
  // Base64 디코딩
  return atob(str);
  }
  
export function decodeJwt() {
    const token = getToken();
    return decodeJwtWithArg(token);
}

export function decodeJwtWithArg(token) {
    if(!isNotBlank(token)) { return {}; }
    const parts = token.split(".");
    const payload = parts[1];
    const decodedPayload = decodeBase64Url(payload);
    const decodedData = JSON.parse(decodedPayload);
    return decodedData;
}

export function adapterEvent(setFunc) {
    return (event) => 
        setFunc(event.target.value);
};

export function adapterCheckBoxEvent(setFunc) {
    return (event) => 
        setFunc(event.target.checked);
};

export function toDate(isoDateTimeString) {
    return format(new Date(isoDateTimeString), "yy/MM/dd").toString();
};

export function generateUUID() {
    return uuidv4();
};

export function getRandomColor() {
    const colors = [
        "\x1b[31m", "\x1b[32m", "\x1b[34m", "\x1b[35m", "\x1b[36m",
        "\x1b[41m", "\x1b[42m", "\x1b[44m", "\x1b[45m", "\x1b[46m",
    ];
    const randomIndex = Math.floor(Math.random() * colors.length);
    return colors[randomIndex];
};

export function withRandomColor(text) {
    const randomColor = getRandomColor();
    return `${randomColor}${text}\x1b[0m`;
};
