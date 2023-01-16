
import { NoteStatus } from "../types/note";
import { authHeader, backendApi } from "./http";

const client = backendApi('/note')
export const notesService = {
    getAll() {
        return client.get('', {
            headers: { 'Authorization': authHeader() }
        })
    },
    getPublic() {
        return client.get('/public', {
            headers: { 'Authorization': authHeader() }
        })
    },

    getById(id: number, notePassword: string) {
        return client.get(`/protected/${id}`, {
            headers: { 'Authorization': authHeader() },
            params: {
                password: notePassword
            }
        })
    },

    create(noteText: string, noteStatus: NoteStatus, notePassword?: string) {
        return client.post('', { "note": noteText, "noteStatus": noteStatus }, {
            headers: { 'Authorization': authHeader() },
            params: {
                password: notePassword
            }
        })
    },

}




