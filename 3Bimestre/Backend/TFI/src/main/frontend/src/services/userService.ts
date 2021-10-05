import { HTTPMethods, ILoginData, IUser } from '../types';
import { fetchAPI } from './fetchAPI';

export const login = async (loginData: ILoginData): Promise<IUser> => {
  document.cookie = 'user="";expires=Thu, 01 Jan 1970 00:00:01 GMT'
  const user: IUser = await fetchAPI(HTTPMethods.Post, 'users/login', loginData);
  document.cookie = 'user='+JSON.stringify(user) + ';expires=' + new Date(Date.now() + 23 * 3600 * 1000);
  return user;
};