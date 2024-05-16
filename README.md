Gerimedica-test

POST
Import Csv
http://localhost:8080/api/csv/import-csv
Body form-data file

GET - Get all
http://localhost:8080/api/csv
﻿

POST - Post
http://localhost:8080/api/csv
Body
{"source":"s_Oov43",
"codeListCode":"CLC-7821",
"code":"AUTO-GEN-1234",
"displayValue":"Sample Display Value",
"longDescription":"This is a randomly generated description for testing purposes.",
"fromDate":"2023-12-21",
"toDate":"2024-02-14",
"sortingPriority":3}

DELETE - Delete all
http://localhost:8080/api/csv/all

GET - Get by code
http://localhost:8080/api/csv/271636001