
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'

import AuthProvider, { useAuth } from "./AuthContext";
import HeaderComponent from "./Header"
import FooterComponent from './Footer';
import LoginComponent from './Login';
import LogoutComponent from './Logout';
import ErrorComponent from './Error';
import WelcomeComponent from './Welcome';
import RequestComponent from './CreateRequest';

export default function MainApp() {

  function AuthenticatedRoute({ children }) {
    const authContext = useAuth()
    if (authContext.isAuthenticated) return children
    return <Navigate to="/" />
  }

  return (
    <div>
      <AuthProvider>
        <BrowserRouter>
          <HeaderComponent />
          <Routes>
            <Route path='/' element={<LoginComponent />}></Route>
            <Route path='/login' element={<LoginComponent />}></Route>
            <Route path='/home/:username' element={<WelcomeComponent />}></Route>
            <Route path='/request' element={<RequestComponent />} />
            <Route path='/userAccess' element={<RequestComponent />} />
            <Route path='/adminAcess' element={<RequestComponent />} />

            <Route path='*' element={<ErrorComponent />}></Route>l
            <Route path='/logout' element={<LogoutComponent />} />
          </Routes>
          <FooterComponent />
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}