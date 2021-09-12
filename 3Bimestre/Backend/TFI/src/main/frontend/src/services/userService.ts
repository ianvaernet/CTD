import { HTTPMethods, IUser } from '../types';
import { fetchAPI } from './fetchAPI';

export const listUsers = async (): Promise<IUser[]> => {
  return await fetchAPI(HTTPMethods.Get, 'users/');
};

export const getUser = async (id: string): Promise<IUser> => {
  return await fetchAPI(HTTPMethods.Get, 'users/' + id);
};

export const createUser = async (user: {}): Promise<IUser> => {
  return await fetchAPI(HTTPMethods.Post, 'users/', user);
};

export const updateUser = async (id: string, updatedUser: {}): Promise<IUser> => {
  return await fetchAPI(HTTPMethods.Put, 'users/' + id, updatedUser);
};

export const deleteUser = async (id: string): Promise<string> => {
  return await fetchAPI(HTTPMethods.Delete, 'users/' + id);
};
