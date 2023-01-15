import { useState } from 'react'
import { NoteStatus, NoteType } from '../types/note'
import Button from './Button'
import Image from './Image'
import { notesService } from '../api/noteService'



const OneNote = (props: NoteType) => {
    const [pass, setPass] = useState<string>("")
    const [encoded, setEncoded] = useState<string>("")

    return (

        <div className="border-2 p-2 max-w-screen-lg text-gray-500 sm:text-lg dark:text-gray-400">
            {/* <a href='#'> */}
            <div className="flex items-center justify-between m-4">
                <Image src={props.link} alt="undefined" />
            </div>
            <h2 className="mb-4 text-4xl tracking-tight font-bold text-gray-900 dark:text-white"> {props.username}</h2>

            {
                props.status === NoteStatus.PRIVATE_ENCODED ?
                    <>
                        <div className="mb-6">
                            <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Your <b>Password</b></label>
                            <input onChange={e => { setPass(e.currentTarget.value) }} type="password" id="password" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required placeholder='Podaj hasło aby odszyfrować notatkę'></input>
                        </div>
                        <Button onClick={() => {
                            props.id && notesService.getById(props.id, pass).then(res => {
                                console.log("resssss:", res)
                                setEncoded(res.data)
                            }).catch(e => {
                                setEncoded("NIESTESTY NIE UDAŁO SIĘ ODKODOWAĆ")
                            })
                        }}>Zakodowana - Odkoduj</Button>
                        <br />
                        <br />
                        <div className='border-2'>{encoded}</div>
                    </>
                    :
                    <p className="mb-4 font-medium">{props.text}</p>
            }
            
        </div>


        // <div className="w-full max-w-sm bg-white rounded-lg shadow-md dark:bg-gray-800 dark:border-gray-700">
        //     {
        //         <div className="flex items-center justify-between m-4">
        //             <a href="#">
        //                 <Image src={props.link} alt="undefined" />
        //             </a>
        //             <span className="text-3xl font-bold text-gray-900 dark:text-white">{props.username}</span>
        //         </div>}
        //     <div className="px-5 pb-5">
        //         {props.status === NoteStatus.PRIVATE_ENCODED ?
        //             <p>Zaszyfrowane</p>
        //             :
        //             <a href="#">
        //                 <h5 className="text-xl font-semibold tracking-tight text-gray-900 dark:text-white">{props.text}</h5>
        //             </a>
        //         }
        //         {/* <div className="flex items-center mt-2.5 mb-5">
        //             {[...Array(rate)].map((_, i) => <svg key={i} aria-hidden="true" className="w-5 h-5 text-yellow-300" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>{i} star</title><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>)}
        //             {[...Array(MAX_RATE - rate)].map((_, i) => <svg key={i} aria-hidden="true" className="w-5 h-5 text-yellow-30" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>{i} star</title><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>)}
        //         </div> */}
        //         {/* <span className="text font-bold text-gray-900 dark:text-white">{props.date.toUTCString()}</span> */}
        //     </div>
        // </div>
    )
}

export default OneNote