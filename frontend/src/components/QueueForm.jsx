// src/components/QueueForm.jsx
import React, { useState } from 'react';
import { addQueue } from '../api';

const QueueForm = () => {
    const [id, setId] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        await addQueue(Number(id));
        setId('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>Add Queue</h3>
            <input
                type="number"
                value={id}
                onChange={(e) => setId(e.target.value)}
                placeholder="Queue ID"
                required
            />
            <button type="submit">Add Queue</button>
        </form>
    );
};

export default QueueForm;