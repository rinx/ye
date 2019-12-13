XMS = 2g
XMX = 8g

.PHONY: all
all: ye

.PHONY: clean
clean:
	rm -f ye
	rm -rf target

ye: \
	target/ye-0.1.0-SNAPSHOT-standalone.jar \
	reflection.json
	native-image \
	-jar target/ye-0.1.0-SNAPSHOT-standalone.jar \
	-H:Name=ye \
	-H:+ReportExceptionStackTraces \
	-J-Dclojure.spec.skip-macros=true \
	-J-Dclojure.compiler.direct-linking=true \
	-H:Log=registerResource: \
	-H:ReflectionConfigurationFiles=reflection.json \
	--verbose \
	--no-fallback \
	--no-server \
	--report-unsupported-elements-at-runtime \
	--initialize-at-build-time \
	--static \
	-J-Xms$(XMS) \
	-J-Xmx$(XMX)

target/ye-0.1.0-SNAPSHOT-standalone.jar:
	lein uberjar

reflection.json:
	echo "[]" > $@

resources.json:
	echo "[]" > $@
