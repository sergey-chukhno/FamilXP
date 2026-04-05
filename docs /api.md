REST Endpoints

Auth
POST /auth/register
POST /auth/login
POST /auth/refresh

Tasks
POST /tasks
GET /tasks
PATCH /tasks/{id}
POST /tasks/{id}/complete
POST /tasks/{id}/approve

Habits
POST /habits
GET /habits
POST /habits/{id}/check

Rewards
POST /rewards
GET /rewards
POST /rewards/{id}/redeem

AI
POST /ai/generate-habits
POST /ai/analyze-child
POST /ai/motivate

GraphQL
Used for dashboard aggregation and flexible queries