-- ***************************************** CHECKPOINT 4 ***************************************** 
-- CAMADA: 6
-- GRUPO: 12

-- 1. Listar todos los usuarios que no hayan generado una playlist.
SELECT nombreusuario FROM usuario
LEFT JOIN playlist ON usuario.idUsuario = playlist.idusuario
WHERE playlist.idusuario IS NULL;

-- 2. Listar todas las canciones indicando cuántos likes recibió —hayan o no recibido likes— y si han sido reproducidas.
SELECT titulo AS cancion, COALESCE(cantlikes, 0) AS cantidad_likes, IF(cantreproduccion > 0, "SI", "NO") AS reproducidas FROM cancion;

-- 3. Listar todas las canciones que pertenezcan a más de una playlist. Incluir en el listado el nombre de la canción,
--    el código y a cuántas playlist pertenecen.
SELECT cancion.idCancion AS codigo_cancion, cancion.titulo AS nombre_cancion, COUNT(*) AS cantidad_playlist FROM cancion
INNER JOIN playlistxcancion ON cancion.idCancion = playlistxcancion.idCancion
GROUP BY codigo_cancion, nombre_cancion
HAVING cantidad_playlist > 1;

-- 4. Generar un reporte con el nombre del artista y el nombre de la canción que no pertenecen a ninguna lista.
SELECT artista.Nombre AS artista, cancion.titulo AS cancion FROM cancion
INNER JOIN album ON cancion.IdAlbum = album.idAlbum
INNER JOIN artista ON album.idArtista = artista.idArtista
LEFT JOIN playlistxcancion ON cancion.idCancion = playlistxcancion.Idcancion
WHERE playlistxcancion.Idcancion IS NULL;

-- 5. Listar todas las canciones, el nombre del autor, la razón social de la discográfica y la cantidad de veces 
--    que fue reproducida.
SELECT cancion.titulo AS titulo_cancion, artista.nombre AS nombre_autor, discografica.nombre AS discografica, COALESCE(cantreproduccion, 0) AS cantidad_reproducciones FROM cancion
INNER JOIN album ON album.idAlbum = cancion.idAlbum
INNER JOIN artista ON artista.idArtista = album.idalbum
INNER JOIN discografica ON discografica.iddiscografica = album.iddiscografica;

-- 6. Listar todas las discográficas que pertenezcan a Inglaterra y la cantidad de álbumes que hayan editado.
SELECT discografica.nombre AS discografica, COUNT(*) AS albumes_editados FROM discografica
INNER JOIN pais ON discografica.idPais = pais.idPais
LEFT JOIN album ON discografica.idDiscografica = album.iddiscografica
WHERE pais.Pais = "Inglaterra"
GROUP BY discografica;

-- 7. Listar a todos los artistas que no poseen ningún álbum.
SELECT Nombre AS artista FROM artista 
LEFT JOIN album ON artista.idArtista = album.idArtista
WHERE idAlbum IS NULL;

-- 8. Listar todos los álbumes que tengan alguna canción que posea más de un género
SELECT DISTINCT album.titulo AS titulo_album FROM cancion 
INNER JOIN album ON cancion.IdAlbum = album.IdAlbum 
INNER JOIN generoxcancion ON generoxcancion.IdCancion = cancion.IdCancion 
GROUP BY generoxcancion.IdCancion
HAVING COUNT(generoxcancion.Idcancion) > 1;

-- 9. Generar un reporte por usuario, listando las suscripciones que tiene o tuvo, el importe que abonó y
--    los datos de las formas de pago con que lo realizó.
SELECT DISTINCT nombreusuario, TipoUsuario, FechaInicio, fecharenovacion, Importe, TipoFormaPago, CBU, Banco, NroTarjeta, MesCaduca, AnioCaduca, CVC  FROM usuario
INNER JOIN suscripcion ON usuario.idusuario = suscripcion.idusuario
INNER JOIN tipousuario ON suscripcion.IdTipoUsuario = tipousuario.IdTipoUsuario
INNER JOIN datospagoxusuario ON usuario.idUsuario = datospagoxusuario.idusuario
INNER JOIN tipoformapago ON datospagoxusuario.idTipoFormaPago = tipoformapago.idTipoFormaPago
INNER JOIN pagos ON datospagoxusuario.idDatosPagoxUsuario = pagos.idDatosPagoxUsuario
ORDER BY nombreusuario, FechaInicio;
