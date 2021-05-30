-- ------------- PARTE 1 -------------

-- 1) Listar a los clientes que hayan realizado compras durante el año 1996.
-- a. El listado debe contener las siguientes columnas:
-- b. Nombre y la inicial del apellido. Por ejemplo, en lugar de 'Mario Pontes' debe decir 'Mario P.'
-- c. El título tiene que ir todo en mayúscula.
SELECT UPPER(CONCAT(SUBSTRING(clientes.Contacto, 1, LOCATE(" ", clientes.contacto)+1), ".")) AS cliente
FROM clientes INNER JOIN facturas ON clientes.ClienteID = facturas.ClienteID;

-- 2) Listar las facturas para las cuales el envío haya sido a una ciudad distinta a la ciudad del cliente. Considerar únicamente a los clientes de UK.
-- a. El listado debe incluir:
-- b. El número de factura completando con ceros a la izquierda 8 posiciones. Por ejemplo, 00001234.
-- c. La fecha de la factura en formato YYYY-MM-DD. Por ejemplo 1996-12-01,
-- d. La ciudad del cliente.
-- e. La ciudad de destino.
SELECT CONCAT(LEFT("00000000", 8-LENGTH(facturas.FacturaID)), facturas.FacturaID) AS Nº_Factura,
DATE_FORMAT(facturas.FechaRegistro, "%Y-%m-%d") AS Fecha_factura, clientes.Ciudad AS Ciudad_cliente, facturas.CiudadEnvio AS Ciudad_destino  
FROM facturas INNER JOIN clientes ON facturas.ClienteID = clientes.ClienteId WHERE pais = "UK" AND clientes.Ciudad != facturas.CiudadEnvio;

-- 3) Listar las categorías y sus productos, incluyendo aquellas categorías para
-- las cuales no haya productos en la base de datos. En el caso de los
-- nombres de los productos, donde diga Mix o Chef, reemplazarlo por MIX
-- o CHEF respectivamente. Por ejemplo, en lugar de Filo Mix, colocar Filo MIX.
SELECT categorias.CategoriaNombre, REPLACE(REPLACE(productos.ProductoNombre, "Mix", "MIX"), "Chef", "CHEF") 
FROM categorias LEFT JOIN productos ON categorias.CategoriaID = productos.CategoriaID;


-- ------------- PARTE 2 -------------

-- 1. Para cada empresa de correo, calcular el total de transporte. Incluir todas
-- las empresas de correo, aun cuando no hayan realizado ninguna operación.


-- 2. Listar la información de contacto (o, dirección, etc.) de los clientes que no
-- hayan realizado compras. Mostrar la información en el formato que se
-- considere más apropiado. Por ejemplo: <direccion> (CP: <codigo postal>).


-- 3. Listar a los empleados, junto con el importe total de sus ventas de la
-- categoría Beverages. Mostrar el importe con dos decimales.
