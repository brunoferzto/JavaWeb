drop Database if exists biosproducionesdb;
create database biosproducionesdb;
USE biosproducionesdb;

Create Table Usuarios
(
Nombre varchar (20) not null primary key,
Contrasenia varchar (20) not null
);



Create Table RecursosHumanos
(
Cedula int primary key, 
NombreCompleto varchar (30) not null,
Estado bit not null Default 1
);

Create Table PersonalTecnico 
(
Cedula int not null primary key, 
Cargo varchar (30) not null check (Cargo ="Director" or Cargo = "Camarógrafo" or Cargo = "Editor" or Cargo = "Otro"),
foreign key (Cedula) references RecursosHumanos(Cedula) 
);

Create Table PersonalLogistico
(
Cedula int not null primary key, 
Area varchar (30) not null check (Area ="Casting" or Area = "Locaciones" or Area = "Contaduría" or Area = "Otro"),
foreign key (Cedula) references RecursosHumanos(Cedula) 
);

Create Table PersonalActores
(
Cedula int (8) primary key, 
FechaNacimiento datetime not null,
Sexo varchar (10) not null check (Sexo ="Femenino" or Sexo = "Masculino"),
Foto varchar (255) not null,
foreign key (Cedula) references RecursosHumanos(Cedula) 
);


Create Table RecursosMateriales 
(
Id int primary key,
Tipo varchar (30) not null check (Tipo ="Cámara" or Tipo = "Grúa" or Tipo = "Reflector" or Tipo = "Otro"),
Marca varchar (30) not null,
Modelo varchar (15) not null,
Descripcion varchar (200) not null,
Estado bit not null Default 1
);

create Table Spot
(
Id int primary key auto_increment,
Titulo varchar (30) not null,
FechaInicio datetime not null,
FechaFinal datetime not null,
Precio int not null
);

Create Table PersonalSpot
(
Spot int not null,
Cedula int not null,
foreign key (Spot) references Spot (Id),
foreign key (Cedula) references RecursosHumanos(Cedula),
primary key (Spot,Cedula)
);

Create Table EquiposSpot
(
Spot int not null,
Equipo int not null,
foreign key (Spot) references Spot (Id),
foreign key (Equipo) references RecursosMateriales (Id),
primary key (Spot,Equipo)
);

use biosproducionesdb;



Create Procedure Logueo (usu varchar (20), contra varchar (20))
Select *  From Usuarios Where Nombre = usu and Contrasenia = contra;
use biosproducionesdb;
Select *  From Usuarios Where Nombre = "admin" and Contrasenia = "a";
call logueo("admin","admin");

Create Procedure AltaSpot (titulo varchar(30),fechaI Date, fechaF Date,precio int)
Insert into Spot  Values (0,titulo,fechaI,fechaF,precio);

Create Procedure AsignarHumanoSpot (ide int,ced int)
Insert into PersonalSpot values (ide,ced);

DELIMITER //
Create Procedure AsignarEquipoSpot (ide int, equ int) 
begin
 if not exists (Select * from EquiposSpot where Equipo = equ and Spot = ide)	then
  insert into EquiposSpot values (ide,equ);
end if;
end //
DELIMITER ;

Create Procedure BuscarSpot (ide int)
Select * From Spot Where Id = ide;

Create Procedure RegistrarEquipo (id int, tipo varchar (30), marca varchar (30),modelo varchar (30),descripcion varchar(200))
Insert Into RecursosMateriales (Id,Tipo,Marca,Modelo,Descripcion,Estado) values (id,tipo,marca,modelo,descripcion,1);

Create Procedure ModificarEquipo (ide int, tipo varchar (30), marca varchar (30),modelo varchar (30),descripcion varchar(200))
    Update RecursosMateriales  set Tipo = tipo, Marca = marca, Modelo = modelo, Descripcion = descripcion where Id = ide;
  
  create procedure ListarEquipo ()
  Select * From RecursosMateriales Where Estado = 1;
  
  Create Procedure EquiposdeSpot (idspot int)
  Select * From RecursosMateriales inner join EquiposSpot on RecursosMateriales.Id = EquiposSpot.Equipo
	where EquiposSpot.Spot = idspot;
    
    
    create Procedure BuscarEquipo (ide int)
    Select * From RecursosMateriales where Id = ide and Estado = 1; 
    
    #Create Procedure VerificarEquipo (ideqp int,  fini datetime, ffin datetime)
  #Select * From RecursosMateriales inner join EquiposSpot on RecursosMateriales.Id = EquiposSpot.Equipo 
	#							where EquiposSpot.Equipo = ideqp and EquiposSpot.Spot in (Select Id from Spot 
     #                           where  Spot.FechaInicio = fini or Spot.FechaInicio = ffin or 
		#						Spot.FechaFinal = ffin or Spot.FechaFinal = fini);
                                
		#	use biosproducionesdb;
         #     CALL VerificarEquipo('2','2019-08-22 00:00:00','2019-01-15 00:00:00');
   
   create procedure SpotAsosciadosEquipos (ideqp int)
   Select Spot.Id, Spot.Titulo, Spot.FechaInicio, Spot.FechaFinal, Spot.Precio
   From Spot Inner Join EquiposSpot on Spot.Id = EquiposSpot.Spot
	where EquiposSpot.Equipo = ideqp;
    
    create procedure SpotAsosciadoPersonal (ced int)
   Select Spot.Id, Spot.Titulo, Spot.FechaInicio, Spot.FechaFinal, Spot.Precio
   From Spot Inner Join PersonalSpot on Spot.Id = PersonalSpot.Spot
	where PersonalSpot.Cedula = ced;
    
    
    


DELIMITER //
  Create Procedure EliminarEquipo (ide int)
 begin
 if exists (Select * from EquiposSpot where Equipo = ide)	then
	update RecursosMateriales set Estado = 0 where Id = ide;
 else
	delete from RecursosMateriales Where  Id = ide;
    end if;
    end//
DELIMITER ;

DELIMITER //
create procedure RegistropersonalTecnico (ced int, nom varchar(30),cargo varchar (30))
Begin
insert RecursosHumanos values (ced,nom,1);
insert PersonalTecnico (Cedula,Cargo) values (ced,cargo);
end//
  DELIMITER ;
  
DELIMITER //
Create Procedure ModificarpersonalTecnico (ced int, nom varchar(30),cargo varchar (30))
begin
Update RecursosHumanos set NombreCompleto = nom where Cedula = ced;
Update PersonalTecnico set Cargo = cargo where  Cedula = ced;
end//
DELIMITER ;


DELIMITER //
  Create Procedure EliminarpersonalTecnico (ced int)
 begin
 if exists (Select * from PersonalSpot where Cedula = ced)	then
	update RecursosHumanos set Estado = 0 where Cedula = ced;
 else
	delete from PersonalTecnico Where Cedula = ced;
	delete from RecursosHumanos Where Cedula = ced;
    end if;
    end//
DELIMITER ;

Create Procedure ListarpersonalTecnico ()
Select * From PersonalTecnico inner join RecursosHumanos on PersonalTecnico.Cedula = RecursosHumanos.Cedula where RecursosHumanos.Estado = 1;

Create Procedure BuscarTecnico (ced int)
Select * From PersonalTecnico inner join RecursosHumanos on PersonalTecnico.Cedula = RecursosHumanos.Cedula where RecursosHumanos.Estado = 1 and RecursosHumanos.Cedula = ced;

Create Procedure TecnicosdeSpot (ide int)
Select RecursosHumanos.Cedula, NombreCompleto, PersonalTecnico.Cargo
 From RecursosHumanos inner join PersonalTecnico on RecursosHumanos.Cedula = PersonalTecnico.Cedula 
								inner join PersonalSpot on RecursosHumanos.Cedula = PersonalSpot.Cedula 
Where PersonalSpot.Spot = ide;


  DELIMITER //
create procedure RegistropersonalLogistico (ced int, nom varchar(30),area varchar (30))
Begin
insert RecursosHumanos values (ced,nom,1);
insert PersonalLogistico values (ced,area);
end//
DELIMITER ;

DELIMITER //
Create Procedure ModificarpersonalLogistico (ced int, nom varchar(30),area varchar (30))
Begin
Update RecursosHumanos set NombreCompleto = nom where Cedula = ced;
Update PersonalLogistico set Area = area where  Cedula = ced;
end//
  DELIMITER ;
  
  
  DELIMITER //
  Create Procedure EliminarpersonalLogistico (ced int)
 begin
 if exists (Select * from PersonalSpot where Cedula = ced)	then
	update RecursosHumanos set Estado = 0 where Cedula = ced;
 else
	delete from PersonalLogistico Where Cedula = ced;
	delete from RecursosHumanos Where Cedula = ced;
    end if;
    end//
DELIMITER ;

Create Procedure ListarpersonalLogistico ()
Select * From PersonalLogistico inner join RecursosHumanos on PersonalLogistico.Cedula = RecursosHumanos.Cedula where RecursosHumanos.Estado = 1;

Create procedure BuscarLogistico(ced int)
Select * From PersonalLogistico inner join RecursosHumanos on PersonalLogistico.Cedula = RecursosHumanos.Cedula where RecursosHumanos.Estado = 1 and RecursosHumanos.Cedula = ced ;


Create Procedure LogisticodeSpot (ide int)
Select RecursosHumanos.Cedula, RecursosHumanos.NombreCompleto, PersonalLogistico.Area
 From RecursosHumanos inner join PersonalLogistico on RecursosHumanos.Cedula = PersonalLogistico.Cedula 
								inner join PersonalSpot on RecursosHumanos.Cedula = PersonalSpot.Cedula 
Where PersonalSpot.Spot = ide;



DELIMITER //
create procedure RegistropersonalActores (ced int, nom varchar(30),fecha date, sexo varchar (10),foto varchar (250))
Begin
insert RecursosHumanos values (ced,nom,1);
insert PersonalActores values (ced,fecha,sexo,foto);
end//
  DELIMITER ;
  
  DELIMITER //
  Create Procedure ModificarpersonalActores (ced int, nom varchar(30),fecha date, sexo varchar (10),foto varchar (250))
  begin
  Update RecursosHumanos set NombreCompleto = nom where Cedula = ced;
  Update PersonalActores set FechaNacimiento = fecha, Sexo = sexo, Foto = foto where Cedula = ced;
  end //
  DELIMITER ;

   DELIMITER //
  Create Procedure EliminarpersonalActores (ced int)
 begin
 if exists (Select * from PersonalSpot where Cedula = ced)	then
	update RecursosHumanos set Estado = 0 where Cedula = ced;
 else
	delete from PersonalActores Where Cedula = ced;
	delete from RecursosHumanos Where Cedula = ced;
    end if;
    end//
DELIMITER ;


	Create Procedure ListarpersonalActores ()
Select * From PersonalActores inner join RecursosHumanos on PersonalActores.Cedula = RecursosHumanos.Cedula where RecursosHumanos.Estado = 1;

Create Procedure BuscarActor (ced int)
Select * From PersonalActores inner join RecursosHumanos on PersonalActores.Cedula = RecursosHumanos.Cedula where RecursosHumanos.Estado = 1 and RecursosHumanos.Cedula = ced;

Create Procedure ActoresdeSpot (ide int)
Select RecursosHumanos.Cedula, RecursosHumanos.NombreCompleto, PersonalActores.FechaNacimiento, PersonalActores.Sexo, PersonalActores.Foto
 From RecursosHumanos inner join PersonalActores on RecursosHumanos.Cedula = PersonalActores.Cedula 
								inner join PersonalSpot on RecursosHumanos.Cedula = PersonalSpot.Cedula 
Where PersonalSpot.Spot = ide;

INSERT Usuarios VALUES ('admin','admin');
INSERT Usuarios VALUES ('admin2','adim2');
INSERT Usuarios VALUES ('admin3','adim3');

  
CALL AltaSpot ('Coca Cola Navidadeña','2019-07-22','2019-08-15',90000);
CALL AltaSpot ('Rexona Plus','2019-06-06','2019-06-17',50000);
CALL AltaSpot ('Dog Chau','2019-10-16','2019-10-31',15500);
CALL AltaSpot ('Uruguay Natural','2019-07-22','2019-08-15', 13000);
CALL AltaSpot ('Las Acacias','2018-12-10','2019-01-10', 8000);
CALL AltaSpot ('Cativelli','2020-06-22','2020-07-10', 8500);
CALL AltaSpot ('Chevrolet Z500','2020-07-22','2020-08-22', 58000);

  
CALL RegistrarEquipo (1,'Camara','Caloi','RH XT22','Baja Definicion');
CALL RegistrarEquipo (2,'Otro','Canon','Estandar Z500','Trípode');
CALL RegistrarEquipo (3,'Reflector','Sony','Sony RF 1','Alta Iluminacion');
CALL RegistrarEquipo (4,'Reflector','Microsonic','RP 88','Reflector');
CALL RegistrarEquipo (5,'Otro','AOC','R24','n/d');
CALL RegistrarEquipo (6,'Camara','Panasonic','Lumix G7','Camara');
CALL RegistrarEquipo (7,'Camara','Panasonic','Lumix G7','Camara');
CALL RegistrarEquipo (8,'Otro','Microsonic','VT5001','Ventilador');
CALL RegistrarEquipo (9,'Otro','Sony','AEK 85','Microfono');
CALL RegistrarEquipo (10,'Otro','Sony','AEK 85','Microfono');

CALL ModificarEquipo(1,'Camara','Panasonic','Lumix G7','Alta Definicion');


insert EquiposSpot values (1,2);
  
  CALL RegistropersonalTecnico (12121212,'Pablo Chevanton','Director');
  CALL RegistropersonalTecnico (23232323,'Orson Welles','Director');
  CALL RegistropersonalTecnico (34343434,'Dario Argento','Director');
  CALL RegistropersonalTecnico (56565655,'Pablo Diaz','Director');
  CALL RegistropersonalTecnico (67676767,'Stanley Kubrick Rodriguez','Camarógrafo');
  CALL RegistropersonalTecnico (78787878,'Madi Di Castro','Camarógrafo');
  CALL RegistropersonalTecnico (89899898,'Julian Dalton','Camarógrafo');
  CALL RegistropersonalTecnico (19191919,'Mauricio Victorino','Camarógrafo');
  CALL RegistropersonalTecnico (91020304,'Juan Perez Noya','Editor');
  CALL RegistropersonalTecnico (91020305,'Ezequiel Mortezzi','Editor');
  CALL RegistropersonalTecnico (91020306,'Carles Diaz','Editor');
  CALL RegistropersonalTecnico (91020307,'Bettina Lores','Editor');
  
  CALL RegistropersonalLogistico (10101010,'Lorenzo Mendes','Contaduria');
  CALL RegistropersonalLogistico (11111111,'Leticia Benitez','Contaduria');
  CALL RegistropersonalLogistico (22222222,'María Bolkonskaya','Contaduria');
  CALL RegistropersonalLogistico (33333333,'Rodrigo Martinez','Casting');
  CALL RegistropersonalLogistico (44444444,'Tim Maia','Casting');
  CALL RegistropersonalLogistico (55555555,'Rody Silva','Casting');
  CALL RegistropersonalLogistico (66666666,'Ester Lamandier','Casting');
  CALL RegistropersonalLogistico (77777777,'Wilson Verselli','Locaciones');
  CALL RegistropersonalLogistico (88888888,'Paulina Rubio','Locaciones');
  CALL RegistropersonalLogistico (99999999,'Pedro Ruí Costa','Otro');
  CALL RegistropersonalLogistico  (10000000,'Lucrecia Borgia','Locaciones'); 
  CALL RegistropersonalLogistico  (10000001,'Juan Andres Perez','Locaciones');
  
  CALL RegistropersonalActores (12121211,'Luana Solari','1996-08-08','Femenino','n/d');
  CALL RegistropersonalActores (13121212,'Vera Miles',	'1929-09-23','Femenino','n/d');
  CALL RegistropersonalActores (12121213,'Henry Fonda', '1926-08-08','Masculino','n/d');
  CALL RegistropersonalActores (12121214,'Natalie Portman Oreiro,','1981-06-09','Femenino','n/d');
  CALL RegistropersonalActores (12121215,'Mariana Ramirez','2019-02-06','Femenino','n/d');
  CALL RegistropersonalActores (12121216,'Vincent Cassel','1981-08-25','Masculino','n/d');
  CALL RegistropersonalActores (12121217,'Maura de Vera','1996-02-08','MAL','n/d');
  CALL RegistropersonalActores (12121218,'Jennifer Connelly','1996-10-18','Femenino','n/d');
  CALL RegistropersonalActores (12121219,'Andrea Gutierrez','1964-11-05','Femenino','n/d');
  CALL RegistropersonalActores (12121220,'Kitty Martinez','2013-12-31','Femenino','n/d');


        


