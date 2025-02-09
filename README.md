## Designing CodeGists

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
- GET /api/auth/login/google
- GET /api/auth/login/google/callback
- POST /api/snippet/{userName}
  body:
  ```
  {
		  
		"description": #values
		"isPublic": true
		"content":[{
			"fileName": "Udhaya.java",
			"fileContent": #values
		},
		{
			"fileName": "Kumar.java",
			"fileContent": #values
		}]	
  }
  ```
Respose - Status: 302, location: /api/snippet/{userName}/{fileName}

- PUT /api/snippet/{userName}/{snippetId}
    body:
  ```
  {
		"description": #values
		"isPublic": false
		"content":[{
			#existingFileUpdate
			"fileId": #value
			"fileName": "Udhaya.java",
			"fileContent": #values,
			"type": "update"
		},
		{
			##deleteFile
			"fileId": #value
			"fileName": "Uk.java",
			"fileContent": #values,
			"type": "delete"
		},
		{
			#newFile
			"fileName": "Kumar.java",
			"fileContent": #values
			"type": "new"
		}]	
  }
  ```
  
- DELETE /api/snipprt/{username}/{snippetId}
- GET /api/snippet/{userName}/{snippetId}
- GET /api/snippet/{userName}/{snippetId}/versions
- GET /api/snippet/{userName}/{snippetId}/{versionsId}
