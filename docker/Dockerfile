FROM golang:alpine as builder

# Copy sources into container and build the binary
COPY hello-world.go /
WORKDIR /
RUN go build /hello-world.go

FROM scratch as final

COPY --from=builder /hello-world /hello-world
ENTRYPOINT ["/hello-world"]
