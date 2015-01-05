Strava Service
===============

This is a project which uses [Scala][scala], [Spray][spray] and the [Play][play] JSON lib to make calls to [Strava's][strava] API

To use the service, you'll need a Strava application token. This needs to be placed in a file called strava-service.conf which is referenced by the application.conf file. Example configuration is below:

    stravaService.appToken = "appToken"

The ExampleStravaService file contains the below sample calls:

    retrieve a segment
    retrieve a segment effort


LICENCE
-------

BSD Licence - see LICENCE.txt


TODO
----

1. Add more API calls
2. Resolve TODOs

[scala]: http://www.scala-lang.org/ "Scala Language"
[spray]: http://spray.io/ "Spray"
[play]: https://www.playframework.com/documentation/2.0/ScalaJson
[strava]: https://www.strava.com/