import { listPatients } from '@services';
import { IPatientList } from '@types';
import { useEffect, useState } from 'react';
import { Table, Button, Row, Space } from 'antd';
import { EyeFilled, EditFilled, DeleteFilled } from '@ant-design/icons';
import { ColumnsType } from 'antd/es/table';
import './style.css';
import { Link } from 'react-router-dom';

const columns: ColumnsType<IPatientList> = [
  {
    title: 'Nombre y apellido',
    dataIndex: 'fullName',
    defaultSortOrder: 'ascend',
    sorter: (patient1: IPatientList, patient2: IPatientList) => (patient1.fullName > patient2.fullName ? 1 : -1),
  },
  {
    title: 'DNI',
    dataIndex: 'dni',
    sorter: (patient1: IPatientList, patient2: IPatientList) => (patient1.DNI - patient2.DNI),
  },
  {
    title: 'Ver',
    dataIndex: 'id',
    align: 'center',
    render: (id) => (
      <Link to={'pacientes/' + id + '?mode=view'}>
        <Button shape="circle" className="bg-info table-button" icon={<EyeFilled className="table-icon color-dark" />}></Button>
      </Link>
    ),
  },
  {
    title: 'Editar',
    dataIndex: 'id',
    align: 'center',
    render: (id) => (
      <Link to={'pacientes/' + id + '?mode=edit'}>
        <Button shape="circle" className="bg-warning" icon={<EditFilled className="table-icon color-dark" />}></Button>
      </Link>
    ),
  },
  {
    title: 'Eliminar',
    dataIndex: 'id',
    align: 'center',
    render: (id) => (
      <Link to={'pacientes/' + id + '?mode=delete'}>
        <Button shape="circle" className="bg-danger" icon={<DeleteFilled className="table-icon color-dark" />}></Button>
      </Link>
    ),
  },
];

export const ListPatients = () => {
  const [patients, setPatients] = useState<IPatientList[]>([]);

  async function getPatients() {
    try {
      const patients = await listPatients();
      setPatients(patients);
    } catch (error) {
      console.error(error);
    }
  }

  useEffect(() => {
    getPatients();
  }, []);

  return (
    <Space direction="vertical" size={15} className="width-100">
      <Row justify="space-between">
        <h1>Pacientes</h1>
        <Link to={'pacientes/nuevo'}>
          <Button type="primary">Añadir paciente</Button>
        </Link>
      </Row>
      <Table columns={columns} dataSource={patients} />
    </Space>
  );
};
