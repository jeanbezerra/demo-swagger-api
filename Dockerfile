FROM alpine:3.21.3

########################################
## Argumentos/Parametros
########################################
ARG build_job_name
ARG build_job_number
ARG build_date
ENV TZ=America/Sao_Paulo

########################################
## Network
########################################

EXPOSE 443/tcp
EXPOSE 80/tcp
EXPOSE 8080/tcp

########################################
## Custom Labels
########################################
LABEL author="Jean Bezerra"
LABEL version=${build_job_number}
LABEL releaseDate=${build_date}
LABEL desc="Image developed and customized for Jean Bezerra"
LABEL website="https://jeanbezerra.com"
LABEL registry="https://dockerhub.com"
LABEL org.label-schema.schema-version=${build_job_number}
LABEL org.label-schema.name="calculadora-api-image"
LABEL org.label-schema.vendor="Jean Bezerra"
LABEL org.label-schema.license="All Rights Reserveds"
LABEL org.label-schema.build-date=${build_date}
LABEL org.opencontainers.image.title="calculadora-api-image"
LABEL org.opencontainers.image.vendor="Jean Bezerra"
LABEL org.opencontainers.image.licenses="All Rights Reserveds"
LABEL org.opencontainers.image.created=${build_date}

########################################
## Contexto do Container
########################################
RUN apk update && apk upgrade
RUN apk add vim wget zip git logtail paxctl tzdata

########################################
### Pastas
########################################
RUN mkdir -p /opt/software/
RUN mkdir -p /opt/software/java/
RUN mkdir -p /opt/software/logs/
RUN mkdir -p /opt/software/configs/

########################################
### Workdir
########################################

WORKDIR /opt/software/

########################################
### Configuracao do Java
########################################
RUN apk update
RUN apk upgrade
RUN apk add ca-certificates
RUN update-ca-certificates
RUN apk add --update coreutils && rm -rf /var/cache/apk/*
RUN apk add --update openjdk21 tzdata curl unzip bash
RUN apk add --no-cache nss
RUN rm -rf /var/cache/apk/*

########################################
### Volumes
########################################

VOLUME /opt/software/java/
VOLUME /opt/software/logs/
VOLUME /opt/software/configs/

########################################
### Adicionar Softwares
########################################

COPY target/app.jar /opt/software/java/

########################################
### Execution
########################################
#CMD ["/bin/bash","-c","tail -f /dev/null"]
CMD ["/bin/sh","-c","java -jar /opt/software/java/app.jar --spring.profiles.active=default -DXms256mb -DXmx512mb"]
