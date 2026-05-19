# Loan Management Microservices

## Overview
This repository contains a Spring Boot microservices system with service discovery and an API gateway.

Services:
- `discovery-server` (Eureka)
- `api-gateway`
- `customer-service`
- `loan-service`
- `payment-service`

## Prerequisites
- Java 17
- Maven (wrapper included)

## Run Order
Start services in this order (each in its own terminal):

```powershell
Set-Location "D:\Desktop\core-spring-labfiles\Microservices\discovery-server"
.\mvnw.cmd -q spring-boot:run
```

```powershell
Set-Location "D:\Desktop\core-spring-labfiles\Microservices\api-gateway"
.\mvnw.cmd -q spring-boot:run
```

```powershell
Set-Location "D:\Desktop\core-spring-labfiles\Microservices\customer-service"
.\mvnw.cmd -q spring-boot:run
```

```powershell
Set-Location "D:\Desktop\core-spring-labfiles\Microservices\loan-service"
.\mvnw.cmd -q spring-boot:run
```

```powershell
Set-Location "D:\Desktop\core-spring-labfiles\Microservices\payment-service"
.\mvnw.cmd -q spring-boot:run
```

## Verify Eureka
Open:
- `http://localhost:8761`

You should see all services registered.

## API Gateway Base URL
All API requests go through:
- `http://localhost:8080`

## Quick API Smoke Tests
### Create Customer
```
POST http://localhost:8080/api/customers
Content-Type: application/json

{
  "name": "John Doe",
  "cnic": "34506-1234567-1",
  "phone": "03001234567",
  "address": "Karachi, Pakistan"
}
```

### Apply for Loan
```
POST http://localhost:8080/api/loans/apply
Content-Type: application/json

{
  "customerId": 1,
  "loanAmount": 100000,
  "tenureMonths": 36
}
```

### Approve Loan
```
PUT http://localhost:8080/api/loans/1/approve
```

### Generate Installments
```
POST http://localhost:8080/api/payments/generate/1
```

### Make Payment (query params)
```
POST http://localhost:8080/api/payments/pay?installmentId=1&amount=5000&method=bank_transfer
```

### Disburse Loan
```
POST http://localhost:8080/api/disbursements/1/disburse
```

## Full API Guide
See `API_DOCUMENTATION.md` for the complete step-by-step testing workflow.

