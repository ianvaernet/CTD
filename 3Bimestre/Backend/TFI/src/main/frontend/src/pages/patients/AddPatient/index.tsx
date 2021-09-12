import { ButtonType, IPatient } from '@types';
import { Form, notification, Row } from 'antd';
import React from 'react';
import { useHistory } from 'react-router';
import { Button, Input } from '@atoms';
import './style.css';
import { createPatient } from '@services';

export const AddPatient: React.FC = () => {
  const history = useHistory();
  const [form] = Form.useForm();

  const onValuesChange = (values: any) => form.setFieldsValue((patient: IPatient) => ({ ...patient, ...values }));

  const onSubmit = async (data: IPatient) => {
    try {
      await createPatient(data);
      notification.success({
        message: 'Paciente añadido con éxito',
      });
      history.push('/pacientes');
    } catch (error) {
      notification.error({
        message: 'Error al añadir paciente',
        description: (error as Error).toString(),
      });
    }
  };

  return (
    <>
      <h1 className="text-center mt-1 mb-2">Añadir Paciente</h1>
      <Form
        form={form}
        layout="vertical"
        className="form"
        requiredMark="optional"
        validateMessages={{ required: 'Campo requerido' }}
        onValuesChange={onValuesChange}
        onFinish={onSubmit}
      >
        <Input name="firstName" label="Nombre" required />
        <Input name="lastName" label="Apellido" required />
        <Input name="username" label="Usuario" required />
        <Input name="password" label="Contraseña" required />
        <Row justify="space-between" className="width-100 mt-1">
          <Button type={ButtonType.Primary} htmlType="submit" className="m-auto">
            AÑADIR PACIENTE
          </Button>
        </Row>
      </Form>
    </>
  );
};
