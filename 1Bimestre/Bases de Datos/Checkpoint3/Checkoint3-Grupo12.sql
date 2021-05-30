## **************** CHECKPOINT 3 **************************
## CAMADA: 6
## GRUPO: 12

## 1. Mostrar el nombre de usuario y contar la cantidad de playlists que tiene.
SELECT nombreusuario, COUNT(*) AS cantidad_playlists
FROM usuario INNER JOIN playlist ON usuario.idUsuario = playlist.idusuario
GROUP BY (nombreusuario);

## 2. Mostrar los nombres de los géneros con la cantidad de temas que tienen asignadas a ellas.
SELECT genero, COUNT(*) AS cantidad_temas
FROM genero INNER JOIN generoxcancion ON genero.idGenero = generoxcancion.idGenero
GROUP BY genero;

## 3. Mostrar las canciones que contienen la letra Z en su nombre, el nombre del álbum al que pertenece y la banda respectiva.
SELECT cancion.titulo AS cancion, album.titulo AS album, artista.nombre AS banda
FROM cancion INNER JOIN album ON cancion.idAlbum = album.idAlbum INNER JOIN artista ON album.idArtista = artista.idArtista
WHERE cancion.titulo LIKE("%Z%");

## 4. Mostrar la playlist con más canciones y el nombre del usuario que la creó.
SELECT playlist.titulo AS playlist, playlist.cantcanciones AS cantidad_canciones, nombreusuario AS creada_por
FROM playlist INNER JOIN usuario ON playlist.idusuario = usuario.idusuario
ORDER BY cantidad_canciones DESC LIMIT 1;

## 5. Generar un reporte de seguridad, listando las claves utilizadas por todos los usuarios, indicando el nombre del usuario y tipo, ordenado por fecha descendentemente.
SELECT nombreusuario, TipoUsuario, password
FROM usuario
INNER JOIN tipousuario ON usuario.idTipoUsuario = tipousuario.idTipoUsuario
ORDER BY fecha_nac DESC;

## 6. Listar por usuario, indicando usuario, la clave y la fecha de caducidad —90 días posterior a la fecha alta de la clave— a aquellos que posean una suscripción de tipo free.
SELECT nombreusuario, usuario.password, DATE_ADD(fecha, INTERVAL 90 DAY) AS fechaCaducidad 
FROM usuario 
INNER JOIN passwordxusuario ON passwordxusuario.idUsuario = usuario.idUsuario
INNER JOIN tipousuario ON usuario.idTipoUsuario = tipousuario.idTipoUsuario 
WHERE TipoUsuario = "free";

## 7. Listar las canciones que tienen los artistas cuyo nombre contiene la letra “r” y pertenecen al género pop.
SELECT cancion.titulo AS cancion FROM cancion
INNER JOIN album ON cancion.IdAlbum = album.IdAlbum
INNER JOIN artista ON album.idArtista = artista.idArtista 
INNER JOIN generoxcancion ON cancion.IdCancion = generoxcancion.IdCancion
INNER JOIN genero ON genero.idGenero = generoxcancion.idGenero
WHERE artista.nombre like "%r%" AND Genero = "Pop";

## 8. Generar un reporte donde se muestre el método de pago, la cantidad de operaciones que se realizaron con cada uno y el importe total.
SELECT TipoFormaPago, count(idPagos) AS cantidad_operaciones, sum(Importe) AS importe_total
FROM tipoformapago
INNER JOIN datospagoxusuario ON datospagoxusuario.idTipoFormaPago = tipoformapago.idTipoFormaPago
INNER JOIN pagos ON pagos.IdDatosPagoxUsuario = datospagoxusuario.IdDatosPagoxUsuario
GROUP BY TipoFormaPago;

## 9. Listar todos los usuarios que pagaron con QR y la fecha de pago sea del 2020.
SELECT nombreusuario, tipoformapago AS TipoPago, pagos.fechaPago
FROM tipoformapago
INNER JOIN datospagoxusuario ON tipoformaPago.idTipoFormaPago = datospagoxusuario.idTipoFormaPago
INNER JOIN pagos ON datospagoxusuario.idDatosPagoxUsuario = pagos.idDatosPagoxUsuario
INNER JOIN usuario ON datospagoxusuario.idusuario = usuario.idusuario
WHERE tipoformapago.TipoFormaPago = "QR" AND EXTRACT(YEAR FROM pagos.fechaPago) = "2020";

## 10. Generar un reporte de todas las canciones cuyo álbum no posee imagen de portada.
SELECT cancion.titulo AS cancion
FROM cancion INNER JOIN album ON cancion.idAlbum = album.idAlbum
WHERE ISNULL(album.imagenportada) ;

## 11. Generar un reporte por género e informar la cantidad de canciones que posee. Si una canción tiene más de un género, debe ser incluida en la cuenta de cada uno de esos géneros.
SELECT genero.Genero AS genero, COUNT(*) AS cantidad_canciones
FROM genero
INNER JOIN generoxcancion ON genero.idGenero = generoxcancion.idGenero
INNER JOIN cancion ON generoxcancion.idcancion = cancion.idcancion
GROUP BY genero;

## 12. Listar todas las playlists que no están en estado activo y a qué usuario pertenecen, ordenado por la fecha de eliminación.
SELECT playlist.titulo AS playlist, estadoplaylist.descripcion AS estado, usuario.nombreusuario
FROM playlist
INNER JOIN estadoplaylist ON playlist.idestado = estadoplaylist.idEstadoPlaylist
INNER JOIN usuario ON playlist.idusuario = usuario.idusuario
WHERE estadoplaylist.descripcion != "activa"
ORDER BY playlist.Fechaeliminada;

## 13. Generar un reporte que muestre por tipo de usuario la cantidad de usuarios que posee.
SELECT tipousuario.tipousuario AS tipo_usuario, COUNT(*) AS cantidad_usuarios
FROM tipousuario
INNER JOIN usuario ON TipoUsuario.idTipoUsuario = usuario.idTipoUsuario
GROUP BY tipo_usuario;

## 14. Listar la suma total obtenida por cada tipo de suscripción, en el periodo del 01-01-2020 al 31-12-2020.
SELECT tipousuario.TipoUsuario AS tipo_suscripcion, SUM(pagos.importe) AS total
FROM suscripcion
INNER JOIN pagos ON suscripcion.idpagos = pagos.idpagos
INNER JOIN tipousuario ON suscripcion.IdTipoUsuario = tipousuario.IdTipoUsuario
WHERE pagos.fechaPago BETWEEN "2020-01-01" AND "2020-12-31"
GROUP BY tipo_suscripcion;

## 15. Listar el álbum y la discográfica que posea la canción con más reproducciones.
SELECT album.titulo AS album, discografica.nombre AS discografica, cancion.titulo AS cancion, cancion.cantreproduccion
FROM cancion
INNER JOIN album ON cancion.idAlbum = album.idAlbum
INNER JOIN discografica ON album.iddiscografica = discografica.iddiscografica
ORDER BY cancion.cantreproduccion DESC LIMIT 1;

## 16. Listar todas las playlist y canciones que tienen los usuarios de Argentina.
SELECT DISTINCT playlist.titulo AS playlist, cancion.titulo AS canciones
FROM playlist
INNER JOIN playlistxcancion ON playlist.idPlaylist = playlistxcancion.idPlaylist
INNER JOIN cancion ON playlistxcancion.idCancion = cancion.idCancion
INNER JOIN usuario ON playlist.idUsuario = usuario.idUsuario
INNER JOIN pais ON usuario.Pais_idPais = pais.idPais
WHERE pais.Pais = "Argentina";

## Opcional
## 17. Listar el nombre de las discográficas que posean más de 10 canciones y más de 1 género.
SELECT discografica.nombre
FROM discografica
INNER JOIN album ON discografica.idDiscografica = album.idDiscografica
INNER JOIN cancion ON album.idAlbum = cancion.idAlbum
INNER JOIN generoxcancion ON cancion.idCancion = generoxcancion.idCancion
GROUP BY discografica.nombre
HAVING COUNT(cancion.titulo)>10 AND COUNT(DISTINCT generoxcancion.idGenero) > 1;