JFLAGS = -d bin
JC = javac

default: classes

classes: 
	mkdir -p bin
	javac -d bin `find src -name "*.java"`

clean:
	$(RM) -Rf bin
