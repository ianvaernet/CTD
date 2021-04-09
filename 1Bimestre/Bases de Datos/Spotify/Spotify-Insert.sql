INSERT INTO artistas (nombre, imagen) VALUES ("Pink Floyd", "https://unaimagen.com"), ("La Renga", "https://unaimagen.com");
INSERT INTO discografias (nombre, pais) VALUES ("Sony Music", "EEUU"), ("Warner", "EEUU");
INSERT INTO albums (artistas_id, titulo, anio_publicacion, imagen_portada, discografia_id) VALUES (1, "The Wall", 1979, "https://unaimagen.com", 1), (2, "Detonador de sueños", 2003, "https://unaimagen.com", 2), (2, "Despedazado por mil partes", 1996, "https://unaimagen.com", 1);
INSERT INTO canciones (album_id, titulo, duracion, numero_reproducciones, numero_likes) VALUES (1, "Mother", "3:45", 1415753, 45422), (2, "A tu lado", "3:27", 1634, 876), (3, "Despedazado por mil partes", "4:11", 8643, 5732);
INSERT INTO usuarios (email, fecha_nacimiento, sexo, codigo_postal) VALUES("yam@gmail.com", "1998-06-20" , "Masculino", 50013), ("sofia@mail.com", "1985-11-16", "Femenino", 8300), ("rocio@gmail.com", "1989-01-06", "Femenino", 4631);
INSERT INTO passwords (usuarios_id, password, fecha_modificacion) VALUES (1, "1234567", "2021-04-08"), (2, "asdfghg", "2021-02-15"), (3, "0987fs3s", "2021-01-27");
INSERT INTO suscripciones(usuarios_id, tipo, fecha_inicio, fecha_renovacion) VALUES(1, "premium", "2021-03-08", "2021-06-08");
INSERT INTO pagos_tarjeta(suscripcion_id, marca, ultimos_digitos, fecha_vencimiento) VALUES(1, "MasterCard", "1234", "2021-05-10");