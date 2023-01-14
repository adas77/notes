export type Note = {
    id?: number,
    password?: number,
    isProtected: boolean,
}

export enum NoteStatus {
    PRIVATE_ENCODED = "PRIVATE_ENCODED",
    PRIVATE = "PRIVATE",
    PUBLIC = "PUBLIC",
}