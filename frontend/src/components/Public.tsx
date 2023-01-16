import { useEffect, useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { notesService } from '../api/noteService';
import { NoteStatus, NoteType } from '../types/note';
import Pass from './Pass';
import sanitizeHtml from 'sanitize-html';
import Navigation from './Navigation';
import OneNote from './OneNote';
import Button from './Button';
import Image from './Image';
import axios from 'axios';
import sanitize from 'sanitize-html';




const Public = () => {
    const [value, setValue] = useState<string>('');
    const [noteStatus, setNoteStatus] = useState<NoteStatus>(NoteStatus.PRIVATE);
    const [values, setValues] = useState<NoteType[]>([]);
    const [pass1, setPass1] = useState<string>("");
    const [pass2, setPass2] = useState<string>("");
    const [imgs, setImgs] = useState<any[]>([]);
    const [img, setImg] = useState<any>();
    const [imgLink, setImgLink] = useState<string>("");
    const [letSend, setLetSend] = useState<boolean>(true);

    useEffect(() => {
        if (noteStatus !== NoteStatus.PRIVATE_ENCODED) {
            setPass1('')
            setPass2('')
        }

        return () => {
        }
    }, [noteStatus])


    // TODO
    const checkPass = () => {
        if (noteStatus === NoteStatus.PRIVATE_ENCODED) {
            if ((pass1 !== pass2) || (pass1.length < 6)) {
                setLetSend(false)
            }
        }

    }

    const handleSave = () => {
        checkPass()
        const clean = sanitizeHtml(value)
        // console.log(letSend)
        console.log(clean)
        console.log(noteStatus)
        console.log(pass1)
        notesService.create(clean, noteStatus, pass1)
        setValue('')
    }

    const show = (htmlContent: string) => {
        setValue(htmlContent)
    }

    useEffect(() => {
        console.log(value)

        return () => {

        }
    }, [value])

    useEffect(() => {
        notesService.getPublic().then(res => {
            setValues([])
            res.data.forEach((p: any) => {
                console.log(p)
                const note: NoteType = { id: p.id, username: p.username, text: p.note, status: p.noteStatus }
                setValues(prev => [...prev, note])
            });
        })

        return () => {
        }
    }, [])



    return (
        <>
            <Navigation />
            <ReactQuill theme="snow" value={value} onChange={setValue} />
            <br />
            <br />
            <h2 className="mb-4 text-4xl tracking-tight font-bold text-gray-900 dark:text-white"> Kliknij na notatke i zobacz text</h2>



            {noteStatus ===

                NoteStatus.PRIVATE_ENCODED &&
                <>
                    <Pass onChange={e => setPass1(e.currentTarget.value)} label={'Password'} />
                    <Pass onChange={e => setPass2(e.currentTarget.value)} label={'Confirm Password'} />
                </>
            }
            <br />
            <div className="mb-6 p-3 b-7">
                <label htmlFor="text" className="block mb-2  text-sm font-medium text-gray-900 dark:text-white">Your <b>Username</b></label>
                <input onChange={e => setImgLink(e.currentTarget.value)} type="text" id="email" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Zgadnij obrazek po nazwie" required></input>
                <Button onClick={() => {
                    setImg(null)
                    axios.get(`http://localhost:8080/image/${sanitize(imgLink)}`).then(response => {
                        // setImg(response.config.url)
                        setImgs((prev: any) => [...prev, response.config.url])
                        console.log(response.config.url)
                    });
                }} >Zatwierdź nazwę</Button>
                <br />
                <br />
                {/* {img && < Image src={img} />} */}
                {imgs && imgs.map((i: string | undefined) =>


                    <div className="flex flex-row-reverse  ">
                        <img className='w-10 h-10 border-2 border-white rounded-full dark:border-gray-800' src={i} />
                    </div>




                )}
            </div>

            {values.map((v, i) =>

                <section key={i} className="border-8 bg-white dark:bg-gray-900">
                    <div onClick={() => setValue(v.text || "")} className="py-8 px-4 mx-auto max-w-screen-xl lg:py-16 lg:px-6">
                        <OneNote id={v.id} text={v.text} username={v.username} status={v.status} />
                    </div>
                </section>
            )
            }

        </>
    )
}

export default Public