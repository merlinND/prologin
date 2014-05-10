prologin-pipercent
==================

## Context

PiPercent is intended to be a multi-agent artifical intelligence: intelligence is distributed into many agents, each having its own objectives. Objectives can be easily added depending on the rules of the game.

For this year's game's rules, we had objectives corresponding to occupying a position and to defending a base, as well as attacking enemies' base.

The code was intended to be fairly generic, and even though many features are still left TODO, it proved easy to add new strategies. The strategy that was actually used for the Prologin contest was created about 30 minutes before the deadline, based on the many tools coded beforehand.

## Usage

This AI is made to be used with `stechec2`, [an open-source tool written by the Prologin team](https://bitbucket.org/prologin/stechec2). The rules of the game are explained [here](http://www.prologin.org/files/archives/2014/finale/sujet/tours-de-magie.pdf) (French) and the corresponding code to run a `stechec2` server will be published [there](https://bitbucket.org/prologin/prologin2014).

The `make` command will generate a `champion.so` lib file. Adapt the `run/config.yml` file to choose which champion to use for the next game. Keep in mind that the `champion.so` file must be accompagnied by all the `.class` files in order to run properly.

## Note

I wanted to sort out the files in tidy packages, but it was hard to do so while using the provided Makefile.
