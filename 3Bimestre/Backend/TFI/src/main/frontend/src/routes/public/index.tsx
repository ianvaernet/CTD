import { LoginPage } from "src/pages";

export const publicRoutes = [
  {
    path: '/',
    exact: false,
    name: 'Login',
    component: () => <LoginPage />,
  },
];
