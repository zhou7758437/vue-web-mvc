ps -ef | grep vue-web-mvc | grep -v grep | awk '{print "kill -9 "$2}' | sh

nohup java    -jar  vue-web-mvc*.jar &

tail -f nohup.out

exit 0
