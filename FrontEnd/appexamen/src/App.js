
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './App.css';

import useToken from './token/useToken';
import MenuNavBar from './layout/navbar';

import Login from './main/login';
import Index from './pages/index';
import ListadoAlumno from './pages/list-student';
import ListadoInscripciones from './pages/list-inscription';
import ListadoSituacion from './pages/list-situation';

const App = () => {

  const { token, setToken } = useToken();

  if (!token) {
    return <Login setToken={setToken} />
  }

  return (
    <div className="App">
      <BrowserRouter>
        <MenuNavBar />

        <Routes>
          <Route exact path="/index" element={<Index />} />
          <Route exact path="/inscripcion-list" element={<ListadoInscripciones />} />
          <Route exact path="/situacion-list" element={<ListadoSituacion />} />
          <Route exact path="/alumno-list" element={<ListadoAlumno />} />
        </Routes>

      </BrowserRouter>
    </div>
  );
}

export default App;
