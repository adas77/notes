import axios from "axios";
import { useState } from "react";

const UploadImage = () => {
    const [selectedImage, setSelectedImage] = useState(null);

    return (
        <div>
            <br />
            <br />
            <h1>Dodaj publiczny obrazek</h1>
            {selectedImage && (
                <div>
                    <img alt="not fount" width={"250px"} src={URL.createObjectURL(selectedImage)} />
                    <button onClick={() => setSelectedImage(null)}>Remove</button>
                </div>
            )}

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