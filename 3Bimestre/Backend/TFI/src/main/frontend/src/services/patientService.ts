import { HTTPMethods, IPatient, IPatientList } from '../types';
import { fetchAPI } from './fetchAPI';

export const listPatients = async (): Promise<IPatientList[]> => {
  return await fetchAPI(HTTPMethods.Get, 'patients/');
};

export const getPatient = async (id: string): Promise<IPatient> => {
  return await fetchAPI(HTTPMethods.Get, 'patients/' + id);
};

export const createPatient = async (patient: IPatient): Promise<IPatient> => {
  return await fetchAPI(HTTPMethods.Post, 'patients/', patient);
};

export const updatePatient = async (id: string, updatedPatient: IPatient): Promise<IPatient> => {
  return await fetchAPI(HTTPMethods.Put, 'patients/' + id, updatedPatient);
};

export const deletePatient = async (id: string): Promise<string> => {
  return await fetchAPI(HTTPMethods.Delete, 'patients/' + id);
};
