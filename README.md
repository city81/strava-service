Strava Service
===============

[![Master Build](https://travis-ci.org/city81/strava-service.svg?branch=master)](https://travis-ci.org/city81/strava-service)

This is a project which uses [Scala][scala], [Spray][spray] and the [Lift][lift] JSON lib to make calls to [Strava's][strava] API

To use the service, you'll need a Strava application token. This needs to be placed in a file called strava-service.conf which is referenced by the application.conf file. Example configuration is below:

    stravaService.appToken = "appToken"

The ExampleStravaService file contains the below sample calls:

    retrieve a segment
    retrieve starred segments
    retrieve a segment effort
    retrieve all segment efforts
    retrieve an activity
    retrieve an athlete
    retrieve gear


LICENCE
-------

BSD Licence - see LICENCE.txt


TODO
----

1. Add more API calls
2. Resolve TODOs
3. Add more tests

[scala]: http://www.scala-lang.org/ "Scala Language"
[spray]: http://spray.io/ "Spray"
[lift]: https://github.com/lift/lift/tree/master/framework/lift-base/lift-json/
[strava]: https://www.strava.com/