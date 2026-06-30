# ⚙️ Assistência Técnica Manager — Backend

## 📌 Descrição

API REST para gerenciamento de assistência técnica. Gerencia clientes, dispositivos e ordens de serviço, com autenticação Basic Auth para técnicos.

## 🚀 Tecnologias

- Java 21 + Spring Boot 4.0
- Spring Web, Spring Data JPA, Spring Security
- MySQL 8.0 + Flyway
- Lombok
- Docker Compose

## 🧱 Estrutura do Projeto

```
src/main/java/Kani0dev/ATM/
├── Config/
│   └── Security.java              # Spring Security (Basic Auth, BCrypt, @EnableMethodSecurity)
├── Controler/
│   ├── AuthController.java        # /auth/register, /auth/me
│   ├── ClientControler.java       # /clients/**
│   ├── DeviceControler.java       # /device/**
│   ├── SOControler.java           # /device/so/**
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

## 📦 Endpoints

### Autenticação (`/auth`)
| Método | Rota | Descrição | Acesso |
|--------|------|-----------|--------|
| POST | `/auth/register` | Registrar novo técnico | Público |
| GET | `/auth/me` | Dados do usuário autenticado | Autenticado |

### Clientes (`/clients`)
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/clients/list` | Listar todos os clientes |
| GET | `/clients/list/{id}` | Buscar cliente por ID |
| POST | `/clients/add` | Criar novo cliente |
| DELETE | `/clients/remove/{id}` | Excluir cliente (hard delete) |
| PUT | `/clients/deactivate/{id}` | Desativar cliente |
| PUT | `/clients/activate/{id}` | Ativar cliente |
| PUT | `/clients/edit/{id}` | Editar cliente |
| POST | `/clients/add-device/{client_id}@{device_id}` | Vincular dispositivo a cliente |
| DELETE | `/clients/rm-device/{client_id}@{device_id}` | Desvincular dispositivo |
| GET | `/clients/get-device/{client_id}` | Listar dispositivos do cliente |

### Dispositivos (`/device`)
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/device/list` | Listar todos os dispositivos |
| GET | `/device/list/{id}` | Buscar dispositivo por ID |
| POST | `/device/add-to/{clientId}` | Adicionar dispositivo a um cliente |
| DELETE | `/device/rm/{id}` | Excluir dispositivo |
| PUT | `/device/edit/{id}` | Editar dispositivo |

### Ordens de Serviço (`/device/so/`)
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/device/so/list/{device_id}` | Listar OS de um dispositivo |
| POST | `/device/so/add/{device_id}` | Adicionar OS a um dispositivo |
| DELETE | `/device/so/rm/{device_id}@{so_id}` | Remover OS |
| PUT | `/device/so/edit/{deviceId}@{so_id}` | Editar OS |

> Endpoints de clientes, dispositivos e OS exigem `@PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")`.

## 🔐 Autenticação

- **Tipo:** HTTP Basic Auth
- **Senhas:** hasheadas com BCrypt
- **Roles:** `TECHNICIAN`, `ADMIN`
- `ClientUser` (cliente) **não faz login** — é apenas um registro de cliente

### Registrar técnico
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"tecnico1","password":"123456","role":"TECHNICIAN"}'
```

### Usar nas requisições
```bash
curl -u tecnico1:123456 http://localhost:8080/clients/list
```

## 🐳 Como executar

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

## 🔗 Frontend

Repositório: [Assistencia_Tecnica_Menager_Frontend](https://github.com/kani0dev/Assistencia_Tecnica_Menager_Frontend)
