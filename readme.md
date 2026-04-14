# Stats App

Simple WebApp to Manage line charts with a date for the x-axis.
![Overview of the App](/assets/screenshot.png)

## Updating Docker image

```
./gradlew bootJar
docker build -t statsapp:latest .
docker save statsapp:latest -o statsapp.tar
```