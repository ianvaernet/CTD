import { HTTPMethods } from '@types';

export const fetchAPI = async (method: HTTPMethods, url: string, body?: {}) => {
  const response = await fetch(process.env.REACT_APP_API_URL + url, {
    method,
    mode: 'cors',
    cache: 'no-cache',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
  });
  if (!response.ok) throw new Error(await response.text());

  const data = await response.json();
  return data;
};
