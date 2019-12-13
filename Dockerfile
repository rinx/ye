ARG GRAALVM_VERSION=latest

FROM oracle/graalvm-ce:${GRAALVM_VERSION} as graalvm

RUN gu install native-image
RUN curl -o /usr/bin/lein https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein \
    && chmod a+x /usr/bin/lein

RUN mkdir -p /ye
WORKDIR /ye

COPY project.clj project.clj
COPY src src

RUN lein uberjar

COPY reflection.json reflection.json

COPY Makefile Makefile

RUN make

FROM scratch

COPY --from=graalvm /ye/ye /ye

ENTRYPOINT ["/ye"]
