// src/components/RunSimulation.jsx
import React, { useState } from 'react';
import { runSimulation } from '../api';

const RunSimulation = () => {
    const [numberOfProducts, setNumberOfProducts] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        await runSimulation(Number(numberOfProducts));
        setNumberOfProducts('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>Run Simulation</h3>
            <input
                type="number"
                value={numberOfProducts}
                onChange={(e) => setNumberOfProducts(e.target.value)}
                placeholder="Number of Products"
                required
            />
            <button type="submit">Run Simulation</button>
        </form>
    );
};

export default RunSimulation;