import { useNavigate } from "react-router-dom"
import Button from "./Button"
import { authService } from "../api/authService"


type Props = {
    href: string,
    name: string,
    logout?: boolean
}

type Lang = {
    pl: string,
    eng: string,
    href: string,
    logout?: boolean,
}
// TODO: navigation + login state
// const NavigationItem = (props: Props) => {
//     const { cmdLogout } = useGlobalDispatch()
//     return (
//         <li>
//             <a onClick={e => props.logout && cmdLogout()} href={props.href} className="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-white dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">{props.name}</a>
//         </li>
//     )
// }

const Navigation = () => {
    // const { lang } = useLang()
    // const [isAuth] = useSelector((s: GlobalState) => {
    //     return [s.isAuth]
    // })
    // const { cmdLogout } = useGlobalDispatch()
    const navigate = useNavigate()
    const items = [{ href: '/notes', name: 'Notatki' }, { href: '/images', name: 'Moje zdjęcia' }, { href: '/create', name: 'Stwórz notatki' }, { href: '/public', name: 'Publiczne notatki' }, { href: '/login', name: 'Zaloguj' }, { href: '/register', name: 'Zarejestruj' }]

    return (
        <>
            <nav className="bg-white px-2 sm:px-4 py-2.5 dark:bg-gray-900 fixed w-full z-20 top-0 left-0 border-b border-gray-200 dark:border-gray-600">
                <div className="container flex flex-wrap items-center justify-between mx-auto">
                    <div className="items-center justify-between hidden w-full md:flex md:w-auto md:order-1" id="navbar-sticky">
                        <ul className="flex flex-col p-4 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                            {/* {items.map((p) => {
                                // return <NavigationItem key={p.href} href={p.href} name={p.name} />
                            })} */}

                            {
                                items.map(i => <li key={i.href}>
                                    <a href={i.href} className="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 md:dark:hover:text-white dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">{i.name}</a>
                                </li>)
                            }
                            <Button onClick={() => {
                                authService.logout()
                                navigate("/login")
                            }}>Wyloguj</Button>
                        </ul>
                        {/* <Button onClick={e => isAuth && cmdLogout()} variant='outline'><Link to={isAuth ? "/widgets" : "/login"}>{isAuth ? lang.logout : lang.login}</Link></Button> */}

                    </div>
                </div>
            </nav>
            <br />
            <br />
            <br />
            <br />

        </>
    )
}

export default Navigation