
import { NoteStatus } from "../types/note";
import { backendApi } from "./http";



const client = backendApi('/note')

export const notesService = {
    getAll() {
        console.log('Fetching notes')
        return client.get('')
    },

    getById(id: number) {
        console.log('Get note', id)
        return client.get(`/${id}`)
    },

    create(noteText: string, noteStatus: NoteStatus, password?: string) {

        console.log('Create note', noteText)
        return client.post('', { "note": noteText, "password": noteText, "noteStatus": noteStatus })
    },

    update(id: number, note: string) {
        console.log('Update note', id, note)
        return client.put(`/${id}`, note);
    },

    delete(id: number) {
        console.log('Delete note', id)
        return client.delete(`/${id}`)
    }
}