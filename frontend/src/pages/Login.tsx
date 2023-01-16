import React, { useEffect, useState } from 'react'
import { authService } from '../api/authService'
import { Auth } from '../types/auth'
import delay from '../utils/login'
import Navigation from '../components/Navigation'
import sanitize from 'sanitize-html'
import Button from '../components/Button'
import { Navigate, useLocation, useNavigate } from 'react-router-dom'

type Props = {}

const Login = (props: Props) => {
    const location = useLocation();
    const navigate = useNavigate();

    const [pass1, setPass1] = useState<string>("")
    const [user, setUser] = useState<string>("")
    const [wait, setWait] = useState<boolean>(false)
    const [loginFailed, setLoginFailed] = useState<boolean>(false)
    const [loggedIn, setLoggedIn] = useState<boolean>(false)

    useEffect(() => {
        console.log(localStorage)
        // if (localStorage.getItem("user")) {
        //     const { from } = location.state || { from: { pathname: "/" } };
        //     navigate(from, { replace: true });
        // }
    }, [localStorage]);

    useEffect(() => {
        console.log("pass1", pass1)
        return () => {
        }
    }, [pass1])


    function handleSubmit(e: React.FormEvent<HTMLFormElement>): void {
        console.log("f")
        e.preventDefault()

        if (pass1.length < 6 || user.length < 6) {
            return;
        }
        const credentials: Auth = {
            username: sanitize(user),
            password: sanitize(pass1),
        }
        authService.login(credentials).then(res => setLoggedIn(true)).catch(e => setLoginFailed(true))

        setWait(true)
        delay(5000).then(() => {
            setWait(false)
        })
    }

    return (
        <>
            {loggedIn && <Navigate to="/notes" replace state={{ from: location }} />}

            <Navigation />
            <form onSubmit={e => handleSubmit(e)}>
                <div className="mb-6">
                    <label htmlFor="text" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your <b>Username</b></label>
                    <input onChange={e => setUser(e.currentTarget.value)} type="text" id="email" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="username" required></input>
                </div>
                <div className="mb-6">
                    <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your <b>Password</b></label>
                    <input onChange={e => setPass1(e.currentTarget.value)} type="password" id="password" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required></input>
                </div>
                <Button type='submit'>Submit</Button>
            </form>
            {wait &&
                <>
                    <br />
                    <big>Prosze czkac...</big>

                </>}
            {loginFailed ? 'Logowanie się nie powiodło'
                :
                ''
            }
        </>
    )
}

export default Login