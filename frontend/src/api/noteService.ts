
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
    getPublic() {
        console.log('Fetching notes')
        return client.get('/public', {
            headers: { 'Authorization': authHeader() }
        })
    },

    getById(id: number, notePassword: string) {
        console.log('Get note', id)
        return client.get(`/protected/${id}`, {
            headers: { 'Authorization': authHeader() },
            params: {
                password: notePassword
            }
        })
    },

    create(noteText: string, noteStatus: NoteStatus, notePassword?: string) {

        console.log('Create note', noteText)
        return client.post('', { "note": noteText, "noteStatus": noteStatus }, {
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




