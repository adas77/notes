import axios from "axios";

export default axios.create({
    // TODO: baseURL: "http://localhost:${port}/${path}" to .env
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-type": "application/json"
    }
});
