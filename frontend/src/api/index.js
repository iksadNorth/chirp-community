import { getToken, isNotBlank, getCookie, addBaseUrl, generateUUID, withRandomColor } from '../utils';

export function request(url, options) {
  // 요청의 고유 ID 설정.
  const ID = withRandomColor(generateUUID().substring(0, 8));

  // proxy url 설정.
  url = addBaseUrl(url);

  // options 구조 설정.
  options = {
    ...options,
    headers: {
      ...options.headers
    },
  }
  
  // 데이터 형식 추가.
  options.headers['Content-Type'] = 'application/json';

  // csrf 토큰 추가.
  options.headers['X-XSRF-TOKEN'] = getCookie('XSRF-TOKEN');
  options.credentials = 'include';

  // JWT 토큰 추가.
  const token = getToken();
  if (isNotBlank(token)) {
    options.headers['Authorization'] = `Bearer ${token}`;
  }

  // 현재 URL 주소 추가.
  const currentPageURL = window.location.href;
  options.headers['current-page-url'] = currentPageURL;
  options.headers['page-session-id'] = localStorage.getItem("page-session-id");

  // 요청 설정 로깅.
  console.log(`[${options.method ?? "GET"}] [ID: ${ID}] \n${url}`);
  console.log(`--> 요청 Header: [ID: ${ID}] \n`, options);

  // 요청 보내기
  return fetch(url, options)
    .then(response => {
      //// For Ok Checking.
      const responseForLogging = response.clone();

      // Ok 답이 안오면 오류로 넘김. 
      // 이 때, 미리 ErrorResponse의 Body에서 errorMessage 필드를 추출하고 throw 함.
      if (!responseForLogging.ok) {
        return responseForLogging.json().then(err => {
          throw new Error(err.errorMessage);
        });
      }
      
      return response;
    })
    .then(response => {
      //// For Logging Body.
      console.log(`<-- 응답 Header: [ID: ${ID}] \n`, response.headers);
      return response; 
    })
    .then(response => {
      //// For Convert To Body. 
      // [Blocking]여기서 응답 받을 때까지 대기함.
      const contentType = response.headers.get('Content-Type');
      if(contentType && contentType.includes('application/json')) {
        return response.json();
      } else {
        return response.text();
      }
    })
    .then(response => {
      //// For Logging Body.
      console.log(`<-- 응답 Body: [ID: ${ID}] \n`, response);
      return response;
    })
    .catch((error) => {
      // 에러 로깅.
      console.error(`??? 에러 발생: [ID: ${ID}] \n`, error.message);

      return Promise.reject(error.message);
    });
}

export function get(url, options={}) {
  // 메서드 추가.
  options.method = 'GET';
  return request(url, options);
}

export function post(url, options={}) {
  // 메서드 추가.
  options.method = 'POST';
  return request(url, options);
  }

export function patch(url, options={}) {
  // 메서드 추가.
  options.method = 'PATCH';      
  return request(url, options);
}

export function del(url, options={}) {
  // 메서드 추가.
  options.method = 'DELETE';
  return request(url, options);
}
  