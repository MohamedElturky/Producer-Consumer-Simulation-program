// src/App.jsx

import SimulationComponent from './components/SimulationComponent';
import MachineForm from './components/MachineForm';
import QueueForm from './components/QueueForm';
import ConnectionForm from './components/ConnectionForm';
import RunSimulation from './components/RunSimulation';
import './app.css'; // Import the CSS file

const App = () => {
    return (
        <div>
            <h1>Producer-Consumer Simulation</h1>
            <MachineForm />
            <QueueForm />
            <ConnectionForm />
            <RunSimulation />
            <SimulationComponent />
        </div>
    );
};

export default App;