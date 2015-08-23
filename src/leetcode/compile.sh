ret=0

if [ $1 == 'c' ]; then
    g++ -ggdb -std=c++0x -o $2 $2.cpp
    ret=$?
    if [[ $ret != 0 ]]; then
        exit $ret
    fi
    cmd="./$2"
    $cmd
    exit $?
fi

if [ $1 == 'java' ]; then
    javac -g -classpath . $2.java
    ret=$?
    if [[ $ret != 0 ]]; then
        exit $ret
    fi
    java -ea $2
    exit $?
fi
