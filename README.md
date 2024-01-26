## Firebase Push Notifications Http V1 (Updated for 2024)

[<img width="250" alt="image" src="https://github.com/realityexpander/FirebasePushNotificationsHttpV1/assets/5157474/d379f090-43be-472c-a104-e3fba1908637">]()
[<img width="250" alt="image" src="https://github.com/realityexpander/FirebasePushNotificationsHttpV1/assets/5157474/d039e474-b722-4145-8394-d5d11c3c1a9f">]()

### Demo App
- Send from 1 user to 1 user
- Broadcast from 1 user to all users subscribed on a topic

### IMPORTANT NOTE
- The Ktor Server must be running & setup properly.
- Server Code: https://github.com/realityexpander/KtorFirebasePushNotifications

## Link to video code walkthru:
- Compose Tips - Updated Firebase Push Notifications for Android
  https://youtu.be/GgnYmV4esY8

## Developer Notes
- Notifications only appear when App is in background / sleeping
- When App is in foreground, the `PushNotificationService.kt` receives the messages and no notifications appear

![Profile Views](https://komarev.com/ghpvc/?username=FirebasePushNotificationsHttpV1)
