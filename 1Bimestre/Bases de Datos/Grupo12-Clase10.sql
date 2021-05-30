## PARTE 1
## Clientes
## 1) ¿Cuántos clientes existen?
SELECT COUNT(*) FROM emarket.clientes;
## 2) ¿Cuántos clientes hay por ciudad?
SELECT ciudad, COUNT(*) FROM emarket.clientes GROUP BY ciudad;

## Facturas
## 1) ¿Cual es el total de transporte?
SELECT SUM(transporte) FROM facturas;
## 2) ¿Cual es el total de transporte por EnvioVia (empresa de envío)?
SELECT EnvioVia, SUM(transporte) FROM facturas GROUP BY EnvioVia;
## 3) Calcular la cantidad de facturas por cliente. Ordenar descendentemente por cantidad de facturas.
SELECT ClienteID, COUNT(*) AS cantidad_facturas FROM facturas GROUP BY ClienteID ORDER BY cantidad_facturas DESC;
## 4) Obtener el Top 5 de clientes de acuerdo a su cantidad de facturas.
SELECT ClienteID, COUNT(*) AS cantidad_facturas FROM facturas GROUP BY ClienteID ORDER BY cantidad_facturas DESC LIMIT 5;
## 5) ¿Cual es el país de envío menos frecuente de acuerdo a la cantidad de facturas?
SELECT PaisEnvio, COUNT(*) AS cantidad_facturas FROM facturas GROUP BY PaisEnvio ORDER BY cantidad_facturas LIMIT 1;
## 6) Se quiere otorgar un bono al empleado con más ventas. ¿Qué ID de empleado realizó más operaciones de ventas?
SELECT EmpleadoID, COUNT(*) AS cantidad_ventas FROM facturas GROUP BY EmpleadoID ORDER BY cantidad_ventas DESC LIMIT 1;

## Factura detalle
## 1) ¿Cuál es el producto que aparece en más líneas de la tabla Factura Detalle?
SELECT ProductoID, COUNT(*) as cantidad_apariciones FROM facturadetalle GROUP BY ProductoID ORDER BY cantidad_apariciones DESC LIMIT 1;
## 2) ¿Cuál es el total facturado? Considerar que el total facturado es la suma de cantidad por precio unitario.
SELECT SUM(cantidad*PrecioUnitario) FROM facturadetalle;
## 3) ¿Cuál es el total facturado para los productos ID entre 30 y 50?
SELECT SUM(cantidad*PrecioUnitario) FROM facturadetalle WHERE ProductoID BETWEEN 30 AND 50;
## 4) ¿Cuál es el precio unitario promedio de cada producto?
SELECT ProductoID, AVG(PrecioUnitario) FROM facturadetalle GROUP BY ProductoID; 
## 5) ¿Cuál es el precio unitario máximo?
SELECT MAX(PrecioUnitario) FROM facturadetalle;

## PARTE 2
## 1) Generar un listado de todas las facturas del empleado 'Buchanan'.
SELECT * FROM facturas INNER JOIN empleados ON facturas.EmpleadoID = empleados.EmpleadoID WHERE empleados.Apellido = "Buchanan"; 
## 2) Generar un listado con todos los campos de las facturas del correo 'Speedy Express'.
SELECT * FROM facturas INNER JOIN correos ON facturas.EnvioVia = correos.CorreoID WHERE correos.Compania = "Speedy Express";
## 3) Generar un listado de todas las facturas con el nombre y apellido de los empleados.
SELECT * FROM facturas INNER JOIN empleados ON facturas.EmpleadoID = empleados.EmpleadoID;
## 4) Mostrar un listado de las facturas de todos los clientes “Owner” y país de envío “USA”.
SELECT * FROM facturas INNER JOIN clientes ON facturas.ClienteID = clientes.ClienteID WHERE facturas.PaisEnvio = "USA" AND clientes.Titulo = "Owner";
## 5) Mostrar todos los campos de las facturas del empleado cuyo apellido sea “Leverling” o que incluyan el producto id = “42”.
SELECT DISTINCT facturas.FacturaID, facturas.ClienteID, facturas.FechaFactura, facturas.FechaRegistro, facturas.FechaEnvio FROM facturas INNER JOIN empleados ON facturas.EmpleadoID = empleados.EmpleadoID INNER JOIN facturadetalle ON facturas.FacturaID=facturadetalle.FacturaID WHERE empleados.Apellido = "Leverling" OR facturadetalle.ProductoID=42;
## 6) Mostrar todos los campos de las facturas del empleado cuyo apellido sea “Leverling” y que incluya los producto id = “80” o ”42”.
SELECT DISTINCT facturas.FacturaID, facturas.ClienteID, facturas.FechaFactura, facturas.FechaRegistro, facturas.FechaEnvio FROM facturas INNER JOIN empleados ON facturas.EmpleadoID = empleados.EmpleadoID INNER JOIN facturadetalle ON facturas.FacturaID=facturadetalle.FacturaID WHERE empleados.Apellido = "Leverling" AND (facturadetalle.ProductoID=80 OR facturadetalle.ProductoID=42);
## 7) Generar un listado con los cinco mejores clientes, según sus importes de compras total (PrecioUnitario * Cantidad).
SELECT facturas.ClienteId, ROUND(SUM(cantidad*PrecioUnitario), 2) AS total_compras FROM facturas INNER JOIN facturadetalle ON facturas.FacturaID = facturadetalle.FacturaID GROUP BY facturas.ClienteId ORDER BY total_compras DESC LIMIT 5;
## 8) Generar un listado de facturas, con los campos id, nombre y apellido del cliente, fecha de factura, país de envío, Total,  ordenado de manera descendente por fecha de factura y limitado a 10 filas.
SELECT facturas.ClienteID, clientes.Contacto AS Cliente, facturas.FechaFactura, facturas.PaisEnvio, ROUND(SUM(cantidad*PrecioUnitario), 2) AS Total FROM facturadetalle INNER JOIN facturas ON facturadetalle.FacturaID = facturas.FacturaID INNER JOIN clientes ON facturas.ClienteID = clientes.ClienteID GROUP BY facturas.clienteId;
