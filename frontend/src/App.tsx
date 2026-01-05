import { Navigate, Route, Routes } from 'react-router'
import Home from '@/pages/Home'
import Login from '@/pages/Login'

function App() {
  return (
    <Routes>
      <Route path='/' element={<Home />} />
      <Route path='/login' element={<Login />} />
      <Route path='/signup' element={<Home />} />
      <Route path='*' element={<Navigate to='/login' />} />
    </Routes>
  )
}

export default App
