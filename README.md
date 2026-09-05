<div align="center">

# 💰 FinanceFlow

### Sistema de controle financeiro pessoal, desenvolvido em grupo como projeto de estudos

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/)

</div>

---

## 📖 Sobre o projeto

**FinanceFlow** é uma API REST para controle financeiro pessoal — permite registrar receitas e despesas, consultar o saldo, filtrar transações por categoria e muito mais.

Este projeto nasceu como um **exercício de aprendizado em grupo**, com foco em praticar:

- 🌱 Spring Boot na prática (Controller → Service → Repository)
- 🔀 Git Flow e Conventional Commits em equipe
- 🐳 Containerização com Docker
- ✅ Validação de dados e tratamento de erros
- 🧪 Testes unitários com JUnit + Mockito
- 📘 Documentação de API com Swagger/OpenAPI

---

## 🧰 Tecnologias utilizadas

| Tecnologia | Uso no projeto |
|---|---|
| ☕ **Java 21** | Linguagem principal |
| 🍃 **Spring Boot 4.1.0** | Framework para construção da API REST |
| 🗄️ **Spring Data JPA** | Persistência e acesso ao banco de dados |
| 🐘 **PostgreSQL 16** | Banco de dados relacional |
| 🐳 **Docker / Docker Compose** | Ambiente de banco de dados padronizado |
| 📘 **springdoc-openapi (Swagger UI)** | Documentação interativa da API |
| ✅ **Bean Validation** | Validação automática dos dados de entrada |
| 🧪 **JUnit 5 + Mockito** | Testes unitários |
| 🪶 **Lombok** | Redução de código boilerplate |
| 📦 **Maven** | Gerenciador de dependências e build |

---

## ✨ Funcionalidades

- ➕ Criar transações (receitas ou despesas)
- 📋 Listar todas as transações
- 🔍 Buscar transação por ID
- 🏷️ Filtrar transações por categoria
- ✏️ Atualizar uma transação existente
- 🗑️ Deletar uma transação
- 💰 Calcular o saldo total (receitas − despesas), com precisão monetária via `BigDecimal`
- ⚠️ Respostas de erro padronizadas (404, 400, 500) via handler global de exceções

---

## 📋 Pré-requisitos

Antes de começar, você vai precisar ter instalado:

| Ferramenta | Link |
|---|---|
| ☕ Java 21 | [Adoptium](https://adoptium.net/) |
| 🧠 IntelliJ IDEA (Community já serve) | [JetBrains](https://www.jetbrains.com/idea/) |
| 🐳 Docker Desktop | [Docker](https://www.docker.com/products/docker-desktop/) |
| 🔧 Git | [Git SCM](https://git-scm.com/) |

> 💡 **Dica para Windows:** o Docker Desktop precisa do WSL2 ativado. Se for a primeira vez instalando, rode `wsl --install` no PowerShell (como administrador) antes de instalar o Docker.

---

## 🚀 Como rodar o projeto

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/GuilhermeSilva07/financeflow.git
cd financeflow
git checkout develop
```

### 2️⃣ Configure suas variáveis de ambiente

Copie o arquivo de exemplo:

```bash
cp .env.example .env
```

Abra o `.env` e defina a sua própria senha (local, só sua):

```env
POSTGRES_PASSWORD=sua_senha_aqui
```

> ⚠️ O `.env` está no `.gitignore` e **nunca** deve ser commitado. Cada membro do grupo cria o seu próprio, com sua própria senha.

### 3️⃣ Suba o banco de dados com Docker

```bash
docker compose up -d
```

> 💡 Em versões mais antigas do Docker Desktop, o comando pode ser `docker-compose` (com hífen) em vez de `docker compose` (com espaço).

Confirme que o container subiu:

```bash
docker ps
```

Você deve ver o container `financeflow-db` com status `Up`.

### 4️⃣ Configure as variáveis de ambiente da aplicação

A aplicação lê a conexão com o banco via variáveis de ambiente. Configure na sua máquina (ou nas *Run Configurations* do IntelliJ):

| Variável | Valor |
|---|---|
| `FINANCEFLOW_DB_URL` | `jdbc:postgresql://localhost:5432/financeflow` |
| `FINANCEFLOW_DB_USERNAME` | `postgres` |
| `FINANCEFLOW_DB_PASSWORD` | a mesma senha definida no seu `.env` |

### 5️⃣ Rode a aplicação

Pelo IntelliJ, rode a classe `FinanceflowApplication`, ou via terminal com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Se tudo estiver certo, o console mostra: Started FinanceflowApplication in X seconds


A API estará disponível em **`http://localhost:8080`** 🎉

---

## 📘 Documentação interativa (Swagger)

Com a aplicação rodando, acesse:

### 🔗 [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

Lá você encontra **todos os endpoints documentados**, com descrições e a possibilidade de **testar cada requisição diretamente pelo navegador** — sem precisar configurar Postman ou qualquer outra ferramenta externa.

<div align="center">
  <sub>Título: <b>FinanceFlow API</b> · Versão: <code>v1</code> · Especificação: <code>OpenAPI 3.1</code></sub>
</div>

O JSON bruto da especificação também fica disponível em:  http://localhost:8080/v3/api-docs



---

## 🔌 Endpoints disponíveis

| Método | Rota | Descrição |
|---|---|---|
| 🟢 `POST` | `/transactions` | Cria uma nova transação |
| 🔵 `GET` | `/transactions` | Lista todas as transações |
| 🔵 `GET` | `/transactions/{id}` | Busca uma transação por ID |
| 🔵 `GET` | `/transactions/category/{category}` | Filtra transações por categoria |
| 🟠 `PUT` | `/transactions/{id}` | Atualiza uma transação existente |
| 🔴 `DELETE` | `/transactions/{id}` | Remove uma transação |
| 🔵 `GET` | `/transactions/balance` | Calcula o saldo (receitas − despesas) |

> 📌 Para os detalhes completos de cada requisição (parâmetros, exemplos de payload, códigos de resposta), consulte o **Swagger UI** — ele é gerado automaticamente a partir do código e nunca fica desatualizado.

### 🏷️ Categorias disponíveis
`FOOD` · `TRANSPORT` · `HEALTH` · `ENTERTAINMENT` · `SALARY` · `OTHERS`

### 💸 Tipos de transação
`INCOME` (receita) · `EXPENSE` (despesa)

---

## 🗂️ Estrutura do projeto

src/main/java/com/grupoestudos/financeflow/
│
├── 📁 config/ → Configurações gerais (CORS, OpenAPI/Swagger)
├── 📁 controller/ → Endpoints REST (camada de entrada da API)
├── 📁 service/ → Regras de negócio
├── 📁 repository/ → Acesso ao banco de dados (Spring Data JPA)
├── 📁 model/ → Entidades JPA (mapeamento com o banco)
├── 📁 dto/ → Objetos de transferência de dados (entrada/saída da API)
├── 📁 enums/ → TransactionType, Category
└── 📁 exception/ → Exceções customizadas + Handler global de erros



---

## 🧪 Testes

O projeto conta com testes unitários da camada de serviço, usando **JUnit 5** e **Mockito**.

Para rodar os testes pelo terminal:

```bash
./mvnw test
```

> ⚠️ Se aparecer um erro de `UnsupportedClassVersionError`, é sinal de que seu terminal está usando uma versão de Java diferente da 21 (comum quando existe mais de um JDK instalado na máquina). Nesse caso, rode os testes diretamente pelo IntelliJ, que já usa a versão correta configurada no projeto.

---

## 🌳 Fluxo de contribuição (Git Flow)

Este projeto segue o padrão **Git Flow** combinado com **Conventional Commits**.

```bash
# 1. Sempre parta da develop atualizada
git checkout develop
git pull origin develop

# 2. Crie sua branch de feature
git checkout -b feature/nome-da-feature

# 3. Desenvolva e comite seguindo o padrão
git add .
git commit -m "feat: descrição curta da mudança"

# 4. Suba sua branch e abra um Pull Request para a develop
git push origin feature/nome-da-feature
```

### 📝 Padrão de commits

| Prefixo | Quando usar |
|---|---|
| `feat:` | Nova funcionalidade |
| `fix:` | Correção de bug |
| `docs:` | Mudanças em documentação |
| `refactor:` | Refatoração sem mudar comportamento |
| `test:` | Adição ou ajuste de testes |

> 🚫 Nunca commitar diretamente na `master` ou `develop` — sempre via Pull Request.

---

## 🐳 Comandos úteis do Docker

| Comando | O que faz |
|---|---|
| `docker compose up -d` | Sobe o banco em segundo plano |
| `docker compose down` | Para o container (mantém os dados) |
| `docker compose down -v` | Para o container e **apaga** os dados (reset completo) |
| `docker ps` | Lista containers em execução |
| `docker logs financeflow-db` | Mostra os logs do banco |

---

## 👥 Grupo de Estudos

Projeto desenvolvido colaborativamente, com foco em aprendizado prático de **Spring Boot**, **APIs REST** e **Git Flow** em equipe.

<div align="center">

**Feito com ☕ e 💻 por quem está aprendendo a construir software de verdade.**

</div>