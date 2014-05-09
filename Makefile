# -*- Makefile -*-

lib_TARGETS = champion

champion-srcs = Interface.java Prologin.java

# Actually needed for `make tar`
champion-srcs += Agent.java AggressiveStrategy.java AloneStrategy.java BalancedStrategy.java
champion-srcs += BuildTowerObjective.java DefendBaseObjective.java DefendObjective.java
champion-srcs += HomeAgent.java KillThemAllObjective.java Logger.java
champion-srcs += Map.java Mothership.java NoopObjective.java Objective.java OccupyObjective.java
champion-srcs += Phase.java Sorcerers.java Sorcerers.java
champion-srcs += SpatialObjective.java Strategy.java Tower.java

champion-cxxflags = -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -ggdb3
champion-ldflags = -Wl,-rpath -Wl,$(JAVA_HOME)/jre/lib/amd64/server/ -L$(JAVA_HOME)/jre/lib/amd64/server/ -ljvm

# Evite de toucher a ce qui suit
champion-dists = interface.hh
champion-srcs += interface.cc
include ../includes/rules.mk

run: all
	mkdir -p run/current
	cp champion.so run/current/champion.so
	cp *.class run/current
	(cd run; stechec2-run ./config.yml)
	# rm -f run/*.class