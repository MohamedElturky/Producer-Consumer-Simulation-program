// src/components/ConnectionForm.jsx
import React, { useState } from 'react';
import { addConnection } from '../api';

const ConnectionForm = () => {
    const [fromId, setFromId] = useState('');
    const [toId, setToId] = useState('');
    const [source, setSource] = useState('machine'); // Default source

    const handleSubmit = async (e) => {
        e.preventDefault();
        await addConnection({ fromId: Number(fromId), toId: Number(toId), source });
        setFromId('');
        setToId('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>Add Connection</h3>
            <input
                type="number"
                value={fromId}
                onChange={(e) => setFromId(e.target.value)}
                placeholder="From ID"
                required
            />
            <input
                type="number"
                value={toId}
                onChange={(e) => setToId(e.target.value)}
                placeholder="To ID"
                required
            />
            <select value={source} onChange={(e) => setSource(e.target.value)}>
                <option value="machine">Machine</option>
                <option value="queue">Queue</option>
            </select>
            <button type="submit">Add Connection</button>
        </form>
    );
};

export default ConnectionForm;