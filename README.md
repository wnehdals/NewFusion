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

## Architecture

Presentation/Domain/Data 레이어 구조로 구성되어 있습니다.
![Image](https://github.com/user-attachments/assets/2fb0629b-95ae-4908-9169-9ddf64f6a7ac)

#### UI Layer

상태는 아래로 이동하고 이벤트는 위로 이동하는 단방향 데이터 흐름(UDF)으로 구성되어 있습니다.

![Image](https://github.com/user-attachments/assets/0236eefe-f2c9-4db1-9e70-049d3bead1d0)

### Module

본 프로젝트는 Multi-module 구조이며 각 Feature마다 모듈 형태로 구성되어 있습니다.
![Image](https://github.com/user-attachments/assets/18f3b6d2-58a1-409b-9bbb-d9a5a26dc8b4)


### 직접 판단 결정 사항

news.json 파일 데이터에서 title이 없는 경우 UI에서 빈 문자열로 표현하였습니다.  
가장 최근에 발생한 뉴스가 먼저 나오도록 하기 위해 news.json 데이터에 임의로 시간을 설정하였습니다.
10초마다 10개의 뉴스를 중복없이 제공하기 위해 news.json 데이터에 id가 필요하여 임의로 id를 설정하였습니다.
news.json 데이터의 첫번째 항목이 가장 오래전에 발생한 뉴스, 마지막 항목이 가장 최근에 발생한 뉴스로 결정하고 개발하였습니다.