import axios from 'axios'

// export function retrieveHelloWorldBean(){
//     return axios.get('http://localhost:8080/hello-world-bean')
// }

const apiClient = axios.create(
    {
        baseURL: 'http://localhost:5050'
    }
);

//same to: axios.get('http://localhost:8080/hello-world-bean')
export const retrieveHelloWorldBean
    = () => apiClient.get('/hello-world-bean')

export const retrieveHelloWorldPathVariable
    = (id) => apiClient.get(`/hello-world/path-variable/${id}`)

export const retrieveMiRNAsbyID
    = (id) => apiClient.get(
        `/miRNA/${id}`,
        {
            headers: {
                Authorization: 'Basic dXNlcjpwYXNz'
            }
        }
    )

export const retrieveTestbyID
    = (id) => apiClient.get(`/test/${id}`)

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicauth`, {
        headers: { Authorization: token }
    }
    )