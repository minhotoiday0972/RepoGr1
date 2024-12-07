from fastapi import FastAPI, Query
from typing import Optional
import json

# Initialize the FastAPI application
app = FastAPI()

# Load user data from a JSON file
USER_DATA_FILE = "generated_users.json"

try:
    with open(USER_DATA_FILE, "r") as f:
        user_data = json.load(f)
except FileNotFoundError:
    user_data = {}  # Default to empty if the file doesn't exist

# Utility function to save data to JSON file
def save_user_data_to_file():
    with open(USER_DATA_FILE, "w") as f:
        json.dump(user_data, f, indent=4)  # Save with pretty formatting

# Root endpoint
@app.get("/")
def read_root():
    return {"message": "JUST FIXING 2"}

# Endpoint to get user information by user_id, with options to retrieve specific fields
@app.get("/get_user")
def get_user(user_id: int, name: Optional[bool] = Query(False), age: Optional[bool] = Query(False), email: Optional[bool] = Query(False)):
    # Retrieve user information based on `user_id`
    user = user_data.get(str(user_id))  # Convert user_id to string to match JSON keys
    if not user:
        return {"error": "User not found"}

    # Filter information based on the optional query parameters
    user_info = {}
    if name:
        user_info["name"] = user["name"]
    if age:
        user_info["age"] = user["age"]
    if email:
        user_info["email"] = user["email"]

    # If no specific field is requested, return the full user info
    if not user_info:
        user_info = user

    return {"user_id": user_id, "user_info": user_info}

# Endpoint to add a new user
@app.post("/add_user")
def add_user(user_id: int, name: str, age: int, email: str):
    # Check if the user_id already exists
    if str(user_id) in user_data:
        return {"error": "User ID already exists"}

    # Add the new user to the mock data
    user_data[str(user_id)] = {"name": name, "age": age, "email": email}

    # Save updated data back to the file
    save_user_data_to_file()

    return {"message": "User added successfully", "user_id": user_id, "user_info": user_data[str(user_id)]}
