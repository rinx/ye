FROM oracle/graalvm-ce:latest as graalvm

RUN gu install native-image
RUN curl -o /usr/bin/lein https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein \
    && chmod a+x /usr/bin/lein

RUN mkdir -p /ye
WORKDIR /ye

COPY project.clj project.clj
COPY src src

RUN lein uberjar
RUN native-image \
    -jar target/ye-0.1.0-SNAPSHOT-standalone.jar \
    -H:Name=ye \
    -H:+ReportExceptionStackTraces \
    -J-Dclojure.spec.skip-macros=true \
    -J-Dclojure.compiler.direct-linking=true \
    -H:Log=registerResource: \
    --verbose \
    --no-fallback \
    --no-server \
    --report-unsupported-elements-at-runtime \
    --initialize-at-build-time \
    --static \
    -J-Xms2g \
    -J-Xmx4g

FROM scratch

COPY --from=graalvm /ye/ye /ye

ENTRYPOINT ["/ye"]
