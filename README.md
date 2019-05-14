# Assignment-01

## Requirements
- Use the latest recommendations from Android Community for
architecture approach and libraries.
- MinSDK Version is 21
- Keep an eye on performance
- Application should be unit tested and instrumentation tested
- Upload the code in Github
- Provide a list of recommendations for future improvements

## The minimum viable product
- Application should have screen which displays albums list sorted by title
- Albums should be persisted for offline viewing

## Application architecture and technical details
- Using MVP architecture
- Uses Kotlin as the programming language
- Uses Retrofit for retrieving data from the webservice
- Uses Repository Pattern for transparent access of data to the view layer
- Adheres to software design principles like 
  - Separation of Concerns
  - DRY(Do not repeat yourself)
  - Dependency Injection

## Recommendations for future improvements

### Security recommendations
- Using ProGaurd for enhanced security by obfuscating the code to protect the app against reverse engineering of the application and reducing the app distributable binary size
- Showing images for the albums received from the network and cached on the device
- Data security using encryption
- Data security using server side authentication
- Data security using client side authentication for e.g. pattern lock, thumb impression lock

### Technical recommendations
- Albums list should be paginated
- Implementing Sync operations using WorkManager

### UX recommendations
- Using live data to bind the DB to the UI. Changing data in the DB will update the UI seamlessly

