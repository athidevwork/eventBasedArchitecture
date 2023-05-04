docker build -t eba/event .
docker run -d -p 8083:8083 --name eba_event eba/event:latest
