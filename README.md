```
Directory structure:
└── sadiquereyaz-growwstocks/
    ├── build.gradle.kts
    ├── settings.gradle.kts
    ├── app/
    │   ├── build.gradle.kts
    │   └── src/
    │       └── main/
    │           ├── AndroidManifest.xml
    │           ├── java/
    │           │   └── com/
    │           │       └── reyaz/
    │           │           └── growwstocks/
    │           │               ├── BaseApplication.kt
    │           │               ├── GrowwStocksApp.kt
    │           │               ├── MainActivity.kt
    │           │               ├── app_bar/
    │           │               │   ├── data/
    │           │               │   │   └── repository/
    │           │               │   │       └── ThemeRepository.kt
    │           │               │   └── presentation/
    │           │               │       ├── AppBarEvent.kt
    │           │               │       ├── MainUiState.kt
    │           │               │       └── MainViewModel.kt
    │           │               ├── di/
    │           │               │   └── AppModule.kt
    │           │               └── navigation/
    │           │                   └── AppNavHost.kt
    │           └── res/
    │               ├── values/
    │               │   ├── colors.xml
    │               │   ├── splash.xml
    │               │   ├── strings.xml
    │               │   └── themes.xml
    │               └── values-night/
    │                   └── splash.xml
    ├── core/
    │   ├── common/
    │   │   ├── build.gradle.kts
    │   │   └── src/
    │   │       └── main/
    │   │           ├── java/
    │   │           │   └── com/
    │   │           │       └── reyaz/
    │   │           │           └── core/
    │   │           │               └── common/
    │   │           │                   ├── Resource.kt
    │   │           │                   ├── model/
    │   │           │                   │   ├── Stock.kt
    │   │           │                   │   ├── StockType.kt
    │   │           │                   │   └── ThemeMode.kt
    │   │           │                   ├── navigation/
    │   │           │                   │   ├── NavigationExtention.kt
    │   │           │                   │   ├── Route.kt
    │   │           │                   │   └── TopLevelDestination.kt
    │   │           │                   └── utils/
    │   │           │                       ├── Constant.kt
    │   │           │                       └── TypeConvertor.kt
    │   │           └── res/
    │   │               ├── drawable/
    │   │               │   ├── bookmark_add_24px.xml
    │   │               │   ├── bookmark_added_24px.xml
    │   │               │   ├── bookmark_added_filled_24px.xml
    │   │               │   ├── bookmarks_24px.xml
    │   │               │   ├── bookmarks_filled_24px.xml
    │   │               │   ├── groww_logo.xml
    │   │               │   ├── groww_placeholder.xml
    │   │               │   ├── home_24px.xml
    │   │               │   ├── home_filled_24px.xml
    │   │               │   ├── ic_launcher_foreground.xml
    │   │               │   ├── outline_dark_mode_24.xml
    │   │               │   └── outline_light_mode_24.xml
    │   │               ├── mipmap-anydpi-v26/
    │   │               │   ├── ic_launcher.xml
    │   │               │   └── ic_launcher_round.xml
    │   │               ├── mipmap-hdpi/
    │   │               │   ├── ic_launcher.webp
    │   │               │   └── ic_launcher_round.webp
    │   │               ├── mipmap-mdpi/
    │   │               │   ├── ic_launcher.webp
    │   │               │   └── ic_launcher_round.webp
    │   │               ├── mipmap-xhdpi/
    │   │               │   ├── ic_launcher.webp
    │   │               │   └── ic_launcher_round.webp
    │   │               ├── mipmap-xxhdpi/
    │   │               │   ├── ic_launcher.webp
    │   │               │   └── ic_launcher_round.webp
    │   │               ├── mipmap-xxxhdpi/
    │   │               │   ├── ic_launcher.webp
    │   │               │   └── ic_launcher_round.webp
    │   │               └── values/
    │   │                   └── ic_launcher_background.xml
    │   ├── database/
    │   │   ├── build.gradle.kts
    │   │   ├── consumer-rules.pro
    │   │   ├── proguard-rules.pro
    │   │   └── src/
    │   │       ├── androidTest/
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── core/
    │   │       │                   └── database/
    │   │       │                       └── ExampleInstrumentedTest.kt
    │   │       ├── main/
    │   │       │   ├── AndroidManifest.xml
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── core/
    │   │       │                   └── database/
    │   │       │                       ├── GrowwDatabase.kt
    │   │       │                       ├── ToDomain.kt
    │   │       │                       ├── dao/
    │   │       │                       │   ├── GrowwDao.kt
    │   │       │                       │   └── RemoteKeysDao.kt
    │   │       │                       ├── di/
    │   │       │                       │   └── DatabaseModule.kt
    │   │       │                       └── entity/
    │   │       │                           ├── DailyPrice.kt
    │   │       │                           ├── RemoteKeys.kt
    │   │       │                           └── Stock.kt
    │   │       └── test/
    │   │           └── java/
    │   │               └── com/
    │   │                   └── reyaz/
    │   │                       └── core/
    │   │                           └── database/
    │   │                               └── ExampleUnitTest.kt
    │   ├── network/
    │   │   ├── build.gradle.kts
    │   │   ├── consumer-rules.pro
    │   │   ├── proguard-rules.pro
    │   │   └── src/
    │   │       ├── androidTest/
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── core/
    │   │       │                   └── network/
    │   │       │                       └── ExampleInstrumentedTest.kt
    │   │       ├── main/
    │   │       │   ├── AndroidManifest.xml
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── core/
    │   │       │                   └── network/
    │   │       │                       ├── data/
    │   │       │                       │   ├── paging/
    │   │       │                       │   │   └── StocksRemoteRepository.kt
    │   │       │                       │   └── remote/
    │   │       │                       │       ├── api/
    │   │       │                       │       │   ├── AlphaVantageApiService.kt
    │   │       │                       │       │   └── OverviewApiService.kt
    │   │       │                       │       └── dto/
    │   │       │                       │           ├── AlphaVantageResponse.kt
    │   │       │                       │           ├── CompanyDetailsResponse.kt
    │   │       │                       │           ├── CompanyOverviewResponse.kt
    │   │       │                       │           ├── GainerLoserResponse.kt
    │   │       │                       │           ├── GlobalQuoteResponse.kt
    │   │       │                       │           ├── StockDto.kt
    │   │       │                       │           └── VintageOverviewResponse.kt
    │   │       │                       ├── di/
    │   │       │                       │   └── NetworkModule.kt
    │   │       │                       └── domain/
    │   │       │                           ├── MonthlyAdjusted.kt
    │   │       │                           └── StockData.kt
    │   │       └── test/
    │   │           └── java/
    │   │               └── com/
    │   │                   └── reyaz/
    │   │                       └── core/
    │   │                           └── network/
    │   │                               └── ExampleUnitTest.kt
    │   └── ui/
    │       ├── build.gradle.kts
    │       ├── consumer-rules.pro
    │       ├── proguard-rules.pro
    │       └── src/
    │           ├── androidTest/
    │           │   └── java/
    │           │       └── com/
    │           │           └── reyaz/
    │           │               └── core/
    │           │                   └── ui/
    │           │                       └── ExampleInstrumentedTest.kt
    │           ├── main/
    │           │   ├── AndroidManifest.xml
    │           │   └── java/
    │           │       └── com/
    │           │           └── reyaz/
    │           │               └── core/
    │           │                   └── ui/
    │           │                       ├── components/
    │           │                       │   ├── BottomNavigationMenu.kt
    │           │                       │   ├── CustomCard.kt
    │           │                       │   ├── DottedUnderlineText.kt
    │           │                       │   ├── GrowwTopAppBar.kt
    │           │                       │   └── StockItem.kt
    │           │                       └── theme/
    │           │                           ├── Color.kt
    │           │                           ├── Theme.kt
    │           │                           └── Type.kt
    │           └── test/
    │               └── java/
    │                   └── com/
    │                       └── reyaz/
    │                           └── core/
    │                               └── ui/
    │                                   └── ExampleUnitTest.kt
    ├── feature/
    │   ├── home/
    │   │   ├── build.gradle.kts
    │   │   ├── consumer-rules.pro
    │   │   ├── proguard-rules.pro
    │   │   └── src/
    │   │       ├── androidTest/
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── feature/
    │   │       │                   └── home/
    │   │       │                       └── ExampleInstrumentedTest.kt
    │   │       ├── main/
    │   │       │   ├── AndroidManifest.xml
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── feature/
    │   │       │                   └── home/
    │   │       │                       ├── data/
    │   │       │                       │   └── repository/
    │   │       │                       │       └── HomeRepository.kt
    │   │       │                       ├── di/
    │   │       │                       │   └── HomeModule.kt
    │   │       │                       ├── domain/
    │   │       │                       │   └── repository/
    │   │       │                       │       └── HomeRepository.kt
    │   │       │                       └── presentation/
    │   │       │                           ├── HomeScreen.kt
    │   │       │                           ├── HomeUiState.kt
    │   │       │                           ├── HomeViewModel.kt
    │   │       │                           └── components/
    │   │       │                               ├── StockItem.kt
    │   │       │                               └── StockListContainer.kt
    │   │       └── test/
    │   │           └── java/
    │   │               └── com/
    │   │                   └── reyaz/
    │   │                       └── feature/
    │   │                           └── home/
    │   │                               └── ExampleUnitTest.kt
    │   ├── product_detail/
    │   │   ├── build.gradle.kts
    │   │   ├── consumer-rules.pro
    │   │   ├── proguard-rules.pro
    │   │   └── src/
    │   │       ├── androidTest/
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── feature/
    │   │       │                   └── product_detail/
    │   │       │                       └── ExampleInstrumentedTest.kt
    │   │       ├── main/
    │   │       │   ├── AndroidManifest.xml
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── feature/
    │   │       │                   └── product_detail/
    │   │       │                       ├── StockDetailRepository.kt
    │   │       │                       ├── data/
    │   │       │                       │   ├── ChartDataConverter.kt
    │   │       │                       │   ├── StockDataParser.kt
    │   │       │                       │   ├── StockRepository.kt
    │   │       │                       │   └── StockRepositoryImpl.kt
    │   │       │                       ├── di/
    │   │       │                       │   └── StockDetailModule.kt
    │   │       │                       ├── domain/
    │   │       │                       │   ├── StockDetail.kt
    │   │       │                       │   └── StockDetailRepository.kt
    │   │       │                       └── presentation/
    │   │       │                           ├── StockChartScreen.kt
    │   │       │                           ├── StockDetailEvent.kt
    │   │       │                           ├── StockDetailUiState.kt
    │   │       │                           ├── StockDetailViewModel.kt
    │   │       │                           └── components/
    │   │       │                               ├── PeriodSelector.kt
    │   │       │                               └── StockChart.kt
    │   │       └── test/
    │   │           └── java/
    │   │               └── com/
    │   │                   └── reyaz/
    │   │                       └── feature/
    │   │                           └── product_detail/
    │   │                               └── ExampleUnitTest.kt
    │   ├── product_list/
    │   │   ├── build.gradle.kts
    │   │   ├── consumer-rules.pro
    │   │   ├── proguard-rules.pro
    │   │   └── src/
    │   │       ├── androidTest/
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── feature/
    │   │       │                   └── product_list/
    │   │       │                       └── ExampleInstrumentedTest.kt
    │   │       ├── main/
    │   │       │   ├── AndroidManifest.xml
    │   │       │   └── java/
    │   │       │       └── com/
    │   │       │           └── reyaz/
    │   │       │               └── feature/
    │   │       │                   └── product_list/
    │   │       │                       ├── StockListUiState.kt
    │   │       │                       ├── data/
    │   │       │                       │   └── repository/
    │   │       │                       │       └── StockListRepositoryImpl.kt
    │   │       │                       ├── di/
    │   │       │                       │   └── StockListModule.kt
    │   │       │                       ├── domain/
    │   │       │                       │   └── repository/
    │   │       │                       │       └── StockListRepository.kt
    │   │       │                       └── presentation/
    │   │       │                           ├── StockListScreen.kt
    │   │       │                           └── StockListViewModel.kt
    │   │       └── test/
    │   │           └── java/
    │   │               └── com/
    │   │                   └── reyaz/
    │   │                       └── feature/
    │   │                           └── product_list/
    │   │                               └── ExampleUnitTest.kt
    │   └── watchlist/
    │       ├── build.gradle.kts
    │       ├── consumer-rules.pro
    │       ├── proguard-rules.pro
    │       └── src/
    │           ├── androidTest/
    │           │   └── java/
    │           │       └── com/
    │           │           └── reyaz/
    │           │               └── watchlist/
    │           │                   └── ExampleInstrumentedTest.kt
    │           ├── main/
    │           │   └── AndroidManifest.xml
    │           └── test/
    │               └── java/
    │                   └── com/
    │                       └── reyaz/
    │                           └── watchlist/
    │                               └── ExampleUnitTest.kt
    └── gradle/
        └── libs.versions.toml

```
