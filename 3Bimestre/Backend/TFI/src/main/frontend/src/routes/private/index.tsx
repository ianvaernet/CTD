import { Role } from '@types';
import { AddDentist, AddPatient, ListAppointments, ListDentists, ListPatients, ViewDentist, ViewPatient } from 'src/pages';

export const privateRoutes = (role: Role) => {
  const routes = [
    {
      path: '/turnos',
      exact: true,
      name: 'Turnos',
      component: () => <ListAppointments />,
    },
  ];

  if (role === Role.Admin) {
    routes.push({
      path: '/odontologos',
      exact: true,
      name: 'Odontologos',
      component: () => <ListDentists />,
    });
    routes.push({
      path: '/odontologos/nuevo',
      exact: true,
      name: 'Odontologo',
      component: () => <AddDentist />,
    });
    routes.push({
      path: '/odontologos/:id',
      exact: true,
      name: 'Odontologo',
      component: () => <ViewDentist />,
    });
    routes.push({
      path: '/pacientes',
      exact: true,
      name: 'Pacientes',
      component: () => <ListPatients />,
    });
    routes.push({
      path: '/pacientes/nuevo',
      exact: true,
      name: 'Pacientes',
      component: () => <AddPatient />,
    });
    routes.push({
      path: '/pacientes/:id',
      exact: true,
      name: 'Paciente',
      component: () => <ViewPatient />,
    });
  }

  return routes;
};
