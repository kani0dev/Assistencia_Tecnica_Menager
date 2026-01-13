# ⚙️ Backend
## 📌 Descrição

O Backend é uma API REST responsável por toda a regra de negócio do sistema. Ele gerencia dispositivos, ordens de serviço e seus estados, fornecendo endpoints para consumo pelo frontend.

A aplicação segue uma arquitetura em camadas, facilitando manutenção e escalabilidade.

## 🚀 Tecnologias Utilizadas

- ** Java **

- ** Spring Boot **

- ** Spring Web **

- ** Spring Data JPA **

- ** Hibernate **

- ** MySql **
- ** Lombok **

## 🧱 Arquitetura

O backend está organizado no padrão:

- Controller → Service → Repository → Model
Camadas

- Controller: Exposição dos endpoints REST

- Service: Regras de negócio

- Repository: Acesso ao banco de dados

- Model: Entidades JPA

- DTO: Transferência de dados entre camadas

## 📂 Estrutura de Pacotes (simplificada)
Kani0dev.ATM
 ├── Controler
 │   ├── DeviceController
 │   └── SOController
 ├── Service
 │   ├── DeviceService
 │   └── SOService
 ├── Model
 │   ├── Device
 │   ├── ServiceOrder
 │   └── OS_State
 ├── DTO
 └── Repository


▶️ Como executar o Backend
# buildar o cointainer docker
docker compose up -d
# Compilar o projeto
mvn clean install


# Executar a aplicação
mvn spring-boot:run

# [Frontend](https://github.com/kani0dev/Assistencia_Tecnica_Menager_Frontend)
A API ficará disponível em:
http://localhost:8080

 Observações Finais:
Projeto desenvolvido com foco acadêmico e prático
