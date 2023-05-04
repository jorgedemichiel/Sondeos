# Sondeos
## Ejercicio práctico Sondeos "Galería de Libros"

### Pasos para probar la aplicación

**1. Iniciar el proyecto que esta configurado con una base de datos en memoria "H2".**

**2. Necesitamos agregar información a nuestra base de datos, por lo cuál realizamos un POST con la información necesaria:**

Paso de ejemplo 2 POST para tener información de estos libros en la DB: 

curl --location --request POST 'http://localhost:8080/api/book' \
--header 'Content-Type: application/json' \
--data-raw '{
 "title": "Prueba1",
 "userName": "Rodrigo",
 "price": "1200",
 "date": "2023-02-15"
}'

curl --location --request POST 'http://localhost:8080/api/book' \
--header 'Content-Type: application/json' \
--data-raw '{
 "title": "Prueba2",
 "userName": "Nestor",
 "price": "800",
 "date": "2023-02-10"
}'

**3. Vamos a validar que se hayan guardado los datos cargados mediante un GET:**

curl --location --request GET 'http://localhost:8080/api/books'

**4. Vamos a validar que se pueda modificar un dato ya cargado en nuestra base, esto lo hacemos mediante el método PUT:**

curl --location --request PUT 'http://localhost:8080/api/book/1' \
--header 'Content-Type: application/json' \
--data-raw '{
 "title": "Suru",
 "userName": "Rodrigo",
 "date": "2023-02-12"
}'

Luego podemos volver a ejecutar el GET para chequear que se modificó la información. 

**5. Vamos a eliminar un registro (libro) de nuestra base de datos:**

curl --location --request DELETE 'http://localhost:8080/api/book/1'

Por último, chequeamos que se haya eliminado el registro elegido ejecutando nuevamente el get. 


