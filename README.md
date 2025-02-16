## Designing CodeGists
Api Documentation:
https://documenter.getpostman.com/view/21272460/2sAYXEFJpg

### Types of snippets
- Public snippets – accessible to everyone 
- Secret snippets – Only accessible to owner.

### Key Features
- Version Control: Each update creates a new version.
- Supports Multiple Files: Not limited to a single file.

### DB Desigin
- snippets
- files
- versions
- users

### Endpoint
- `GET /api/auth/login/google`
- `GET /api/auth/login/google/callback`
- `POST /api/snippet/{userName}`
  ```
  #Request-body
  {
		"description": #values,
		"isPublic": true,
		"content":[{
			"fileName": "Udhaya.java",
			"fileContent": #values,
			"language": #value,
		},
		{
			"fileName": "Kumar.java",
			"fileContent": #values,
			"language": #value,
		}]	
  }
  #Responce
	status: 201 CREATED
	location: /api/snippet/{userName}/{snippetId}
  ```
  
- `POST /api/snippet/{userName}/{snippetId}`
	```
  #Request-body
  {
		"description": #values,
		"isPublic": false,
		"content":[{
			#existingFile
			"fileId": #value,
			"fileName": "Udhaya.java",
			"fileContent": #values,
			"language": #value,
			"type": "Intact"
		},
		{
			#existingFileUpdate
			"fileId": #value,
			"fileName": "Udhaya.java",
			"fileContent": #values,
			"language": #value,
			"type": "update"
		},
		{
			##deleteFile
			"fileId": #value,
			"fileName": "Uk.java",
			"fileContent": #values,
			"language": #value,
			"type": "delete"
		},
		{
			#newFile
			"fileName": "Kumar.java",
			"fileContent": #values,
			"language": #value,
			"type": "new"
		}]	
  }
    #Responce
	status: 200 OK
	location: /api/snippet/{userName}/{snippetId}
  ```
  
- `DELETE /api/snipprt/{username}/{snippetId}`
```
#Responce
	status: 204 No Content
```

- `GET /api/snippet/{userName}/{snippetId}`
```
#Responce-body
{
	{
		"snippetId": #value,
		"versionsId": #value,
		"description": #values,
		"isPublic": false,
		"content":[{
			"fileId": #value,
			"fileName": "Udhaya.java",
			"fileContent": #values,
			"language": #value,	
		},
		{
			"fileId": #value,
			"fileName": "Udhaya.java",
			"fileContent": #values,
			"language": #value,
		},
		{
			"fileId": #value,
			"fileName": "Uk.java",
			"fileContent": #value,
			"language": #value,
		},
		{
			"fileId": #value,
			"fileName": "Kumar.java",
			"fileContent": #values,
			"language": #value,
		}]
		"createdAt": #value,
		"updateAt": #value,
		"createdBy": #value,
		"updateBy": #value
  }
}
```
- `GET /api/snippet/{userName}/{snippetId}/versions`
- `GET /api/snippet/{userName}/{snippetId}/{versionsId}`

------------
### For login
http://localhost:8080/api/auth/login/google/callback

http://localhost:8080/api/auth/login/google
