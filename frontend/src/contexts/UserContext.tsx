// import type { User } from '@/types/User'
import {
  createContext,
  useContext,
  useState,
  type Dispatch,
  type ReactNode,
  type SetStateAction,
} from 'react'

interface UserContextType {
  // userDetails: User
  isSignedIn: boolean
  setIsSignedIn: Dispatch<SetStateAction<boolean>>
}

const UserContext = createContext<UserContextType | undefined>(undefined)

export function UserProvider({ children }: { children: ReactNode }) {
  const [isSignedIn, setIsSignedIn] = useState(false)

  return (
    <UserContext.Provider value={{ isSignedIn, setIsSignedIn }}>
      {children}
    </UserContext.Provider>
  )
}

export function useUser() {
  const context = useContext(UserContext)
  if (!context) return 'useUser must be used within User Provider'
  return context
}
