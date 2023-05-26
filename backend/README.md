# Projeto Spring Boot com Banco de Dados H2

Este é um projeto Spring Boot que utiliza o banco de dados H2. Siga as etapas abaixo para configurar e executar o projeto em sua máquina local.

## Pré-requisitos

Antes de começar, verifique se você possui os seguintes itens instalados:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) (versão 11)
- [Maven](https://maven.apache.org/)

## Primeiros Passos

1. Clone este repositório para sua máquina local:


2. Navegue até o diretório `backend`:

   ```bash
   cd backend
   ```

3. Inicie o servidor de desenvolvimento:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse o servidor em [http://localhost:8080](http://localhost:8080).

4. As URL's estão protegidas por uma autenticação basic, exite um usuário padrão que não pode ser deletado por via sistema, mas é possível criar usuários personalizados via API
   ```bash
   usuario: admin
   senha admin
   ```
