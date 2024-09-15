import axios from 'axios'

// export function retrieveHelloWorldBean(){
//     return axios.get('http://localhost:8080/hello-world-bean')
// }

const apiClient = axios.create({
    //baseURL: process.env.REACT_APP_API_URL
    baseURL: 'http://localhost:6001/maintenance',
    headers: {
        'Content-Type': 'application/json'
    }
});

export const executeBasicAuthenticationService = (token) =>
    apiClient.get('/basicauth', {
        headers: { Authorization: token }
    });

//same to: axios.get('http://localhost:6000/maintenance/test/1')
export const retrieveTestRequest = () =>
    apiClient.get('/test/1')

// for User access
export const retrieveUserRequestbyID = (id, token) =>
    apiClient.get(`/user/${id}`, {
        headers: { Authorization: token }
    });

export const createUserRequest = (request, token) =>
    apiClient.post('/user/request', request, {
        headers: { Authorization: token }
    });

export const updateUserRequest = (request, token) =>
    apiClient.post('/user/update', request, {
        headers: { Authorization: token }
    });

// for Admin access
export const retrieveAdimRequestbyPoriority = (prioroty, token) =>
    apiClient.get(`/admin/${prioroty}`, {
        headers: { Authorization: token }
    });

export const createAdminRequest = (request, token) =>
    apiClient.post('/user/request', request, {
        headers: { Authorization: token }
    });

export const updateAdminRequest = (request, token) =>
    apiClient.put('/admin/update', request, {
        headers: { Authorization: token }
    });

export default apiClient;