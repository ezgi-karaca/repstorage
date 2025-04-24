# repstorage
 
This project is part of a technical assessment and simulates a minimalistic `.rep` package deployment and distribution system.

## Technologies Used

- Java 17
- Spring Boot
- PostgreSQL
- Maven (multi-module structure)
- Docker & Docker Compose
- Repsy (Private Maven Repository)

##  Module Overview

- `application`: Main Spring Boot service with REST endpoints.
- `storage-filesystem`: File-based storage implementation.
- `storage-object`: Placeholder for object storage (e.g. MinIO).
- `storage-common`: Shared interface for pluggable storage.

##  How to Run with Docker

```bash
docker compose up --build
```

- Application runs at `http://localhost:8080`  
- PostgreSQL runs on port `5432`

##  Upload a Package

**Endpoint:**

```
POST /api/packages/{packageName}/{version}
```

**multipart/form-data fields:**
- `file` → the `.rep` file (e.g., `package.rep`)
- `metadata` → JSON metadata (e.g., `meta.json` content)

**Example cURL:**

```bash
curl -X POST http://localhost:8080/api/packages/mypackage/1.0.0 \
  -F "file=@/path/to/package.rep" \
  -F 'metadata={"packageName":"mypackage","version":"1.0.0","author":"Ezgi"};type=application/json'
```

##  Download a Package

**Endpoint:**

```
GET /api/packages/{packageName}/{version}/{fileName}
```

**Example:**

```
GET /api/packages/mypackage/1.0.0/package.rep
```

## Repsy Deployment

Both `storage-filesystem` and `storage-object` modules were deployed to a private Repsy Maven repository:

```xml
<distributionManagement>
  <repository>
    <id>repsy</id>
    <url>https://repo.repsy.io/mvn/ezgikaraca/repstorage</url>
  </repository>
</distributionManagement>
```

The `application` module retrieves them via:

```xml
<repositories>
  <repository>
    <id>repsy</id>
    <url>https://repo.repsy.io/mvn/ezgikaraca/repstorage</url>
  </repository>
</repositories>
```

Credentials are configured in `~/.m2/settings.xml` and never included in source code.

##  Notes

- Object storage support (MinIO) is planned but not yet implemented.
- `.rep` files are treated as simple zip binaries — no validation on content is performed.
- No authentication is applied, as per task specification.

## Author

Developed by Ezgi Karaca as part of a junior fullstack developer assessment.
