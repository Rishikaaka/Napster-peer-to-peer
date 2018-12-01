# Java Compiler
JC = javac
# Suffixes of input and output files
.SUFFIXES: .java .class
# Compile statement
.java.class:
	$(JC) $(JFLAGS) $*.java
# Classes declaration
CLASSES = \
	CentralServer.java MainThread.java
# main command for Makefile
default: classes
# Classes initialization
classes: $(CLASSES:.java=.class)
# clean class files
clean:
	$(RM) *.class
