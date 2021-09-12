import React from 'react';
import { Button as AntdButton, ButtonProps } from 'antd';
import './style.css';
import { ButtonType, ButtonSize } from '@types';

type Props = Omit<ButtonProps, 'type' | 'size'> & {
  children: string | React.FC;
  type?: ButtonType;
  size?: ButtonSize;
  backgroundColor?: string;
  className?: string;
};

export const Button: React.FC<Props> = ({ children, type, size, backgroundColor, className, ...props }: Props) => {
  return (
    <AntdButton {...props} className={className + [' button', type, size].join(' ')} style={{ backgroundColor }}>
      {children}
    </AntdButton>
  );
};

Button.defaultProps = {
  type: ButtonType.Primary,
  size: ButtonSize.Medium,
  className: '',
};
