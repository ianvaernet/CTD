import React, { useState } from 'react';
import { Breadcrumb, Layout as AntdLayout, Menu } from 'antd';
import { CalendarOutlined, MedicineBoxOutlined, UserOutlined } from '@ant-design/icons';
import { Link, useLocation } from 'react-router-dom';
import './style.css';
import { Role } from '@types';
const { Sider, Content } = AntdLayout;

type Props = {
  children: JSX.Element;
  userRole: Role;
};

export const Layout: React.FC<Props> = ({ children, userRole }: Props) => {
  const [collapsed, setCollapsed] = useState(false);
  const location = useLocation();
  const urlParts = location.pathname.split('/');
  if (urlParts[1] === '') urlParts.shift();

  const toggleMenu = () => {
    setCollapsed((isCollapsed) => !isCollapsed);
  };

  return (
    <AntdLayout>
      <Sider collapsible collapsed={collapsed} onCollapse={toggleMenu} className="layout-sidebar">
        <div className="layout-sidebar-logo-container">
          <img src="/logo.svg" alt="logo" className="layout-sidebar-logo" />
        </div>
        <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
          <Menu.Item key="1" icon={<CalendarOutlined className="layout-sidebar-icon" />} >
            <Link to="/turnos">Turnos</Link>
          </Menu.Item>
          {userRole === Role.ADMIN && (
            <Menu.Item key="2" icon={<MedicineBoxOutlined className="layout-sidebar-icon" />}>
              <Link to="/odontologos">Odontólogos</Link>
            </Menu.Item>
          )}
          {userRole === Role.ADMIN && (
            <Menu.Item key="3" icon={<UserOutlined className="layout-sidebar-icon" />}>
              <Link to="/pacientes">Pacientes</Link>
            </Menu.Item>
          )}
        </Menu>
      </Sider>
      <AntdLayout className="site-layout">
        <Content className="layout-content">
          <Breadcrumb className="layout-breadcrumb">
            {urlParts.map((urlPart, index) => (
              <Breadcrumb.Item key={urlPart}>
                <Link to={urlParts.slice(0, index + 1).join('/')}>
                  {urlPart ? urlPart.slice(0, 1).toUpperCase() + urlPart.slice(1) : 'Inicio'}
                </Link>
              </Breadcrumb.Item>
            ))}
          </Breadcrumb>
          <div className="layout-content-background">
            {children}
          </div>
        </Content>
      </AntdLayout>
    </AntdLayout>
  );
};
