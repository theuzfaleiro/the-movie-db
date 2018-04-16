# The Movie DB

App created using MVVM Architecture and Google Architecture Components (Live Data and View Model).

The Retrofit library was used to fetch data from The Movie DB API. Dagger2 was used to improve dependency injection management.

Mockito was used to improve productivity when writing unit tests.
MockWebServer and RestMock were used to create a hermetic environment for instrumented tests.

To maintain, ensure that all the tests (unit and instrumented) are successful, Bitrise is executing both test suites
for every open Pull Request at develop branch. Also, Bitrise is executing when the Pull Request was approved to merge
with the develop branch.


Bitrise Status: [![Build Status](https://www.bitrise.io/app/633154dd1054f6ef/status.svg?token=L0W7YomnKx0tKB7_mNPv4Q&branch=develop)](https://www.bitrise.io/app/633154dd10¡¡54f6ef)