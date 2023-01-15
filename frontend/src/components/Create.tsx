import React, { useEffect, useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { notesService } from '../api/noteService'
import RadioButton from './RadioButton';
import { NoteType, NoteStatus } from '../types/note';
import Pass from './Pass';
// https://www.npmjs.com/package/sanitize-html
import sanitizeHtml from 'sanitize-html';
import { backendApi } from '../api/http';
import Button from './Button';
import Navigation from './Navigation';




const Create = () => {
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
        console.log(clean)
        console.log(noteStatus)
        console.log(pass1)
        notesService.create(clean, noteStatus, pass1)
        setValue('')
    }

    const show = (htmlContent: string) => {
        setValue(htmlContent)
    }




    return (
        <>
            <Navigation />
            <ReactQuill theme="snow" value={value} onChange={setValue} />
            <br />

            <RadioButton onClick={e => {
                setNoteStatus(NoteStatus.PRIVATE_ENCODED);
            }} name={'PRIVATE_ENCODED'} />
            <RadioButton onClick={e => {
                setNoteStatus(NoteStatus.PRIVATE)
            }} name={'PRIVATE'} />
            <RadioButton onClick={e => {
                setNoteStatus(NoteStatus.PUBLIC)
            }} name={'PUBLIC'} />
            {noteStatus ===

                NoteStatus.PRIVATE_ENCODED &&
                <>
                    <Pass onChange={e => setPass1(e.currentTarget.value)} label={'Password'} />
                    <Pass onChange={e => setPass2(e.currentTarget.value)} label={'Confirm Password'} />
                </>

            }
            <Button onClick={handleSave}>save</Button>




            {values.map((v, i) => <div onClick={() => show(v.text ? v.text : "brak")} key={i}>NOTATKA NUMER:{i}
                <br></br>
                <br></br>
                {v.text ? v.text : "brak"}<br></br>
                <br></br></div>)}
        </>)
}

export default Create