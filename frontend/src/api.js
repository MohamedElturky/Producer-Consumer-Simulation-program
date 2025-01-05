// src/api.js
const API_BASE_URL = "http://localhost:8080"; // Change to your backend URL

export const addMachine = async (id) => {
    await fetch(`${API_BASE_URL}/addMachine`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(id),
    });
};

export const addQueue = async (id) => {
    await fetch(`${API_BASE_URL}/addQueue`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(id),
    });
};

export const addConnection = async (connectionData) => {
    await fetch(`${API_BASE_URL}/addConnection`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(connectionData),
    });
};

export const runSimulation = async (numberOfProducts) => {
    await fetch(`${API_BASE_URL}/run`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(numberOfProducts),
    });
};

export const getUpdates = async () => {
    const response = await fetch(`${API_BASE_URL}/update`);
    return response.json();
};

export const clearSystem = async () => {
    await fetch(`${API_BASE_URL}/clear`, {
        method: 'DELETE',
    });
};