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
  Password = 'password',
  Text = 'text',
}

export enum Mode {
  View = 'view',
  Edit = 'edit',
  Delete = 'delete',
}

export enum Role {
  Admin = 'ADMIN',
  Dentist = 'DENTIST',
  Patient = 'PATIENT',
}

// =============================================================

export interface IAddress {
  street: string;
  number: number;
  floor?: number;
  apartment?: string;
}

export interface IAppointment {
  id: number;
  dateTime: string;
  dentist: IDentistList;
  patient: IPatientList;
}

export interface ICreateAppointment {
  dateTime: Date;
  dentistId: number;
  patientId: number;
}

export interface IDentist {
  licenseNumber: number;
  username: string;
  firstName: string;
  lastName: string;
}

export interface IDentistList {
  id: number;
  fullName: string;
  licenseNumber: number;
}

export interface ILoginData {
  username: string;
  password: string;
}
export interface IPatient {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  DNI: number;
  entryDate: string;
  address: IAddress;
}
export interface IPatientList {
  id: number;
  fullName: string;
  DNI: number;
}

export interface IUser {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  role: Role;
}

export interface IUserContext {
  user: IUser | void;
  setUser: (user: IUser | undefined) => void;
}
