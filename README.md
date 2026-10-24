# spring-boot-mongodb-k8s

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-mongodb-k8s/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-mongodb-k8s/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot)
![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-47A248?logo=mongodb)
![Kubernetes](https://img.shields.io/badge/Kubernetes-ready-326CE5?logo=kubernetes)

A Spring Boot REST API backed by MongoDB, packaged for Kubernetes. The repository also contains a set of
Kubernetes lab manifests (ConfigMaps, resource limits/quotas, QoS classes, DaemonSet) and a Helm chart.

## Tech Stack

- Java 25
- Spring Boot 4.1 (Web, Validation, Actuator, Data MongoDB)
- Lombok
- Maven (wrapper included)
- Kubernetes & Helm
- GitHub Actions CI + Dependabot

## Prerequisites

- JDK 25
- A MongoDB instance (the default configuration targets MongoDB Atlas via `mongodb+srv://`)
- Docker, a Kubernetes cluster (e.g. Minikube / Docker Desktop) and Helm — for deployment

## Configuration

The MongoDB connection is built from environment variables in `src/main/resources/application.yml`:

```yaml
spring.data.mongodb.uri: mongodb+srv://${MONGODB_LOGIN}:${MONGODB_PWD}@${MONGODB_URL}/${MONGODB_DATABASE}?${MONGODB_OPTS}
```

| Variable           | Description                       | Example                        |
|--------------------|-----------------------------------|--------------------------------|
| `MONGODB_LOGIN`    | MongoDB username                  | `admin`                        |
| `MONGODB_PWD`      | MongoDB password                  | `secret`                       |
| `MONGODB_URL`      | MongoDB host / cluster address    | `cluster0.xxxxx.mongodb.net`   |
| `MONGODB_DATABASE` | Database name                     | `library_db`                   |
| `MONGODB_OPTS`     | Connection options                | `retryWrites=true&w=majority`  |

The `/api/db-config` endpoint reads `host`, `database` and `debug_mode` (injected from a Kubernetes ConfigMap).

## Build & Run

```bash
# Build and run tests
./mvnw clean package

# Run locally
export MONGODB_LOGIN=... MONGODB_PWD=... MONGODB_URL=... MONGODB_DATABASE=library_db MONGODB_OPTS='retryWrites=true&w=majority'
./mvnw spring-boot:run
```

The application listens on port `8080`.

### Container image

```bash
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=spring-boot-k8s:latest
```

## API Endpoints

| Method   | Path                      | Description                                   |
|----------|---------------------------|-----------------------------------------------|
| `GET`    | `/api/author`             | List all authors                              |
| `GET`    | `/api/author/{id}`        | Get an author by id                           |
| `POST`   | `/api/author`             | Create an author                              |
| `PUT`    | `/api/author`             | Update an author                              |
| `DELETE` | `/api/author/{id}`        | Delete an author                              |
| `GET`    | `/api/book`               | List all books                                |
| `GET`    | `/api/book/{id}`          | Get a book by id                              |
| `POST`   | `/api/book`               | Create a book                                 |
| `PUT`    | `/api/book`               | Update a book                                 |
| `DELETE` | `/api/book/{id}`          | Delete a book                                 |
| `GET`    | `/api/db-config`          | Show DB settings injected from a ConfigMap    |
| `GET`    | `/api/db-config-volume`   | Show ConfigMap content mounted as a volume    |
| `GET`    | `/actuator/health`        | Health, incl. liveness & readiness probes     |

Example:

```bash
curl -X POST http://localhost:8080/api/author \
  -H 'Content-Type: application/json' \
  -d '{"firstname":"Hendi","lastname":"Santika"}'
```

## Kubernetes

| Path                  | Topic                                                                   |
|-----------------------|-------------------------------------------------------------------------|
| `k8s/k8s.yaml`        | ConfigMap, Secret, Service & Deployment for the API                     |
| `k8s/lab3`            | ConfigMaps consumed via `env`, `envFrom`, container `args` and volumes  |
| `k8s/lab5`            | Requests/limits, LimitRange, ResourceQuota and QoS classes              |
| `k8s/lab9`            | DaemonSet                                                               |

```bash
kubectl apply -f k8s/k8s.yaml
```

### Helm

```bash
helm install book-api ./lab-helm-chart \
  --set configmap.values.MONGODB_URL=<cluster-url>
```

Probes are wired to `/actuator/health/liveness` and `/actuator/health/readiness`.

## CI/CD & Dependency Management

- **GitHub Actions** (`.github/workflows/maven.yml`) builds and tests the project on JDK 25 (Temurin) for every
  push and pull request to `main`, then submits the dependency graph to GitHub.
- **Dependabot** (`.github/dependabot.yml`) checks Maven dependencies and GitHub Actions daily (05:00 Asia/Jakarta):
  - minor/patch updates are grouped (`spring-boot`, `maven-plugins`, `maven-dependencies`, `github-actions`)
    to reduce PR noise, while major updates still arrive as individual PRs
  - PRs are labelled (`dependencies`, `java` / `github_actions`), auto-rebased and use conventional commit
    prefixes (`build(deps)`, `ci(deps)`)

## Author

**Hendi Santika** — [GitHub](https://github.com/hendisantika)
