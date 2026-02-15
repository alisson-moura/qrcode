# Gerador de QR Code

API REST para geração de QR Codes com armazenamento automático em bucket S3.

**Repositório:** [github.com/alisson-moura/qrcode](https://github.com/alisson-moura/qrcode)

## 📋 Sobre o Projeto

Aplicação Spring Boot que permite a criação de QR Codes a partir de texto fornecido via API. Os QR Codes gerados são automaticamente salvos em um bucket S3 e a URL de acesso é retornada ao usuário.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Maven
- AWS S3 SDK
- Docker

## 📦 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Docker (opcional)
- Acesso a um bucket S3 (AWS ou compatível)

## ⚙️ Configuração

### Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```env
AWS_ACCESS_KEY_ID=sua_access_key_aqui
AWS_SECRET_ACCESS_KEY=sua_secret_key_aqui
AWS_REGION=us-east-1
AWS_BUCKET_NAME=nome-do-seu-bucket
AWS_ENDPOINT=https://seu-endpoint-s3.com
```

> **Nota:** Um arquivo `.env.template` está disponível no repositório com exemplos fictícios.

## 🔧 Instalação e Execução

### Desenvolvimento Local

1. Clone o repositório
```bash
git clone https://github.com/alisson-moura/qrcode.git
cd qrcode
```

2. Configure as variáveis de ambiente
```bash
cp env.template .env
# Edite o arquivo .env com suas credenciais
```

3. Compile o projeto
```bash
mvn clean install
```

4. Execute a aplicação
```bash
# Com variáveis de ambiente do arquivo .env
export $(cat .env | xargs) && mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

### Build com Maven

Para gerar o arquivo JAR executável:

```bash
mvn clean package
```

O arquivo será gerado em `target/qrcode-0.0.1-SNAPSHOT.jar`

Para executar o JAR:

```bash
export $(cat .env | xargs) && java -jar target/qrcode-0.0.1-SNAPSHOT.jar
```

### Docker

1. Build da imagem Docker
```bash
docker build -t qrcode .
```

2. Execute o container
```bash
docker run -p 8080:8080 --env-file .env qrcode
```

### 👥 Autor

**AM.dev**

### 📄 Licença

Este projeto está sob a licença MIT.

---

Desenvolvido com ☕ por AM.dev