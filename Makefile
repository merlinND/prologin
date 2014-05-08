# -*- Makefile -*-

lib_TARGETS = champion

champion-srcs = Interface.java Prologin.java

# Useless because .class files are compiled by Eclipse?
# champion-srcs += Agent.java DefendTargetObjective.java Map.java
# champion-srcs += Mothership.java Objective.java Phase.java Sorcerers.java Tower.java

champion-cxxflags = -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -ggdb3
champion-ldflags = -Wl,-rpath -Wl,$(JAVA_HOME)/jre/lib/amd64/server/ -L$(JAVA_HOME)/jre/lib/amd64/server/ -ljvm

# Evite de toucher a ce qui suit
champion-dists = interface.hh
champion-srcs += interface.cc
include ../includes/rules.mk

run: all
	cp champion.so run/current/champion.so
	cp *.class run/current
	(cd run; stechec2-run ./config.yml)
	# rm -f run/*.class