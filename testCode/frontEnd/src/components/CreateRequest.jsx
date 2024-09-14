import { useState } from 'react'
import { createUserRequest } from './ApiService'

export default function RequestComponent() {

    const [request, setRequest] = useState({
        type: 'requrest type',
        poriority: 'LOW',
        description: 'details of your request'
    });

    const [newRequestId, setNewRequestId] = useState(null);

    const [requests, setRequests] = useState([]);

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setRequest(prevState => ({
            ...prevState,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        createUserRequest(request)
            .then(response => {
                console.log('Request created:', response.data);
                setRequests(prevRequests => [...prevRequests, response.data]);
                setNewRequestId(response.data.id);
                // Clear the form
                setRequest({ type: '', poriority: 'LOW', description: '' });
            })
            .catch(error => {
                console.error('Error creating request:', error);
                // Handle error (e.g., show an error message)
            });
    };

    return (
        <div>
            <h1>Please input your request in below form </h1>
            <form onSubmit={handleSubmit}>

                <div>
                    <label>Type:</label>
                    <input type="text" name="type" value={request.type} onChange={handleChange} required />
                </div>

                <div>
                    <label>Priority:</label>
                    <select name="priority" value={request.poriority} onChange={handleChange} >
                        <option value="LOW">Low</option>
                        <option value="MED">Medium</option>
                        <option value="HIGH">High</option>
                    </select>
                </div>

                <div>
                    <label>Description:</label>
                    <textarea name="description" value={request.description} onChange={handleChange} required />
                </div>

                <button type="submit">Submit Request</button>
            </form>

            {newRequestId && (
                <div>
                    <h2>Request Submitted Successfully</h2>
                    <p>Your request ID is: {newRequestId}</p>
                </div>
            )}

            <h2>Submitted Requests</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Request Type</th>
                        <th>Priority</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    {requests.map(req => (
                        <tr key={req.id}>
                            <td>{req.id}</td>
                            <td>{req.type}</td>
                            <td>{req.priority}</td>
                            <td>{req.description}</td>
                        </tr>
                    ))}
                </tbody>
            </table>

        </div>)
}
