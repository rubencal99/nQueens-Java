# Author: Ruben Calderon
# CruzID: 1698961
# Class: 12B/M
# Date: April 22, 2019
# Description: Compiles and archives an executable JAR file of Queens.java. 
# File Name: Makefile

JAVASRC = Queens.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = Queens
CLASSES = Queens.class
JARFILE = Queens
SUBMIT = submit cmps012b-pt.s19 pa2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s19/pa2/rucalder

