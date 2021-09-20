import { listDentists } from '@services';
import { IDentist } from '@types';
import { useEffect, useState } from 'react';
import { Table, Button, Row, Space } from 'antd';
import { EyeFilled, EditFilled, DeleteFilled, SearchOutlined } from '@ant-design/icons';
import { ColumnsType } from 'antd/es/table';
import './style.css';
import { Link } from 'react-router-dom';

const columns: ColumnsType<IDentist> = [
  {
    title: 'Nombre y apellido',
    dataIndex: 'fullName',
    defaultSortOrder: 'ascend',
    sorter: (dentist1: IDentist, dentist2: IDentist) => (dentist1.fullName > dentist2.fullName ? 1 : -1),
  },
  {
    title: 'Nº licencia',
    dataIndex: 'licenseNumber',
    sorter: (dentist1: IDentist, dentist2: IDentist) => dentist1.licenseNumber - dentist2.licenseNumber,
  },
  {
    title: 'Ver',
    dataIndex: 'id',
    align: 'center',
    render: (id) => (
      <Link to={'odontologos/' + id + '?mode=view'} key={'see' + id}>
        <Button shape="circle" className="bg-info table-button" icon={<EyeFilled className="table-icon color-dark" />}></Button>
      </Link>
    ),
  },
  {
    title: 'Editar',
    dataIndex: 'id',
    align: 'center',
    render: (id) => (
      <Link to={'odontologos/' + id + '?mode=edit'} key={'edit' + id}>
        <Button shape="circle" className="bg-warning" icon={<EditFilled className="table-icon color-dark" />}></Button>
      </Link>
    ),
  },
  {
    title: 'Eliminar',
    dataIndex: 'id',
    align: 'center',
    render: (id) => (
      <Link to={'odontologos/' + id + '?mode=delete'} key={'delete' + id}>
        <Button shape="circle" className="bg-danger" icon={<DeleteFilled className="table-icon color-dark" />}></Button>
      </Link>
    ),
  },
];

export const ListDentists = () => {
  const [dentists, setDentists] = useState<IDentist[]>([]);

  async function getDentists() {
    try {
      const dentists = await listDentists();
      setDentists(dentists);
    } catch (error) {
      console.error(error);
    }
  }

  useEffect(() => {
    getDentists();
  }, []);

  return (
    <Space direction="vertical" size={15} className="width-100">
      <Row justify="space-between">
        <h1>Odontólogos</h1>
        <Link to={'odontologos/nuevo'}>
          <Button type="primary">Añadir odontólogo</Button>
        </Link>
      </Row>
      <Table columns={columns} dataSource={dentists} />
    </Space>
  );
};
