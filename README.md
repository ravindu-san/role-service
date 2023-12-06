# role-service

## REST End Points
|        Endpoind       |            Request Body             |             Description          |
|:--------------------- |:-----------------------------------:|:---------------------------------|
| `[GET] /roles`        |                      -              | Retreive all roles               |
| `[GET] /permissions`  |                      -              | Retreive all permissions         |
| `[PATCH] /roles/{id}` | { "permissions": [{"id": \<str\>}]} | Set permissions for a given role |





  ## Tests
* (few) unit and integration tests can be found in [src/test](src/test)  
