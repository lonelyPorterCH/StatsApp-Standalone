# Stats App

Simple WebApp to Manage line charts with a date for the x-axis.
![Overview of the App](/assets/screenshot.png)

## Updating Docker image

### Build new Image

```
./gradlew bootJar
docker build -t statsapp:latest .
docker save statsapp:latest -o statsapp.tar
```

### Upload to Synology

1. Upload tar file to docker (or any other folder)
2. Open Container Manager
3. Images: select statsapp, then Action > Import > Add From File > From this DSM
4. Container: select statsapp, stop, reset, start