
# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Installation
1. Clone the repository:
```bash
git clone https://github.com/yourusername/pokeserene.git
```

2. Open the project in Android Studio.

3. Build the project:

In Android Studio, click on Build -> Rebuild Project.

4. Run the app:

Click on the Run button or use Shift + F10.

## Screenshots & Videos

https://github.com/user-attachments/assets/a017149a-0acf-4063-b2c4-c7b2536b8ed5

<img src="https://github.com/user-attachments/assets/0a840513-14db-4cc3-af2a-39d466775c25" alt="Screenshot" width="350"/>
<img src="https://github.com/user-attachments/assets/fe380524-418d-4442-9de4-4fb7b70339f1" alt="Screenshot" width="350"/>
<img src="https://github.com/user-attachments/assets/b2b888c6-fe19-4ab6-9397-a592c667fcd6" alt="Screenshot" width="350"/>

## APK Link
https://drive.google.com/file/d/1jY2xv5hmXDP7g4_Gc0LFC0ZmDISkOC6D/view?usp=sharing


## Usage
1. Browse Pokémon:
The main screen displays a list of Pokémon fetched from the PokeAPI.

2. View Details:
Tap on any Pokémon to view detailed information, including its abilities, types, and stats.


## Architecture
This app uses the MVVM (Model-View-ViewModel) architecture, which helps in separating concerns and making the code more maintainable.

- Model: Represents the data and business logic of the app. In this project, data models are used to fetch and parse data from the PokeAPI.
- View: Represents the UI of the app. Built using Jetpack Compose.
- ViewModel: Manages the UI-related data in a lifecycle-conscious way. Handles the logic to fetch data from the repository and expose it to the UI.


## Dependencies
- Jetpack Compose
- Navigation Compose
- Coil for image loading
- Retrofit for network calls
- Moshi for JSON parsing

## build.gradle
```bash
plugins {
    id 'com.android.application'
    id 'kotlin-android'
}

android {
    compileSdk 30

    defaultConfig {
        applicationId "com.example.pokeserene"
        minSdk 21
        targetSdk 30
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion '1.0.1'
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    buildFeatures {
        compose true
    }
}

dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib:1.5.21"
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.compose.ui:ui:1.0.1'
    implementation 'androidx.compose.material:material:1.0.1'
    implementation 'androidx.compose.ui:ui-tooling-preview:1.0.1'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
    implementation 'androidx.activity:activity-compose:1.3.1'
    implementation 'androidx.navigation:navigation-compose:2.4.0-alpha10'
    implementation 'io.coil-kt:coil-compose:1.3.2'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    androidTestImplementation 'androidx.compose.ui:ui-test-junit4:1.0.1'
    debugImplementation 'androidx.compose.ui:ui-tooling:1.0.1'
}

```

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

