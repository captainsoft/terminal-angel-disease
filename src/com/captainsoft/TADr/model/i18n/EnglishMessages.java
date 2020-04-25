/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.i18n;

import static com.captainsoft.TADr.model.item.ItemType.BodyArmor;
import static com.captainsoft.TADr.model.item.ItemType.CurrentFunPoints;
import static com.captainsoft.TADr.model.item.ItemType.FeetArmor;
import static com.captainsoft.TADr.model.item.ItemType.FitPointsMax;
import static com.captainsoft.TADr.model.item.ItemType.FoxPointsMax;
import static com.captainsoft.TADr.model.item.ItemType.FunPointsMax;
import static com.captainsoft.TADr.model.item.ItemType.HeadArmor;
import static com.captainsoft.TADr.model.item.ItemType.MagicFireMultiple;
import static com.captainsoft.TADr.model.item.ItemType.MagicFireSingle;
import static com.captainsoft.TADr.model.item.ItemType.MagicMultiple;
import static com.captainsoft.TADr.model.item.ItemType.MagicSingle;
import static com.captainsoft.TADr.model.item.ItemType.MagicWaterMultiple;
import static com.captainsoft.TADr.model.item.ItemType.MagicWaterSingle;
import static com.captainsoft.TADr.model.item.ItemType.Protection;
import static com.captainsoft.TADr.model.item.ItemType.Puzzle;
import static com.captainsoft.TADr.model.item.ItemType.Shaolin;
import static com.captainsoft.TADr.model.item.ItemType.SlimeBomb;
import static com.captainsoft.TADr.model.item.ItemType.Weapon;

import com.captainsoft.TADr.TAD;

/**
 * English gui messages.
 *
 * @author mathias fringes
 */
public final class EnglishMessages implements GuiMessages {

    public String[] data() {
        String[] data = {
                "press.f1.forHelp", "Press F1 for help.",
                "chest.needsKeyNoDietrich.1", "Strange... the lock pick doesn't work here!",
                "chest.needsKey.1", "I cannot open this chest with the best will!",
                "chest.cannotOpen.1", "Puh, this chest I cannot crack yet!",
                "chest.found", "You find",
                "chiliStuff.dontEat.1", "Ugh. That smells disgusting!",
                "chiliStuff.dontEat.2", "I eat almost everything. But this never!!",
                "chiliStuff.dontEat.3", "No " + np + ", I am not eating this even for your sake.",
                "chiliStuff.dontEat.4", "Uh. No, thanks, I really don't like that.",
                "equiq.left.1", "Head",
                "equiq.left.2", "Body",
                "equiq.left.3", "Legs",
                "equiq.right.1", "Weapon",
                "equiq.right.2", "Belt",
                "equiq.right.3", "Protection",
                "item.info.type." + CurrentFunPoints.id(), "$itm! That's a whim for  $p  FunPoints!",
                "item.info.type." + HeadArmor.id(), "$itm! It has  $p  Ar! Wearable by: $suit ($cp Cp).",
                "item.info.type." + BodyArmor.id(), "$itm! It has  $p  Ar! Wearable by: $suit ($cp Cp).",
                "item.info.type." + FeetArmor.id(), "$itm! It has  $p  Ar! Wearable by: $suit ($cp Cp).",
                "item.info.type." + Protection.id(), "$itm! It has  $p  Ar! Wearable by: $suit ($cp Cp).",
                "item.info.type." + Weapon.id(), "$itm! It has  $p  Wp! Usable by: $suit ($cp Cp)!",
                "item.info.type." + MagicSingle.id(), "$itm! Magic that hits one monster for  $p  FunPoints!",
                "item.info.type." + MagicMultiple.id(), "$itm! Magic that hits several monsters for  $p  FunPoints!",
                "item.info.type." + MagicFireSingle.id(), "$itm! Firemagic that hits one monster for  $p  FunPoints!",
                "item.info.type." + MagicFireMultiple.id(), "$itm! Firemagic that hits several monsters for  $p  FunPoints!",
                "item.info.type." + MagicWaterSingle.id(), "$itm! Watermagic that hits one monster for  $p  FunPoints!",
                "item.info.type." + MagicWaterMultiple.id(), "$itm! Watermagic that hits several monsters for  $p  FunPoints!",
                "item.info.type." + Shaolin.id(), "$itm! Powder that lets us fly!",
                "item.info.type." + SlimeBomb.id(), "$itm! That slows down most Monsters!",
                "item.info.type." + Puzzle.id(), "$itm! That looks quite useful.",
                "item.info.type." + FunPointsMax.id(), "$itm! That is infinite jest!",
                "item.info.type." + FitPointsMax.id(), "$itm! That makes you big and strong!",
                "item.info.type." + FoxPointsMax.id(), "$itm! That makes your tired spirits perk!",
                "item.use.cannot.1", "That is not going to work...",
                "item.use.cannot.2", "Doesn't work...",
                "item.use.cannot.3", "Nothing happenend.",
                "item.use.cannot.4", "That does not work this way...",
                "item.use.wrong.1", "Mhmm, odd. Nothing has happened.",
                "item.use.wrong.2", "What do you always thinking up, " + np + " ?",
                "item.use.wrong.3", "There has nothing happenend...",
                "item.use.wrong.4", "So, " + np + " I guess you must rethink this!",
                "item.use.fightOnly.1", "That I'll keep on me for a fight.",
                "item.use.fightOnly.2", "I want it, but I would rather wait yet!",
                "item.use.fightOnly.3", "I can only use this in a battle.",
                "item.use.fightOnly.4", "No, that would be too dangerous yet!",
                "item.use.funPointsMax.1", "You're doing a great job, " + np + "!",
                "item.use.funPointsMax.2", "Smat. Mampf.",
                "item.use.funPointsMax.3", "Mhmm. Get it on boys, lets move onwards!",
                "item.use.funPointsMax.4", "Thank you, " + np + "!",
                "item.use.fitPointsMax.1", "Better than exercises!",
                "item.use.fitPointsMax.2", "I am the most nourishing!",
                "item.use.fitPointsMax.3", "Arm wrestling, " + np + "?",
                "item.use.fitPointsMax.4", "Yes, I am feeling more stronger now.",
                "item.use.foxPointsMax.1", "Now I can even better investigate!",
                "item.use.foxPointsMax.2", "The root out of $1 is $2.",
                "item.use.foxPointsMax.3", "I can see clearer now...",
                "item.use.foxPointsMax.4", "Who is in talking about physics?",
                "item.take.wrongPlace.1", "This is really the wrong place, " + np + ".",
                "item.take.wrongPlace.2", "I should walk around like that? No way!",
                "item.take.wrongPlace.3", "I am not sure if that fits here!",
                "item.take.wrongPlace.4", "No, I really should not wear that there, " + np + ".",
                "item.take.cannotUseIt.1", "But $1 really does not suit me!",
                "item.take.cannotUseIt.2", "No " + np + ", I have a taste for fashion!",
                "item.take.cannotUseIt.3", "This is really not suitable for me, " + np + ".",
                "item.take.cannotUseIt.4", "I really don't understand how to use $1.",
                "inventory.carry", "To carry:  $1 / $2",
                "ifc.itemTaken", "$1 taken.",
                "intro.thisisthestory.1", "This is the story <br> of the city Bunga Terra Tissa Tu.",
                "intro.formerHomeOf.1", "Former setting of the successfull <br> Role Playing Game series \"Deep Deep Dungeon\"",
                "intro.formerHomeOf.2", "...and its hero Justin Chamberlain.",
                "intro.fatHeroesDrinking.1", "But coming with the slack of RPG games in the midst nineties, also \"Deep Deep Dungeon\" was ceased.",
                "intro.fatHeroesDrinking.2", "Today, Bunga Terra Tissa Tu is only a tourist attraction. <br> School classes make a field trip through the old labyrinth, extras from \"Deep Deep Dungeon\" sell cheap souveniers, and the heroes from back in the days meet at the Drinking Hall.",
                "intro.untilThisDay.1", "Business is bad, and life in Bunga Terra Tissa Tu is rather unspectacular.",
                "intro.untilThisDay.2", "Until one day...",
                "intro.monsterDance.1", "Chevy monsters hunt the city again!",
                "intro.monsterDance.2", "Meanwhile, in the Conduct Center of Bunga Terra Tissa Tu...",
                "intro.vhtalk.1", "These monsters kick out every lantern in the city!",
                "intro.vhtalk.2", "And throw empty soda cans onto the streets!",
                "intro.vhtalk.3", "And loot the sweets automatons!",
                "intro.vhtalk.4", "Something has to happen!",
                "intro.vhtalk.5", "Yes Sir!",
                "intro.vhtalk.6", "We are the Wise Guys!",
                "intro.vhtalk.7", "Exactly!",
                "intro.vhtalk.8", "That's why we call someone to help!",
                "intro.vhtalk.9", "Good idea!",
                "intro.vhtalk.10", "But who can solve this problem?",
                "intro.vhtalk.11", "Only " + np + " and " + prt + "!",
                "intro.vhtalk.12", "Of course!",
                "intro.vhtalk.13", "Let's sent them a Will-E-Wisp!",
                "intro.drucing.1", "Detective Summerkamp",
                "intro.drucing.2", "Kurtis the Blob",
                "intro.drucing.3", "King Ole Ozelot",
                "intro.drucing.4", "Professor Zett",
                "intro.irrlicht.1", "A game by Captainsoft",
                "intro.irrlicht.2", "Coded in the evenings and weekends",
                "intro.irrlicht.3", "Graphics are home made",
                "intro.irrlicht.4", "Sound-Effects by DJ Fresh Tzaziki",
                "intro.irrlicht.5", "Music (not included in the game) by Captain Burschi and the Pogo Jumpers",
                "intro.irrlicht.6", "Written and directed by Captainsoft",
                "telep.prisma.funny.1", "Hihi, that tickles!",
                "telep.prisma.funny.2", "Whoooaa... that is fun!",
                "telep.prisma.funny.3", "Wusch....",
                "telep.prisma.funny.4", np + ", how exactly does that work?",
                "fight.learn.weapon.1", "I'm getting better at combat now with  $1  points!",
                "fight.learn.weapon.2", "Beating up makes now more fun with  $1  points!",
                "fight.learn.weapon.3", "I can now go to duel with  $1  points!",
                "fight.learn.weapon.4", "The science of skirmish is developed now with $1  points!",
                "fight.learn.magicItem.2", "These scrolls I can now use with  $1  points!",
                "fight.learn.magicItem.3", "Magic scrolls are now known to me with  $1  points!",
                "fight.learn.magicItem.4", "I can handle scrolls now with  $1  points!",
                "fight.learn.magicItemChem.4", "I have now $! points in the discipline of chemical reactions!",
                "fight.learn.special.1", "I can arrest baddies now with  $1  points!",
                "fight.learn.special.2", "Haha! I hit the  $1  points score in Bump Roll!",
                "fight.learn.special.3", "I just learned  $1  points in going Berserk!",
                "fight.attack.magic.multi.hit", "$1 charms $2, and hits  $3  monsters!",
                "fight.attack.magic.multi.fail", "$1 charms $2, but hit no monsters!",
                "fight.attack.magic.single.hit", "$1 charms $2, and hits for $3 FunPoints!",
                "fight.attack.magic.single.fail", "$1 charms $2, but cannot cause effect!",
                "fight.attack.chem.multi.hit", "$1 mixes $2, and hits  $3  monsters!",
                "fight.attack.chem.multi.fail", "$1 mixes $2, but cannot hit any monster!",
                "fight.attack.chem.single.hit", "$1 mixes $2, and hits for  $3  FunPoints!",
                "fight.attack.chem.single.fail", "$1 mixes $2, but cannot cause effect!",
                "fight.attack.bomb.multi.hit", "$1 throws $2, and hits  $3  monsters!",
                "fight.attack.bomb.multi.fail", "$1 throws $2, but cannot hit any monster!",
                "fight.attack.bomb.single.hit", "$1 throws $2, and hits for  $3  FunPoints!",
                "fight.attack.bomb.single.fail", "$1 throws $2, but cannot cause effect!",
                "fight.attack.metzel.noWeapon", "$1 does not hold a weapon in hand!",
                "fight.attack.metzel.hit", "$1 plunges boldly to the enemy, and hits  $2  monsters!",
                "fight.attack.metzel.fail", "$1 plunges boldly to the enemy, but cannot cause effect!",
                "fight.attack.police.hit", "$1 intervenes rigorously, and arrests  $2  monsters!",
                "fight.attack.police.fail", "$1 intervenes rigorously, but cannot cause effect.",
                "fight.attack.rempel.hit", "$1 bumps onto the enemies, and hits  $2  monsters!",
                "fight.attack.rempel.fail", "$1 bumps onto the enemies, but cannot cause effect.",
                "fight.attack.slime.hit", "$1 lights the slime bomb, and gumms  $2  monsters!",
                "fight.attack.slime.fail", "$1 lights the slime bomb, but cannot gumm any monster!",
                "fight.weapon.attack.hit", "$1 attacks with $2, and scores for  $3  FunPoints!",
                "fight.weapon.attack.miss", "$1 attacks with $2, but hits into the empty!",
                "fight.weapon.attack.fail", "$1 attacks with $2, but cannot steal any FunPoints!",
                "fight.weapon.attack.noWeapon", "$1 does not hold a weapon in hand!",
                "fight.use.shaolin.ok", "$1 sprays the shaolin powder, and the party begins to fly!",
                "fight.use.shaolin.twice", "$1 sprays the shaolin powder, but cannot cause any effect!",
                "fight.use.incrFunPoints", "$1 takes  $2  and acts with more fun!",
                "fight.win", "$1 <br> defeat the monsters and gain <br> $2 <br> Chips!",
                "fight.title.against", "$1 compete against",
                "fight.defaultAttack.hitsWithDefense", "and strikes despite resistance for  $1  FunPoints!",
                "fight.defaultAttack.hits", "and strikes for  $1  FunPoints!",
                "fight.defaultAttack.canDefense", "but is successfully repelled!",
                "fight.defaultAttack.notHitCharm", "but it cannot spoil the fun!",
                "fight.defaultAttack.intoTheVoid", "but hits far off!",
                "fight.gameover", "Due to creative differences, <br> $1 <br> has left the party.",
                "outro.thatWas", "And that was",
                "outro.madeBy", "Imagined and created by",
                "outro.playedBy", "Played by",
                "outro.andPlayedBy", "and",
                "outro.starring", "Starring:",
                "outro.starring.11", "Chili-Tschong Kahn",
                "outro.starring.12", "as the evil master of the dungeon",
                "outro.starring.21", "Fridayguy",
                "outro.starring.22", "as the psychotic helper of the evil master",
                "outro.starring.31", "The Wise Guys",
                "outro.starring.32", "as the three wise guys",
                "outro.starring.41", "Justin Chamberlain",
                "outro.starring.42", "as the hero of the other game",
                "outro.starring.51", "The brother of 'Chuck, die Pflanze'",
                "outro.starring.52", "as the Rhododendron",
                "outro.starring.61", "Jutta the Princess",
                "outro.starring.62", "not really included in the game",
                "outro.alsoStarring", "also starring",
                "outro.alsoStarring.1", "A Traffic Light <br> Big Star <br> Bobby <br> Mister Sparkel <br> Burgomaster Schulze <br> Captain N! <br> The Cavel Guys <br> Chili-Tschong Kahn <br> Commander Blop <br> Tuesdayguy <br> ",
                "outro.alsoStarring.2", "Mister Pani <br> Engineer Ringring <br> Thursdayguy <br> The Emus <br> Eyk, man of Emus <br> Flying Zett <br> Dmasel Honey <br> GIGANTUS <br> Goldie <br> Mister Pizza <br> Grimaldi <br> ",
                "outro.alsoStarring.3", "Hans <br> Mr Fred <br> Lawks <br> Hubert <br> Jeffrey <br> Mister Klaar <br> Kurle Wosch <br> Little Star <br> Marvin <br> Miss Storck <br> Wednesdayguy <br> Monsieur Bradfish <br> ",
                "outro.alsoStarring.4", "Mondayguy <br> General Fu-Huh-Sang <br> Muck, the Magician <br> Nowsed <br> Owsed <br> Renko <br> Mistel Ling <br> Saturdayguy <br> The Scripts <br> Sundayguy <br> Chili-Tschong Kahn <br> ",
                "outro.alsoStarring.5", "Tea Schobber <br> The Friendly Saur <br> TimeShop Clerk <br> Mister Tar <br> Bouncer <br> Wilbur <br> <br> and the <br> Jo-Jo-Jazz Ensemble",
                "outro.cakeguystalk.1", "Do you think they will bring us the cake?",
                "outro.cakeguystalk.2", "Maybe.",
                "outro.cakeguystalk.3", "They should have been back by now.",
                "outro.cakeguystalk.4", "I know.",
                "outro.cakeguystalk.5", "I'm cold.",
                "outro.cakeguystalk.6", "Me too.",
                "outro.cakeguystalk.7", "I'm hungry.",
                "outro.cakeguystalk.8", "Shut up.",
                "outro.thankYou", "Captainsoft would like to thank the following",
                "outro.thankYou.1", "All Fringes family, all Berlin friends, all M�nster friends, GM <br> "
                + "Sana Gesundheitszentrum Berlin <br> "
                + "FH Trier, University of Westminster <br> "
                + "Wizardry 7, Ultima 7, Indiana Jones <br> "
                + "Pepsi-Cola, Heineken, Galeria Kaufhof, Pizza XXL <br> "
                + "PC Player, Kultpower.de <br> "
                + "Robert DeNiro, Jeff Hanneman <br> "
                + "Tempo90, Berghain <br> "
                + "At The Gates - for the title <br> ",
                "outro.thankYou.2", "Alexander Krug & the DUS <br> "
                + "Painkilla & GameDev Page <br> "
                + "Developia, TIGF, Gamejolt, Java Gaming Community <br> "
                + "EgonOlsen, Karl Wessel, Thorsten L�ders, Bene Heying, Toby B�rkle, Arun + Steffi <br> "
                + " <br> <br> <br> <br> <br> "
                + "... and everybody who I forgot to mention! <br> ",
                "outro.valloSchaunffIndianaJonesTalk.1", "\"Ah, Bunga Terra Tissa Tu!\"",
                "outro.valloSchaunffIndianaJonesTalk.2", "\"Hi, I'm selling these fine leather jackets\"",
                "outro.valloSchaunffIndianaJonesTalk.3", "\"Look behind you, a three headed monkey!\"",
                "outro.valloSchaunffIndianaJonesTalk.4", "\"Eh...\"",
                "outro.weUse", "Captainsoft uses",
                "outro.weUseList", "IntelliJ IDEA Community Edition 13.1 <br> "
                + "Ulead PhotoImpact 12 <br> "
                + "Samsung Laptops <br> "
                + "Microsoft Keyboards <br> "
                + "Logitech Mouses <br> "
                + "Jackson Guitars <br> "
                + "Replay Perfumes <br> "
                + "Pepsi, Heineken, Pizza XXL, Balisto <br> ",
                "outro.starConversation.1", "Well done, Little Star! As far as <br> I can see, all monsters are doing time. <br> We've been successful again!",
                "outro.starConversation.2", "And what now, boss? We're going for holidays?",
                "outro.starConversation.3", "No. You go get some donuts.",
                "outro.starConversation.4", "I'm flying, boss!",
                "outro.endAndTeaser", "Visit Captainsoft at <br> "
                + "www.captainsoft.de <br> "
                + "and sign the guestbook (or facebook)! <br> <br> <br> <br> <br> "
                + "Summerkamp, Kurtis, Ole & Zett <br> "
                + "will return in TAD 2: arrival of the chili-gods",
                "outro.freitagmanLast", "After a successful stay in a chewing gum rehab, <br> Fridayguy now works part time as a weekend <br> in the Caribbean.",
                "outro.copyright", "(c) " + TAD.Copyright + " Captainsoft. <br> www.captainsoft.de",
                "party.protect", "Armor:  ",
                "party.special.1.a", "Combat",
                "party.special.1.b", "Secure",
                "party.special.1.c", "Frogmarch",
                "party.special.1.d", "Chest Lockpick",
                "party.special.2.a", "Beating",
                "party.special.2.b", "Block",
                "party.special.2.c", "Bump Roll",
                "party.special.2.d", "Spells",
                "party.special.3.a", "Duel",
                "party.special.3.b", "Ward",
                "party.special.3.c", "Berserk",
                "party.special.3.d", "Magic Formulas",
                "party.special.4.a", "Skirmish",
                "party.special.4.b", "Refrain",
                "party.special.4.c", "Chem. Reaktion",
                "party.special.4.d", "Scrolls",
                "quicksave.cannot.isnew", "Quicksave not yet possible - Game is new.",
                "quicksave", "Game  '$1'  saved.",
                "fitPoints.eat.full.1", "Thanks, I have had enough.",
                "fitPoints.eat.full.2", "Booa.... nope " + np + " now it is good.",
                "fitPoints.eat.full.3", "I am full.",
                "fitPoints.eat.full.4", "No thank you.",
                "fitPoints.eat.thankyou.1", "Yes that is good!",
                "fitPoints.eat.thankyou.2", "Ahhh, mjam, mjam.",
                "fitPoints.eat.thankyou.3", "Thank you very much, Sir " + np + ".",
                "fitPoints.eat.thankyou.4", "Tasty! Thanks, " + np + ".",
                "gameload.welcome.1", "Hello, " + np + "!",
                "gameload.welcome.2", "Hi, " + np + "! How is it hanging?",
                "gameload.welcome.3", "Welcome again " + np + "!",
                "gameload.welcome.4", "Good Day " + np + ".",
                "member.info.overweight.1", "Puh, I have too carry a lot of stuff...",
                "member.info.overweight.2", "Eh, " + np + ", why dont you come down here and help us carry?",
                "member.info.overweight.3", "So for this I usually grind my vassals.",
                "member.info.overweight.4", "Wait, I cannot catch up with all the stuff I have to carry!",
                "member.info.littleFun.1", "Maybe I will leave and join the force again...",
                "member.info.littleFun.2", "This is no fun!! I just want to go home.",
                "member.info.littleFun.3", "I want to go home again soon, to visit my castle.",
                "member.info.littleFun.4", "I now you are busy " + np + ", but I am almost out of FunPoints.",
                "member.info.noHouse.1", "I don't know if you have noticed " + np + ", but I am wearing no pants!",
                "member.info.noHouse.2", "Haha, a king without pants!",
                "member.info.noHouse.4", "Something is wrong on me...",
                "menu.main.link", "Visit us under www.captainsoft.net",
                "menu.main.copyright", "Copyright " + TAD.Copyright + " Captainsoft. All rights reserved.",
                "menu.main.version", "Version $1",
                "menu.main.new", "Start a new Adventure",
                "menu.main.load", "Load an Adventure",
                "menu.main.save", "Save the Adventure",
                "menu.main.back", "Back to the Adventure",
                "menu.main.settings", "Forms",
                "menu.main.quit", "End the Game",
                "menu.main.about", "About",
                "menu.main.linkToHomepage", "http://www.captainsoft.net",
                "menu.main.about.thankYou", "Thank you",
                "menu.main.about.txt", "The Terminal Angel Disease was created by" + br + "Captainsoft (Mathias Fringes)" + br +
                "from 1996-2015." + br2 +
                "Support us via:" + br +
                "www.captainsoft.net" + br +
                "www.facebook.com/TheTerminalAngelDisease" + br +
                "twitter.com/KurtisTheBlob" + br +
                "indiedb.com/games/the-terminal-angel-disease" + br2 +
                "Press F1 in the game for help."
                , "menu.save.caption", "Save Adventure",
                "menu.save.button", "Save",
                "menu.load.caption", "Load Adventure",
                "menu.load.button", "Load",
                "menu.settings.namePlayer", "Name of Player",
                "menu.settings.nameParty", "Name of Party",
                "menu.settings.caption", "Forms",
                "menu.settings.page", "Turn Page",
                "menu.button.yes", "Yep!",
                "menu.button.no", "No way!",
                "menu.quit.really", "Really quit the game, " + np + "!? <br> (Did you save?)",
                "menu.settings.playMusic", "Play Music",
                "menu.settings.playSounds", "Play Sounds",
                "menu.settings.walkSpeed", "Walking Speed",
                "menu.settings.walkSlow", ">>",
                "menu.settings.walkFast", "Fast",
                "teleporter.misuse.4", "The Teleporter does not work down here!",
                "teleporter.misuse.5", "The Teleporter does not work down here!",
                "teleporter.misuse.18", "We are surely too highly up for the Teleporter.",
                "teleporter.misuse.19", "The Teleporter does not work here certainly!",
                "teleporter.misuse.20", "The Teleporter does not function here!",
                "tile.coffeeAutomaton.1", "Ahh, Coffee just like we had in the office!",
                "tile.coffeeAutomaton.2", "Now we're talking brother!",
                "tile.coffeeAutomaton.3", "That makes it all more fun!",
                "tile.coffeeAutomaton.4", np + ", an excellent idea of yours!",
                "tile.useAutomation.noChips.1", "Too little Chips...",
                "tile.useAutomation.noChips.2", "Well " + np + ", where did all the Chips go?",
                "tile.useAutomation.noChips.3", "Oh, we don't have no more Chips for that.",
                "tile.useAutomation.noChips.4", "We would need $1 Chips!",
                "shop.hello", "\"Buy what you want!\"",
                "shop.dontwantit", "\"I do not want that!\"",
                "shop.solditem", "\"Here you have  $1  Chips!\"",
                "shop.solditemscroll", "$1 sold.",
                "shop.buyitem", "\"Thank you for  $1  Chips!\"",
                "shop.buyitemscroll", "$1 bought.",
                "shop.buyitemnomoney", "\"Unfortunately, you don't not have enough chips!",
                "helpWindow.title", "Help!",

                "helpWindow.1",
                "Hello " + np + ", here are some tips to help you with " + prt + ":" + br2 +
                        "The Action World:" + br +
                        " * Left click: Move Your Feet, open chests, talk to people, press buttons." + br +
                        " * Right click: Pick up, drop items." + br +
                        " * Drop an item on the party member, to add it to the next available space in the rucksack!" + br2 +

                        " Rucksacks:" + br +
                        " * Left click: Item Analysis" + br +
                        " * Double Click: Eat food, use keys and other useful items, read books." + br +
                        " * Right click: Pick up & drop items." + br2 +

                        " Talking:" + br +
                        "  * Click on a person's head to talk to them." + br +
                        "  * Some People may react differently if you have an item in your rucksack that is interesting for them." + br2 +

                        " Shopping:" + br +
                        "  * Pick up a item from the shop to buy." + br +
                        "  * Drop an item on the sell button to ... sell!" + br2

                ,
                "helpWindow.2",

                " Fighting:" + br +
                        "  * Right click on little monster icon: all party attack." + br +
                        "  * Right click on \"X\" top right: Give up :o" + br +
                        "  * When a monster is hit, it cannot attack you ;)" + br +
                        "  * Using fight techniques improves the party's skills!" + br2 +

                        " Quick Access Keys:" + br +
                        "  * 1-4: Select rucksack of each member" + br +
                        "  * F1: Help screen" + br +
                        "  * F2: Quest Log" + br +
                        "  * F3: Info" + br +
                        "  * F5: Quick save" + br +
                        "  * F12: Boss key (use it wisely)" + br +
                        "  * awsd, arrows: Move" + br +
                        "  * Space: Stop; next talk" + br +
                        "  * ESC: Disk Menu",

                "infoWindow",
                "Captainsoft's" + br +
                        "The Terminal Angel Disease" + sep +
                        "* $1  -  $2 " + sep +
                        "Visit our page for frequent updates" + sep +
                        "*www.captainsoft.de" + sep +
                        "Please support us by becoming a fan on Facebook" + sep +
                        "*www.facebook.com/TheTerminalAngelDisease" + sep +
                        "Please share it also amongst your friends! The more fans we get," + br +
                        "the more we can work on the Terminal Angel Disease!" + sep +
                        "Enjoy the game!" + br +
                        "And save early, save often :)",


                "questWindow.title", "The Quest Chicken Report",
                "quest.m1.WiseGuys", "Let's talk to the Wise Guys about what we should do in this game anyway. They live in the north west of the city. We have to cross the big bridge in the north.",
                "quest.m1.EmuEggBradfish", "A classic quest: Get an egg of an emu and bring it to the crazy talking french cook. We can get the egg at Eyk's emu farming facilities.",
                "quest.m1.TransitAmpel", "A talking traffic light? Isn't that a cocktail? But it won't let us pass the big bridge unless we have the transitvisum.",
                "quest.m1.searchForEvidence", "Burgomaster Schulze has his fingers in the pie. Who would have guessed!? We must find the motive and confront him with it!",
                "quest.m1.HarborShipLost", "The tourists on the pleasure boat did not return from their trip. Maybe they found a game with better graphics... Hint: there is NO boat in this game.",

                "quest.m2.NoPassGrimaldi", "We need a password to get past Grimaldi, the weapon bristling city guard.",
                "quest.m2.NoPassAtStars", "The police stars won't tell us the password to the city gates (because Kurtis was drunk). Eh, they will change it soon anyway.",
                "quest.m2.findJustinCave", "Only with the holy Code Wheel we can enter the Deep Deep Dungeon. It is hidden in the big cave beneath the city. The entry to the cave is beyond the big Justin Chamberlain monument. But please let us buy some snacks before we descend down there!",
                "quest.m2.findRingRing", "We should now visit Engineer Ringring, as the Level Designer, he can tell us more about the Deep Deep Dungeon.",

                "quest.m3.schulzTelporter", "With the key from the Burgomaster we can access the Teleporter to Chili-Tschong Kahn. We need to go deep down the Deep Deep Dungeon to find the high plateau area.",

                "quest.m5.Gigantus", "The poor - but cute - Cavel Guys want us to terminate GIGANTUS. Though this is not a hitman killer game, they won't let us exit their cave until we do him in.",

                "quest.m6.Waldfgee", "The beautiful lady of the woods won't let us enter the wilderness until we found some holy keys or whatever, and gain more experience. Alternative: bribe her with a pack of cigarettes.",

                "quest.m7.getBook", "The little wood gnome is missing one of his books somewhere in the western mountains. Get it, return it, reap the reward. Soooo classic.",
                "quest.m7.toTehDungeon", "We have the ticket for the inner part of the Deep Deep Dungeon, valid for 3 adults and one Kurtis. Let's go for the ride!",

                "quest.m9.Justin1", "Justin Chamberlain! What a nice fellow. He thinks Chili-Tschong Kahn is the ultimate endboss. With the key we should get into the maze of the Deep Deep Dungeon where the uber-baddie lurks.",
                "quest.m9.Justin2", "The Deep Deep Dungeon game is so old, it had a copy protection. We need the codes to access the first castle level.",

                "quest.m13.cold", "The shopkeeper caught a cold in the damp climate of the dungeon. He needs a big hanky!",
                "quest.m13.keysToDungeonCells", "From the runny nose guy we got the key to the dungeon entry beyond the jail cells. Maybe we find something - or someone - interesting there (and even more chocolate)!",
                "quest.m13.FridayGum", "Fridayguy needs some bubblegum to speak. This little sugar pout!",
                "quest.m13.FindBeweisBunga", "Fridayguy suspects a companion helps Chili-Tschong Kahn. He - or she? - lives in a mansion in Bunga Terra Tissa Tu. Use the back door to get in and loot the place.",

                "quest.m15.questMachineTalk", "We need to convince the silly microphone that we are eligible to enter the secret area. Where do we get a stoutly quest from?",

                "quest.m16.chcolata", "The two airheads want a chocolate cake... in the dungeon. Otherwise they won't let us pass their magic barrier. Looks kinda ramshackle anyway.",

                "quest.m18.lattenGame", "Is this game suddenly turning into an action adventure? We have to jump'n'run through the picket labyrinth in 10 seconds.",

                "quest.m19.saltTheChiliKing", "What we learned in this game: Big cooperation bosses are always evil, mean, and sadistic. Let's oversalt his coffee and ruin his retirement pension for good.",

                "quest.m20.toTheOfficeRoom", "Why should they open the office door for a RPG party like us? This is so bureaucratic."

                , "x", "-",
        };
        return data;
    }

}
