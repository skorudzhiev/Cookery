# Cookery
Cookery is a recipes app, which connects with [TheMealDB](https://www.themealdb.com/api.php).

<p align="left"> 
<img src="https://github.com/skorudzhiev/skorudzhiev/blob/main/assets/Cookery.svg" alt="quizexplorer" width="120" height="120"/>
</p>

### Development Progress - 🚧 First screens 🚧
 
## Android Development
Cookery is aspired to be an ongoing project, in an attempt to practice some MAD (Modern Android development) skills.
 
* The app is written entirely in [Kotlin](https://kotlinlang.org/)
* Multi-modular project
* [CircleCI](https://circleci.com/) integration
* Dependency injection with [Hilt](https://dagger.dev/hilt/)
* [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-guide.html) and [Kotlin Flow](https://kotlinlang.org/docs/flow.html) for an asynchronous development
* Use of many [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) throughout the project
* The UI is built with [Jetpack Compose](https://developer.android.com/jetpack/compose)

## Development setup

#### Code style
This project uses [ktlint](https://github.com/pinterest/ktlint), provided through the [spotless](https://github.com/diffplug/spotless) gradle plugin.

If your PR doesn't pass the checkstyle CI check, make sure to run `./gradlew spotlessApply` and fix all warnings in the current branch.

## Screenshots

### Home screen
Dark theme             |  Light theme
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/26792749/141307913-81b12728-b49e-45b7-a54b-4f79a0617258.png)  |  ![](https://user-images.githubusercontent.com/26792749/141307992-484d602e-2c36-4782-b1d3-027f1be9688f.png)
 
### Details screens
Category details - dark | Category details - light 
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/26792749/147881620-9ac22851-95f7-418b-a766-f9d931ebc8c9.png)  |  ![](https://user-images.githubusercontent.com/26792749/147881641-e8899704-65ce-4fb7-8feb-5ac234113ede.png)
Meal details - dark| Meal details - light 
![](https://user-images.githubusercontent.com/26792749/147881726-d3834e8e-e940-451b-9011-67d59523bbe0.png)  |  ![](https://user-images.githubusercontent.com/26792749/147881741-39e750e1-5811-4908-a384-dbb9d4b5cf68.png)
Meal details - dark| Meal details - light 
![](https://user-images.githubusercontent.com/26792749/147881817-24dcb49d-55b7-4c50-850f-625d256aaf36.png)  |  ![](https://user-images.githubusercontent.com/26792749/147881828-a84a973e-872a-4a18-a4d1-59a21f257d15.png)
 
## Contributions
If you're interested in contributions to this project, make sure to read the project's [Wiki](https://github.com/skorudzhiev/Cookery/wiki/Git-Workflow)

 
## Attributions
 
 <div>Icon made by <a href="https://www.flaticon.com/authors/photo3idea-studio" title="photo3idea_studio">photo3idea_studio</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>

## License

```
Copyright [2021] [Stoyan Korudzhiev]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
