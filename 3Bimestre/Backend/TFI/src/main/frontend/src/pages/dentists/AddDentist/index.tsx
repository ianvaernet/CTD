import { Button } from '@atoms';
import { ButtonType, IDentist, InputType } from '@types';
import { Form, notification, Row } from 'antd';
import React from 'react';
import { useHistory } from 'react-router';
import { Input } from '@atoms';
import './style.css';
import { createDentist } from '@services';

export const AddDentist: React.FC = () => {
  const history = useHistory();
  const [form] = Form.useForm();

  const onValuesChange = (values: any) => form.setFieldsValue((dentist: IDentist) => ({ ...dentist, ...values }));

  const onSubmit = async (data: IDentist) => {
    try {
      await createDentist(data);
      notification.success({
        message: 'Odontólogo añadido con éxito',
      });
      history.push('/odontologos');
    } catch (error) {
      notification.error({
        message: 'Error al añadir odontólogo',
        description: (error as Error).toString(),
      });
    }
  };

  return (
    <>
      <h1 className="text-center mt-1 mb-2">Añadir Odontólogo</h1>
      <Form
        form={form}
        layout="vertical"
        className="form"
        requiredMark="optional"
        validateMessages={{ required: 'Campo requerido' }}
        onValuesChange={onValuesChange}
        onFinish={onSubmit}
      >
        <Input name="username" label="Usuario" required />
        <Input name="password" label="Contraseña" required />
        <Input name="firstName" label="Nombre" required />
        <Input name="lastName" label="Apellido" required />
        <Input type={InputType.Number} name="licenseNumber" label="Nº de licencia" required />
        <Row justify="space-between" className="width-100 mt-1">
          <Button type={ButtonType.Primary} htmlType="submit" className="m-auto">
            AÑADIR ODONTÓLOGO
          </Button>
        </Row>
      </Form>
    </>
  );
};
