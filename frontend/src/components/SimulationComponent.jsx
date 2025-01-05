import  { useEffect, useState } from 'react';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { getUpdates } from '../api';

const SimulationComponent = () => {
    const [updates, setUpdates] = useState([]);
    const [error, setError] = useState(null); // State to track connection errors

    useEffect(() => {
        const socket = new SockJS('http://localhost:8080/ws'); // Ensure this URL is correct
        const stompClient = Stomp.over(socket);

        stompClient.connect({}, (frame) => {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/updates', (message) => {
                setUpdates((prevUpdates) => [...prevUpdates, JSON.parse(message.body)]);
            });
        }, (error) => {
            console.error('Connection error:', error);
            setError('Could not connect to WebSocket. Please check the server and URL.');
        });

        const fetchUpdates = async () => {
            try {
                const initialUpdates = await getUpdates();
                setUpdates(initialUpdates);
            } catch (err) {
                console.error('Error fetching updates:', err);
            }
        };

        fetchUpdates();

        return () => {
            stompClient.disconnect();
        };
    }, []);

    return (
        <div>
            <h2>Simulation Updates</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>} {/* Display connection error if it exists */}
            <ul>
                {updates.map((update, index) => (
                    <li key={index}>{JSON.stringify(update)}</li>
                ))}
            </ul>
        </div>
    );
};

export default SimulationComponent;