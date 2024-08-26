--Tabla de Roles
CREATE TABLE ROLES (
   Id int NOT NULL,
   nombre VARCHAR2(20),
   PRIMARY KEY (Id)
);

--Tabla Usuario
CREATE TABLE USUARIO(
Id_Usuario int NOT NULL,
nombre VARCHAR2(100),
apellido VARCHAR2(100),
correo VARCHAR2(100),
direccion VARCHAR2(2048),
username VARCHAR2(100),
tarjeta VARCHAR2(16),
pin VARCHAR2(4),
fecha DATE,
activo int AS (1),--Se podria colocar como opción bool
Id_rol int NOT NULL,
 PRIMARY KEY (Id_Usuario),
 FOREIGN KEY (Id_Usuario_fk) REFERENCES USUARIO(Id_Usuario)
);

--Tabla Factura
CREATE TABLE FACTURA(
Id_Factura int NOT NULL,
Id_Usuario int NOT NULL,
fecha DATE,
total DOUBLE,
estado int AS (1),
 PRIMARY KEY (Id_Factura),
 FOREIGN KEY (Id_Usuario_fk) REFERENCES FACTURA(Id_Usuario)
);

--Tabla Venta
CREATE TABLE VENTA(
Id_Venta int NOT NULL,
Id_Factura int NOT NULL,
Id_Producto int NOT NULL,
precio DOUBLE,
cantidad int,
 PRIMARY KEY (Id_Venta),
 FOREIGN KEY (Id_Factura) REFERENCES VENTA(Id_Factura),
 FOREIGN KEY (Id_Producto) REFERENCES VENTA(Id_Producto)
);

--Tabla Producto
CREATE TABLE PRODUCTO(
Id_Producto int NOT NULL,
Id_Categorias int NOT NULL,
nombre VARCHAR2(1000),
descripcion VARCHAR2(1600),
precio DOUBLE,
existencias int,
ruta_imagen VARCHAR2(2048),
activo int AS (1),
PRIMARY KEY (Id_Producto),
 FOREIGN KEY (Id_Categorias) REFERENCES PRODUCTO(Id_Categorias)
);

--Tabla Categoria
CREATE TABLE CATEGORIA(
Id_Categorias int NOT NULL,
nombre VARCHAR2(30),
tipo VARCHAR2(30),
activo int AS (1),
PRIMARY KEY (Id_Categorias)
);