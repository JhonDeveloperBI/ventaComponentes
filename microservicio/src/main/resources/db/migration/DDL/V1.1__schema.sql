CREATE TABLE articulo (
id_articulo int(13) NOT NULL auto_increment,
nombre VARCHAR(100) NOT NULL,
unidades INT NOT NULL,
precio FLOAT,
primary key(id_articulo)
);

CREATE TABLE venta (
id_venta int(13) NOT NULL auto_increment,
id_articulo int NOT NULL,
id_usuario int NOT NULL,
unidad_venta INT NOT NULL,
precio_unidad FLOAT,
total_venta FLOAT,
fecha_venta DATETIME DEFAULT current_timestamp,
primary key(id_venta),
foreign key(id_articulo) references articulo(id_articulo),
foreign key(id_usuario) references usuario(id)
);