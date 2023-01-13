import axios from 'axios'

export const backendApi = (url:string) => {
  let client = axios.create({
    baseURL: process.env.REACT_APP_BACKEND + url,
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json'
    },
  })

  client.interceptors.response.use(response => {
    return response;
  }, function (error) {
    console.log('An error occurred while calling backend', error)
    if (error.response.status === 404) {
      return {status: error.response.status};
    }
    return Promise.reject(error.response);
  });

  return client;
}