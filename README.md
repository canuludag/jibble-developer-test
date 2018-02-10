Jibble Mobile Developer Test Application
--------------------
Application fetches data from a remote fake api and displays them on a list. Three different endpoints used from the api and the results combined on a single data list. Application was written in MVP architecture and Material Design Guidelines applied. Most of the classes were written in Java but some of the classes were written in Kotlin. Kotlin is a beautiful language and helps you to reduce boilerplate code and write cleaner classes. In order to use these functionalities I've used it in this project.

Prerequisites
-------------
* Java 1.8
* Kotlin Plugin 1.2.21
* Android Studio 3.0 and above

Fake API That Used In This Project
------------------------------------
**Base URL:** https://jsonplaceholder.typicode.com 
**Endpoints:**
* /posts	*(100 items available)*
* /albums	*(100 items available)*
* /users	*(10 items available)*

**Query Parameters:**
* _limit *(limits the results with a given value)*

Main Libraries That Used In This Project
------------------------------------
* [Dagger][1] --> *(For dependency injection)*
* [ButterKnife][2] --> *(For view injection)*
* [Retrofit][3] --> *(For network requests)*
* [RxJava & RxAndroid][4] --> *(For reactive programming)*
* [GSON][5] --> *(For object serialization)*

Dagger Modules That Created For This Project
-----------------------------------
* **AppModule** --> *(Provides application context)*
* **ApiModule** --> *(Provides API services)*
* **HomeModule** --> *(Provides Model and Presenter classes)*

Main Model Classes That Created For This Project
-----------------------------------
* **Post** --> *(For the result of posts endpoint)*
* **Album** --> *(For the result of albums endpoint)*
* **User** --> *(For the result of users endpoint)*
* **CombinedData** --> *(For combining all 3 results together)*

Demo
----
![](./art/art_demo_1) ![](./art/art_demo_2) 

![](./art/art_demo_3) ![](./art/art_demo_4)

Developed By
---------------
* Can Uludağ
* cucanuludag@gmail.com
* https://www.linkedin.com/in/canuludag/
 
License
----------
    Copyright 2018 Can Uludağ

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://github.com/google/dagger
[2]: https://github.com/JakeWharton/butterknife
[3]: http://square.github.io/retrofit/
[4]: https://github.com/ReactiveX/RxAndroid 
[5]: https://github.com/google/gson