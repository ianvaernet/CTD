import React, { useEffect, useState } from 'react';
import FullCalendar from '@fullcalendar/react';
import esLocale from '@fullcalendar/core/locales/es';
import dayGridPlugin from '@fullcalendar/daygrid';
import listPlugin from '@fullcalendar/list';
import interaction from '@fullcalendar/interaction';
import { Modal, notification, Select, TimePicker } from 'antd';
import { useUserContext } from '@store';
import { createAppointment, deleteAppointment, listAppointments, listDentists, listPatients } from '@services';
import { IAppointment, IDentistList, IPatientList, Role } from '@types';
import './style.css';

export const ListAppointments = () => {
  const { user } = useUserContext();
  const isUserADentist = user?.role === Role.Dentist;
  const isUserAPatient = user?.role === Role.Patient;
  const [appointments, setAppointments] = useState<IAppointment[]>([]);
  const [dentists, setDentists] = useState<IDentistList[]>([]);
  const [patients, setPatients] = useState<IPatientList[]>([]);
  const [visibleAppointments, setVisibleAppointments] = useState<IAppointment[]>([]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [appointment, setAppointment] = useState<{
    id?: number;
    date?: string;
    time: any;
    dentistId: number;
    dentistName?: string;
    patientId: number;
    patientName?: string;
    timeForDelete?: string;
  }>({
    id: 0,
    date: '',
    time: '',
    dentistId: user && isUserADentist ? user.id : 0,
    patientId: user && isUserAPatient ? user.id : 0,
  });
  const [appointmentsFilters, setAppointmentsFilters] = useState({
    dentistId: 0,
    patientId: 0,
  });

  function getCalendarEvents() {
    return visibleAppointments.map(({ id, patient, dateTime }) => ({
      id: id.toString(),
      title: patient.fullName,
      start: new Date(dateTime + 'Z'),
      end: new Date(new Date(dateTime + 'Z').getTime() + 3600 * 1000),
    }));
  }

  function showModal({ id, dateStr }: { id?: number; dateStr?: string }) {
    const { dentist, patient, dateTime } = getAppointmentById(id);
    const time = new Date(dateTime + 'Z');
    setAppointment({
      ...appointment,
      id,
      date: dateStr,
      dentistName: dentist.fullName,
      patientName: patient.fullName,
      timeForDelete: ('0' + time.getHours()).slice(-2) + ':' + ('0' + time.getMinutes()).slice(-2),
    });
    setIsModalVisible(true);
  }

  async function handleCreateOrDeleteAppointment() {
    try {
      if (appointment.id) {
        await deleteAppointment(appointment.id);
      } else {
        await createAppointment({
          dateTime: new Date(appointment.date + 'T' + appointment.time.format('HH:mm')),
          dentistId: appointment.dentistId,
          patientId: appointment.patientId,
        });
      }
      setIsModalVisible(false);
      await getAppointemts();
      notification.success({
        message: 'Turno ' + (appointment.id ? 'eliminado' : 'reservado') + ' con éxito',
      });
    } catch (error) {
      notification.error({
        message: 'Error al ' + (appointment.id ? 'eliminar' : 'reservar') + ' el turno',
        description: (error as Error).toString(),
      });
    }
  }

  async function getAppointemts() {
    let appointmentList: IAppointment[] = [];
    const dentistId = isUserADentist ? user?.id : undefined;
    const patientId = isUserAPatient ? user?.id : undefined;
    try {
      appointmentList = await listAppointments(dentistId, patientId);
    } catch (error) {
      notification.error({
        message: 'Error al obtener los turnos',
        description: (error as Error).toString(),
      });
    }
    setAppointments(appointmentList);
  }

  function getAppointmentById(
    id?: number
  ): IAppointment | { dentist: { fullName: string }; patient: { fullName: string }; dateTime: string } {
    if (id) {
      return appointments.find((appointment) => appointment.id === id) as IAppointment;
    } else {
      return { dentist: { fullName: '' }, patient: { fullName: '' }, dateTime: '' };
    }
  }

  useEffect(() => {
    getAppointemts();
  }, []);

  useEffect(() => {
    const getDentists = async () => {
      let dentistList: IDentistList[] = [];
      try {
        dentistList = await listDentists();
      } catch (error) {
        notification.error({
          message: 'Error al obtener los odontólogos',
          description: (error as Error).toString(),
        });
      }
      setDentists(dentistList);
    };
    const getPatients = async () => {
      let patientList: IPatientList[] = [];
      try {
        patientList = await listPatients();
      } catch (error) {
        notification.error({
          message: 'Error al obtener los pacientes',
          description: (error as Error).toString(),
        });
      }
      setPatients(patientList);
    };
    if (!isUserAPatient) getPatients();
    if (!isUserADentist) getDentists();
  }, [isUserADentist, isUserAPatient]);

  useEffect(() => {
    let filteredAppointments = appointmentsFilters.dentistId
      ? appointments.filter(({ dentist }) => dentist.id === appointmentsFilters.dentistId)
      : appointments;
    filteredAppointments = appointmentsFilters.patientId
      ? filteredAppointments.filter(({ patient }) => patient.id === appointmentsFilters.patientId)
      : filteredAppointments;
    setVisibleAppointments(filteredAppointments);
  }, [appointments, appointmentsFilters]);

  const filterAppointmentsByDentist = (dentistId: string) => {
    setAppointmentsFilters((appointmentsFilters) => ({ ...appointmentsFilters, dentistId: parseInt(dentistId) }));
  };
  const filterAppointmentsByPatient = (patientId: string) => {
    setAppointmentsFilters((appointmentsFilters) => ({ ...appointmentsFilters, patientId: parseInt(patientId) }));
  };
  const setAppointmentDentist = (dentistId: number) => setAppointment((appointment) => ({ ...appointment, dentistId }));
  const setAppointmentPatient = (patientId: number) => setAppointment((appointment) => ({ ...appointment, patientId }));

  return (
    <>
      <h1 className="appointments-title">Turnos</h1>
      {isUserADentist ? null : (
        <div className="appointements-filter">
          <label>Filtrar turnos por odntólogo</label>
          <Select
            showSearch
            placeholder="Seleccionar odontólogo"
            optionFilterProp="children"
            onChange={filterAppointmentsByDentist}
            filterOption={(input, option) =>
              dentists
                .filter(({ fullName }) => fullName.toLowerCase().includes(input.toLowerCase()))
                .map(({ id }) => id.toString())
                .includes(option?.value)
            }
            className="appointments-filter_select"
          >
            <Select.Option value="0">Todos</Select.Option>
            {dentists.map(({ id, fullName }) => (
              <Select.Option value={id.toString()}>{fullName}</Select.Option>
            ))}
          </Select>
        </div>
      )}
      {isUserAPatient ? null : (
        <div className="appointements-filter">
          <label>Filtrar turnos por paciente</label>
          <Select
            showSearch
            placeholder="Seleccionar paciente"
            optionFilterProp="children"
            onChange={filterAppointmentsByPatient}
            filterOption={(input, option) =>
              patients
                .filter(({ fullName }) => fullName.toLowerCase().includes(input.toLowerCase()))
                .map(({ id }) => id.toString())
                .includes(option?.value)
            }
            className="appointments-filter_select"
          >
            <Select.Option value="0">Todos</Select.Option>
            {patients.map(({ id, fullName }) => (
              <Select.Option value={id.toString()}>{fullName}</Select.Option>
            ))}
          </Select>
        </div>
      )}
      <div className="appointements-calendar">
        <FullCalendar
          plugins={[dayGridPlugin, listPlugin, interaction]}
          locales={[esLocale]}
          initialView="dayGridMonth"
          dateClick={showModal}
          eventClick={({ event }) => showModal({ id: parseInt(event.id) })}
          height="75vh"
          headerToolbar={{
            start: 'title',
            center: 'dayGridMonth listMonth',
            end: 'today prev,next',
          }}
          events={getCalendarEvents()}
        />
      </div>
      <Modal
        title={appointment.id ? 'Eliminar turno' : 'Reservar turno'}
        visible={isModalVisible}
        okText={appointment.id ? 'Eliminar' : 'Reservar'}
        okButtonProps={{
          disabled: appointment.id ? false : !(appointment.dentistId && appointment.patientId && appointment.date && appointment.time),
          className: appointment.id ? 'btn danger' : 'btn primary',
        }}
        onOk={handleCreateOrDeleteAppointment}
        cancelText="Cancelar"
        onCancel={() => setIsModalVisible(false)}
        className="modal-body"
      >
        <div className="appointment_form-div">
          <label className="appointment_form-label">Odontólogo:</label>
          {appointment.id ? (
            <label>{appointment.dentistName}</label>
          ) : isUserADentist ? (
            <label>{user?.firstName + ' ' + user?.lastName}</label>
          ) : (
            <Select
              showSearch
              style={{ width: 200 }}
              placeholder="Seleccionar odontólogo"
              optionFilterProp="children"
              onChange={setAppointmentDentist}
              filterOption={(input, option) =>
                dentists
                  .filter(({ fullName }) => fullName.toLowerCase().includes(input.toLowerCase()))
                  .map(({ id }) => id.toString())
                  .includes(option?.value)
              }
              className="appointment_form-picker"
            >
              {dentists.map(({ id, fullName }) => (
                <Select.Option value={id.toString()}>{fullName}</Select.Option>
              ))}
            </Select>
          )}
        </div>
        <div className="appointment_form-div">
          <label className="appointment_form-label">Paciente:</label>
          {appointment.id ? (
            <label>{appointment.patientName}</label>
          ) : isUserAPatient ? (
            <label>{user?.firstName + ' ' + user?.lastName}</label>
          ) : (
            <Select
              showSearch
              style={{ width: 200 }}
              placeholder="Seleccionar paciente"
              optionFilterProp="children"
              onChange={setAppointmentPatient}
              filterOption={(input, option) =>
                patients
                  .filter(({ fullName }) => fullName.toLowerCase().includes(input.toLowerCase()))
                  .map(({ id }) => id.toString())
                  .includes(option?.value)
              }
              className="appointment_form-picker"
            >
              {patients.map(({ id, fullName }) => (
                <Select.Option value={id.toString()}>{fullName}</Select.Option>
              ))}
            </Select>
          )}
        </div>
        <div className="appointment_form-div">
          <label className="appointment_form-label">Hora del turno:</label>
          {appointment.id ? (
            <label>{appointment.timeForDelete}</label>
          ) : (
            <TimePicker
              format="HH:mm"
              minuteStep={15}
              disabledHours={() => [0, 1, 2, 3, 4, 5, 6, 7, 13, 14, 15, 21, 22, 23, 24]}
              hideDisabledOptions
              value={appointment.time}
              onChange={(value) => setAppointment((appointment) => ({ ...appointment, time: value }))}
              placeholder="Seleccione la hora"
              className="appointment_form-picker"
            />
          )}
        </div>
      </Modal>
    </>
  );
};
