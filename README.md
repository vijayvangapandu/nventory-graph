## operation inventory management system

# Sample Queries

### Get Allocated Memory By Application
Match(n:Application{name:'Arrow'})-[no: NODE_OF]-()-[r: ALLOCATED_MEMORY]-() return sum(r.memoryInGB)

### Get Allocated CPU By Application
Match(n:Application{name:'Arrow'})-[no: NODE_OF]-()-[r: ALLOCATED_CPU]-() return sum(r.numberOfCores)

### Get Allocated DiskSpace By Application
Match(n:Application{name:'Arrow'})-[no: NODE_OF]-()-[r: ALLOCATED_DISK]-() return sum(r.allocatedSpaceInGB)

### Number of allocated processes by application
Match(a:Application {name: 'Arrow'})-[NODE_OF]-(n:Server)-[e]-(p:Processor) return n, e

### API's

#### Get All Teams
http://host:7001/nventory/v1/teams/{teamName}
ex: http://localhost:7001/nventory/v1/teams
ex: http://localhost:7001/nventory/v1/teams/Platform

#### Get All Applications by team
http://host:7001/nventory/v1/teams/{teamName}/applications
ex: http://host:7001/nventory/v1/teams/Platform/applications

#### Get Application by appName
http://host:7001/nventory/v1/applications/{appName}
ex: http://host:7001/nventory/v1/applications/Arrow

#### Get All Servers by appName
http://localhost:7001/nventory/v1/teams/applications/{appName}/servers
ex: http://localhost:7001/nventory/v1/teams/applications/Arrow/servers

### Get all resources by team
http://localhost:7001/nventory/v1/teams/{teamName}/applications/resources
Ex: http://localhost:7001/nventory/v1/teams/Platform/applications/resources


