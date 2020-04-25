/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui.boxes.inv;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.captainsoft.TADr.gui.boxes.common.ItemBox;
import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.party.Inventory;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.CString;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The inventory and skill/equiq background box. Also available: The four quick select party
 * buttons on the bottom.
 *
 * @author mathias fringes
 */
public final class InventoryBox extends UiBoxContainer {

    // fields

    private static final Color colWgtOver = new Color(255, 0, 0);
    private static final Color colWgtDef = new Color(154, 176, 199);

    private final ItemBox[] inventoryItemBoxes = new ItemBox[20];
    private final TextBox nameBox;

    public ArrayList<ItemBox> allItemBoxes = new ArrayList<ItemBox>();
    public EquiqBox equiqBox;
    public QuickMemberBox[] quickMemberBoxes;
    public SkillBox skillBox;
    public String carryString;
    public TextBox weightBox;
    public UiBox invViewToggleBox;
    public UiBoxContainer inventoryCtrlBox;

    // constructors

    public InventoryBox(ImageLoader imageLoader, int height) {
        super();
        name = "inventory-box";
        Surface background = imageLoader.load("ifc", 27);
        initBackground(background);
        visible(false);
        pos(3, 3);
        this.height = height;
        //
        // name 
        nameBox = new TextBox();
        nameBox.font = new Font(Fonts.Times, Font.BOLD, 18);
        nameBox.oneLine = true;
        nameBox.alignLeft();
        nameBox.set(16, 10, 190, 32);
        add(nameBox);
        //
        // skill
        skillBox = new SkillBox();
        skillBox.initBackground(imageLoader.load("ifc", 30));
        skillBox.visible(false);
        skillBox.debugColor = Color.RED;
        skillBox.pos(16, 48);
        add(skillBox);
        //
        // toggle
        invViewToggleBox = new UiBox(149, 229, 52, 21);
        add(invViewToggleBox);
        //
        // inventory boxes
        // item boxes (bag)
        int x = 16;
        int y = 256;
        for (int i = 0; i < 20; i++) {
            ItemBox itemBox = new ItemBox();
            itemBox.inventoryIndex = i;
            itemBox.name = "ItemBox (bag) " + i;
            itemBox.pos(x, y);
            inventoryItemBoxes[i] = itemBox;
            add(itemBox);
            allItemBoxes.add(itemBox);
            x += 48;
            if (i % 4 == 3) {
                y += 48;
                x = 16;
            }
        }
        //
        // equiq
        equiqBox = new EquiqBox();
        add(equiqBox);
        allItemBoxes.addAll(equiqBox.allItemBoxes);
        //
        // weight box
        weightBox = new TextBox();
        weightBox.oneLine = true;
        weightBox.font = new Font(Fonts.Times, Font.BOLD, 12);
        weightBox.alignRight();
        weightBox.set(50, 495, 152, 16);
        weightBox.stampBackground(background);
        add(weightBox);
        //
        // switch to overview
        inventoryCtrlBox = new UiBoxContainer();
        inventoryCtrlBox.initBackground(imageLoader.load("ifc", 26));
        inventoryCtrlBox.pos(-3, 527);
        inventoryCtrlBox.name = "inventoryCtrlBox";
        add(inventoryCtrlBox);
        //
        // quick member switch boxes
        quickMemberBoxes = new QuickMemberBox[4];
        for (int i = 0; i < 4; i++) {
            quickMemberBoxes[i] = new QuickMemberBox();
            quickMemberBoxes[i].parent(inventoryCtrlBox);
            quickMemberBoxes[i].pos(i * 29 - (i > 1 ? 1 : 0), 0);
            quickMemberBoxes[i].stampBackground(inventoryCtrlBox.background());
        }
    }

    // public

    public void member(PartyMember member) {
        Inventory inventory = member.inventory();
        nameBox.text = member.name();
        nameBox.color(member.color());
        for (int i = 0; i < inventoryItemBoxes.length; i++) {
            ItemBox itemBox = inventoryItemBoxes[i];
            Item item = inventory.item(i);
            itemBox.item(item);
        }
        equiqBox.member(member);
        skillBox.updateValues(member);
    }

    public void toggleSkillEquiq() {
        skillBox.visible(!skillBox.visible());
        equiqBox.visible(!equiqBox.visible());
    }

    public void updateWeigthBox(int wgt, int max) {
        weightBox.color((wgt <= max) ? colWgtDef : colWgtOver);
        weightBox.text = new CString(carryString, wgt, max).eval();
    }

    public ItemBox findItemBox(ItemPosition itemPosition) {
        for (ItemBox b : allItemBoxes) {
            if (b.inventoryIndex == itemPosition.index) {
                return b;
            }
        }
        return null;
    }

    public ItemBox updateItemBox(ItemPosition itemPosition, Item item) {
        ItemBox box = findItemBox(itemPosition);
        box.item(item);
        return box;
    }

}
