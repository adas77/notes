
import { NoteStatus } from "../types/note";
import { authHeader, backendApi } from "./http";



const client = backendApi('/note')

export const notesService = {
    getAll() {
        console.log('Fetching notes')
        return client.get('', {
            headers: { 'Authorization': authHeader() }
        })
    },

    getById(id: number) {
        console.log('Get note', id)
        return client.get(`/${id}`, {
            headers: { 'Authorization': authHeader() }
        })
    },

    create(noteText: string, noteStatus: NoteStatus, notePassword?: string) {

        console.log('Create note', noteText)
        const f = client.post('', { "note": noteText, "noteStatus": noteStatus }, {
            headers: { 'Authorization': authHeader() },
            params: {
                password: notePassword
            }
        })
    },

    update(id: number, note: string) {
        console.log('Update note', id, note)
        return client.put(`/${id}`, note, {
            headers: { 'Authorization': authHeader() }
        })
    },

    delete(id: number) {
        console.log('Delete note', id)
        return client.delete(`/${id}`, {
            headers: { 'Authorization': authHeader() }
        })
    }
}




