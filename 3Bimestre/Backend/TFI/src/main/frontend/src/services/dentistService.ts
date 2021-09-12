import { HTTPMethods, IDentist } from '../types';
import { fetchAPI } from './fetchAPI';

export const listDentists = async (): Promise<IDentist[]> => {
  return await fetchAPI(HTTPMethods.Get, 'dentists');
};

export const getDentist = async (id: string): Promise<IDentist> => {
  return await fetchAPI(HTTPMethods.Get, 'dentists/' + id);
};

export const createDentist = async (dentist: IDentist): Promise<IDentist> => {
  return await fetchAPI(HTTPMethods.Post, 'dentists/', dentist);
};

export const updateDentist = async (id: string, updatedDentist: IDentist): Promise<IDentist> => {
  return await fetchAPI(HTTPMethods.Put, 'dentists/' + id, updatedDentist);
};

export const deleteDentist = async (id: string): Promise<string> => {
  return await fetchAPI(HTTPMethods.Delete, 'dentists/' + id);
};
