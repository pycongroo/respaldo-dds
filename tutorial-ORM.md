# Tutorial ORM
## Cosas Previas
### Dependencias
Se agregan las dependecias al pom.xml
```xml
<!-- JPA Persistence Dependencies -->
  <dependency>
    <groupId>org.uqbar-project</groupId>
    <artifactId>jpa-java8-extras</artifactId>
    <version>1.0-alpha-1</version>
  </dependency>

<!-- driver jdbc -->
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.26</version>
  </dependency>
```

### Configuracion
#### Ubicacion
Se necesita ubicar el archivo de configuracion en:
```
proyecto/
└ src/
  └ main/
    ├ java/
    └ resources/
      └ META_INF/
        └ persistence.xml

```
o en:
```
proyecto/
└ src/
  ├ main/
  └ test/
    ├ java/
    └ resources/
      └ META_INF/
        └ persistence.xml

```

#### Contenido
se puede llevar el contenido tal cual como esta aca abajo, pero es mejor eliminar lo comentado
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
    http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
    version="2.0">

    <persistence-unit name="db" transaction-type="RESOURCE_LOCAL">
    	<provider>org.hibernate.ejb.HibernatePersistence</provider>

		<!--
    ACA SE ESCRIBEN LAS CLASES A PERSISTIR
    se las llama de la misma forma que se importara
    <class>paquete.clase_a_persistir</class>
    -->

        <properties>
	    	<property name="hibernate.archive.autodetection" value="class"/>        
    <!-- si se persiste en base de datos final
    -->
            <property name="hibernate.connection.driver_class" value="com.mysql.jdbc.Driver" />
    <!--NOMBRE BASE DE DATOS-->
            <property name="hibernate.connection.url" value="jdbc:mysql://localhost:3306/nombreDB" />
            <property name="hibernate.connection.username" value="root" />
            <property name="hibernate.connection.password" value="mysql" />

<!-- SI SE LO QUIERE TENER EN MEMORIA
            <property name="hibernate.connection.driver_class" value="org.hsqldb.jdbcDriver" />
            <property name="hibernate.connection.url" value="jdbc:hsqldb:mem:app-db" />
            <property name="hibernate.connection.username" value="sa" />
            <property name="hibernate.connection.password" value="" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect" />
 -->

            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true" />
            <property name="use_sql_comments" value="true" />
            <property name="hibernate.hbm2ddl.auto" value="update" />
        </properties>

    </persistence-unit>

</persistence>
```

### En las CLASES
Se debe agregar el anotation _Entity_ en las  clases a persistir que fueron señaladas en la configuracion.
``` java
@Entity
class ClaseAPersistir {
  @Id
  private id long;
}
```
