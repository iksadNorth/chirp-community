import { getToken, isNotBlank, getCookie, addBaseUrl } from '../utils';

export function request(url, options) {
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

    // 요청 설정 로깅.
    console.log(options);
  
    // 요청 보내기
    return fetch(url, options)
      .then(response => {
        //// 응답 후 사후 처리 작업 수행

        // 응답 json형태로 타입 변경.
        const status = response.ok;
        response = response.json();

        // Ok 답이 안오면 오류 메세지 받아서 넘김.
        if (!status) {
          return response.then(err => {
            throw err;
          })
        }

        // 응답 내용 로깅.
        response.then(res => {
          console.log('응답 내용: \n', res);
        });
        
        return response;
      })
      .catch((error) => {
        //// 오류 발생 시, 오류 일괄 처리
        
        // 에러 로깅.
        console.log('에러 발생: \n', error);

        return Promise.reject(error);
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
  