# -*- Makefile -*-

lib_TARGETS = champion

champion-srcs = Interface.java Prologin.java
champion-cxxflags = -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -ggdb3
champion-ldflags = -Wl,-rpath -Wl,$(JAVA_HOME)/jre/lib/amd64/server/ -L$(JAVA_HOME)/jre/lib/amd64/server/ -ljvm

# Evite de toucher a ce qui suit
champion-dists = interface.hh
champion-srcs += interface.cc
include ../includes/rules.mk

run: all
	cp champion.so run/current.so
	cp *.class run/
	(cd run; stechec2-run ./config.yml)
	rm -f run/*.class