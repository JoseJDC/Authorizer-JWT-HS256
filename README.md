# Authorizer-JWT-HS256

**Authorizer-JWT-HS256** es un boilerplate para autenticación basada en JWT utilizando el algoritmo **HS256** y **Spring Security**. Está diseñado para ser reutilizable en proyectos donde se necesita autenticación por token de forma rápida y segura.

---

## 🚀 Descripción

Este proyecto expone un único endpoint `GET /login`, el cual genera un token JWT **solo si el usuario existe previamente en una base de datos PostgreSQL**.

> ⚠️ Este boilerplate **no registra usuarios nuevos**. Asume que ya existen en la base de datos.

---

## 📦 Características

- 🔐 Generación de token JWT firmado con HS256
- 🔍 Consulta a base de datos PostgreSQL para verificar usuarios
- 🔧 Configuración mínima de Spring Security
- 🧱 Listo para manipular roles, expiración, claims personalizados, etc.

---

## 🧪 Endpoint disponible

### `GET /login?username={username}`

Valida si el usuario existe en la base de datos. Si existe, genera un JWT con claims mínimas y lo retorna.

#### Ejemplo de solicitud:

