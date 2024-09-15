import { Link } from 'react-router-dom'
import { useAuth } from './AuthContext'

function HeaderComponent() {

    const authContext = useAuth()
    const isAuthenticated = authContext.isAuthenticated
    console.log(authContext.number);
    console.log(authContext.isAuthenticated);
    return (
        <header className="border-bottom border-light border-5 mb-5 p-2">
            <div className="container">
                <div className="created-by">User Request Maintenance Page!</div>
                <div className="row">
                    <nav className="navbar navbar-expand-lg">
                        <div className="collapse navbar-collapse">
                            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                                <li className="nav-item">
                                    <Link className="nav-link" to="/login">Home</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/request">Create Request</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/user">User Access</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/admin">Admin Access</Link>
                                </li>
                            </ul>
                            <ul className="navbar-nav">
                                <li className="nav-item">
                                    {!isAuthenticated && <Link className="nav-link" to="/login">Login</Link>}
                                </li>
                                <li className="nav-item">
                                    {isAuthenticated && <Link className="nav-link" to="/logout">Logout</Link>}
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </header>
    );
}


export default HeaderComponent


