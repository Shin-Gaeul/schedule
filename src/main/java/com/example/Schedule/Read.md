1. Api 명세서
{
"info": {
"_postman_id": "f763783f-c131-4464-b73c-02b07834eb89",
"name": "Schedule API",
"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
"_exporter_id": "40220243"
},
"item": [
{
"name": "Create Schedule",
"request": {
"method": "POST",
"header": [],
"url": {
"raw": "http://localhost:8080/schedules",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"schedules"
]
}
},
"response": []
},
{
"name": "Get All Schedules",
"protocolProfileBehavior": {
"disableBodyPruning": true
},
"request": {
"method": "GET",
"header": [],
"body": {
"mode": "raw",
"raw": "",
"options": {
"raw": {
"language": "json"
}
}
},
"url": {
"raw": "http://localhost:8080/schedules?author&updated_at",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"schedules"
],
"query": [
{
"key": "author",
"value": null
},
{
"key": "updated_at",
"value": null
}
]
}
},
"response": []
},
{
"name": "Get Schedule By ID",
"request": {
"method": "GET",
"header": [],
"url": {
"raw": "http://localhost:8080/schedules/:id",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"schedules",
":id"
],
"variable": [
{
"key": "id",
"value": "1"
}
]
}
},
"response": []
},
{
"name": "Update Schedule",
"request": {
"method": "PUT",
"header": [],
"body": {
"mode": "raw",
"raw": "{\n    \"todo\": \"수정된 할일\",\n    \"author\": \"홍길동\"\n}\n",
"options": {
"raw": {
"language": "json"
}
}
},
"url": {
"raw": "http://localhost:8080/schedules/:id?password= 일정 수정 권한 확인 비밀번호",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"schedules",
":id"
],
"query": [
{
"key": "password",
"value": " 일정 수정 권한 확인 비밀번호"
}
],
"variable": [
{
"key": "id",
"value": "수정할 일정 ID"
}
]
}
},
"response": []
},
{
"name": "Delete Schedule",
"request": {
"method": "DELETE",
"header": [],
"url": {
"raw": "http://localhost:8080/schedules/:id?password=일정 삭제 권한 확인 비밀번호",
"protocol": "http",
"host": [
"localhost"
],
"port": "8080",
"path": [
"schedules",
":id"
],
"query": [
{
"key": "password",
"value": "일정 삭제 권한 확인 비밀번호"
}
],
"variable": [
{
"key": "id",
"value": "삭제할 일정 ID"
}
]
}
},
"response": []
}
]
}
2. erd

3. read.md
## 🛠 기술 스택
- **Framework**: Spring Boot
- **Database**: MySQL
- **Build Tool**: Gradle
- **Language**: Java 17
- **API Tool**: Postman

