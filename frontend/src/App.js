import './App.css';
import RouteComponent from './routes';

import 'bootstrap-icons/font/bootstrap-icons.css';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

import { Provider } from "react-redux";
import store from './store';

function App() {

  return (
    <Provider store={store}>
      <div className="App">
        <RouteComponent />
      </div>
    </Provider>
  );
}

export default App;
