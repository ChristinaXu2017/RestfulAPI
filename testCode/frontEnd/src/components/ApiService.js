import axios from 'axios'

// export function retrieveHelloWorldBean(){
//     return axios.get('http://localhost:8080/hello-world-bean')
// }

const apiClient = axios.create({
    //baseURL: process.env.REACT_APP_API_URL
    baseURL: 'http://localhost:6000/maintenance',
    headers: {
        'Content-Type': 'application/json'
    }
});

//same to: axios.get('http://localhost:6000/maintenance/test/1')
export const retrieveTestRequest
    = () => apiClient.get('/test/1')

// for User access
export const retrieveUserRequestbyID = (id) =>
    apiClient.get(`/user/${id}`, {
        headers: { Authorization: 'Basic dXNlcjp1c2VycGFzc3dvcmQ=' }
    });

export const createUserRequest = (request) =>
    apiClient.post('/user/request', request, {
        headers: { Authorization: 'Basic dXNlcjp1c2VycGFzc3dvcmQ=' }
    });


// for Admin access
export const retrieveAdimRequestbyPoriority = (prioroty) =>
    apiClient.get(`/admin/${prioroty}`, {
        headers: { Authorization: 'Basic YWRtaW46YWRtaW5wYXNzd29yZA==' }
    });

export const createAdminRequest = (request) =>
    apiClient.post('/user/request', request, {
        headers: {
            Authorization: 'Basic YWRtaW46YWRtaW5wYXNzd29yZA=='
        }
    });

export const updateAdminRequest = (request) =>
    apiClient.put('/admin/update', request, {
        headers: {
            Authorization: 'Basic YWRtaW46YWRtaW5wYXNzd29yZA=='
        }
    });


export default apiClient;