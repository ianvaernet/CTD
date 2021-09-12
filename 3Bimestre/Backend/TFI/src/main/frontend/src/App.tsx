import React from 'react';
import { UserContextProvider } from '@store';
import { Navigator } from './routes';
import { BrowserRouter as Router } from 'react-router-dom';

type Props = {};

export const App: React.FC<Props> = () => {
  return (
    <UserContextProvider>
      <Router>
        <Navigator />
      </Router>
    </UserContextProvider>
  );
};
