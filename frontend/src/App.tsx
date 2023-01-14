import "quill/dist/quill.snow.css"; // Add css for snow theme
import './App.css';
import Note from "./components/Note";
import { authService } from './api/authService'
import { notesService } from './api/noteService'
import { Auth } from "./types/auth";

const App = () => {
  const credentials: Auth = {
    username: "toja",
    email: "toja@gmail.com",
    password: "toja",
  }
  return (

    <>
      <button onClick={() => authService.login(credentials)}>login</button>
      <br />
      <button onClick={() => authService.logout(credentials)}>logout</button>
      <br />
      <button onClick={() => authService.register(credentials)}>register</button>
      <br />

      {/* <img src="https://w1.pngwing.com/pngs/370/239/png-transparent-chinese-beagle-chinese-crested-dog-puppy-maltese-dog-pet-sitting-bark-dog-walking.png" alt="pies" /> */}

      {/* gegie */}
      <Note />

    </>
  );
}



// function App() {

//   return (
//     <>

//       <p>Nosiema:{a}:</p>
//       <button onClick={() => notesService.create("hello")
//       }>create</button>
//       <Note/>
//       <img src="https://w1.pngwing.com/pngs/370/239/png-transparent-chinese-beagle-chinese-crested-dog-puppy-maltese-dog-pet-sitting-bark-dog-walking.png" alt="pies" />
//     </>
//   );
// }

export default App;
