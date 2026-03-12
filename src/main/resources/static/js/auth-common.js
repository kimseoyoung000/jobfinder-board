(function () {
  const ctx = window.location.pathname.startsWith('/finalProject') ? '/finalProject/' : '/';
  const reissueUrl = ctx + 'auth/reissue';

  function getAccessToken() {
    return sessionStorage.getItem('accessToken');
  }

  function setAccessToken(token) {
    if (!token) return;
    sessionStorage.setItem('accessToken', token);
  }

  function clearAccessToken() {
    sessionStorage.removeItem('accessToken');
    sessionStorage.removeItem('refreshToken');
    localStorage.removeItem('loginUser');
    localStorage.removeItem('JWT');
  }

  async function reissueAccessToken() {
      console.log('[JPAuth] accessToken 재발급 요청');

      const refreshToken = sessionStorage.getItem('refreshToken');

      if (!refreshToken) {
        console.warn('[JPAuth] refreshToken 없음');
        clearAccessToken();
        throw new Error('NO_REFRESH_TOKEN');
      }

      const response = await fetch(reissueUrl + '?refreshToken=' + encodeURIComponent(refreshToken), {
        method: 'POST',
        headers: {
          'Accept': 'application/json'
        },
        credentials: 'same-origin'
      });

	  const contentType = response.headers.get('content-type') || '';
	      const data = contentType.includes('application/json') ? await response.json() : null;

	      if (!response.ok || !data || !data.accessToken) {
	        console.warn('[JPAuth] accessToken 재발급 실패', data);
	        clearAccessToken();
	        throw new Error((data && data.error) ? data.error : 'REISSUE_FAILED');
	      }

	      setAccessToken(data.accessToken);

	      if (data.refreshToken) {
	        sessionStorage.setItem('refreshToken', data.refreshToken);
	      }

	      console.log('[JPAuth] accessToken 재발급 성공');

    return data.accessToken;
  }

  async function authFetch(url, options = {}) {
    const originalOptions = { ...options };
    const originalHeaders = { ...(options.headers || {}) };

    let token = getAccessToken();

    const attemptRequest = async (accessToken) => {
      const headers = { ...originalHeaders };

      if (accessToken) {
        headers['Authorization'] = 'Bearer ' + accessToken;
      }

      return fetch(url, {
        ...originalOptions,
        headers
      });
    };

    let response = await attemptRequest(token);

    if (response.status !== 401) {
      return response;
    }

    console.warn('[JPAuth] 401 감지 - 재발급 시도');

    token = await reissueAccessToken();
    response = await attemptRequest(token);

    return response;
  }

  async function moveWithAuthCheck(event, url, checkUrl) {
    event.preventDefault();

    const response = await authFetch(checkUrl, {
      method: 'GET',
      headers: {
        'Accept': 'application/json'
      }
    });

    if (!response.ok) {
      throw new Error('AUTH_CHECK_FAILED');
    }

    const token = getAccessToken();

    const redirectUrl = url + '?token=' + encodeURIComponent(token);

    window.location.href = redirectUrl;

    return false;
  }

    window.JPAuth = {
      getAccessToken,
      setAccessToken,
      clearAccessToken,
      reissueAccessToken,
      authFetch,
      moveWithAuthCheck
    };
  })();