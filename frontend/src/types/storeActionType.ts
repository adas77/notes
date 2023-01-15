export const AUTH_LOGIN = "AUTH_LOGIN"
export const AUTH_LOGOUT = "AUTH_LOGOUT"

export type GlobalState = {
    isAuth: boolean,
}

export type Action = {
    type: string
    username?: string
    pass?: string
}

export type AuthDispatchType = (args: Action) => Action