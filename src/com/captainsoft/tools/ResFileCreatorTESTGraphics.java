/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mathias fringes
 */
public class ResFileCreatorTESTGraphics {

    // fields

    private String soundSourceFolder = "snd";

    // constructors

    public ResFileCreatorTESTGraphics() {
        super();

        final String BasePathOrig = "e:/captainsoft/TAD_dev/_working/src_files/";
        final String BasePathDest = "E:/captainsoft/TAD_dev/tad_java/";

        final String sourceFolder = BasePathOrig + "files_source_graphics";
        final String destFolder = BasePathDest + "files_graphics/res";

        ///////

        System.out.println("HERE WE GO!");
        System.out.println("destination folder: " + destFolder);
        //
        System.out.println("compiling vrs files.");
        make_ifc(sourceFolder, destFolder);
        make_intro(sourceFolder, destFolder);
        make_teils(sourceFolder, destFolder);
        make_npc(sourceFolder, destFolder);
        make_fgtIfc(sourceFolder, destFolder);

        System.out.println("compiling srs files.");
        make_monprep_srs(sourceFolder, destFolder);
        make_monattack_srs(sourceFolder, destFolder);
        make_ifc_srs(sourceFolder, destFolder);
        //
        System.out.println("DONE. Thank you and goodbye.");
    }

    // private methods

    private void make_ifc(String sourceFolder, String destFolder) {
        Map<Integer, String> l = new HashMap<Integer, String>();
        l.put(1, "book");
        l.put(3, "actpnl1");
        l.put(4, "actpnl2");
        l.put(5, "actpnl3");
        l.put(6, "actpnl4");
        l.put(12, "fgt_defstd");
        l.put(13, "fgt_hit");
        l.put(14, "fgt_puste");
        l.put(15, "fgt_sel");
        l.put(20, "found");
        l.put(26, "icon_uber");
        l.put(27, "inter_inv");
        l.put(28, "inter_main");
        l.put(30, "inv_skll");
        l.put(31, "inv_weap");
        l.put(32, "mondead");
        l.put(33, "shop");
        l.put(36, "talk");
        l.put(38, "title");
        l.put(39, "fgt_ex");
        l.put(40, "gameoverscreen");
        //
        for (Map.Entry<Integer, String> e : l.entrySet()) {
            e.setValue(e.getValue() + ".png");
        }
        //
        compileVrs(sourceFolder + "/ifc", destFolder, "iface_p", l);
    }

    private void make_intro(String sourceFolder, String destFolder) {
        Map<Integer, String> l = new HashMap<Integer, String>();
        for (int i = 1; i < 26; i++) {
            l.put(i, "intro" + i + ".png");
        }
        //
        compileVrs(sourceFolder + "/intro", destFolder, "intr_p", l);
    }

    private void make_npc(String sourceFolder, String destFolder) {
        Map<Integer, String> l = new HashMap<Integer, String>();
        for (int i = 1; i < 200; i++) {
            if (new File(sourceFolder + "/npc/npc" + i + ".png").exists()) {
                l.put(i, "npc" + i + ".png");
            } else {
                System.out.println("..... skipping npc number " + i);
            }
        }
        //
        compileVrs(sourceFolder + "/npc", destFolder, "npcs_p", l);
    }

    private void make_teils(String sourceFolder, String destFolder) {
        Map<Integer, String> l = new HashMap<Integer, String>();
        //
        l.put(1, "tiles1.png");
        l.put(2, "tiles2.png");
        l.put(3, "tiles3.png");
        l.put(4, "tiles4.png");
        l.put(6, "tiles6.png");
        l.put(9, "tiles9.png");
        l.put(10, "tiles10.png");
        l.put(12, "tiles12.png");
        l.put(13, "tiles13.png");
        l.put(17, "tiles17.png");
        l.put(18, "tiles18.png");
        l.put(19, "tiles19.png");
        l.put(20, "tiles20.png");
        l.put(21, "java_fake_plastic_trees.png");
        l.put(22, "party.png");
        l.put(25, "items_high_noborder.png");
        //
        compileVrs(sourceFolder + "/", destFolder, "tiles_p", l);
    }

    private void make_fgtIfc(String sourceFolder, String destFolder) {
        Map<Integer, String> l = new HashMap<Integer, String>();
        for (int i = 1; i < 8; i++) {
            l.put(i, "intfgt" + i + ".png");
        }
        l.put(99, "intfgt_99.png");
        //
        compileVrs(sourceFolder + "/fgtint", destFolder, "fgtscr_p", l);
    }

    private void make_monprep_srs(String sourceFolder, String destFolder) {
        Map<Integer, String> l = new HashMap<Integer, String>();
        for (int i = 1; i < 10; i++) {
            l.put(i, "prepare" + i);
        }
        l.remove(4);
        l.put(99, "prepare99");
        //
        for (Map.Entry<Integer, String> e : l.entrySet()) {
            e.setValue(e.getValue() + ".wav");
        }
        //
        compileSrs(sourceFolder + "/" + soundSourceFolder, destFolder, "monprep", l);
    }

    private void make_monattack_srs(String sourceFolder, String destFolder) {
        Map<Integer, String> l = new HashMap<Integer, String>();
        for (int i = 3; i < 10; i++) {
            l.put(i, "attack" + i);
        }
        l.remove(4);
        l.put(99, "attack99");
        //
        for (Map.Entry<Integer, String> e : l.entrySet()) {
            e.setValue(e.getValue() + ".wav");
        }
        //
        compileSrs(sourceFolder + "/" + soundSourceFolder, destFolder, "monatt", l);
    }

    private void make_ifc_srs(String sourceFolder, String destFolder) {
        Map<Integer, String> l = new HashMap<Integer, String>();
        l.put(1, "1dead");
        l.put(2, "1gong");
        l.put(3, "1special1");
        l.put(4, "1special2");
        l.put(5, "1special3");
        l.put(6, "1special4");
        l.put(7, "1special5");
        l.put(8, "1special6");
        l.put(9, "1special7");
        l.put(10, "1special8");
        l.put(11, "1specials");
        l.put(12, "1start");
        l.put(13, "1win");
        l.put(14, "book");
        l.put(15, "chest");
        l.put(16, "chips");
        l.put(17, "dead");
        l.put(18, "id");
        l.put(19, "switch");
        l.put(20, "telep");
        l.put(21, "woncp");
        l.put(22, "slurp");
        l.put(23, "upgrade");
        //
        for (Map.Entry<Integer, String> e : l.entrySet()) {
            e.setValue(e.getValue() + ".wav");
        }
        //
        compileSrs(sourceFolder + "/" + soundSourceFolder, destFolder, "iface", l);
    }

    // --------------------------------------------------------------------------------------------------

    private void compileVrs(String sourceFolder, String destFolder, String name, Map<Integer, String> list) {
        compileFile(sourceFolder, destFolder, name, list, "vrs");
    }

    private void compileSrs(String sourceFolder, String destFolder, String name, Map<Integer, String> list) {
        compileFile(sourceFolder, destFolder, name, list, "srs");
    }

    private void compileFile(String sourceFolder, String destFolder, String name, Map<Integer, String> list, String ext) {
        VbResFileCompiler rc = new VbResFileCompiler(destFolder + "/" + name + "." + ext);
        System.out.println("--------------------------------------");
        System.out.println("Making file: " + rc);
        try {
            Map<Integer, String> ifcList = list;
            for (Map.Entry<Integer, String> e : ifcList.entrySet()) {
                String fileName = sourceFolder + "/" + e.getValue();
                System.out.println("....." + e.getValue());
                rc.addFile(e.getKey(), fileName);
            }
            System.out.println("Compiling file...." + rc);
            rc.compileResourceFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done with res file: " + rc);
        System.out.println("######################################");
    }

    // main methods

    public static void main(String[] args) {
        new ResFileCreatorTESTGraphics();
    }

}
