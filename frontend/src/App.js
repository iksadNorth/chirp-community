import './App.css';
import * as c from './components';
import RouteComponent from './routes';

import 'bootstrap-icons/font/bootstrap-icons.css';

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
