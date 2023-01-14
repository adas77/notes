import { Auth } from "../types/auth";
import { backendApi } from "./http";



const client = backendApi('/auth')
const BEARER = 'Bearer '

export const authService = {
    login(auth: Auth) {

        console.log('Create auth', auth)
        const res = client.post('/authenticate', { "username": auth.username, "password": auth.password }).then((res) => {
            if (res.data.accesToken) {
                localStorage.setItem("user", JSON.stringify(res.data));
            }
        })
        console.log('res', res)
        return client.post('/authenticate', { "username": auth.username, "password": auth.password })
    },
    register(auth: Auth) {
        console.log('Create auth', auth)
        return client.post('/register', { "username": auth.username, "email": auth.email, "password": auth.password })
    }
    ,
    logout(auth: Auth) {
        localStorage.removeItem("user")
    }


}

export default function authHeader() {
    const localStorageUser = localStorage.getItem('user')
    if (localStorageUser) {

        const user = JSON.parse(localStorageUser);
        if (user && user.accessToken) {
            return { Authorization: BEARER + user.accessToken };
        }
    }
    else {
        return {};
    }


}
