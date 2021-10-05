import { HTTPMethods } from '@types';
import { getCookie } from 'src/utils';

export const fetchAPI = async (method: HTTPMethods, url: string, body?: {}) => {
  const headers: { [key: string]: string } = {
    'Content-Type': 'application/json',
  };
  try {
    const user = JSON.parse(getCookie("user") as string);
    if (user.token) headers.Authorization = user.token;
  } catch (error) {
    console.log(error);
  }
  const response = await fetch(process.env.REACT_APP_API_URL + url, {
    method,
    mode: 'cors',
    cache: 'no-cache',
    headers,
    body: JSON.stringify(body),
  });
  if (!response.ok) throw new Error(await response.text());

  const data = await response.json();
  return data;
};
