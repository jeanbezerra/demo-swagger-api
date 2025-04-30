# calculadora-api


## Gerar o certificado digital


```sh
keytool -genkeypair -alias 1 -keyalg RSA -keysize 2048 -validity 365 -keystore certificate.pfx -storetype PKCS12 -storepass PASSWORD -keypass PASSWORD -dname "CN=SeuNome, OU=SeuDepartamento, O=SuaEmpresa, L=SuaCidade, ST=SeuEstado, C=BR"
```

## Constuindo a Imagem Docker

```docker
docker scan --accept-license
```

```docker
docker build --file build-image.dockerfile -t calculadora-api-image --no-cache=false .
```

```docker

```