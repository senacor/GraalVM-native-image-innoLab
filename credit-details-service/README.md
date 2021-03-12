# `credit-details-service`

Getting details for a specific credit:

```sh
❯ http :8080/credit/123456
HTTP/1.1 200 OK
Content-Type: application/json
Date: Fri, 12 Mar 2021 13:36:14 GMT
connection: keep-alive
content-length: 94

{
    "amount": 123456.0,
    "end": "2020-12-31",
    "id": "123456",
    "interestRate": 1.0,
    "start": "2020-01-01"
}
```
