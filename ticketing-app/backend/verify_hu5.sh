#!/bin/bash

BASE_URL="http://localhost:8082/api/v1/events"
AUTH_URL="http://localhost:8082/auth"

echo "1. Registering ADMIN user..."
REGISTER_RESPONSE=$(curl -s -X POST "$AUTH_URL/register" \
  -H "Content-Type: application/json" \
  -d '{
    "firstname": "Admin",
    "lastname": "User",
    "email": "admin@test.com",
    "password": "password123",
    "role": "ADMIN"
  }')
echo "Response: $REGISTER_RESPONSE"

echo -e "\n2. Logging in..."
LOGIN_RESPONSE=$(curl -s -X POST "$AUTH_URL/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@test.com",
    "password": "password123"
  }')
echo "Response: $LOGIN_RESPONSE"

TOKEN=$(echo $LOGIN_RESPONSE | grep -o '"token":"[^"]*' | grep -o '[^"]*$')
echo "Token: $TOKEN"

if [ -z "$TOKEN" ]; then
  echo "Failed to get token. Exiting."
  exit 1
fi

echo -e "\n3. Creating Event (Success Case)..."
CREATE_RESPONSE=$(curl -s -i -X POST "$BASE_URL" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Concierto de Rock",
    "description": "Un gran concierto",
    "eventDate": "2025-12-25T20:00:00",
    "endDate": "2025-12-25T23:00:00",
    "venueId": 1,
    "capacity": 500,
    "ticketPrice": 50.00
  }')
echo "$CREATE_RESPONSE"

echo -e "\n4. Creating Event (Validation Error - EndDate before StartDate)..."
ERROR_RESPONSE=$(curl -s -i -X POST "$BASE_URL" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Concierto Fail",
    "description": "Fail",
    "eventDate": "2025-12-25T20:00:00",
    "endDate": "2025-12-24T23:00:00",
    "venueId": 1,
    "capacity": 500,
    "ticketPrice": 50.00
  }')
echo "$ERROR_RESPONSE"

echo -e "\n5. Creating Event (Unauthorized - No Token)..."
UNAUTH_RESPONSE=$(curl -s -i -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Concierto Unauthorized",
    "eventDate": "2025-12-25T20:00:00",
    "endDate": "2025-12-25T23:00:00",
    "venueId": 1
  }')
echo "$UNAUTH_RESPONSE"
