# CHAT me — Android trial source

This is an early native Android UI prototype for the CHAT me concept.

## Included
- Welcome, registration, login, email-verification and pending-approval screens
- Chat list and sample conversation
- Voice/video call preview screens
- Settings and STEV FOUNDATION contact branding

## Important limitations
This is a **UI trial**, not a production messaging app. Registration, email verification, foundation approval, message delivery, push notifications, and live voice/video calls are placeholders and are not connected to a backend. Live calls require internet. Do not use real passwords or personal data in this demo.

## Build
Open this folder in Android Studio, allow Gradle sync, then choose **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
- Application ID: `com.stevfoundation.chatme`
- Minimum Android version: Android 6.0 (API 23)
- Version: `0.1-trial`

Before production, configure Firebase (or another backend), enforce verified-email and foundation-approval checks on the server, and integrate a secure calling service.


## Cloud build from a phone (GitHub Actions)
1. Create/sign in to a GitHub account using your Google account at https://github.com.
2. Create a new repository named `chat-me-android-trial`.
3. Upload the contents of this ZIP to the repository (including the hidden `.github` folder).
4. Open the repository's **Actions** tab and run **Build CHAT me Android trial APK** (or wait for the push build).
5. When it finishes, open the workflow run and download the `CHAT-me-debug-APK` artifact.
6. Extract the artifact ZIP to get `app-debug.apk`, then install it on your Android phone.

This produces an unsigned-for-release debug APK for testing only. It is not a production-signed release.
