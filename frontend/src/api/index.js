import { getToken } from '../utils';

function addBaseUrl(url) {
  return `http://localhost:8080${url}`;
}

// function getCsrfToken() {  
//   // proxy url 설정.
//   url = addBaseUrl('/api/v1/auth/csrf-token');

//   // 요청 보내기
//   return fetch(url, modifiedOptions)
//     .then(response => {        
//       return response.headers['csrf-token'];
//     })
//     .catch((error) => {
//       console.log('에러 발생: \n', error);
//       return Promise.reject(error);
//     });
// }

export function request(url, options) {
    // 옵션 추가.
    const modifiedOptions = {
        ...options,
        headers: {
            ...options.headers,
            // 요청 시, 데이터 형식은 json
            'Content-Type': 'application/json',
        },
    };

    // proxy url 설정.
    url = addBaseUrl(url);

    // JWT 토큰 추가.
    const token = getToken();
    if (token != null) {
        modifiedOptions.headers.Authorization = `Bearer ${token}`;
    }
  
    // 요청 보내기
    return fetch(url, modifiedOptions)
      .then(response => {
        //// 응답 후 사후 처리 작업 수행

        // 응답 json형태로 타입 변경.
        response = response.json();

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

  export function get(url, options) {
    // 메서드 추가.
    const modifiedOptions = {
        ...options,
        method: 'GET',
      };

    return request(url, modifiedOptions);
  }

  export function post(url, options) {
    // 메서드 추가.
    const modifiedOptions = {
        ...options,
        method: 'POST',
      };

    return request(url, modifiedOptions);
    }

  export function patch(url, options) {
    // 메서드 추가.
    const modifiedOptions = {
        ...options,
        method: 'PATCH',
      };
      
    return request(url, modifiedOptions);
  }

  export function del(url, options) {
    // 메서드 추가.
    const modifiedOptions = {
        ...options,
        method: 'DELETE',
      };

    return request(url, modifiedOptions);
  }
  