Architecture
UI Layer (React Native)
State Layer (Zustand)
Data Layer (SQLite)
Sync Engine

Rules
UI never calls API directly
All writes go to local DB first
Sync is asynchronous

Example Flow
User completes task
Write to SQLite
Add event to queue
UI updates immediately
Background sync executes