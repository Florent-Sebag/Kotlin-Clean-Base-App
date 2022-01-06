# Kotlin Modularized Clean/MVVM Architecture with Dagger/RX/Navigation/Firebase Base App

This repository is a base app you can use in order to create a new android application in Kotlin without creating again the architecture and importing the differents main's library.

It contains 2 versions :

- One simple version with all the librarys, where there is a Home and a Detail page. It implement a very simple API to see how to do proper api calls with this architecture. This version is actually on the master branch.

- One more advanced version with an implementation of the Firebase Authentication SDK. In this version, 3 pages are disponible : Login, subscription, Home; with the possibilty to stay connected when the user already logged in. This version is on the FirebaseAuth branch.

For each library implemented, there is a sample use to see how to use it properly.

# The project

The architecture used here is one of the most popular in android developpement actually : Clean Architecture + MVVM, implemented in a modularized way.
Thanks to that, the UI and API calls are totally independant, so you can change it easily without changing everything of the project.

# The library used

- Navigation Component - used for the navigation between views
- Dagger2 - used for the dependency injection (OP)
- Okhttp, retrofit, gson - used for the API calls
- RxKotlin - to do API calls in an asynchronous way
- Databinding - For binding dynamically values on front

And, for the firebase version :
- Material design
- Firebase sdk (without UI).
