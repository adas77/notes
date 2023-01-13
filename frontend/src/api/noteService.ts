
import { backendApi } from "./http";

const client = backendApi('/note')

export const notesApi = {
    getAll() {
        console.log('Fetching notes')
        return client.get('')
    },

    getById(id: number) {
        console.log('Get note', id)
        return client.get(`/${id}`)
    },

    create(noteText: string) {
       
        console.log('Create note', noteText)
        return client.post('', { "note": "noteText", "noteStatus":"l"})
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