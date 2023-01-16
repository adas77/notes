import { useEffect, useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { notesService } from '../api/noteService';
import { NoteType } from '../types/note';
import Navigation from '../components/Navigation';
import OneNote from '../components/OneNote';

const Note = () => {
  const [value, setValue] = useState<string>('');
  const [values, setValues] = useState<NoteType[]>([]);

  useEffect(() => {
    notesService.getAll().then(res => {
      setValues([])
      res.data.forEach((p: any) => {
        const note: NoteType = { id: p.id, username: p.username, text: p.note, status: p.noteStatus, date: p.date }
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
      <h2 className="mb-4 text-4xl tracking-tight font-bold text-gray-900 dark:text-white"> Kliknij na notatke i zobacz text</h2>
      {values.map((v, i) =>
        <section key={i} className="border-8 bg-white dark:bg-gray-900">
          <div onClick={() => setValue(v.text || "")} className="py-8 px-4 mx-auto max-w-screen-xl lg:py-16 lg:px-6">
            <OneNote id={v.id} date={v.date} text={v.text} username={v.username} status={v.status} />
          </div>
        </section>
      )
      }
    </>
  )
}

export default Note