FROM golang

ENV GOBIN=/go/bin

ADD ./golang /go

RUN go install /go/main.go

ENTRYPOINT /go/bin/main

EXPOSE 8080
