import { createContext, useContext, useState } from "react";
import { executeBasicAuthenticationService, retrieveTestRequest } from "./ApiService"
//1: Create a Context
export const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext)

//2: Share the created context with other components
export default function AuthProvider({ children }) {

    const [username, setUsername] = useState("");
    //Put some state in the context
    const [isAuthenticated, setAuthenticated] = useState(false)
    const [token, setToken] = useState(null)

    async function login(username, password) {
        console.log('Logging in with username and password:', username, password); // Add logging to debug the username and password
        const baToken = 'Basic ' + window.btoa(username + ":" + password)
        try {
            console.log('Logging in with token:', baToken); // Add logging to debug the token
            //const response = await executeBasicAuthenticationService(baToken)
            const response = await retrieveTestRequest()
            console.log('Response:', response); // Add logging to debug the response
            if (response.status == 200) {
                setAuthenticated(true)
                setUsername(username)
                setToken(baToken)
                return true
            } else {
                logout()
                return false
            }
        } catch (error) {
            logout()
            return false
        }
    }

    function logout() {
        setAuthenticated(false)
        setUsername(null)
    }

    return (
        <AuthContext.Provider value={{ username, isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    )
}