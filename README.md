# Android Native Starter Project (Kotlin)

This repository provides a starter template for building a native Android app using **Kotlin**. It includes essential setup, project structure, and basic configurations to help you kickstart your Android development.

---

## Features

- **Kotlin** as the primary language
- **Jetpack Components** (ViewModel, LiveData, Navigation, Room)
- **Material Design UI** components
- **MVVM architecture** for scalable code
- Pre-configured **Dependency Injection** using Dagger Hilt
- **Retrofit** for network calls
- **Coroutines** and **Flow** for asynchronous operations
- **Timber** for logging
- Sample app structure with simple screens and navigation

---

## Getting Started

Follow these steps to get started with the project:

### Prerequisites

Make sure you have the following installed on your machine:

- **Android Studio** (Arctic Fox or later)
- **JDK 11 or higher**
- **Gradle 8.x**
- **Kotlin 1.9.x**

### How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/aselahemantha/Kotlin-Starter
   ```

2. Open the project in **Android Studio**.

3. Sync the project with Gradle files by clicking **"Sync Now"** when prompted.

4. Build and run the project on an emulator or physical device.

---

## Project Structure

```
android-native-starter/
│
├── app/                        # Main application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/           # Kotlin source files
│   │   │   ├── res/            # Resource files (layouts, drawables, etc.)
│   │   │   └── AndroidManifest.xml
│   │
│   └── build.gradle             # Module-level Gradle file
├── UILibrary/                        # UI LIbrary module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/           # Kotlin source files related to UI
│   │   │   ├── res/            # Resource files (layouts, drawables, etc.)
│   │   │   └── AndroidManifest.xml
│   │
│   └── build.gradle             # Module-level Gradle file
├── BaseSDK/                        # Main Logic module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/           # Kotlin source files for logic
│   │   │   ├── res/            # Resource files (layouts, drawables, etc.)
│   │   │   └── AndroidManifest.xml
│   │
│   └── build.gradle             # Module-level Gradle file
│
├── build.gradle                 # Project-level Gradle file
├── settings.gradle              # Gradle settings file
├── gradle.properties            # Gradle properties
└── README.md                    # This file
```

---

## Libraries and Tools Used

- **Kotlin**: Primary development language
- **Dagger Hilt**: Dependency injection
- **Jetpack Components**: ViewModel, LiveData, Room, Navigation
- **Retrofit**: HTTP client for RESTful APIs
- **Gson**: JSON serialization/deserialization
- **Coroutines**: Asynchronous programming
- **Timber**: Logging library
- **Material Design**: Modern UI components

---

## How to Contribute

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m "Add your message here"`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Contact

If you have any questions or suggestions, feel free to reach out:

- Email: aselahemantha50@gmail.com || e19300@eng.pdn.ac.lk
- GitHub: [Asela Hemantha](https://github.com/aselahemantha)
