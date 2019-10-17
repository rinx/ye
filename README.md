# ye

[![GitHub Actions: Build native image](https://github.com/rinx/ye/workflows/Build%20native%20image/badge.svg)](https://github.com/rinx/ye/actions)
[![GitHub Actions: Build docker image](https://github.com/rinx/ye/workflows/Build%20docker%20image/badge.svg)](https://github.com/rinx/ye/actions)
[![GitHub Actions: Release](https://github.com/rinx/ye/workflows/Release/badge.svg)](https://github.com/rinx/ye/actions)

yaml <-> edn (<-> json) converter CLI tool.

ye = yaml-edn converter

ye is aimed to be built as a native binary powered by GraalVM.

## Install

It is available to download a native binary from the [latest release](https://github.com/rinx/ye/releases/latest).

Docker image is also available.

    $ docker pull docker.pkg.github.com/rinx/ye/ye:latest

## Build

Please read `Dockerfile`.

## Usage

    $ ye --help
    Usage: ye [options] [filename]
      -f, --from TYPE  :yaml  From type
      -t, --to TYPE    :edn   To type
      -h, --help

    $ cat sample.yaml
    jobs:
      build:
        runs-on: ubuntu-latest
        steps:
        - uses: actions/checkout@v1

    # from a file
    $ ye --from yaml --to edn sample.yaml
    {:jobs
     {:build
      {:runs-on "ubuntu-latest", :steps [{:uses "actions/checkout@v1"}]}}}

    # from stdin
    $ cat sample.yaml | ye --from yaml --to edn
    {:jobs
     {:build
      {:runs-on "ubuntu-latest", :steps [{:uses "actions/checkout@v1"}]}}}

    # also, ye can convert edn and json into other types.
    $ ye --from edn --to yaml sample.edn
    $ ye --from json --to edn sample.json

## License

Copyright © 2019 rinx

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
