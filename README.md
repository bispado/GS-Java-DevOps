# 🚀 GS-Java - DevOps com Docker e Oracle XE

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![PowerShell](https://img.shields.io/badge/PowerShell-5391FE?style=for-the-badge&logo=powershell&logoColor=white)

## 📋 Descrição do Projeto

Este projeto demonstra a conteinerização de uma API RESTful desenvolvida em Java com Spring Boot, integrada a um banco de dados Oracle Express Edition (XE), ambos rodando em containers Docker. O objetivo principal é ilustrar os princípios de DevOps através da infraestrutura como código, garantindo que a aplicação e o banco de dados sejam facilmente implantáveis e gerenciáveis em um ambiente conteinerizado.

A API oferece um CRUD completo para a entidade `Abrigo`, permitindo criar, ler, atualizar e deletar registros de abrigos, usuários e necessidades, com persistência de dados em um volume nomeado do Docker.

## ⚙️ Tecnologias Utilizadas

*   ✅ **Java 21:** Linguagem de programação principal.
*   ✅ **Spring Boot 3.5.0:** Framework para construção da API.
*   ✅ **Maven:** Ferramenta de gerenciamento de dependências e build do projeto Java.
*   ✅ **Docker & Docker Compose:** Para conteinerização e orquestração dos serviços.
*   ✅ **Oracle Database XE 21.3.0:** Banco de dados relacional para persistência dos dados.
*   ✅ **PowerShell:** Ambiente de linha de comando para execução dos scripts e interação com a API e containers.

## 📁 Estrutura do Projeto

*   `.` (diretório raiz): Contém o `docker-compose.yml` e o `Dockerfile` da aplicação Java.
*   `gs/`: Contém o código-fonte da aplicação Spring Boot.
*   `oracle-db/`: Contém o `Dockerfile` para a imagem personalizada do Oracle XE.

## 🚀 Como Executar o Projeto (Roteiro DevOps)

Siga estes passos no seu terminal PowerShell, a partir do diretório raiz do projeto (`GS-Java`).

---

**1.0. Configuração Inicial do Script (Defina no início do seu script PowerShell):**

```powershell
# Define o caminho para o arquivo SQL temporário que será usado nas consultas ao Oracle
$sqlFilePath = "temp_query.sql"
```

**1.1. Navegar para o diretório raiz do projeto:**

```powershell
cd "C:\Users\Gustavo Bispo\OneDrive\Documentos\GS-Java"
```

**1.2. Parar e limpar containers e volumes antigos (para uma demonstração limpa):**

```powershell
docker-compose down -v
```

**1.3. Construir as imagens Docker da aplicação e do banco de dados:**

```powershell
docker-compose build
```

**1.4. Subir os containers da aplicação e do banco de dados em modo background (`detached`):**

```powershell
docker-compose up -d
```

**1.5. Exibir os logs dos containers (verificar se ambos iniciaram corretamente):**

```powershell
docker-compose logs -f
# Aguarde até ver as mensagens de "Tomcat started" (app) e "DATABASE IS READY TO USE!" (oracle-db)
# Pressione Ctrl + C para sair do modo de visualização de logs e continuar.
```

## 📊 Como Usar a API (CRUD)

A API expõe o endpoint `/abrigo` na porta `8080`. Usaremos `Invoke-RestMethod` no PowerShell para interagir com ela e comandos `docker exec` para verificar a persistência no banco de dados Oracle.

**2.1. Definir o JSON para Criação (POST):**

```powershell
$createJson = @'
{
  "nome": "Abrigo Solidariedade",
  "endereco": "Rua da Esperança, 100",
  "capacidadeMaxima": 75,
  "usuario": [
    {
      "nome": "Ana Voluntaria",
      "email": "ana.voluntaria@example.com",
      "senha": "senhaSegura1",
      "perfil": "VOLUNTARIO"
    },
    {
      "nome": "Jose",
      "email": "jose@example.com",
      "senha": "senha456",
      "perfil": "VOLUNTARIO"
    }
  ],
  "necessidades": [
    {
      "tipo": "ROUPAS",
      "prioridade": "ALTA",
      "status": "PENDENTE"
    }
  ]
}
'@
```

**2.2. READ (GET All) - Inicial (Deve retornar uma lista vazia `[]`):**

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/abrigo" -Method GET
```

**2.3. CREATE (POST) - Criar um novo abrigo:**

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/abrigo" `
    -Method POST `
    -ContentType "application/json" `
    -Body $createJson
# (Não haverá output se a criação for bem-sucedida, pois o método retorna void)
```

**2.4. VERIFICAR PERSISTÊNCIA no Oracle (Consulta SQL Direta):**

```powershell
Set-Content -Path $sqlFilePath -Value @"
SET PAGESIZE 0 FEEDBACK OFF HEADING OFF VERIFY OFF;
SELECT ID_ABRIGO, NOME, ENDERECO, CAPACIDADE_MAXIMA FROM TAB_ABRIGO;
EXIT;
"@
docker cp $sqlFilePath oracle-db:/tmp/$sqlFilePath
docker exec -i oracle-db sqlplus "system/140204@XEPDB1" "@/tmp/$sqlFilePath"
Remove-Item $sqlFilePath
# Deve exibir o ID e os dados do abrigo criado.
```

**2.5. READ (GET All) - Após Criação (Deve listar o abrigo recém-criado):**

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/abrigo" -Method GET
```

**2.6. READ (GET by ID) - Consultar um abrigo específico (Use o ID retornado no passo anterior, geralmente '1'):**

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/abrigo/1" -Method GET
```

**2.7. Definir o JSON para Atualização (PUT):**

```powershell
$updateJson = @'
{
  "nome": "Abrigo Solidariedade Atualizado",
  "endereco": "Avenida da Paz, 200",
  "capacidadeMaxima": 90
}
'@
```

**2.8. UPDATE (PUT) - Atualizar o abrigo (ID '1'):**

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/abrigo/1" `
    -Method PUT `
    -ContentType "application/json" `
    -Body $updateJson
# Deve retornar o JSON do abrigo atualizado.
```

**2.9. Conferir Atualização no Oracle (Consulta SQL Direta):**

```powershell
Set-Content -Path $sqlFilePath -Value @"
SET PAGESIZE 0 FEEDBACK OFF HEADING OFF VERIFY OFF;
SELECT ID_ABRIGO, NOME, ENDERECO, CAPACIDADE_MAXIMA FROM TAB_ABRIGO WHERE ID_ABRIGO = 1;
EXIT;
"@
docker cp $sqlFilePath oracle-db:/tmp/$sqlFilePath
docker exec -i oracle-db sqlplus "system/140204@XEPDB1" "@/tmp/$sqlFilePath"
Remove-Item $sqlFilePath
# Deve exibir os dados atualizados do abrigo.
```

**2.10. DELETE (DELETE) - Remover o abrigo (ID '1'):**

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/abrigo/1" -Method DELETE
# (Não haverá output se a exclusão for bem-sucedida, pois o método retorna void)
```

**2.11. Conferir Remoção no Oracle e na API (Ambos devem retornar vazio):**

```powershell
Set-Content -Path $sqlFilePath -Value @"
SET PAGESIZE 0 FEEDBACK OFF HEADING OFF VERIFY OFF;
SELECT ID_ABRIGO, NOME, ENDERECO, CAPACIDADE_MAXIMA FROM TAB_ABRIGO;
EXIT;
"@
docker cp $sqlFilePath oracle-db:/tmp/$sqlFilePath
docker exec -i oracle-db sqlplus "system/140204@XEPDB1" "@/tmp/$sqlFilePath"
Remove-Item $sqlFilePath
# A consulta SQL deve retornar nenhuma linha.

Invoke-RestMethod -Uri "http://localhost:8080/abrigo" -Method GET
# Deve retornar uma lista vazia: []
```

## 🧹 Limpeza do Ambiente

**3.1. Encerrar e remover todos os containers e volumes criados:**

```powershell
docker-compose down -v
```

---

## 📝 Notas para a Apresentação em Vídeo

*   **Explicação Clara:** Para cada comando executado, explique o que você está fazendo, o porquê e o que espera ver como resultado.
*   **Qualidade Visual:** Certifique-se de que o texto no terminal esteja legível. Aumente o tamanho da fonte se necessário.
*   **Pacing:** Não corra. Dê tempo para os comandos serem executados e para você explicar.
*   **Transições:** Ao alternar entre a execução de comandos e a verificação de logs ou saídas, explique a transição para o espectador.
*   **Cópia de Arquivos SQL:** Lembre-se que o `docker cp` e `Remove-Item` são para gerenciamento de arquivos temporários e não precisam de explicação aprofundada, apenas que servem para executar as queries no DB.
*   **ID do Abrigo:** O ID padrão para o primeiro abrigo criado será '1'. Se você criar múltiplos abrigos, os IDs podem aumentar. Ajuste o `/abrigo/{id}` conforme necessário.

---

Este `README.md` deve servir como um guia sólido para sua apresentação, cobrindo todos os aspectos técnicos e de demonstração do seu projeto DevOps. Boa sorte!