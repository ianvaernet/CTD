## Categorías y productos
## SELECT * FROM categorias;
## SELECT categoriaNombre, descripcion FROM categorias;
## SELECT * FROM productos;
## SELECT * FROM productos WHERE discontinuado = 1;
## SELECT * FROM productos WHERE proveedorID = 8;
## SELECT * FROM productos WHERE precioUnitario BETWEEN 10 AND 22;
## SELECT * FROM productos WHERE unidadesStock < nivelReorden;
## SELECT * FROM productos WHERE unidadesStock < nivelReorden AND unidadesPedidas = 0;

## Clientes
## SELECT contacto, compania, titulo, pais FROM clientes ORDER BY pais;
## SELECT * FROM clientes WHERE titulo = 'owner';
## SELECT * FROM clientes WHERE contacto LIKE 'C%';

## Facturas
## SELECT * FROM facturas ORDER BY fechaFactura;
## SELECT * FROM facturas WHERE paisEnvio = 'USA' AND envioVia != 3;
## SELECT * FROM facturas WHERE clienteID = 'GOURL';
## SELECT * FROM facturas WHERE empleadoID IN (2,3,5,8,9);

## Productos
## SELECT * FROM productos ORDER BY precioUnitario DESC;
## SELECT * FROM productos ORDER BY precioUnitario DESC LIMIT 5;
## SELECT * FROM productos ORDER BY unidadesStock DESC LIMIT 10;

## FacturaDetalle
## SELECT facturaID, productoID, cantidad FROM facturaDetalle;
## SELECT facturaID, productoID, cantidad FROM facturaDetalle ORDER BY cantidad DESC;
## SELECT facturaID, productoID, cantidad FROM facturaDetalle WHERE cantidad BETWEEN 50 AND 100;
## SELECT facturaID AS NroFactura, productoID as Producto, precioUnitario*cantidad AS Total FROM facturaDetalle;