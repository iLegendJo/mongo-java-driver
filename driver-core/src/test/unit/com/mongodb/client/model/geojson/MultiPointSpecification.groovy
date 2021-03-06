/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.client.model.geojson

import spock.lang.Specification

import static com.mongodb.client.model.geojson.NamedCoordinateReferenceSystem.EPSG_4326_STRICT_WINDING


class MultiPointSpecification extends Specification {
    def coordinates = [new Position([40.0d, 18.0d]),
                       new Position([40.0d, 19.0d]),
                       new Position([41.0d, 19.0d]),
                       new Position([40.0d, 18.0d])]

    def 'constructor should set coordinates'() {
        expect:
        new MultiPoint(coordinates).coordinates == coordinates
    }

    def 'constructor should set coordinate reference system'() {
        expect:
        new MultiPoint(coordinates).coordinateReferenceSystem == null
        new MultiPoint(EPSG_4326_STRICT_WINDING, coordinates).coordinateReferenceSystem == EPSG_4326_STRICT_WINDING
    }

    def 'constructors should throw if preconditions are violated'() {
        when:
        new MultiPoint(null)

        then:
        thrown(IllegalArgumentException)

        when:
        new MultiPoint([new Position([40.0d, 18.0d]),
                        null])

        then:
        thrown(IllegalArgumentException)
    }

    def 'should get type'() {
        expect:
        new MultiPoint(coordinates).type == GeoJsonObjectType.MULTI_POINT
    }

    def 'equals, hashcode and toString should be overridden'() {
        expect:
        new MultiPoint(coordinates) == new MultiPoint(coordinates)
        new MultiPoint(coordinates).hashCode() == new MultiPoint(coordinates).hashCode()
        new MultiPoint(coordinates).toString() ==
        'MultiPoint{coordinates=[Position{values=[40.0, 18.0]}, ' +
        'Position{values=[40.0, 19.0]}, ' +
        'Position{values=[41.0, 19.0]}, ' +
        'Position{values=[40.0, 18.0]}]}'
    }
}
