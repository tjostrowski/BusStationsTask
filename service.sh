#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Keep the pwd in mind!
# Exampla -jare: RUN="java -jar $DIR/target/bus-1.0-SNAPSHOT.jar"
DATA_FILE=$2
RUN="mvn exec:java -Dexec.mainClass=org.otto.Main -Dexec.args=\"$DATA_FILE\""
NAME="bus-to-station-service"

PIDFILE=/tmp/$NAME.pid
LOGFILE=/tmp/$NAME.log

start() {
  echo "Running: $RUN"
  if [ -f $PIDFILE ] && kill -0 $(cat $PIDFILE); then
    echo 'Service already running' >&2
    return 1
  fi
  local CMD="$RUN &> \"$LOGFILE\" & echo \$!"
  sh -c "$CMD" > "$PIDFILE"
}

stop() {
  if [ ! -f "$PIDFILE" ] || ! kill -0 $(cat "$PIDFILE"); then
    echo 'Service not running' >&2
    return 1
  fi
  kill -15 $(cat "$PIDFILE") && rm -f "$PIDFILE"
}


case "$1" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  block)
    start
    sleep infinity
    ;;
  *)
    echo "Usage: $0 {start|stop|block} DATA_FILE"
esac