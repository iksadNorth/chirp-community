import './App.css';
import * as c from './components';
import RouteComponent from './routes';

function App() {

  return (
    <div className="App">
      <c.Header />
      <RouteComponent />
      <c.Footer />
    </div>
  );
}

export default App;
