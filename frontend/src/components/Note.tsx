import React, { useEffect, useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { notesService } from '../api/noteService';
import { NoteStatus, NoteType } from '../types/note';
import Pass from './Pass';
import RadioButton from './RadioButton';
// https://www.npmjs.com/package/sanitize-html
import sanitizeHtml from 'sanitize-html';
import Navigation from './Navigation';
import OneNote from './OneNote';




const Note = () => {
  const [value, setValue] = useState<string>('');
  const [noteStatus, setNoteStatus] = useState<NoteStatus>(NoteStatus.PRIVATE);
  const [values, setValues] = useState<NoteType[]>([]);
  const [pass1, setPass1] = useState<string>("");
  const [pass2, setPass2] = useState<string>("");
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
    notesService.getAll().then(res => {
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
      <h2 className="mb-4 text-4xl tracking-tight font-bold text-gray-900 dark:text-white"> Kliknij na notatke i zobacz text</h2>



      {noteStatus ===

        NoteStatus.PRIVATE_ENCODED &&
        <>
          <Pass onChange={e => setPass1(e.currentTarget.value)} label={'Password'} />
          <Pass onChange={e => setPass2(e.currentTarget.value)} label={'Confirm Password'} />
        </>
      }



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

export default Note