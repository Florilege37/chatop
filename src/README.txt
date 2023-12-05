La dépendance suivante est à commenter:

<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

Cette


//Tu ne peux pas importer deux dépendences de base de données differentes il faut en choisir une soit MYSQL ou H2
<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>


<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
</dependency>