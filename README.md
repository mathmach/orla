# Aplicação Java com Docker para Cadastro de Funcionários e Projetos

Esta é uma aplicação Java que permite gerenciar funcionários e projetos, permitindo a associação de funcionários a projetos em uma relação n para n. Com esta aplicação, você pode facilmente:

- Cadastrar funcionários, incluindo informações como nome, CPF, e-mail e salário.
- Cadastrar projetos, especificando nome e data de criação.
- Associar funcionários a projetos, permitindo que múltiplos funcionários participem de um único projeto e vice-versa.
- Visualizar informações detalhadas sobre funcionários e projetos, incluindo suas associações.

## Pré-requisitos

Antes de começar, você precisará ter instalados em sua máquina:

- [Docker](https://www.docker.com/get-started)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) (versão 11 ou superior)

## Configuração do Ambiente com Docker

Siga estas etapas para configurar e executar a aplicação com Docker:

1. Clone este repositório:

   ```bash
   git clone https://github.com/mathmach/orla.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd orla
   ```

3. Use o Docker Compose para construir e iniciar os contêineres:

   ```bash
   docker-compose up --build
   ```

   Isso irá configurar um contêiner para a aplicação Java e outro para um banco de dados (por exemplo, PostgreSQL).

4. Acesse a documentação da API através do Swagger em `http://localhost:8080/swagger-ui.html`.

## Uso da API

Acesse a documentação da API usando o Swagger para interagir com a aplicação:

- Para visualizar e testar as operações da API, acesse `http://localhost:8080/swagger-ui.html` no seu navegador.

- Você pode usar o Swagger para cadastrar funcionários, projetos, associar funcionários a projetos e muito mais.

## Contribuição

Contribuições são bem-vindas! Se você deseja melhorar ou adicionar recursos a esta aplicação Java com Docker, sinta-se à vontade para abrir um problema ou enviar um pull request.

## Licença

Este projeto é licenciado sob a Licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.

---
