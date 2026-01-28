-- 1. Tabela de Usuários (Base para tudo)
CREATE TABLE `users` (
                        `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(255) DEFAULT NULL,
                        `password` VARCHAR(255) DEFAULT NULL,
                        `telefone` VARCHAR(255) DEFAULT NULL,
                        `is_active` BIT(1) DEFAULT NULL,
                        `role` VARCHAR(20) NOT NULL DEFAULT 'CLIENT',
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 2. Tabela de Clientes (Extensão de User)
CREATE TABLE `clients` (
                           `user_id` BIGINT NOT NULL,
                           PRIMARY KEY (`user_id`),
                           CONSTRAINT `FK_user_client` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 3. Tabela de Dispositivos
CREATE TABLE `devices` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                           `brand` VARCHAR(255) NOT NULL,
                           `model` VARCHAR(255) NOT NULL,
                           `type` VARCHAR(255) NOT NULL,
                           `color` VARCHAR(255) DEFAULT NULL,
                           `serial_number` VARCHAR(255) DEFAULT NULL,
                           `sign_up_date` VARCHAR(255) NOT NULL,
                           `observations` TEXT,
                           `client_id` BIGINT NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UK_serial_number` (`serial_number`),
                           CONSTRAINT `FK_client_device` FOREIGN KEY (`client_id`) REFERENCES `clients` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 4. Tabela de Ordens de Serviço
CREATE TABLE `service_order` (
                                 `id` BIGINT NOT NULL AUTO_INCREMENT,
                                 `curent_state` VARCHAR(255) DEFAULT NULL,
                                 `defect_reported` VARCHAR(255) DEFAULT NULL,
                                 `entry_date` VARCHAR(255) DEFAULT NULL,
                                 `service_description` VARCHAR(255) DEFAULT NULL,
                                 `status` VARCHAR(255) DEFAULT NULL,
                                 `warranty_period` VARCHAR(255) DEFAULT NULL,
                                 `device_id` BIGINT NOT NULL,
                                 PRIMARY KEY (`id`),
                                 CONSTRAINT `FK_device_so` FOREIGN KEY (`device_id`) REFERENCES `devices` (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;