export enum ButtonSize {
  Small = 'small',
  Medium = 'medium',
  Large = 'large',
}

export enum ButtonType {
  Primary = 'primary',
  Secondary = 'secondary',
  Success = 'success',
  Warning = 'warning',
  Danger = 'danger',
}

export enum Direction {
  Left = 'left',
  Right = 'right',
}

export enum HTTPMethods {
  Get = 'GET',
  Post = 'POST',
  Put = 'PUT',
  Delete = 'DELETE',
}

export enum InputType {
  Number = 'number',
  Text = 'text',
}

export enum Mode {
  View = 'view',
  Edit = 'edit',
  Delete = 'delete',
}

export enum Role {
  ADMIN = 'ADMIN',
  DENTIST = 'DENTIST',
  PATIENT = 'PATIENT',
}

// =============================================================

export interface IAddress {
  street: string;
  number: number;
  floor?: number;
  apartment?: string;
}

export interface IDentist {
  fullName: string;
  licenseNumber: number;
}

export interface ILoginData {
  username: string;
  password: string;
}
export interface IPatient {
  fullName: string;
  DNI: number;
  address?: IAddress;
}

export interface IUser {
  firstName: string;
  lastName: string;
  username: string;
  role: Role;
}

export interface IUserContext {
  user: IUser | void;
  setUser: (user: IUser) => void;
}
