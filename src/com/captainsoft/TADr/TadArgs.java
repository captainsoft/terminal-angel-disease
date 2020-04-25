/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr;

import com.captainsoft.TADr.engine.debug.Debugger;
import com.captainsoft.TADr.model.Game;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.utils.Log;
import com.captainsoft.spark.utils.sys.CommandArgLine;
import com.captainsoft.spark.utils.sys.SysUtils;

/**
 * Command line arguments for the TAD.
 * <p>
 * Currently:
 * -v: version information.
 * -p: show java parameters.
 * -d: use debugger.
 * -l: language (le, lg).
 * -g: load game (g1, g2, ... g9).
 * -o: sys log level output.
 * -h: show all available commands!
 *
 * @author mathias fringes
 */
final class TadArgs {

    // fields

    int gameNr = Game.NEW_GAME_NR;

    // constructors

    public TadArgs() {
        super();
    }

    // public

    public void process(String[] args) {

        final CommandArgLine commandLine = new CommandArgLine();

        //
        // version
        commandLine.add("v", "display version information", new Command() {

            public void execute() {
                System.out.println("Captainsoft's The Terminal Angel Disease");
                System.out.println("Version: " + TAD.Version);
                System.out.println("Type: " + TAD.Type);
                System.out.println("(c) " + TAD.Copyright + " Captainsoft.");
                System.out.println();
                System.exit(0);
            }
        });

        //
        // properties
        commandLine.add("p", "show java parameters", new Command() {

            public void execute() {
                for (String p : SysUtils.getParameters()) {
                    System.out.println(p);
                }

                System.exit(0);
            }
        });

        //
        // debugger
        commandLine.add("d", "use debugger", new Command() {

            public void execute() {
                Debugger.Inst.switchOn();
            }
        });

        //
        // language
        commandLine.add("l", "set language", new Command() {

            public void execute() {
                String lang = commandLine.stringAt("l");
                if (lang.equals("g")) {
                    TadLang.toGerman();
                } else if (lang.equals("e")) {
                    TadLang.toEnglish();
                }
            }
        });

        //
        // game number
        commandLine.add("g", "load game number", new Command() {

            public void execute() {
                gameNr = commandLine.intAt("g", gameNr);
            }
        });

        //
        // use system out for debug messages
        commandLine.add("o", "level of sys out", new Command() {

            public void execute() {
                Log.enabled = true;
            }
        });

        commandLine.parse(args);
        commandLine.execute();
    }

}