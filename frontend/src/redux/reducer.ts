import { AUTH_LOGIN, AUTH_LOGOUT, GlobalState, Action, AuthDispatchType } from '../types/storeActionType'
// import user1 from '../data/auth'

const initialState: GlobalState = {
    isAuth: false,
}

const reducer = (state: GlobalState = initialState, action: Action): GlobalState => {
    switch (action.type) {



        case AUTH_LOGIN:
            //TODO:
            if (false) {
                return state
            }
            let authLogin: GlobalState = {
                ...state,
                isAuth: true,
            }
            return authLogin

        case AUTH_LOGOUT:
            let authLoout: GlobalState = {
                ...state,
                isAuth: false,
            }
            return authLoout

    }
    return state
}

export default reducer