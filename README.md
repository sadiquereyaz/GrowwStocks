
# GrowwStocks - Stock Market App

A modern stock market application built for the Groww assignment, showcasing Android development best practices with Jetpack Compose, Clean Architecture, and modern Android libraries.

## Screenshots

| Home Screen | Stock List | Stock Detail |
|-------------|------------|--------------|
|![image](https://github.com/user-attachments/assets/8f51a6a2-f4cb-4309-b420-f4046ceea411) |![image](https://github.com/user-attachments/assets/73123deb-7873-455c-9485-43d54858ad7b) | ![image](https://github.com/user-attachments/assets/8442dd93-f0b8-420d-b1d3-2345d60494ac)
|![image](https://github.com/user-attachments/assets/dd69318a-0b9d-4a0d-a553-c0f0bc16f1f9) |![image](https://github.com/user-attachments/assets/d0355c11-c8d1-400b-b987-86ba4cecb217) | ![image](https://github.com/user-attachments/assets/465ace57-d460-4071-bc24-eee8c5613564)

## Features

- **Stock Listings**: View top gainers and losers in the market
- **Stock Details**: Detailed stock charts with historical data
- **Watchlist**: Track your favorite stocks (coming soon)
- **Dark/Light Theme**: Support for system theme and manual selection
- **Search Functionality**: Find stocks quickly
- **Pagination**: Efficient loading of stock data
- **Splash Screen**: Branded launch experience

## Tech Stack

- **Jetpack Compose** for declarative UI
- **Paging 3** for efficient data loading
- **YCharts** for interactive stock charts
- **Koin** for dependency injection
- **Room Database** for local data persistence
- **DataStore** for preferences storage
- **Type-Safe** navigation using `@kotlinx-Serializable` library
- **Retrofit** for network operations
- **Clean Architecture** with feature modules

## Multi-Modular Architecture

The app follows Clean Architecture principles with clear separation of concerns:

```
app/ (Presentation Layer)
├── di/
├── navigation/
└── app_bar/

core/ (Domain Layer)
├── common/
├── database/
├── network/
└── ui/

feature/ (Feature Modules)
├── home/
├── product_detail/
├── product_list/
└── watchlist/
```

## API Integration

The app integrates with two financial APIs:

1. **Alpha Vantage API**
   - Used for: Historical stock price data and time series
   - Base URL: `https://www.alphavantage.co/`
   - Key Features:
     - Daily/Weekly/Monthly adjusted prices
     - Time series data for charts
     - Global stock quotes

2. **Financial Modeling Prep API**
   - Used for: Company fundamentals and overview
   - Base URL: `https://financialmodelingprep.com/`
   - Key Features:
     - Company profiles
     - Financial ratios
     - Key metrics and valuations

## Getting Started

### Prerequisites

- Android Studio Giraffe or later
- Android SDK 33+
- Kotlin 1.9.0+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/sadiquereyaz/growwstocks.git
   ```
2. Open the project in Android Studio
3. Build and run the app

### Configuration

To use the Alpha Vantage API:

1. Get an API key from [Alpha Vantage](https://www.alphavantage.co/)
2. Add it to your `local.properties` file:
   ```kotlin
   API Configuration
   The app requires API keys for both services. Add them to your gradle.properties file:
   
    ALPHA_VINTAGE_API_KEY="UXACHVZSJ7HMDYSE"
    ALPHA_VINTAGE_BASE_URL="https://www.alphavantage.co/"

    OVERVIEW_API_KEY="mvJ7qVVjPbe4M0J4bXTVr8QOIiXDmMtH"
    OVERVIEW_BASE_URL="https://financialmodelingprep.com/"

```
```
## Implementation Details

### Key Components

- **Theme Management**: Uses DataStore to persist user theme preferences
- **Pagination**: Implements Paging 3 with Room and network integration
- **Charting**: YCharts library for interactive stock price visualization
- **Navigation**: Type-safe navigation with Compose Navigation
- **Error Handling**: Resource wrapper for consistent state management

### Challenges & Solutions

1. **Stock Chart Performance**:
   - Implemented efficient data conversion from API responses
   - Used YCharts for smooth rendering of large datasets

2. **Theme Persistence**:
   - Created a ThemeRepository with DataStore
   - Integrated with system dark mode settings

3. **API Rate Limits**:
   - Implemented caching with Room database
   - Added error states and retry mechanisms

## Future Improvements

- [ ] Complete watchlist functionality
- [ ] Add portfolio tracking
- [ ] Implement stock news integration
- [ ] Add more chart customization options
- [ ] Improve error handling and empty states

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
