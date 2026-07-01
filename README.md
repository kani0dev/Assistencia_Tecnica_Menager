#  Assistência Técnica Manager — Backend

## Descrição

API REST para gerenciamento de assistência técnica. Gerencia clientes, dispositivos e ordens de serviço, com autenticação Basic Auth para técnicos.

##  Tecnologias

- Java 21 + Spring Boot 4.0
- Spring Web, Spring Data JPA, Spring Security
- MySQL 8.0 + Flyway
- Lombok
- Docker Compose

##  Estrutura do Projeto

```
src/main/java/Kani0dev/ATM/
├── Config/
│   └── Security.java              # Spring Security (Basic Auth, BCrypt, @EnableMethodSecurity)
├── Controler/
│   ├── AuthController.java        # /auth/register, /auth/me
│   ├── ClientControler.java       # /clients/**
│   ├── DeviceControler.java       # /devices/**
│   ├── SOControler.java           # /service-orders/**
│   └── Output/
│       ├── ClientResponse.java    # Response DTOs
│       └── DeviceResponse.java
├── DTO/
│   ├── ClientDTO.java
│   ├── DeviceDTO.java
│   ├── SODTO.java
│   └── RegisterRequest.java
├── Mapper/
│   ├── ClientMapper.java
│   ├── DeviceMapper.java
│   └── ServiceOrderMapper.java
├── Model/
│   ├── User/
│   │   ├── User.java              # Entidade abstrata base (users table)
│   │   ├── ClientUser.java        # Cliente (não faz login)
│   │   ├── TechnicianUser.java    # Técnico (faz login)
│   │   └── Role.java              # CLIENT | TECHNICIAN | ADMIN
│   └── Device/
│       ├── Device.java
│       └── ServiceOrder.java
├── Repository/
│   ├── ClientRepo.java
│   ├── DeviceRepo.java
│   ├── SORepo.java
│   └── TechnicianRepo.java
└── Service/
    ├── ClienteService.java
    ├── DeviceService.java
    ├── SOService.java
    └── UserDetailsServiceImpl.java  # Carrega TechnicianUser para autenticação
```

##  Endpoints

### Autenticação (`/auth`)
| Método | Rota | Descrição | Acesso |
|--------|------|-----------|--------|
| POST | `/auth/register` | Registrar novo técnico | Público |

### Clientes (`/clients`)
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/clients` | Listar todos os clientes |
| GET | `/clients/{id}` | Buscar cliente por ID |
| POST | `/clients` | Criar novo cliente |
| DELETE | `/clients/{id}` | Excluir cliente (hard delete) |
| PATCH | `/clients/{id}` | Ativar/desativar cliente (body: `{"active": true/false}`) |
| PUT | `/clients/{id}` | Editar cliente |
| POST | `/clients/{clientId}/devices/{deviceId}` | Vincular dispositivo existente a cliente |
| DELETE | `/clients/{clientId}/devices/{deviceId}` | Desvincular dispositivo |
| GET | `/clients/{clientId}/devices` | Listar dispositivos do cliente |

### Dispositivos (`/devices`)
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/devices` | Listar todos os dispositivos |
| GET | `/devices/{id}` | Buscar dispositivo por ID |
| POST | `/devices` | Adicionar dispositivo (enviar `ownerid` no body) |
| DELETE | `/devices/{id}` | Excluir dispositivo |
| PUT | `/devices/{id}` | Editar dispositivo |

### Ordens de Serviço (`/service-orders`)
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/service-orders?deviceId={id}` | Listar OS (opcional: filtrar por dispositivo) |
| POST | `/service-orders?deviceId={id}` | Adicionar OS a um dispositivo |
| DELETE | `/service-orders/{soId}` | Remover OS |
| PUT | `/service-orders/{soId}?deviceId={id}` | Editar OS |

> Endpoints de clientes, dispositivos e OS exigem `@PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")`.

##  Exemplos de Requisição

### Autenticação
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"tecnico1","password":"123456","role":"TECHNICIAN"}'
```

### Clientes
```bash
# Criar cliente
curl -u tecnico1:123456 -X POST http://localhost:8080/clients \
  -H "Content-Type: application/json" \
  -d '{"name":"João","telefone":"11999999999","isActive":true}'

# Listar todos
curl -u tecnico1:123456 http://localhost:8080/clients

# Buscar por ID
curl -u tecnico1:123456 http://localhost:8080/clients/1

# Ativar/desativar cliente
curl -u tecnico1:123456 -X PATCH http://localhost:8080/clients/1 \
  -H "Content-Type: application/json" \
  -d '{"active":false}'

# Editar cliente
curl -u tecnico1:123456 -X PUT http://localhost:8080/clients/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"João Editado","telefone":"11988888888","isActive":true}'

# Excluir cliente
curl -u tecnico1:123456 -X DELETE http://localhost:8080/clients/1

# Vincular dispositivo existente a cliente
curl -u tecnico1:123456 -X POST http://localhost:8080/clients/1/devices/2

# Desvincular dispositivo
curl -u tecnico1:123456 -X DELETE http://localhost:8080/clients/1/devices/2

# Listar dispositivos do cliente
curl -u tecnico1:123456 http://localhost:8080/clients/1/devices
```

### Dispositivos
```bash
# Criar dispositivo
curl -u tecnico1:123456 -X POST http://localhost:8080/devices \
  -H "Content-Type: application/json" \
  -d '{"type":"Smartphone","brand":"Samsung","model":"S24","serialNumber":"SN123","color":"Preto","observations":"Tela trincada","ownerid":1}'

# Listar todos
curl -u tecnico1:123456 http://localhost:8080/devices

# Buscar por ID
curl -u tecnico1:123456 http://localhost:8080/devices/1

# Editar dispositivo
curl -u tecnico1:123456 -X PUT http://localhost:8080/devices/1 \
  -H "Content-Type: application/json" \
  -d '{"type":"Smartphone","brand":"Samsung","model":"S24 Ultra","serialNumber":"SN123","color":"Preto","observations":"Tela trincada e bateria","ownerid":1}'

# Excluir dispositivo
curl -u tecnico1:123456 -X DELETE http://localhost:8080/devices/1
```

### Ordens de Serviço
```bash
# Criar OS para um dispositivo (entry_date é gerado automaticamente)
curl -u tecnico1:123456 -X POST "http://localhost:8080/service-orders?deviceId=1" \
  -H "Content-Type: application/json" \
  -d '{"curent_State":"Aguardando","status":"ABERTA","defect_reported":"Não liga","service_description":"Troca de bateria","warranty_period":"90 dias"}'

# Listar OS (todas ou filtrar por dispositivo)
curl -u tecnico1:123456 "http://localhost:8080/service-orders?deviceId=1"

# Editar OS
curl -u tecnico1:123456 -X PUT "http://localhost:8080/service-orders/1?deviceId=1" \
  -H "Content-Type: application/json" \
  -d '{"curent_State":"Em andamento","status":"EM_REPARO","defect_reported":"Não liga","service_description":"Troca de bateria","warranty_period":"90 dias"}'

# Excluir OS
curl -u tecnico1:123456 -X DELETE http://localhost:8080/service-orders/1
```

##  Autenticação

- **Tipo:** HTTP Basic Auth
- **Senhas:** hasheadas com BCrypt
- **Roles:** `TECHNICIAN`, `ADMIN`
- `ClientUser` (cliente) **não faz login** — é apenas um registro de cliente

### Usar nas requisições
```bash
curl -u tecnico1:123456 http://localhost:8080/clients
```

##  Como executar

```bash
docker compose up -d
./mvnw clean install
./mvnw spring-boot:run
```

A API fica em: `http://localhost:8080`

## .env

Crie um arquivo `.env` na raiz:

```bash
MYSQL_HOST=localhost
MYSQL_DATABASE=atm-db
MYSQL_USER=atm-db-adm
MYSQL_PASSWORD=xyz112233
```

## Frontend

Repositório: [Assistencia_Tecnica_Menager_Frontend](https://github.com/kani0dev/Assistencia_Tecnica_Menager_Frontend)
