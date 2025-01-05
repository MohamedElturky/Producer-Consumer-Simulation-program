// src/components/MachineForm.jsx
import  { useState } from 'react';
import { addMachine } from '../api';

const MachineForm = () => {
    const [id, setId] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        await addMachine(Number(id));
        setId('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>Add Machine</h3>
            <input
                type="number"
                value={id}
                onChange={(e) => setId(e.target.value)}
                placeholder="Machine ID"
                required
            />
            <button type="submit">Add Machine</button>
        </form>
    );
};

export default MachineForm;