import axios from 'axios';
import { useEffect, useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { default as sanitize } from 'sanitize-html';
import { notesService } from '../api/noteService';
import { NoteType } from '../types/note';
import Button from '../components/Button';
import Navigation from '../components/Navigation';
import OneNote from '../components/OneNote';

const Public = () => {
    const [value, setValue] = useState<string>('');
    const [values, setValues] = useState<NoteType[]>([]);
    const [imgs, setImgs] = useState<any[]>([]);
    const [imgLink, setImgLink] = useState<string>("");
    const [noImg, setNoImg] = useState<boolean>();

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
            <h2 className="mb-4 text-4xl tracking-tight font-bold text-gray-900 dark:text-white"> Kliknij na notatke i zobacz text w Markdownie</h2>
            <br />
            <div className="mb-6 p-3 b-7">
                <label htmlFor="text" className="block mb-2  text-sm font-medium text-gray-900 dark:text-white">Zgadnij <b>Obrazek</b> po nazwie pliku</label>
                <input onChange={e => setImgLink(e.currentTarget.value)} type="text" id="email" className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Zgadnij obrazek po nazwie" required></input>
                <Button onClick={() => {
                    setNoImg(false)
                    axios.get(`${process.env.REACT_APP_BACKEND}/image/${sanitize(imgLink)}`).then(response => {
                        setImgs((prev: any) => [...prev, response.config.url])
                        console.log(response.config.url)
                    }).catch(e => { setNoImg(true) });
                }} >Zatwierdź nazwę</Button>
                <br />
                {noImg && 'Nie znaleziono'}
                <br />
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