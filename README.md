# NOAuth

Fake authorization server which approves any token.

## Usage

### Start the server

```
docker run -it --rm -p 7777:8080 experimentalsoftware/noauth
```

### Example request

```
echo "token=myscope" | http POST http://localhost:7777/token-info
```

```
HTTP/1.1 200 OK
Content-Encoding: gzip
Content-Type: application/json
Matched-Stub-Id: 9fa94da1-aee3-4039-871a-324e89e453bf
Server: Jetty(9.4.20.v20190813)
Transfer-Encoding: chunked
Vary: Accept-Encoding, User-Agent

{
    "active": true,
    "client_id": "apitest",
    "scope": [
        "myscope"
    ]
}
```

### Spring Boot

Spring Boot projects can apply the following configuration to use the NOAuth server for their token validation:

```
security:
  oauth2:
    resource:
      tokenInfoUri: http://localhost:7777/token-info
    client:
      clientId: noauth
```

## Maintenance

### Publish new Docker image

```
./docker_build.sh
./docker_push.sh
```

## References

- http://wiremock.org/docs
- https://github.com/rodolpheche/wiremock-docker
- [`POST /oauth/userinfo`](https://docs.akana.com/cm/api_oauth/oauth_userinfo/m_userinfo_getAuthenticatedUserInfoPOST.htm) - docs.akana.com

## License

This project is licensed under the [Apache License Version 2.0](LICENSE).
