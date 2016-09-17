# IlegraTest - Installation Instructions

## Requirements

* Java Development Kit (JDK) - http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html 

## Installation process

Checkout GIT project
```bash
$ git clone http://github.com/selatotal/IlegraTest.git
```


Go to IlegraTest directory and make source code
```bash
$ cd IlegraTest
$ make
```
This installation process uses Makefile to compile code, but you can use your favorite IDE if you prefer. In this case, import Java classes in src dir in your IDE and compile. 

Execute program
```bash
$ ./run.sh
```

You can set HOMEPATH environment variable if you want to change input files dir. If you don`t, HOMEPATH will have the value homepath
