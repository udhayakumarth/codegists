## Designing CodeGists
- Api Documentation: 
https://documenter.getpostman.com/view/21272460/2sAYXEFJpg

- UI Desigin: 
https://www.figma.com/design/zNWijW6x66dZ4hgGOKQDOQ/CodeGists

### Key Features
- Version Control
- Supports Multiple Files
- Public snippets
- Secret snippets

### DB Desigin(Mongo)
Collections:
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


***
### New API Desigin
- `GET /api/auth/login/google`
- `GET /api/auth/login/google/callback`
- `GET /api/snippets/{userName}`
- `GET /api/snippets/{userName}/{snippetId}`
- `GET /api/files/{snippetId}`
- `GET /api/files/{snippetId}/{fileId}`
- `GET /api/files/{snippetId}/versions`
- `GET /api/files/{snippetId}/{versionId}`
- `POST /api/snippets/{userName}`
- `POST /api/files/{snippetId}`
- `PUT /api/snippets/{userName}/{snippetId}`
- `PUT /api/files/{snippetId}/{fileId}`

### API Desigin for !nano URL
- `POST /api/url`
- `GET /api/url?nanoUrl=https://url.udhayakumarth.life/asHk6`

### DB Desigin for !nano URL
url(collection):
urlId, originalUrl, nanoUrl
 
------------
### For Google login
- http://localhost:8080/api/auth/login/google/callback

- http://localhost:8080/api/auth/login/google
