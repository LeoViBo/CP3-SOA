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


# Clínica API - Gerenciamento de Pacientes, Médicos e Consultas

Esta aplicação é uma **API RESTful** desenvolvida em **Spring Boot** para gerenciamento de uma clínica médica.  
Permite o controle de **Pacientes**, **Médicos** e **Consultas**, com CRUD completo (Create, Read, Update, Delete) e paginação.

---

## Tecnologias Utilizadas
- Java 17+  
- Spring Boot  
- Spring Data JPA  
- Hibernate / Jakarta Persistence  
- H2 ou outro banco relacional (configurável)  
- Lombok  
- Jakarta Validation  
- Springdoc OpenAPI (Swagger)

---

## Funcionamento da Aplicação

A aplicação segue o padrão **MVC** (Model-View-Controller) e está estruturada em:

- **Model**: entidades JPA que representam Paciente, Medico e Consulta.  
- **DTOs**: objetos de transferência de dados para requests e responses.  
- **Controller**: endpoints REST que recebem e retornam dados via HTTP.  
- **Service**: camada de negócio que faz validações, regras e chamadas aos repositórios.  
- **Repository**: interface Spring Data JPA para acesso ao banco de dados.

---

## Estrutura de CRUD

Para cada recurso (**Paciente**, **Médico**, **Consulta**) os endpoints seguem o padrão:

| Recurso   | Método HTTP | Endpoint             | Descrição |
|-----------|------------|--------------------|-----------|
| Paciente  | POST       | /pacientes          | Cria um novo paciente |
| Paciente  | GET        | /pacientes          | Lista pacientes (com paginação) |
| Paciente  | GET        | /pacientes/{id}     | Busca paciente por ID |
| Paciente  | PUT        | /pacientes/{id}     | Atualiza paciente existente |
| Paciente  | DELETE     | /pacientes/{id}     | Remove paciente |

| Recurso   | Método HTTP | Endpoint             | Descrição |
|-----------|------------|--------------------|-----------|
| Médico    | POST       | /medicos           | Cria um novo médico |
| Médico    | GET        | /medicos           | Lista médicos (com paginação) |
| Médico    | GET        | /medicos/{id}      | Busca médico por ID |
| Médico    | PUT        | /medicos/{id}      | Atualiza médico existente |
| Médico    | DELETE     | /medicos/{id}      | Remove médico |

| Recurso   | Método HTTP | Endpoint             | Descrição |
|-----------|------------|--------------------|-----------|
| Consulta  | POST       | /consultas         | Cria uma nova consulta |
| Consulta  | GET        | /consultas         | Lista consultas (com paginação) |
| Consulta  | GET        | /consultas/{id}    | Busca consulta por ID |
| Consulta  | PUT        | /consultas/{id}    | Atualiza consulta existente (data e status) |
| Consulta  | DELETE     | /consultas/{id}    | Remove consulta |

---

## Validações e Regras de Negócio

- **Paciente**
  - `nome`, `cpf` e `email` são obrigatórios.  
  - CPF deve ter exatamente 11 dígitos.  
  - Email deve ser válido.  

- **Médico**
  - `nome`, `especialidade`, `crm` e `email` são obrigatórios.  
  - CRM deve ser único.  
  - Email deve ser válido.  

- **Consulta**
  - Deve ter paciente e médico existentes.  
  - `dataHora` deve ser futura.  
  - Status inicial é `AGENDADA`. Pode ser atualizado para `CONFIRMADA` ou `CANCELADA`.

## Evoluções de projeto
- Agenda do Médico → endpoint que mostra as consultas futuras de um médico.
- Histórico de Consultas por Paciente → endpoint que retorna todas as consultas de um paciente.
- Evitar conflitos de horário → validação para impedir que um médico ou paciente tenha duas consultas no mesmo horário.

## Melhoria de projeto
- Melhorar o tratamento de exceções que não são aplicadas a muitos

## Prova
- Implementar os repositórios para Paciente, Médico e Consulta;
- Implementar classe na service (Paciente, Médico e Consulta);
- Implementar DTOs de Paciente, Médico e Consulta;
- Utilizar persistência em banco de dados para Paciente, Médico e Consulta (oracle, mysql, postgre, etc);
- Montar um ReadMe explicativo sobre os endpoints para Paciente, Médico e Consulta;
- Sugerir ao mínimo 3 evoluções no projeto e das 3 implementar pelo menos 1 melhoria no projeto. 