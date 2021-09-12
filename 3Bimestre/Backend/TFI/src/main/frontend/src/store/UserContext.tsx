import React, { createContext, useContext, useState } from 'react';
import { IUser, IUserContext } from 'src/types';

const UserContext = createContext<IUserContext>({ user: undefined, setUser: () => null });
UserContext.displayName = 'UserContext';

export const UserContextProvider = ({ authenticatedUser, children }: { authenticatedUser?: IUser; children: JSX.Element }): JSX.Element => {
  const [user, setUser] = useState<IUser | undefined>(authenticatedUser);

  return <UserContext.Provider value={{ user, setUser }}>{children}</UserContext.Provider>;
};

export const useUserContext = (): IUserContext => useContext(UserContext);
