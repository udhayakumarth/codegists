# **CodeGists** 🗃
A **lightweight and flexible code snippet manager** with built-in **version control**, **public & secret snippets**, and **multi-file support**.  

## **✨ Key Features**  
✔ **Version Control** – Keep track of snippet changes over time.  
✔ **Supports Multiple Files** – Each snippet can have multiple associated files.  
✔ **Public & Secret Snippets** – Share code with the world or keep it private.  
✔ **Google Authentication** – Secure login with Google OAuth.  


## **🛠️ Architecture**  

![Architecture Diagram](https://github.com/user-attachments/assets/2b86eaff-d6f2-49d2-9357-390051d8bec5)  


## **📜 API Documentation**  
🔗 **Postman API Docs:** (https://documenter.getpostman.com/view/21272460/2sAYXEFJpg)  

## **🎨 UI Design**  
🔗 **Figma Prototype:** (https://www.figma.com/design/zNWijW6x66dZ4hgGOKQDOQ/CodeGists)  


## **🗄️ Database Design (MongoDB)**  
**Collections:**  
📂 `snippets` 
📂 `files`  
📂 `versions` 
📂 `users`   


## **📡 API Endpoints**  
### **Authentication**  
- `GET /api/auth/login/google` – Login via Google  
- `GET /api/auth/login/google/callback` – Handle Google OAuth callback  

### **Snippets**  
- `GET /api/snippets/{userName}` – Fetch all snippets by user  
- `GET /api/snippets/{userName}/{snippetId}` – Fetch a specific snippet  
- `POST /api/snippets/{userName}` – Create a new snippet  
- `PUT /api/snippets/{userName}/{snippetId}` – Update a snippet  
- `DELETE /api/snippets/{userName}/{snippetId}` – Delete a snippet  

### **Files**  
- `GET /api/files/{userName}/{snippetId}` – Fetch all files in a snippet  
- `GET /api/files/{userName}/{snippetId}/{fileId}` – Fetch a specific file  
- `GET /api/files/{userName}/{snippetId}/versions` – Fetch snippet version history  
- `POST /api/files/{userName}/{snippetId}` – Upload a new file  
- `PUT /api/files/{userName}/{snippetId}/{fileId}` – Update a file  
- `DELETE /api/files/{userName}/{snippetId}` – Delete a file  
