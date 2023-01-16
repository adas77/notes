import "quill/dist/quill.snow.css"; // Add css for snow theme
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Create from "./components/Create";
import Login from "./components/Login";
import Register from "./components/Register";
import Error from "./pages/Error";
import Note from "./components/Note";
import Public from "./components/Public";
import PrivRoute from "./components/PrivRoute";
import Image from "./components/Image";
import MyImage from "./components/MyImage";
import UploadImage from "./components/UploadImage";

const App = () => {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <PrivRoute />,
      errorElement: <Error />,
      children: [
        {
          path: "notes",
          element: <Note />,
          errorElement: <Error />,

        },
        {
          path: "public",
          element: <Public />,
          errorElement: <Error />,

        },
        {
          path: "create",
          element: <Create />,
          errorElement: <Error />,
        },
        {
          path: "images",
          element: <MyImage />,
          errorElement: <Error />,
        },
      ],
    },
    {
      path: "/login",
      element: <Login />,
      errorElement: <Error />,

    },
    {
      path: "/register",
      element: <Register />,
      errorElement: <Error />
    },
    // {
    //   path: "/image",
    //   element: <MyImage />,
    //   errorElement: <Error />
    // },
    // {
    //   path: "/image/upload",
    //   element: <UploadImage />,
    //   errorElement: <Error />
    // },

    // {
    //   path: "/",
    //   element: <Note />,
    //   errorElement: <Error />
    // },
    // {
    //   path: "/notes",
    //   element: <Note />,
    //   errorElement: <Error />
    // },
    // {
    //   path: "/public",
    //   element: <Public />,
    //   errorElement: <Error />
    // },
    // {
    //   path: "/login",
    //   element: <Login />,
    //   errorElement: <Error />
    // },
    // {
    //   path: "/register",
    //   element: <Register />,
    //   errorElement: <Error />
    // },
    // {
    //   path: "/create",
    //   element: <Create />,
    //   errorElement: <Error />
    // },


  ])


  return (


    <RouterProvider router={router} />

  );
}


export default App;
