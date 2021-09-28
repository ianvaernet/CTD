import { HTTPMethods, IAppointment, ICreateAppointment } from '@types';
import { fetchAPI } from './fetchAPI';

export const listAppointments = async (dentistId: number | void, patientId: number | void): Promise<IAppointment[]> => {
  let query = '';
  if (dentistId) {
    query = '?dentistId=' + dentistId;
  } else if (patientId) {
    query = '?patientId=' + patientId;
  }
  return await fetchAPI(HTTPMethods.Get, 'appointments' + query);
};

export const createAppointment = async (newAppointment: ICreateAppointment): Promise<IAppointment> => {
  return await fetchAPI(HTTPMethods.Post, 'appointments', newAppointment);
};

export const deleteAppointment = async (id: number): Promise<boolean> => {
  return await fetchAPI(HTTPMethods.Delete, 'appointments/' + id);
};
