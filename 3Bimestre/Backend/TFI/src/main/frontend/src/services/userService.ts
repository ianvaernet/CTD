import { HTTPMethods, ILoginData, IUser } from '../types';
import { fetchAPI } from './fetchAPI';

export const login = async (loginData: ILoginData): Promise<IUser> => {
  return await fetchAPI(HTTPMethods.Post, 'users/login', loginData);
};