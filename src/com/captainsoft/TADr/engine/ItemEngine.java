/*
 * Copyright Captainsoft 2010-2012.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine;

import static com.captainsoft.TADr.model.item.ItemType.BodyArmor;
import static com.captainsoft.TADr.model.item.ItemType.CurrentFunPoints;
import static com.captainsoft.TADr.model.item.ItemType.FeetArmor;
import static com.captainsoft.TADr.model.item.ItemType.HeadArmor;
import static com.captainsoft.TADr.model.item.ItemType.Protection;
import static com.captainsoft.TADr.model.item.ItemType.Weapon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.captainsoft.TADr.model.i18n.TadTranslator;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.item.ItemType;
import com.captainsoft.TADr.model.party.Inventory;
import com.captainsoft.TADr.model.party.InventoryPlace;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.i18n.TrKey;

/**
 * Item Engine.
 *
 * @author mathias fringes
 */
public final class ItemEngine {

    // fields

    private final TadTranslator translator;

    // constructors

    public ItemEngine() {
        translator = TadRepo.inst().Trans();
    }

    // public methods

    public String sayItemInfoString(Item item) {
        ItemType type = item.type();
        String key = "item.info.type." + type.id();
        String text = "";
        if (translator.contains(key)) {
            text = itemInfoString(item, key);
        } else {
            text = item.name();
        }
        return text;
    }

    public String sayItemTakeString(Item item) {
        ItemType type = item.type();
        String key = "item.info.type." + type.id();
        String text;
        if (translator.contains(key)) {
            text = itemInfoString(item, key);
        } else {
            text = "." + translator.word("ifc.itemTaken", item.name());
        }
        return text;
    }

    public TrKey tryDropItem(ItemPosition position, Item item) {
        if (position.place == InventoryPlace.Bag) {
            return null;
        }
        int nr = position.member.nr();
        boolean canDrop = canDropHere(position, item);
        if (!canDrop) {
            return new TrKey("item.take.wrongPlace", item.name()).variant(nr);
        }
        canDrop = item.canWear(position.member.nr());
        return (canDrop ? (null) : new TrKey("item.take.cannotUseIt", item.name()).variant(nr));
    }

    public ItemPosition findSuitablePos(List<PartyMember> members, Item item) {
        //
        for (PartyMember member : members) {
            Inventory inventory = member.inventory();
            for (InventoryPlace ip : InventoryPlace.allSpecialPlaces) {
                if (inventory.empty(ip.index())) {
                    ItemPosition p = new ItemPosition(member, ip.index());
                    if (tryDropItem(p, item) == null) {
                        return p;
                    }
                }
            }
        }
        //
        for (PartyMember member : members) {
            Inventory inventory = member.inventory();
            for (Integer pos : Inventory.bagPositions) {
                if (inventory.empty(pos)) {
                    return new ItemPosition(member, pos);
                }
            }
        }
        //
        return null;
    }

    // private

    private boolean canDropHere(ItemPosition pos, Item item) {
        InventoryPlace place = pos.place;
        if (place == InventoryPlace.Bag) {
            return true;
        }
        ItemType type = item.type();
        if (place.isBelt() && !canDropForBelt(type)) {
            return false;
        }
        if (place == InventoryPlace.Head && type != HeadArmor) {
            return false;
        }
        if (place == InventoryPlace.Body && type != BodyArmor) {
            return false;
        }
        if (place == InventoryPlace.Feet && type != FeetArmor) {
            return false;
        }
        if (place == InventoryPlace.Weapon && type != Weapon) {
            return false;
        }
        if (place == InventoryPlace.Protection && type != Protection) {
            return false;
        }
        return true;
    }

    private boolean canDropForBelt(ItemType type) {
        return (type == CurrentFunPoints || type.isFightItem());
    }

    private String itemInfoString(Item item, String key) {
        ItemType type = item.type();
        String text = "";
        Map<String, String> data = new HashMap<String, String>();
        data.put("$itm", item.name());
        data.put("$p", item.value() + "");
        if (type.isArmor() || type == ItemType.Weapon) {
            data.put("$cp", (item.coins() / 2) + "");
            data.put("$suit", item.suitString());
        }
        text = translator.word(key, data);
        return text;
    }

}
