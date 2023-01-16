import React, { useState } from "react";
import { backendApi } from "../api/http";
import axios from "axios";

const UploadImage = () => {
    const [selectedImage, setSelectedImage] = useState(null);

    return (
        <div>
            <h1>Upload and Display Image usign React Hook's</h1>
            {selectedImage && (
                <div>
                    <img alt="not fount" width={"250px"} src={URL.createObjectURL(selectedImage)} />
                    <br />
                    <button onClick={() => setSelectedImage(null)}>Remove</button>
                </div>
            )}
            <br />

            <br />
            <input
                type="file"
                name="myImage"
                onChange={(event: any) => {
                    console.log(event.target.files[0]);
                    setSelectedImage(event.target.files[0]);
                    // axios.post("http://localhost:8080/image",  image: selectedImage }).then(response => {
                    //     console.log(response)
                    // }).catch(e => console.log(e));

                    axios.post('http://localhost:8080/image', { image: event.target.files[0] }, {
                        headers: {
                            'accept': 'application/json',
                            'Content-Type': `multipart/form-data`,
                        }
                    }
                    )
                }}
            />
        </div>
    );
};

export default UploadImage;