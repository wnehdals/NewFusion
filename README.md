# NewFusion
NewsFusion

## Development

### Required

- IDE : Android Studio 최신 버전
- JDK : Java 17을 실행할 수 있는 JDK
    - (권장) Android Studio 설치 시 Embeded 된 JDK (Open JDK)
    - Java 17을 사용하는 JDK (Open JDK, AdoptOpenJDK, GraalVM)

- Kotlin Language : 2.0.21

### Language

- Kotlin

### Libraries

- AndroidX
    - Activity & Activity Compose
    - AppCompat
    - Core
    - Lifecycle & ViewModel Compose
    - Navigation

- Kotlin Libraries (Coroutine, Serialization)
- Compose
    - Material3
    - Navigation

- Dagger & Hilt

#### UI Layer

상태는 아래로 이동하고 이벤트는 위로 이동하는 단방향 데이터 흐름(UDF)으로 구성되어 있습니다.

![img](https://lh5.googleusercontent.com/Cy5hT9u87lJ9w4mKtGOvyWIaHAUMXQJakV_1RVdjeHGeAUFMnTS1P33yan05Sw5AcPbfkI6DiXt4SupBVnziDjl-ylvNqhTb0u1uZWTgp0saetrqFYhjH0LrxTocOFIKOvOSZ26wYSJDJi6nrRUrUJg)

### Module

본 프로젝트는 Multi-module 구조이며 각 Feature마다 모듈 형태로 구성되어 있습니다.

