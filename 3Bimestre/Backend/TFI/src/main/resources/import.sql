INSERT INTO USERS (username, password, role, first_name, last_name) VALUES ('admin', 'admin', 0, 'Usuario', 'Administrador'), ('odontologo', 'odontologo', 1, 'Usuario', 'Odontologo'), ('paciente', 'paciente', 2, 'Usuario', 'Paciente');
INSERT INTO DENTISTS (id, license_number) VALUES (2, 2654);
INSERT INTO ADDRESSES (street, number) VALUES ('Av. Alberdi', 1450);
INSERT INTO PATIENTS (id, DNI, entry_date, address_id) VALUES (3, 12345678, current_timestamp, 1);
INSERT INTO APPOINTMENTS (date_time, dentist_id, patient_id) VALUES ('2021-10-14T20:00', 2, 3);