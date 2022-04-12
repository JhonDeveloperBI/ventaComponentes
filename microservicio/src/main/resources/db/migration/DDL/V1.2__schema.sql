CREATE TABLE articulo (
id int(11) not null auto_increment,
nombre VARCHAR(100) not null,
unidades INT NOT NULL,
precio FLOAT NOT NULL,
primary key(id)
);

CREATE TABLE venta (
id int(13) NOT NULL auto_increment,
id_articulo int NOT NULL,
id_usuario int NOT NULL,
unidad_venta INT NOT NULL,
precio_unidad FLOAT NOT NULL,
total_venta FLOAT NOT NULL,
detalle_venta_articulo VARCHAR(50) NOT NULL,
fecha_venta DATETIME DEFAULT current_timestamp,
primary key(id)
);