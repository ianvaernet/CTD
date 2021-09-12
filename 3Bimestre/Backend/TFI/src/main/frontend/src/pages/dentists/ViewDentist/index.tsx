import { Button } from '@atoms';
import { Direction, IDentist, InputType, Mode } from '@types';
import { Form, notification, Row } from 'antd';
import React, { useEffect, useState } from 'react';
import { useHistory, useLocation, useParams } from 'react-router';
import { changeMode, getButtonType, getButtonText } from 'src/utils';
import { Input } from '@atoms';
import './style.css';
import { deleteDentist, getDentist, updateDentist } from '@services';

export const ViewDentist: React.FC = () => {
  const [mode, setMode] = useState<Mode>(new URLSearchParams(useLocation().search).get('mode') as Mode);
  const [dentist, setDentist] = useState<any>();
  const { id } = useParams<{ id: string }>();
  const history = useHistory();
  const [form] = Form.useForm();
  const isDisabled = mode !== Mode.Edit;

  const getData = async () => {
    const dentistData = await getDentist(id);
    setDentist(dentistData);
    form.setFieldsValue(dentistData);
  };

  useEffect(() => {
    getData();
  }, []);

  const onValuesChange = (values: any) => form.setFieldsValue((dentist: IDentist) => ({ ...dentist, ...values }));

  const onSubmit = async (data: IDentist) => {
    if (mode === Mode.Delete) {
      try {
        await deleteDentist(id);
        notification.success({
          message: 'Odontólogo eliminado con éxito',
        });
        history.push('/odontologos');
      } catch (error) {
        notification.error({
          message: 'Error al eliminar odontólogo',
          description: (error as Error).toString(),
        });
      }
    }
    if (mode === Mode.View) setMode(Mode.Delete);
    if (mode === Mode.Edit) {
      try {
        await updateDentist(id, data);
        notification.success({
          message: 'Odontólogo actualizado con éxito',
        });
        setMode(Mode.View);
      } catch (error) {
        notification.error({
          message: 'Error al actualizar odontólogo',
        });
      }
    }
  };

  return (
    <>
      <h1 className="text-center mt-1 mb-2">{'Odontólogo ID = ' + id}</h1>
      <Form
        form={form}
        layout="vertical"
        className="form"
        requiredMark="optional"
        validateMessages={{ required: 'Campo requerido' }}
        onValuesChange={onValuesChange}
        onFinish={onSubmit}
      >
        <Input type={InputType.Number} name="licenseNumber" label="Nº de licencia" disabled={isDisabled} required />
        <Input name="firstName" label="Nombre" disabled={isDisabled} required />
        <Input name="lastName" label="Apellido" disabled={isDisabled} required />
        <Input name="username" label="Usuario" disabled={isDisabled} required />
        <Row justify="space-between" className="width-100 mt-1">
          <Button type={getButtonType(Direction.Left, mode)} htmlType="submit">
            {getButtonText(Direction.Left, mode)}
          </Button>
          <Button type={getButtonType(Direction.Right, mode)} onClick={() => changeMode(mode, setMode, () => form.setFieldsValue(dentist))}>
            {getButtonText(Direction.Right, mode)}
          </Button>
        </Row>
      </Form>
    </>
  );
};
