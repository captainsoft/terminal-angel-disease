/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.tools;

import java.util.Map;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.loader.vb.VbItemRepository;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.tools.patch.de.ItemPatchGerman;
import com.captainsoft.tools.patch.en.ItemPatchEnglish;

/**
 * Patch names for items. Runs this and it will do it for German and English
 * item names.
 *
 * @author
 */
public final class ItemPatcher {

    // fields

    private final VbItemRepository itemReps;

    // constructors

    public ItemPatcher() {
        super();
        itemReps = new VbItemRepository(null);
        itemReps.load();
    }

    // public

    private void patch(Map<String, String> data) {
        for (int i = 1; i < VbItemRepository.MAX; i++) {
            Item item = itemReps.item(i);
            String newName = data.get(item.id() + "");

            if (newName != null) {
                item.name(newName);
                itemReps.save(item);
                System.out.println("Patching " + item.id() + ": " + item.name());
            }
        }
    }

    public void showPatchStructure() {
        for (int i = 1; i < VbItemRepository.MAX; i++) {
            Item item = itemReps.item(i);
            String name = item.name().replace("\"", "\\\"");
            System.out.println("p.put(\"" + i + "\", \"" + name + "\");");
        }
    }

    // main

    public static void main(String[] args) {
        ItemPatcher ip = new ItemPatcher();

        TadLang.toGerman();
        ip.patch(new ItemPatchGerman().data());

        TadLang.toEnglish();
        ip.patch(new ItemPatchEnglish().data());

        System.out.println("Patching Items is done! Goodbye ;)");
    }

}
