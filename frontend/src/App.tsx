import "quill/dist/quill.snow.css"; // Add css for snow theme
import './App.css';
import Note from "./components/Note";


const App = () => {

  return (

    <>
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
