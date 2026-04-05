Event Schema

{ "id": "uuid", "type": "TASK_COMPLETED", "entityId": "uuid", "payload": {}, "timestamp": 0, "status": "PENDING" }

Rules
Events are immutable
Events must be idempotent
Retry with exponential backoff

Conflict Resolution
Server is source of truth
Reject invalid events
Return correction payload

Retry Policy
Retry up to 5 times
Backoff: 1s, 2s, 5s, 10s, 30s