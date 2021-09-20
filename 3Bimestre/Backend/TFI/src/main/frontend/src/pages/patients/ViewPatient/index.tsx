import { Button } from '@atoms';
import { Direction, InputType, IPatient, Mode } from '@types';
import { Col, Form, notification, Row } from 'antd';
import React, { useEffect, useState } from 'react';
import { useHistory, useLocation, useParams } from 'react-router';
import { changeMode, getButtonType, getButtonText } from 'src/utils';
import { Input } from '@atoms';
import './style.css';
import { deletePatient, getPatient, updatePatient } from '@services';

export const ViewPatient: React.FC = () => {
  const [mode, setMode] = useState<Mode>(new URLSearchParams(useLocation().search).get('mode') as Mode);
  const [patient, setPatient] = useState<any>();
  const { id } = useParams<{ id: string }>();
  const history = useHistory();
  const [form] = Form.useForm();
  const isDisabled = mode !== Mode.Edit;

  const getData = async () => {
    const patientData = await getPatient(id);
    if (patientData.address?.floor === 0) delete patientData.address.floor;
    setPatient({ ...patientData, ...patientData.address });
    form.setFieldsValue({ ...patientData, ...patientData.address });
  };

  useEffect(() => {
    getData();
  }, []);

  const onValuesChange = (values: any) => form.setFieldsValue((patient: IPatient) => ({ ...patient, ...values }));

  const onSubmit = async (data: any) => {
    if (mode === Mode.Delete) {
      try {
        await deletePatient(id);
        notification.success({
          message: 'Paciente eliminado con éxito',
        });
        history.push('/pacientes');
      } catch (error) {
        notification.error({
          message: 'Error al eliminar paciente',
          description: (error as Error).toString(),
        });
      }
    }
    if (mode === Mode.View) setMode(Mode.Delete);
    if (mode === Mode.Edit) {
      try {
        data.address = {
          street: data.street,
          number: data.number,
          floor: data.floor,
          apartment: data.apartment,
        };
        await updatePatient(id, data);
        notification.success({
          message: 'Paciente actualizado con éxito',
        });
        setMode(Mode.View);
      } catch (error) {
        notification.error({
          message: 'Error al actualizar paciente',
        });
      }
    }
  };

  return (
    <>
      <h1 className="text-center mt-1 mb-2">{'Paciente ID = ' + id}</h1>
      <Form
        form={form}
        layout="vertical"
        className="form"
        requiredMark="optional"
        validateMessages={{ required: 'Campo requerido' }}
        onValuesChange={onValuesChange}
        onFinish={onSubmit}
      >
        <Input name="username" label="Usuario" disabled={isDisabled} required />
        <Input name="firstName" label="Nombre" disabled={isDisabled} required />
        <Input name="lastName" label="Apellido" disabled={isDisabled} required />
        <Input type={InputType.Number} name="dni" label="DNI" disabled={isDisabled} required />
        <h2 className="mt-1">Dirección</h2>
        <Row justify="space-between" className="width-100">
          <Col span={16}>
            <Input name="street" label="Calle" disabled={isDisabled} className="mt-0" required />
          </Col>
          <Col span={7}>
            <Input type={InputType.Number} name="number" label="Número" disabled={isDisabled} className="mt-0" required />
          </Col>
        </Row>
        <Row justify="space-between" className="width-100">
          <Col span={11}>
            <Input type={InputType.Number} name="floor" label="Piso" disabled={isDisabled} />
          </Col>
          <Col span={11}>
            <Input name="apartment" label="Departamento" disabled={isDisabled} />
          </Col>
        </Row>
        <Row justify="space-between" className="width-100 mt-1">
          <Button type={getButtonType(Direction.Left, mode)} htmlType="submit">
            {getButtonText(Direction.Left, mode)}
          </Button>
          <Button type={getButtonType(Direction.Right, mode)} onClick={() => changeMode(mode, setMode, () => form.setFieldsValue(patient))}>
            {getButtonText(Direction.Right, mode)}
          </Button>
        </Row>
      </Form>
    </>
  );
};
