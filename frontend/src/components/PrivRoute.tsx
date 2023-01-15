import { Navigate, Outlet, useLocation } from 'react-router-dom';

const PrivRoute = () => {
    const location = useLocation();
    return localStorage.getItem("user")
        ? <Outlet />
        : <Navigate to="/login" replace state={{ from: location }} />;
}

export default PrivRoute
