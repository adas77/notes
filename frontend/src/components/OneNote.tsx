import { useState } from 'react'
import { NoteStatus, NoteType } from '../types/note'
import Button from './Button'
import Image from './Image'
import { notesService } from '../api/noteService'
import sanitize from 'sanitize-html'



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
                            props.id && notesService.getById(props.id, sanitize(pass)).then(res => {
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


    )
}

export default OneNote