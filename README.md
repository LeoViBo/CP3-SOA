# Clínica API — Spring Boot (SOA — Prof. Salatiel Luz Marinho)

## Requisitos
- Java 21
- Maven 3.9+
- Docker (para rodar testes com Testcontainers) e PostgreSQL local (para desenvolvimento)

## Como rodar
```bash
mvn spring-boot:run
# Swagger UI:
# http://localhost:8080/swagger-ui.html
```

## Banco de Desenvolvimento
A aplicação espera um Postgres local:
- URL: `jdbc:postgresql://localhost:5432/clinica`
- user: `clinica`
- password: `secret`

Use o script do Flyway (automaticamente aplicado) em `src/main/resources/db/migration/V1__init.sql`.

## Endpoints iniciais
- `POST /pacientes` — cria paciente (201)
- `GET  /pacientes` — lista paginada

## Atividade dos estudantes
Implemente o CRUD de **Médico** com validações e documentação OpenAPI.

## Testes de Integração
```bash
mvn -q -DskipTests=false test
```
Os testes utilizam **Testcontainers** com uma instância PostgreSQL efêmera.

## Prova
- Implementar os repositórios para Paciente, Médico e Consulta;
- Implementar classe na service (Paciente, Médico e Consulta);
- Implementar DTOs de Paciente, Médico e Consulta;
- Utilizar persistência em banco de dados para Paciente, Médico e Consulta (oracle, mysql, postgre, etc);
- Montar um ReadMe explicativo sobre os endpoints para Paciente, Médico e Consulta;
- Se possível documentar o projeto utilizando Swagger e colocar print de execução via Swagger (Caso queria -  gravar um video demonstrativo também será válido ou poderá mostrar a execução ao professor no dia 22/09/2025).
- Sugerir ao mínimo 3 evoluções no projeto e das 3 implementar pelo menos 1 melhoria no projeto. 
- O trabalho precisa ser feito de forma individual, melhorias que forem iguais serão penalizadas com retirada de ponto no CP1.
- Postar na tarefa link do repositório com todos os requisitos atendidos!