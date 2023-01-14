import React, { useEffect, useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { notesService } from '../api/noteService'
import RadioButton from './RadioButton';
import { NoteStatus } from '../types/note';
import Pass from './Pass';
// https://www.npmjs.com/package/sanitize-html
import sanitizeHtml from 'sanitize-html';

const Note = () => {
  const [value, setValue] = useState<string>('');
  const [noteStatus, setNoteStatus] = useState<NoteStatus>(NoteStatus.PRIVATE);
  const [values, setValues] = useState<string[]>([]);
  const [pass1, setPass1] = useState<string>("");
  const [pass2, setPass2] = useState<string>("");
  const [letSend, setLetSend] = useState<boolean>(false);

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
    const analisePass = (pass: string) => {
      pass.length < 6 && setLetSend(false)
      return true
    }
    if (pass1 !== pass2) {

    }
  }

  const handleSave = () => {
    const clean = sanitizeHtml(value)
    notesService.create(clean, noteStatus,)
    setValues(prev => [...prev, value])
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


  return (
    <>
      <ReactQuill theme="snow" value={value} onChange={setValue} />
      <button onClick={handleSave}>SAVE</button>
      <RadioButton onClick={e => {
        setLetSend(false);
        setNoteStatus(NoteStatus.PRIVATE_ENCODED);
      }} name={'PRIVATE_ENCODED'} />
      <RadioButton onClick={e => {
        setLetSend(true)
        setNoteStatus(NoteStatus.PRIVATE)
      }} name={'PRIVATE'} />
      <RadioButton onClick={e => {
        setLetSend(true)
        setNoteStatus(NoteStatus.PUBLIC)
      }} name={'PUBLIC'} />
      {noteStatus ===

        NoteStatus.PRIVATE_ENCODED &&
        <>
          <Pass onChange={e => setPass1(e.currentTarget.value)} label={'Password'} />
          <Pass onChange={e => setPass2(e.currentTarget.value)} label={'Confirm Password'} />
        </>
      }
      <br></br>
      noteStatus={noteStatus}
      <br />
      letSend={letSend.toString()}
      <br />
      pass1={pass1}
      <br />
      pass2={pass2}


      {values.map((v, i) => <div onClick={() => show(v)} key={i}>NOTATKA NUMER:{i}
        <br></br>
        <br></br>
        {v}<br></br>
        <br></br></div>)}
    </>)
}

export default Note