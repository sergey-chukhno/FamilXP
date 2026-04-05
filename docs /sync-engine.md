Event Model

Each action generates an event stored locally.

Flow

Action occurs
Event stored in queue
Sync worker sends to backend
Backend confirms

Key Concepts

Idempotency
Retry mechanism
Conflict resolution