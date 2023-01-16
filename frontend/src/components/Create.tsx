import { useEffect, useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { notesService } from '../api/noteService';
import { NoteStatus, NoteType } from '../types/note';
import Pass from './Pass';
import RadioButton from './RadioButton';

import sanitizeHtml from 'sanitize-html';
import Button from './Button';
import Navigation from './Navigation';
import UploadImage from './UploadImage';

const Create = () => {
    const [value, setValue] = useState<string>('');
    const [noteStatus, setNoteStatus] = useState<NoteStatus>(NoteStatus.PRIVATE);
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
        const cleanPass = sanitizeHtml(pass1)
        letSend && notesService.create(clean, noteStatus, cleanPass)
        setValue('')
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

            <Button onClick={handleSave}>Save</Button>
            <UploadImage />

        </>)
}

export default Create