import React from 'react';
import { InputType } from '@types';
import { Form, FormItemProps, Input as AntdInput, InputNumber, InputProps } from 'antd';
import './style.css';

type Props = FormItemProps & {
  type?: InputType;
  name: string;
  value?: string;
  label: string;
  help?: string;
  required?: boolean;
  disabled?: boolean;
  formProps?: FormItemProps;
  inputProps?: InputProps;
};

export const Input: React.FC<Props> = ({ type, name, label, required, disabled, inputProps, ...props }: Props) => {
  return (
    <Form.Item name={name} label={label} required={required} rules={[{ required }]} hasFeedback={!disabled} className={"mt-1 "+ props.className} {...props}>
      {type === InputType.Number ? (
        <InputNumber placeholder={label} disabled={disabled} className={"width-100 " + props.className} />
      ) : (
        <AntdInput type={type} placeholder={label} disabled={disabled} className={props.className} {...inputProps} />
      )}
    </Form.Item>
  );
};

Input.defaultProps = {
  type: InputType.Text,
  help: 'Campo requerido',
};
