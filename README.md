# ye

[![GitHub Actions: Build native image](https://github.com/rinx/ye/workflows/Build%20native%20image/badge.svg)](https://github.com/rinx/ye/actions)
[![GitHub Actions: Build docker image](https://github.com/rinx/ye/workflows/Build%20docker%20image/badge.svg)](https://github.com/rinx/ye/actions)
[![GitHub Actions: Release](https://github.com/rinx/ye/workflows/Release/badge.svg)](https://github.com/rinx/ye/actions)

yaml <-> edn (<-> json) converter CLI tool.

ye = yaml-edn converter

## Usage

    $ ye --help

    $ ye --from yaml --to edn sample.yaml

    $ ye --from edn --to yaml sample.edn

    $ ye --from json --to edn sample.json

    $ cat sample.yaml | ye --from yaml --to edn

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
