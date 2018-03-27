# codewars-challenge-android

A code challenge that showcase some core Android development topics.

The application is designed using Clean Architecture concepts and guidelines. There are three different
separated layers: Data, Domain and UI.

In the Data layer we have three entities, the Database (using Room), the network HTTP client (Retrofit)
and a Repository mechanism. This will try first to get data from local, and if doesn't exist will fetch from the backend and
then save to the database.

For the presentation layer and for demonstration purposes, there is a MVP pattern implemented for
the Search feature. So we have a passive View (Activity), a Presenter and an Interactor that implement the use case.

In this part for the data flow it has been used RxJava, LiveData for sync with the UI and LifeCycle to manage the Presenter life.

For the Challenges feature the MVVM pattern has been implemented, using the Architecture Components ViewModel,
LiveData and LyceCycle.

Unit Test and Espresso tests has been added but due to time constraints there is no full coverage.
Also the challenges are not saved to the database after being fetched like in the Search functionality.
Although the implementation would be trivial, just following same repository pattern.
In this regards, also some kind of "refresh with live data" should be implemented.

