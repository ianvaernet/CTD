import React, { createContext, useContext, useState } from 'react';
import { IUser, IUserContext } from 'src/types';
import { getCookie } from 'src/utils';

const userCookie = getCookie('user');
const UserContext = createContext<IUserContext>({ user: userCookie ? JSON.parse(userCookie) : undefined, setUser: () => null });
UserContext.displayName = 'UserContext';

export const UserContextProvider = ({ authenticatedUser, children }: { authenticatedUser?: IUser; children: JSX.Element }): JSX.Element => {
  const [user, setUser] = useState<IUser | undefined>(authenticatedUser);

  return <UserContext.Provider value={{ user, setUser }}>{children}</UserContext.Provider>;
};

export const useUserContext = (): IUserContext => useContext(UserContext);
