# aj_client_employee

Login:
http://localhost:8087/login
Method: POST
JSON: 
{
"email":"a1@a.com",
"password":"12345"
}

Registration:
http://localhost:8087/users
Method: POST
JSON: 
{
  "id":1,
  "userName": null,
  "password": "12345",
  "email": "a@a.com",
  "fname": "Rohan",
  "lname": "Basu",
  "phone": null,
  "isActive": 1,
  "isLocked": 0
}
