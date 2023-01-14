export type NoteType = {
    id?: number,
    password?: number,
    username?: string,
    text?: string,
    status?: string
    // isProtected: boolean,
}

export enum NoteStatus {
    PRIVATE_ENCODED = "PRIVATE_ENCODED",
    PRIVATE = "PRIVATE",
    PUBLIC = "PUBLIC",
}