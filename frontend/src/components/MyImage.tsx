import axios from 'axios'
import Image from './Image'
import React, { useEffect, useState } from 'react'
import { authHeader, backendApi } from '../api/http'

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
    const [f, setF] = useState<any[]>()

    // return client.get('', {
    //     headers: { 'Authorization': authHeader() }
    // })


    // axios.post('http://localhost:8080/image', { image: event.target.files[0] }, {
    //     headers: {
    //         'accept': 'application/json',
    //         'Content-Type': `multipart/form-data`,
    //         'Authorization': authHeader(),
    //     }
    // }
    // )


    useEffect(() => {
        axios.get("http://localhost:8080/image", {
            headers: {
                'accept': 'application/json',
                'Content-Type': `imageFile.type`,
                // 'Content-Type': `multipart/form-data`,
                'Authorization': authHeader(),
            }
        }).then(response => {
            // setF(response.config.url)
            setF(prev => [prev, response.config.url])

            console.log(response.config.url)
            console.log(f)
            console.log(response)
        });
    }, [])
    return (
        <>
            {f && f.map(f => <Image key={f} src={f} />)}
            {/* <Image src={f} />
            <div>Image</div> */}
        </>
    )
}

export default MyImage