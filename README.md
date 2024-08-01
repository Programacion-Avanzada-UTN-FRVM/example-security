# Ejemplo de Auditoria

Este proyecto sirve como ejemplo para integrar **Spring Security** y la **auditoria de JPA** en un proyecto de Spring Boot.

Tomar como referencia el codigo para integrar en tu propio proyecto.

## Referencia

Para integrar auditoria con JPA, [vease en este enlace](https://docs.spring.io/spring-data/jpa/reference/auditing.html).

## Integracion

Siga estos pasos para desplegar esta aplicacion de ejemplo correctamente.

1. Clonar el repositorio.
```bash
$ pwd
/home/user
$ git clone https://github.com/Programacion-Avanzada-UTN-FRVM/example-security.git
$ cd example-security
$ pwd
/home/user/example-security
```

2. Crear un contenedor nuevo de MariaDB en Docker.
```bash
$ docker volumes create volumen_db
volumen_db
$ docker run -d --name mariadb_container -e MARIADB_ROOT_PASSWORD=test1234 -v volumen_db:/var/lib/mysql -p 3306:3306 mariadb
1012b9c0a63e7301ab88718cf80fa2860777ba1a3f46d055768e43e78d50cd24
```

3. Entrar al contenedor de MariaDB y crear una nueva base de datos. El proyecto de ejemplo esta configurado para funcionar en la base `test` con las credenciales `root` de usuario y `test1234` de contrasena, puedes cambiarlas si utilizaste un comando diferente para crear el contenedor o ya tienes una DB existente en el `application.properties` del proyecto.
```sh
$ docker exec -it mariadb_container /bin/sh
# mariadb -p
Enter password: test1234

Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 5
Server version: 11.4.2-MariaDB-ubu2404 mariadb.org binary distribution

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MariaDB [(none)]> CREATE DATABASE test;
Query OK, 1 row affected (0.000 sec)

MariaDB [(none)]> exit
Bye
# exit
puntero@puntero:~$
```

(Opcionalmente puedes conectarte a la instancia visualmente con algun cliente grafico como **DBeaver** o similares.)

4. Iniciar la aplicacion de Spring Boot
```bash
$ pwd
/home/user/example-security
$ ./gradlew bootRun
```

5. Si inicia correctamente, ya puedes hacer peticiones HTTP al controlador.

## Troubleshooting

### A que rutas puedo hacer peticiones?

Verifica visitando el archivo del controlador en **`ExampleEntityController.java`**.

### Como integro esta auditoria en mi proyecto?

Revisa las clases `AuditorAwareImpl`, `AuditApplication`, `ExampleEntity` y `SecurityConfig` para conocer como integrar la auditoria y Spring Security a tu proyecto especifico. Encontraras comentarios que explican que hace cada cosa.

Ademas, debes tener en cuenta las **dependencias** de Gradle que utiliza el proyecto para funcionar.