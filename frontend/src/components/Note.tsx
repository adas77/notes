import React, { useEffect, useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { notesService } from '../api/noteService'
import RadioButton from './RadioButton';
import { NoteStatus } from '../types/note';
import Pass from './Pass';

const Note = () => {
  const [value, setValue] = useState<string>('');
  const [noteStatus, setNoteStatus] = useState<NoteStatus>(NoteStatus.PRIVATE);
  const [values, setValues] = useState<string[]>([]);
  const [pass1, setPass1] = useState<string>("");
  const [pass2, setPass2] = useState<string>("");
  const [letSend, setLetSend] = useState<boolean>(true);

  const checkPass = () => {
    const analisePass = (pass: string) => {
      if (pass.length < 6) { return false }
      return true
    }
    if (pass1 !== pass2) {

    }
  }

  const handleSave = () => {
    notesService.create(value, noteStatus,)
    setValues(prev => [...prev, value])
    setValue('')
  }

  const show = (htmlContent: string) => {
    // setValues(prev => [...prev, value])
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
        setLetSend(false)
        setNoteStatus(NoteStatus.PRIVATE_ENCODED)
      }} name={'PRIVATE_ENCODED'} />
      <RadioButton onClick={e => setNoteStatus(NoteStatus.PRIVATE)} name={'PRIVATE'} />
      <RadioButton onClick={e => setNoteStatus(NoteStatus.PUBLIC)} name={'PUBLIC'} />
      {noteStatus ===
        NoteStatus.PUBLIC &&
        <>
          <Pass label={'Password'} />
          <Pass label={'Confirm Password'} />
        </>
      }
      <br></br>
      noteStatus={noteStatus}
      <br />
      letSend={letSend.toString()}

      {/* <div className="flex items-center mb-4">
        <input id="default-radio-1" type="radio" value="" name="default-radio" className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"></input>
        <label htmlFor="default-radio-1" className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Default radio</label>
      </div>
      <div className="flex items-center mb-4">
        <input id="default-radio-1" type="radio" value="" name="default-radio" className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"></input>
        <label htmlFor="default-radio-1" className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Default radio</label>
      </div><div className="flex items-center mb-4">
        <input id="default-radio-1" type="radio" value="" name="default-radio" className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"></input>
        <label htmlFor="default-radio-1" className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Default radio</label>
      </div> */}
      {/* <div className="flex items-center">
        <input checked id="default-radio-2" type="radio" value="" name="default-radio" className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"></input>
        <label htmlFor="default-radio-2" className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Checked state</label>
      </div> */}
      {/* <div className="flex items-center">
        <input checked id="default-radio-2" type="radio" value="" name="default-radio" className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"></input>
        <label htmlFor="default-radio-2" className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Checked state</label>
      </div> */}

      {values.map((v, i) => <div onClick={() => show(v)} key={i}>NOTATKA NUMER:{i}
        <br></br>
        <br></br>
        {v}<br></br>
        <br></br></div>)}
    </>)
}

export default Note