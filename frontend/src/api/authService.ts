import axios from "axios";
import { Auth } from "../types/auth";
import { backendApi } from "./http";



const client = backendApi('/auth')

export const authService = {
    login(auth: Auth) {

        console.log('Create auth', auth)
        const response = client.post('/authenticate', { "username": auth.username, "password": auth.password }).then((res) => {
            const { token } = res.data
            if (token) {
                localStorage.setItem("user", JSON.stringify(token));
            }
            console.log("sef", localStorage.getItem("user"))
        })
        // console.log('res', response)
        return client.post('/authenticate', { "username": auth.username, "password": auth.password })
    },
    // async login(auth: Auth) {
    //     const response = await axios
    //         .post("http://localhost:8080/auth/authenticate", {
    //             "username": auth.username, "password": auth.password
    //         });
    //     console.log("ds");
    //     console.log(response.data.accessToken);
    //     const { token } = response.data
    //     if (token) {
    //         localStorage.setItem("token", JSON.stringify(token));
    //         console.log(localStorage.getItem("token"))
    //     }
    //     return response.data;
    // },

    register(auth: Auth) {
        console.log('Create auth', auth)
        return client.post('/register', { "username": auth.username, "email": auth.email, "password": auth.password })
    }
    ,
    logout(auth: Auth) {
        localStorage.removeItem("user")
        localStorage.removeItem("token")
    }


}


