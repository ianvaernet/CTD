import { ButtonType, InputType, IPatient } from '@types';
import { Col, Form, notification, Row } from 'antd';
import React from 'react';
import { useHistory } from 'react-router';
import { Button, Input } from '@atoms';
import './style.css';
import { createPatient } from '@services';

export const AddPatient: React.FC = () => {
  const history = useHistory();
  const [form] = Form.useForm();

  const onValuesChange = (values: any) => form.setFieldsValue((patient: IPatient) => ({ ...patient, ...values }));

  const onSubmit = async (data: any) => {
    try {
      data.address = {
        street: data.street,
        number: data.number,
        floor: data.floor,
        apartment: data.apartment,
      };
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
        className="add_patient-form"
        requiredMark="optional"
        validateMessages={{ required: 'Campo requerido' }}
        onValuesChange={onValuesChange}
        onFinish={onSubmit}
      >
        <Input name="username" label="Usuario" required />
        <Input type={InputType.Password} name="password" label="Contraseña" required />
        <Input name="firstName" label="Nombre" required />
        <Input name="lastName" label="Apellido" required />
        <Input type={InputType.Number} name="dni" label="DNI" required />
        <h2 className="mt-1">Dirección</h2>
        <Row justify="space-between" className="width-100">
          <Col span={16}>
            <Input name="street" label="Calle" className="mt-0" required />
          </Col>
          <Col span={7}>
            <Input type={InputType.Number} name="number" label="Número" className="mt-0" required />
          </Col>
        </Row>
        <Row justify="space-between" className="width-100">
          <Col span={11}>
            <Input type={InputType.Number} name="floor" label="Piso" />
          </Col>
          <Col span={11}>
            <Input name="apartment" label="Departamento" />
          </Col>
        </Row>
        <Row justify="space-between" className="width-100">
          <Button type={ButtonType.Primary} htmlType="submit" className="m-auto">
            AÑADIR PACIENTE
          </Button>
        </Row>
      </Form>
    </>
  );
};
