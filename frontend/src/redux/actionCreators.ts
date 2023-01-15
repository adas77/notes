
import { useDispatch } from "react-redux";
import { AUTH_LOGIN, AUTH_LOGOUT } from '../types/storeActionType';



const useGlobalDispatch = () => {
    const dispatch = useDispatch()

    const cmdLogin = (username: string, pass: string) => {
        dispatch({
            type: AUTH_LOGIN,
            username: username,
            pass: pass
        })
    }

    const cmdLogout = () => {
        dispatch({
            type: AUTH_LOGOUT,
        })
    }


    return { cmdLogin, cmdLogout }

}

export default useGlobalDispatch