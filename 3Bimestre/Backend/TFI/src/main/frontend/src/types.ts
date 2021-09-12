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

export interface IDentist extends IUser {
  licenseNumber: number;
}

export interface IPatient extends IUser {}

export interface IUser {
  firstName: string;
  lastName: string;
  fullName: string;
  username: string;
  role: Role;
}

export interface IUserContext {
  user: IUser | void;
  setUser: (user: IUser) => void;
}
