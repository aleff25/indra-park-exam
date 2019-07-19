# indra-park-exam
Desafio técnico – Desenvolvedor Web

### Database

* [Postgres] 

Create your table 
### 
<code>CREATE TABLE public.park (
                        	plate varchar(9) NOT NULL,
                        	car_model varchar(20) NOT NULL,
                        	created_at timestamp NOT NULL,
                        	operation varchar(20) NOT NULL,
                        	updated_at timestamp NOT NULL,
                        	CONSTRAINT park_pk PRIMARY KEY (plate)
                        );
                  </code>

### Clone
Run: `git clone https://github.com/Andryev/indra-park-exam.git`

### Build

Run: `mvn clean install`

### Config Database

Configurar o banco de dados em application.properties.

### Start

Run: `IndraParkApiApplication.java`


