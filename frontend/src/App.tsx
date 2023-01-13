import './App.css';
import { notesService } from './api/noteService';
const a = process.env.REACT_APP_SECRET_NAME
function App() {

  return (
    <>

      <p>Nosiema:{a}:</p>
      <button onClick={() => notesService.create("hello")
      }>create</button>
      <img src="https://w1.pngwing.com/pngs/370/239/png-transparent-chinese-beagle-chinese-crested-dog-puppy-maltese-dog-pet-sitting-bark-dog-walking.png" alt="pies" />
    </>
  );
}

export default App;
