Conventions
JSON only
REST for commands
GraphQL for queries
Idempotent endpoints where applicable

Example: Create Task

POST /tasks

Request: { "title": "Clean room", "rewardPoints": 10, "childId": "uuid", "dueDate": "ISO8601" }

Response: { "id": "uuid", "status": "CREATED" }


Example: Complete Task

POST /tasks/{id}/complete

Request: { "proofMediaId": "uuid" }

Response: { "status": "PENDING_APPROVAL" }

Error Model

{ "error": "VALIDATION_ERROR", "message": "Title is required" }
