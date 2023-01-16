import axios from 'axios'
import Image from './Image'
import React, { useEffect, useState } from 'react'
import { backendApi } from '../api/http'

type Props = {}
function getBase64(url: string) {
    return axios
        .get(url, {
            responseType: 'arraybuffer'
        })
        .then(response => Buffer.from(response.data, 'binary').toString('base64'))
}
function getStreetView(url: string) {

}



const MyImage = (props: Props) => {
    const [f, setF] = useState<any>()
    useEffect(() => {

        // axios.get("http://localhost:8080/image/imgxd.png").then(response => {
        axios.get("http://localhost:8080/image/imgxd.png").then(response => {
            setF(response.config.url)
            console.log(response.config.url)
        });
    }, [])
    return (
        <>
            <Image src={f} />
            <div>Image</div>
        </>
    )
}

export default MyImage