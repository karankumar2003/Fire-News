# NewsApp
NewsApp is a feature-rich Android application that allows users to read and save news articles from various sources.  
The app is built using modern Android development technologies, including Jetpack Compose, Retrofit, Hilt, Coroutines, Firebase Authentication, Firebase Firestore, and Coil for efficient image loading.

# Features
## User Authentication:
* Users can sign up using their email and password.  
* Existing users can log in to access personalized features.
## News Feed:
* Fetches news articles from the NewsAPI.org service.  
* Displays news articles with title, source, publication date, and article thumbnail images using smooth and efficient image loading with Coil.  
* Click on an article to read it completely.  
* Implements **paging** for seamless loading and browsing of news articles.  
## News Search:
* Provides a powerful search functionality for users to find news articles based on keywords or topics of interest.
## Saved Articles:
* Allow users to save news articles to their personal collection.  
* Saved articles are securely stored in Firebase Firestore and persist even if the app is uninstalled or reinstalled.
## Error Handling:
* Handles network errors gracefully, ensuring a smooth and error-free user experience.

# Technologies Used
NewsApp leverages the following technologies and libraries:

## Jetpack Compose
A modern UI toolkit for building beautiful Android apps.
## Retrofit
A type-safe HTTP client for network requests.
## Hilt
A dependency injection library for Android.
## Coroutines
A lightweight concurrency framework for asynchronous programming.
## Firebase Authentication
Provides user authentication and authorization functionality.
## Firebase Firestore
A NoSQL document database for storing and syncing app data.
## Coil
An image loading library for efficient and hassle-free image loading and caching.
