# MovieSearchApp

**Project Overview**

This app allows users to search for movies by title. When a user enters a search term, the app will fetch results from the OMDb API and display a list of movies with their title, release year, and poster image. This app will provide a seamless and user-friendly experience, ensuring smooth navigation, responsiveness, and clarity.

**Architecture and Design Patterns**

For a maintainable, scalable app, I have adopted the below popular design patterns and architecture principles:

MVVM (Model-View-ViewModel):

Model: Represents the data structure (movie details, such as title, year, and poster).

View: The UI components (e.g., RecyclerView) that display the data.

ViewModel: Acts as the intermediary between the view and the model, handling data fetching and transformation.

**Networking:**

Retrofit for network requests.
Handle API responses with Coroutine to maintain a responsive UI.

**Tools and Technologies:**

Frontend:

Android: Kotlin, Android Studio, RecyclerView (for displaying the list), Retrofit (networking), Glide(for loading images)

Backend:

API: OMDb API (GET request to https://www.omdbapi.com/?s=[movie title]&apikey=8d6aa4ca)

Version Control:

Git for version control and collaboration.

**Key Features & UX Considerations**

Search Bar:

A search bar at the top of the screen where users can type movie titles. 

Display Movie Results:

Each result will have the movie’s title, release year, and poster image.
Used an ImageView in this app to display the movie poster, ensuring images are loaded asynchronously using a caching library like Glide.

Error Handling:

If the API request fails (e.g.,invalid search, No movie data),will display a Toast with an error message.
Also, will show an appropriate message if no results are found.

Loading State:

Will show a loading indicator (e.g., an animated movie widget spinner) while the app is fetching data.
And also, will hide the loading indicator once the data is successfully fetched.

**Getting Started**

1) Clone the repository into your machine

2) Run the app

Open the project in Android Studio and run it.

![start_screen](https://github.com/user-attachments/assets/9d036650-1a65-4dc9-967a-ae6239a2f32f)

![search_result](https://github.com/user-attachments/assets/68c76d24-13ce-482b-ad89-ed79b03e613d)
