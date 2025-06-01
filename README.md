# 🚀 GS-Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

## 📋 Descrição

Projeto desenvolvido em Java com Spring Boot.

---

## ⚙️ Tecnologias utilizadas

- ✅ Java 21
- ✅ Spring Boot
- ✅ Maven
- ✅ H2 Database
- ✅ Postman

---

## 🚀 Como executar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/VMurtis/GS-Java.git

## 📫 Como usar a API

A seguir, mostramos como utilizar o Postman ou qualquer cliente HTTP para consumir a API, enviando requisições no formato **JSON**.

---



###  Método

- **Método:** `POST`
- **URL:**  
  `http://localhost:8080/abrigo`

---

#### Body (JSON):

```json
{
  "nome": "Abrigo Central",
  "endereco": "Rua Principal, 123",
  "capacidadeMaxima": 50,
  "usuario": [
    {
      "nome": "Maria",
      "email": "maria@example.com",
      "senha": "senha123",
      "perfil": "VOLUNTARIO" 
    },
    {
      "nome": "Jose",
      "email": "joao@example.com",
      "senha": "senha456",
      "perfil": "VOLUNTARIO"
    }
  ],
  "necessidades": [
    {
      "tipo": "ALIMENTOS",         
      "prioridade": "ALTA",      
      "status": "PENDENTE"       
    }
  ]
}
```

###  Método

- **Método:** `PUT`
- **URL:**  
  `http://localhost:8080/abrigo/1`

---

#### Body (JSON):

```json
{
  "nome": "Abrigo Santas",
  "endereco": "Rua Paz, 111",
  "capacidadeMaxima": 70
}

```

###  Método

- **Método:** `GET`
- **URL:**  
  `http://localhost:8080/abrigo/1`

---

###  Método

- **Método:** `DELETE`
- **URL:**  
  `http://localhost:8080/abrigo/1`

---




