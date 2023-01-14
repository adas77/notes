import axios from 'axios'

// export default function authHeader() {
//   const localStorageUser = localStorage.getItem('user')
//   if (localStorageUser) {
//       const user = JSON.parse(localStorageUser);
//       if (user && user.accessToken) {
//           return { Authorization: BEARER + user.accessToken };
//       }
//   }
//   else {
//       return {};
//   }
// }

// export default function authHeader() {
//   const BEARER = 'Bearer '
//   const localStorageUser = localStorage.getItem('user')
//   if (localStorageUser) {
//     const user = JSON.parse(localStorageUser);
//     if (user && user.accessToken) {
//       return { Authorization: BEARER + user.accessToken };
//     }
//     else { }
//   }
//   else {
//     return {}
//   }
// }

export const authHeader = () => {
  const BEARER = 'Bearer '
  const localStorageUser = localStorage.getItem('user')
  console.log("f")
  console.log(localStorage)
  console.log(localStorageUser)
  if (localStorageUser) {
    const user = JSON.parse(localStorageUser);
    console.log("user ", user)
    console.log("akcesToke", user.accessToken)


    if (user) {
      // return BEARER + user
      console.log("gsegse", user)
      return user
    }
    else return ""
  }
  else {
    return ""
  }
}






export const backendApi = (url: string) => {
  let client = axios.create({
    baseURL: process.env.REACT_APP_BACKEND + url,
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    },
  })

  client.interceptors.response.use(response => {
    return response;
  }, function (error) {
    console.log('An error occurred while calling backend', error)
    if (error.response.status === 404) {
      return { status: error.response.status };
    }
    return Promise.reject(error.response);
  });

  return client;
}