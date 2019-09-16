# empresaREST

# API REST CRUD para trabajar sobre una empresa y guardar empleados en una base de datos Mongo

# Requisitos para consumir la api:
mongodb 3.6.x or +<br /> 
java jdk 8 or +<br /> 

# Body de ejemplo:
{<br />
    "nombre": "marc",<br />
    "apellido": "lopez",<br />
    "dni": "3959537",<br />
    "direccion": "rosario",<br />
    "antiguedad": 0,<br />
    "cargo": "VENDEDOR",<br />
    "telefono": "12",<br />
    "sueldo": "5000",<br />
    "despacho":"4a",<br />
    "fax":"5123123",<br />
    "comisiones":"1500",<br />
    "secretarioDni":"3"<br />
}

# Endpoints utiles
# Crear empleados:
(solo se pueden crear JEFE_DE_ZONA, VENDEDOR o SECRETARIO)<br />
(el dni es unico no se pueden crear 2 empleados con mismo dni)<br />
http://localhost:8080/v1/api/empleados/jefes <--- POST<br />
http://localhost:8080/v1/api/empleados/vendedores <--- POST<br />
http://localhost:8080/v1/api/empleados/secretarios <--- POST<br />
(crear empleados obteniendo datos desde la api FacultadRest)<br />
http://localhost:8080/v1/api/facultad/jefes/{dni} <--- POST<br />
http://localhost:8080/v1/api/facultad/vendedores/{dni} <--- POST<br />
http://localhost:8080/v1/api/facultad/secretarios/{dni} <--- POST<br />

# Buscar empleados
(traer una lista de empleados)<br />
http://localhost:8080/v1/api/empleados <--- GET<br />
(traer una lista de empleados segun su cargo)<br />
http://localhost:8080/v1/api/empleados/jefes <--- GET<br />
http://localhost:8080/v1/api/empleados/secretarios <--- GET<br />
http://localhost:8080/v1/api/empleados/vendedores <--- GET<br />
(traer un empleado en particular segun su dni)<br />
http://localhost:8080/v1/api/empleados/{dni}<--- GET<br />
(traer una lista de empleados de la api FacultadRest)<br />
http://localhost:8080/v1/api/facultad/ <--- GET<br />
(traer un empleado en particular segun su dni de la api FacultadRest)<br />
http://localhost:8080/v1/api/facultad/{dni} <--- GET<br />
(traer una lista de empleados filtrando segun parametros particulares)<br />
(se puede filtrar por N cantidad de parametros)<br />
http://localhost:8080/v1/api/empleados/?atributo1=valor1&?atributo2=valor2 <--- GET
(traer una lista de empleados de la facultad filtrando por la query dinamica de la api de FacultadRest)<br />
http://localhost:8080/v1/api/facultad/?atributo1=valor1&?atributo2=valor2 <--- GET<br />

# Modificar empleado
(modificar un empleado en particular segun su dni)<br />
(si se quiere modificar el cargo debe hacerlo desde el endpoint del cargo nuevo que queire darle)<br />
(el dni es unico y no es modificable)<br />
http://localhost:8080/v1/api/empleados/jefes/{dni} <--- PUT<br />
http://localhost:8080/v1/api/empleados/secretarios/{dni} <--- PUT<br />
http://localhost:8080/v1/api/empleados/vendedores/{dni} <--- PUT<br />

# Borrar empleado
(borrar un empleado segun su dni)<br />
http://localhost:8080/v1/api/empleados/{dni} <--- DELETE<br />



















