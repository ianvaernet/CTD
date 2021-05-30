-- --------------------------- PARTE 1 ----------------------------------------
-- 1) Empleados
-- a) Crear un SP que liste los apellidos y nombres de los empleados ordenados alfabéticamente.
DELIMITER $$
CREATE PROCEDURE listar_empleados()
BEGIN
	SELECT empleadoId, apellido, nombre
    FROM empleados
    ORDER BY Apellido ASC;
END $$
DELIMITER ;
-- b) Invocar el SP para verificar el resultado.
CALL listar_empleados();

-- 2) Empleados por ciudad
-- a) Crear un SP que reciba el nombre de una ciudad y liste los empleados de esa ciudad.
DELIMITER $$
CREATE PROCEDURE empleados_por_ciudad(IN nombre_ciudad VARCHAR(15))
BEGIN
	SELECT *
	FROM clientes
	WHERE Ciudad = nombre_ciudad;
END $$
DELIMITER ;
-- b) Invocar al SP para listar los empleados de Seattle.
CALL empleados_por_ciudad("Seattle");


-- 3) Clientes por país
-- a) Crear un SP que reciba el nombre de un país y devuelva la cantidad de clientes en ese país.
DELIMITER $$
CREATE PROCEDURE clientes_por_pais (IN pais VARCHAR(50))
BEGIN
	SELECT COUNT(*) AS Cantidad_clientes FROM clientes
	WHERE clientes.Pais = pais;
END $$
DELIMITER ;
-- b) Invocar el SP para consultar la cantidad de clientes en Portugal.
CALL clientes_por_pais("Portugal");

-- 4) Productos sin stock
-- a) Crear un SP que reciba un número y liste los productos cuyo stock está por
-- debajo de ese número. El resultado debe mostrar el nombre del producto, el
-- stock actual y el nombre de la categoría a la que pertenece el producto.
DELIMITER $$
CREATE PROCEDURE productos_con_stock(IN numero INT)
BEGIN
	SELECT ProductoNombre, UnidadesStock, CategoriaNombre FROM productos
    INNER JOIN categorias ON productos.CategoriaID = categorias.CategoriaID
    WHERE UnidadesStock < numero;
END $$
DELIMITER ;
-- b) Listar los productos con menos de 10 unidades en stock.
CALL productos_con_stock(10);
-- c) Listar los productos sin stock.
CALL productos_con_stock(1);

-- 5) Ventas con descuento
-- a) Crear un SP que reciba un porcentaje y liste los nombres de los productos que
-- hayan sido vendidos con un descuento igual o superior al valor indicado,
-- indicando además el nombre del cliente al que se lo vendió.
DELIMITER $$
CREATE PROCEDURE ventas_con_descuento(IN porcentaje_descuento FLOAT)
BEGIN
	SELECT ProductoNombre, CONCAT(Descuento*100,'%') AS Porcentaje_descuento, Contacto AS Cliente FROM facturadetalle
	INNER JOIN productos ON facturadetalle.ProductoID = productos.ProductoID
	INNER JOIN facturas ON facturadetalle.FacturaID = facturas.FacturaID
	INNER JOIN clientes ON facturas.ClienteID = clientes.ClienteID
	WHERE Descuento >= (porcentaje_descuento/100);
END $$
DELIMITER ;
-- b) Listar la información de los productos que hayan sido vendidos con un descuento mayor al 10%.
CALL ventas_con_descuento(10);


-- -------------------- PARTE 2 --------------------
-- 1) Cálculo de edad
-- a) Crear un SP que muestre apellidos, nombres y edad de cada empleado, debe
-- calcular la edad de los empleados a partir de la fecha de nacimiento y que
-- tengan entre n y n años de edad.
DELIMITER $$
CREATE PROCEDURE edad_empleados(IN rango1 INT, IN rango2 INT)
BEGIN
	SELECT Apellido, Nombre, TIMESTAMPDIFF(YEAR, FechaNacimiento, CURDATE()) AS Edad
	FROM empleados
	HAVING Edad BETWEEN rango1 AND rango2;
END $$
DELIMITER ;
-- b) Ejecutar el SP indicando un rango de edad entre 50 y 60 años de edad.
CALL edad_empleados(50,80);

-- 2) Actualización de productos
-- a) Crear un SP que reciba un porcentaje y un nombre de categoría y actualice los
-- productos pertenecientes a esa categoría, incrementando las unidades pedidas
-- según el porcentaje indicado. Por ejemplo: si un producto de la categoría
-- Seafood tiene 30 unidades pedidas, al invocar el SP con categoría Seafood y
-- porcentaje 10%, el SP actualizará el valor de unidades pedidas con el nuevo valor 33.
DELIMITER $$
CREATE PROCEDURE actualizar_productos (IN porcentaje FLOAT, IN categoria VARCHAR(50))
BEGIN
	DECLARE id_categoria INT;
	SET id_categoria = (SELECT CategoriaID FROM categorias WHERE CategoriaNombre = categoria);
    
	UPDATE productos
	SET UnidadesStock = ROUND(UnidadesStock * (1 + (porcentaje/100)))
	WHERE CategoriaID = id_categoria;
END $$
DELIMITER ;
-- b) Listar los productos de la categoría Beverages para ver cuántas unidades
-- pedidas hay de cada uno de ellos.
SELECT ProductoNombre, UnidadesStock FROM productos
INNER JOIN categorias ON productos.CategoriaID = categorias.CategoriaID
WHERE CategoriaNombre LIKE "Beverages";
-- c) Invocar al SP con los valores Beverages como categoría y 15 como porcentaje.
CALL actualizar_productos(15, "Beverages");
-- d) Volver a listar los productos como en (a), y validar los resultados.
SELECT ProductoNombre, UnidadesStock FROM productos
INNER JOIN categorias ON productos.CategoriaID = categorias.CategoriaID
WHERE CategoriaNombre LIKE "Beverages";

-- 3) Actualización de empleados
-- a) Crear un SP que cree una tabla con los nombres, apellidos y teléfono de contacto
-- de todos los empleados que hayan sido contratados con fecha anterior a una fecha dada.
DELIMITER $$
CREATE PROCEDURE crear_tabla_empleados_viejos(IN _fecha_contratacion DATE)
BEGIN
	CREATE TABLE `empleados_viejos` (
	  `id` INT NOT NULL AUTO_INCREMENT,
	  `Nombre` VARCHAR(10) NULL,
	  `Apellido` VARCHAR(20) NULL,
	  `Telefono` VARCHAR(24) NULL,
	  PRIMARY KEY (`id`)
	);
    
    INSERT INTO empleados_viejos (Nombre, Apellido, Telefono)
	SELECT Nombre, Apellido, Telefono FROM emarket.empleados
	WHERE FechaContratacion < _fecha_contratacion; 
END $$
DELIMITER ;
-- b) Ejecutar el SP para generar una tabla de empleados con fecha de contratación anterior a 01/01/1994.
CALL crear_tabla_empleados_viejos('1994-01-01');
-- c) Consultar la tabla generada y validar los resultados.
SELECT * FROM empleados_viejos;