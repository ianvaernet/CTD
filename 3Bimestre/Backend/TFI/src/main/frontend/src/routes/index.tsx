import { publicRoutes } from './public';
import { privateRoutes } from './private';
import { useUserContext } from '@store';
import { Switch, Route } from 'react-router-dom';
import { Role } from '@types';
import { Layout } from '@templates';

export function Navigator() {
  // const { user } = useUserContext();
  const user = { role: Role.ADMIN };
  const routes = user?.role ? privateRoutes(user.role) : publicRoutes;

  const navigation = (
    <Switch>
      {routes.map((route) => (
        <Route key={route.name} path={route.path} exact={route.exact} children={route.component} />
      ))}
    </Switch>
  );

  return user?.role ? <Layout userRole={user.role}>{navigation}</Layout> : navigation;
}
