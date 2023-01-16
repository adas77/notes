import "quill/dist/quill.snow.css"; // Add css for snow theme
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Create from "./pages/Create";
import Login from "./pages/Login";
import Note from "./pages/Note";
import PrivRoute from "./components/PrivRoute";
import Public from "./pages/Public";
import Register from "./pages/Register";
import Error from "./pages/Error";

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
  ])

  return (
    <RouterProvider router={router} />
  );
}


export default App;
