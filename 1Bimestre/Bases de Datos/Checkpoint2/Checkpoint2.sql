## 1. Mostrar la cantidad de usuarios cuyo código de tipo de usuario es 1 (free) , 2 (standard) y 3 (premium).
SELECT IdTipoUsuario, count(*) AS Cantidad FROM usuario GROUP BY IdTipoUsuario;

## 2. Listar los álbumes que poseen la letra Z en su título.
SELECT titulo FROM album WHERE titulo LIKE '%z%';

## 3. Listar la cantidad de canciones que posee por usuario en sus playlist y su promedio, la cantidad máxima y mínima.
SELECT idusuario, idPlaylist, SUM(cantcanciones) AS Cantidad_canciones_de_todas_las_playlist, AVG(cantcanciones) AS Cantidad_promedio, MIN(cantcanciones) AS Cantidad_minima, MAX(cantcanciones) AS Cantidad_maxima FROM playlist GROUP BY idusuario;

## 4. Mostrar la playlist que tiene más canciones.
SELECT titulo, cantcanciones FROM playlist ORDER BY cantcanciones DESC LIMIT 1;

## 5. Listar los 10 usuarios más jóvenes.
SELECT * FROM usuario ORDER BY fecha_nac DESC LIMIT 10;

## 6. Listar los 10 usuarios más longevos.
SELECT * FROM usuario ORDER BY fecha_nac LIMIT 10;

## 7. Listar las 5 canciones con más likes, ordenadas descendentemente.
SELECT * FROM cancion ORDER BY CantLikes DESC LIMIT 5;

## 8. Generar un reporte de todos los artistas ordenados alfabéticamente.
SELECT * FROM artista ORDER BY Nombre;

## 9. Listar por país la cantidad de usuarios que posee.
SELECT Pais_idPais, COUNT(idUsuario) FROM usuario GROUP BY Pais_idPais;

## 10. Listar la última fecha de creación de una playlist cuyo título incluya una A.
SELECT FechaCreacion, titulo FROM playlist WHERE titulo LIKE "%a%" ORDER BY FechaCreacion DESC LIMIT 1;

## 11. Listar de las passwords históricas, las 3 más utilizadas, cuántas veces se utilizaron y la primera fecha que se utilizó y la última.
SELECT Password, COUNT(Password) AS Veces_usada, MIN(Fecha) AS Primera_vez_usada, MAX(Fecha) AS Ultima_vez_usada FROM passwordxusuario GROUP BY Password ORDER BY Veces_usada DESC LIMIT 3;

## 12. Insertar un usuario nuevo, con los siguientes datos, tener en cuenta las relaciones.
INSERT INTO usuario (idUsuario, nombreusuario, nyap, password, fecha_nac, sexo, CP, Pais_idPais, idTipoUsuario) VALUES (20, "miusuariodespotify@gmail.com", "Evangelina Gomez", "S1234m", "1995-11-15", "F", "B1429ATF", 1, 3);

## 13. Insertar un nuevo método de pago, que sea QR y, luego, modificar todos los usuarios que tienen como método pago en efectivo por el nuevo método QR.
INSERT INTO tipoformapago (idTipoFormaPago, TipoFormaPago) VALUES (5, "QR");
UPDATE datospagoxusuario SET idTipoFormaPago = 5 WHERE idTipoFormaPago = 1;

## 14. Eliminar todos los géneros de tipo trap.
SET SQL_SAFE_UPDATES=0;
DELETE FROM genero WHERE genero like "Trap";

## 15. Generar un listado de los países con la cantidad de discográficas que posee cada uno, utilizando el código de país.
SELECT idPais, count(*) AS Cantidad_de_discograficas FROM DISCOGRAFICA GROUP BY idPais;

## 16. Listar por género la cantidad de canciones, solo utilizar el codigo de género.
SELECT idGenero, count(IdGenero) AS Cantidad_de_canciones FROM generoxcancion GROUP BY idGenero; 

## 17. Listar la canción de menor duración.
SELECT titulo, MIN(duracion) AS Duracion FROM cancion;

## 18. Listar las 5 canciones de mayor duración.
SELECT titulo, duracion FROM cancion ORDER BY duracion DESC LIMIT 5;

## 19. Listar todas las tarjetas cuyo vencimiento sucede este año.
SELECT NroTarjeta, AnioCaduca FROM datospagoxusuario WHERE AnioCaduca = 21; 

## 20. Listar a todos los artistas que no tengan imagen, ordenados alfabéticamente.
SELECT * FROM artista WHERE imagen IS NULL ORDER BY Nombre;

