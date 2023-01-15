import React, { useEffect, useState } from 'react'
import { authService } from '../api/authService'
import { Auth } from '../types/auth'
import delay from '../utils/login'
import Navigation from './Navigation'
import sanitize from 'sanitize-html'

type Props = {}

const Register = (props: Props) => {
    const [pass1, setPass1] = useState<string>("")
    const [user, setUser] = useState<string>("")
    const [email, setEmail] = useState<string>("")
    const [wait, setWait] = useState<boolean>(false)
    const [registerError, setRegisterError] = useState<boolean>(false)

    useEffect(() => {
        console.log("pass1", pass1)
        return () => {
        }
    }, [pass1])




    function handleSubmit(e: React.FormEvent<HTMLFormElement>): void {
        console.log("f")
        e.preventDefault()

        if (pass1.length < 6 || user.length < 6 || email.length < 6) {
            return;
        }
        const credentials: Auth = {
            email: sanitize(email),
            username: sanitize(user),
            password: sanitize(user),
        }
        authService.register(credentials).catch(e => setRegisterError(true))

        setWait(true)
        delay(5000).then(() => {
            setWait(false)
        }

        );

    }

    return (
        <>
            <Navigation />
            <form onSubmit={e => handleSubmit(e)}>
                <div className="mb-6">
                    <label htmlFor="email" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your email</label>
                    <input onChange={e => setEmail(e.currentTarget.value)} type="email" id="email" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="name@gmail.com" required></input>
                </div>
                <div className="mb-6">
                    <label htmlFor="text" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your <b>Username</b></label>
                    <input onChange={e => setUser(e.currentTarget.value)} type="text" id="email" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="username" required></input>
                </div>
                <div className="mb-6">
                    <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your <b>Password</b></label>
                    <input onChange={e => setPass1(e.currentTarget.value)} type="password" id="password" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required></input>
                </div>
                <button type="submit" className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Submit</button>
            </form>
            {wait &&
                <>
                    <br />
                    <big>Prosze czkac...</big>

                </>}
            {
                registerError && 'Rejestracja się nie powiodła'
            }
        </>
    )
}

export default Register