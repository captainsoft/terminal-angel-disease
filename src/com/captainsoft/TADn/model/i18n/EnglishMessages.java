/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.model.i18n;

import static com.captainsoft.TADn.party.ItemType.BodyArmor;
import static com.captainsoft.TADn.party.ItemType.CurrentFunPoints;
import static com.captainsoft.TADn.party.ItemType.FeetArmor;
import static com.captainsoft.TADn.party.ItemType.FitPointsMax;
import static com.captainsoft.TADn.party.ItemType.FoxPointsMax;
import static com.captainsoft.TADn.party.ItemType.FunPointsMax;
import static com.captainsoft.TADn.party.ItemType.HeadArmor;
import static com.captainsoft.TADn.party.ItemType.MagicFireMultiple;
import static com.captainsoft.TADn.party.ItemType.MagicFireSingle;
import static com.captainsoft.TADn.party.ItemType.MagicMultiple;
import static com.captainsoft.TADn.party.ItemType.MagicSingle;
import static com.captainsoft.TADn.party.ItemType.MagicWaterMultiple;
import static com.captainsoft.TADn.party.ItemType.MagicWaterSingle;
import static com.captainsoft.TADn.party.ItemType.Protection;
import static com.captainsoft.TADn.party.ItemType.Puzzle;
import static com.captainsoft.TADn.party.ItemType.Shaolin;
import static com.captainsoft.TADn.party.ItemType.SlimeBomb;
import static com.captainsoft.TADn.party.ItemType.Weapon;

/**
 * English gui messages.
 *
 * @author mathias fringes
 */
public final class EnglishMessages implements GuiMessages {

	@Override
	public String[] data() {
		String[] data = {
		"chest.needsKeyNoDietrich.1", "Strange... the lock pick doesn't work here!",
		"chest.needsKey.1", "I connot open this chest with the best will!",
		"chest.cannotOpen.1", "Puh this chest i cannot crack yet!",
		"chest.found", "You find",
		"chiliStuff.dontEat.1", "Ugh. That smells disgusting!",
		"chiliStuff.dontEat.2", "I eat almost everything. But this never!!",
		"chiliStuff.dontEat.3", "No " + np + ", i am not eating this for your sake.",
		"chiliStuff.dontEat.4", "Uh. No, thanks, i really don't like that.",
		"item.info.type." + CurrentFunPoints.id(), "$itm! That's a whim for  $p  FunPoints!",
		"item.info.type." + HeadArmor.id(), "$itm! It has  $p  Ar! Wearable by: $suit ($cp Cp).",
		"item.info.type." + BodyArmor.id(), "$itm! It has  $p  Ar! Wearable by: $suit ($cp Cp).",
		"item.info.type." + FeetArmor.id(), "$itm! It has  $p  Ar! Wearable by: $suit ($cp Cp).",
		"item.info.type." + Protection.id(), "$itm! It has  $p  Ar! Wearable by: $suit ($cp Cp).",
		"item.info.type." + Weapon.id(), "$itm! It has  $p  Wp! Usable by: $suit ($cp Cp)!",
		"item.info.type." + MagicSingle.id(), "$itm! Magic that hits one monster for  $p  FunPoints!",
		"item.info.type." + MagicMultiple.id(), "$itm! Magic that hits several monsters for  $p  FunPoints!",
		"item.info.type." + MagicFireSingle.id(), "$itm! Firemagic that hits one monster for  $p  FunPoints!",
		"item.info.type." + MagicFireMultiple.id(), "$itm! Firemagic that hist severla monsters for  $p  FunPoints!",
		"item.info.type." + MagicWaterSingle.id(), "$itm! Watermagic that hits one monster for  $p  FunPoints!",
		"item.info.type." + MagicWaterMultiple.id(), "$itm! Watermagic that hits several monsters for  $p  FunPoints!",
		"item.info.type." + Shaolin.id(), "$itm! Powder that lets us fly!",
		"item.info.type." + SlimeBomb.id(), "$itm! That slows down most Monsters!",
		"item.info.type." + Puzzle.id(), "$itm! That looks quite useful.",
		"item.info.type." + FunPointsMax.id(), "$itm! Thats is infinite jest!",
		"item.info.type." + FitPointsMax.id(), "$itm! That makes you big and strong!",
		"item.info.type." + FoxPointsMax.id(), "$itm! That makes your tired spirits perk!",
		"item.use.cannot.1", "That is not going to work...",
		"item.use.cannot.2", "Doesn't work...",
		"item.use.cannot.3", "Nothing happenend.",
		"item.use.cannot.4", "That does not work this way...",
		"item.use.wrong.1", "Mhmm, odd. Nothing has happened.",
		"item.use.wrong.2", "What do you always thinking up, " + np + " ?",
		"item.use.wrong.3", "There has nothing happenend...",
		"item.use.wrong.4", "So, " + np + " i guess you must rethink this!",
		"item.use.fightOnly.1", "That i keep on me for a fight.",
		"item.use.fightOnly.2", "I want it, but i would rather wait yet!",
		"item.use.fightOnly.3", "I can only use this in a battle.",
		"item.use.fightOnly.4", "No, that would be too dangerous yet!",
		"item.use.funPointsMax.1", "You're doing a great job, " + np + "!",
		"item.use.funPointsMax.2", "Smat. Mampf.",
		"item.use.funPointsMax.3", "Mhmm. Get it on boys, lets move onwards!",
		"item.use.funPointsMax.4", "Thank you, " + np,
		"item.use.fitPointsMax.1", "Better than exercises!",
		"item.use.fitPointsMax.2", "I am the most nourishing!",
		"item.use.fitPointsMax.3", "Arm wrestling, " + np + "?",
		"item.use.fitPointsMax.4", "Yes, i am feeling more stronger now.",
		"item.use.foxPointsMax.1", "Now i can even better investigate!",
		"item.use.foxPointsMax.2", "The root out of $1 is $2.",
		"item.use.foxPointsMax.3", "I can see clearer now...",
		"item.use.foxPointsMax.4", "Who is in talking about physics?",		
		"item.take.wrongPlace.1", "This is really the wrong place, " + np + ".",
		"item.take.wrongPlace.2", "I should walk around like that? No way!",
		"item.take.wrongPlace.3", "I am not sure if that fits here!",
		"item.take.wrongPlace.4", "No, i really should not wear that there, " + np + ".",		
		"item.take.cannotUseIt.1", "But $1 really does not suit me!",
		"item.take.cannotUseIt.2", "No " + np + ", i have a taste for fashion!",
		"item.take.cannotUseIt.3", "This is really not suitable for me, " + np + ".",
		"item.take.cannotUseIt.4", "I really understand how to use $1.",		
		"inventory.carry", "To carry:  $1 / $2",		
		"ifc.itemTaken", "$1 taken.",
		"intro.thisisthestory.1", "Dies ist die Geschichte der <br> Stadt Bunga Terra Tissa Tu.",
		"intro.formerHomeOf.1", "Ehemals Schauplatz der erfolgreichen <br> Rollenspielserie \"Deep Deep Dungeon\"",
		"intro.formerHomeOf.2", "...und ihrem Helden Justin Chamberlain.",
		"intro.fatHeroesDrinking.1", "Doch mit der Rollenspielflaute Mitte der Neunziger wurde auch \"Deep Deep Dungeon\" eingestellt.",
		"intro.fatHeroesDrinking.2", "Heute ist Bunga Terra Tissa Tu nur noch eine Touristenattraktion. <br> Schulklassen werden durch das alte Labyrinth geführt, Statisten aus \"Deep Deep Dungeon\" verkaufen billige Andenken und die alten Helden der Serie treffen sich in der Trinkhalle.",
		"intro.untilThisDay.1", "Das Geschäft geht mangels Interesse an alten Rollenspielen schlecht, und das Leben in Bunga Terra Tissa Tu ist eher unspektakulär.",
		"intro.untilThisDay.2", "Bis eines Tages...",	
		"intro.monsterDance.1", "Wilde Monster die Stadt überfallen!",
		"intro.monsterDance.2", "Währenddessen, im Verhaltenszentrum von Bunga Terra Tissa Tu...",
		"intro.vhtalk.1", "Diese Monster verbiegen sämtliche Laternen in der Stadt!",
		"intro.vhtalk.2", "Und werfen leere Getränkedosen auf die Strassen!",
		"intro.vhtalk.3", "Und plündern die Süßigkeitenautomaten!",
		"intro.vhtalk.4", "Es muß etwas geschehen!",
		"intro.vhtalk.5", "Yes Sir!",
		"intro.vhtalk.6", "Wir sind die Verhaltensweisen!",
		"intro.vhtalk.7", "Genau!",
		"intro.vhtalk.8", "Deswegen rufen wir jemanden zu Hilfe!",
		"intro.vhtalk.9", "Gute Idee!",
		"intro.vhtalk.10", "Aber wer könnte dieses Problem lösen?",		
		"intro.vhtalk.11", "Nur " + np + " und " + prt + "!",
		"intro.vhtalk.12", "Natürlich!",
		"intro.vhtalk.13", "Lasst uns ihnen sofort ein E-rrlicht schicken!",
		"intro.drucing.1", "Detective Summerkamp",
		"intro.drucing.2", "Kurtis der Blob.",
		"intro.drucing.3", "Ritter Ole-Ozelot",
		"intro.drucing.4", "Professor Zett",
		"intro.irrlicht.1", "Ein Spiel von Captainsoft.",
		"intro.irrlicht.2", "Programmiert abends und an den Wochenenden.",
		"intro.irrlicht.3", "Grafiken sind heimgewerkelt.",		
		"intro.irrlicht.4", "Sound-Effects von DJ Fresh Tzaziki.",
		"intro.irrlicht.5", "Musik (nicht im Spiel enthalten) von Captain Burschi and the Pogo Jumpers",
		"intro.irrlicht.6", "* Hier könnte Ihre Werbung stehen *",						
		"intro.irrlicht.7", "Written and directed by Captainsoft",
		"telep.prisma.funny.1", "Hihi, that tickles!", 
		"telep.prisma.funny.2", "Whoooaa... that is fun!",
		"telep.prisma.funny.3", "Wusch....",
		"telep.prisma.funny.4", np + ", how exactly does that work?",
		"fight.learn.weapon.1", "Ich werde im Kämpfen immer besser mit  $1  Punkten!",
		"fight.learn.weapon.2", "Verkloppen macht mit  $1  Punkten noch mehr Spaß!",
		"fight.learn.weapon.3", "Ich kann mich nun mit  $1  Punkten duellieren!",
		"fight.learn.weapon.4", "Die Wissenschaft des Schubsens erschließt sich mir nun mit  $1  Punkten!",				
		"fight.learn.magicItem.2", "Diese Schriftrollen kann ich nun  $1  Punkten einsetzen!",
		"fight.learn.magicItem.3", "Mit Zaubersprüche kann ich nun mit  $1  Punkten umgehen!",
		"fight.learn.magicItem.4", "Schriftrollen kann ich nun mit  $1  Punkten benutzen!",
		"fight.learn.magicItemChem.4", "Ich habe im Fach chemische Reaktionen nun  $1  Punkte!",				
		"fight.learn.special.1", "Ich hab  $1  Punkte im Fach Verhaften!",
		"fight.learn.special.2", "Haha! Ich habe beim Umrempeln die  $1  Punkte-Marke geknackt!",
		"fight.learn.special.3", "Ich habe im Nahkämpfen nun  $1  Punkte!",		
		"fight.attack.magic.multi.hit", "$1 zaubert $2, und trifft  $3  Monster!",
		"fight.attack.magic.multi.fail", "$1 zaubert $2, aber trifft kein Monster!",
		"fight.attack.magic.single.hit", "$1 zaubert $2, und trifft für  $4  FunPoints!",
		"fight.attack.magic.single.fail", "$1 zaubert $2, kann aber keine Wirkung erzielen!",		
		"fight.attack.chem.multi.hit", "$1 mischt $2, und trifft  $3  Monster!",
		"fight.attack.chem.multi.fail", "$1 mischt $2, aber trifft kein Monster!",
		"fight.attack.chem.single.hit", "$1 mischt $2, und trifft für  $4  FunPoints!",
		"fight.attack.chem.single.fail", "$1 mischt $2, kann aber keine Wirkung erzielen!",		
		"fight.attack.bomb.multi.hit", "$1 wirft $2, und trifft  $3  Monster!",
		"fight.attack.bomb.multi.fail", "$1 wirft $2, aber trifft kein Monster!",
		"fight.attack.bomb.single.hit", "$1 wirft $2, und trifft für  $4  FunPoints!",
		"fight.attack.bomb.single.fail", "$1 wirft $2, kann aber keine Wirkung erzielen!",		
		"fight.attack.metzel.noWeapon", "$1 does not hold a weapon in hand!",
		"fight.attack.metzel.hit", "$1 stürzt sich mutig auf die Gegner, und trifft  $2  Monster!",
		"fight.attack.metzel.fail", "$1 stürzt sich mutig auf die Gegner, aber kann nichts bewirken!",				
		"fight.attack.police.hit", "$1 greift rigoros ein, und verhaftet  $2  Monster!",
		"fight.attack.police.fail", "$1 greift rigoros ein, kann aber nichts bewirken.",		
		"fight.attack.rempel.hit", "$1 rempelt sich gegen die Monster, und trifft  $2  Monster!",
		"fight.attack.rempel.fail", "$1 rempelt sich gegen die Monster, kann aber nichts bewirken.",		
		"fight.attack.slime.hit", "$1 zündet die Schleim-Bombe, und verklebt  $2  Monster!",
		"fight.attack.slime.fail", "$1 zündet die Schleim-Bombe, kann aber keine Monster verkleben!",		
		"fight.weapon.attack.hit", "$1 attacks with $2, and scores for  $3  FunPoints!",
		"fight.weapon.attack.miss", "$1 attacks with $2, but hits into the empty!",
		"fight.weapon.attack.fail", "$1 attacks with $2, but cannot steal any FunPoints!",
		"fight.weapon.attack.noWeapon", "$1 does not hold a weapon in hand!",		
		"fight.use.shaolin.ok", "$1 versprüht das Shaolin-Pulver, und die Party fängt an zu fliegen!",
		"fight.use.shaolin.twice", "$1 versprüht das Shaolin-Pulver, aber die Wirkung kommt nicht mehr zum tragen!",
		"fight.use.incrFunPoints", "$1 nimmt  $2  zu sich und ist mit mehr Spaß bei der Sache!",		
		"fight.win", "$1 <br> defeat the monsters and gain <br> $2 <br> Chips!",		
		"fight.title.against", "$1 compete against",
		"fight.defaultAttack.hitsWithDefense", "and strikes despite resistance for   $1   FunPoints!",
		"fight.defaultAttack.hits", "and strikes for   $1   FunPoints!",
		"fight.defaultAttack.canDefense", "but is successfully repelled!",
		"fight.defaultAttack.notHitCharm", "but it cannot spoil the fun!",
		"outro.thatWas", "Das war",
		"outro.madeBy", "Erdacht und geschaffen von",
		"outro.playedBy", "Gespielt von",
		"outro.andPlayedBy", "und",
		"outro.starring", "mit dabei:",		
		"outro.starring.11", "Chili-Tschong Kahn",
		"outro.starring.12", "as the evil master of the dungeon",
		"outro.starring.21", "Freitagmännchen",
		"outro.starring.22", "as the psychotic helper of the evil master",
		"outro.starring.31", "Die Verhaltensweisen",
		"outro.starring.32", "as the drei weisen",
		"outro.starring.41", "Vallo Schnauff",
		"outro.starring.42", "as the hero of the other game",
		"outro.starring.51", "The brother of 'Chuck, die Pflanze'",
		"outro.starring.52", "as the Rhododendron",
		"outro.starring.61", "Jutta the Princess",
		"outro.starring.62", "not really included in the game",		
		"outro.alsoStarring", "also starring",
		"outro.alsoStarring.1", "Die Scripts <br> Herr Fred <br> Holla, die Waldfee <br> Wilbur <br> Die Cavel Guys <br> Gigantus <br> Die Emus <br> Bobby <br> Misses Storck",			
		"outro.alsoStarring.2", "Grimaldi <br> Flying Zett <br> Mistel Ling <br> General Fu-Huh-Sang <br> Bürgermeister Schulze <br> Timeshop Clerk <br> Dipl.Ing Ringring <br> Little Star <br> Big Star <br>",        
		"outro.alsoStarring.3", "Hans <br> Mister Tar <br> Mistel Ling <br> Mister Pizza <br> Kurle Wosch <br> Marvin <br> Mister Pani <br> Fräulein Honig <br> Die Wochenmännchen <br>",                              
		"outro.alsoStarring.4", "Captain N! <br> Owsed <br> Nowsed <br> Commander Blop <br> Monsieur Bradfish <br> Muck, der Magier <br> Jeffrey <br> Das Ampel-Männchen <br> The friendly Saur <br>",
		"outro.alsoStarring.5", "Hubert <br> Tee Schobber <br> Mister Klaar <br> Mister Sparkle <br> Eyk, der Emu-Mann <br> Goldie <br> <br> and the <br> JoJo-Jazz Ensemble <br>",              	
		"outro.cakeguystalk.1", "Meinst Du die bringen uns die Torte noch vorbei?",
		"outro.cakeguystalk.2", "Vielleicht.",
		"outro.cakeguystalk.3", "Die müßten doch schon längst wieder da sein!",
		"outro.cakeguystalk.4", "Ich weiß.",
		"outro.cakeguystalk.5", "Mir ist kalt.",
		"outro.cakeguystalk.6", "Mir auch.",		
		"outro.cakeguystalk.7", "Ich hab Hunger.",
		"outro.cakeguystalk.8", "Halt die Klappe!",			
		"outro.thankYou", "Captainsoft would like to thank the following",		
		"outro.thankYou.1", "Pc Player, Gamestar, Wizardry 7, Doom, Indiana Jones v <br> "
				+ "Maniac Mansion, Theme Park, Ultima 7 && 8, Zelda <br> "
				+ "Babylon, Istanbul, Burger King, McDonalds <br> "
		        + "Coca-cola, Pepsi-Cola, Heinecken, Becks <br> "
		        + "Chow Yun-Fat, Robert De Niro, Jeff Hanneman <br> "
		        + "Takeshi Kitano, Maggie Cheung, Al Pacino <br> "
		        + "WinMx, Soulseek, eMule, Outpost, Outlook, Napster <br> "
		        + "Java, Spiegel Online, Spotlight, Mozilla <br> "
		        + "Simpsons, Spongebob, Futurama, Monthy Phyton <br> "
		        + "Mercedes-Benz, Aral, Shell <br> "
		        + "Westminster University, Holden Caulfield <br> "
		        + "Mediamarkt, Saturn, Siemens, Grundig <br> "
		        + "Tempo90, Louisiana, Exhaus, Eintracht Trier <br> ",        
		"outro.thankYou.2", "alexander krug & the d.u.s. <br> "
                 + "Painkilla & GameDev Page <br> "
                 + "Karl Wessel, Thorsten Lüders, Bene Heying <br> "
                 + "312, tti, wmdcu, Toby Bürkle - the special customer <br> "
                 + "fh trier <br> "
                 + "Mike && all @ granatenstramm.de <br> "
                 + " <br> <br> <br> <br> <br> "                 
                 + "... und allen, die hier bestimmt vergessen wurden! <br> ",
		"outro.valloSchaunffIndianaJonesTalk.1", "\"Ah, Bunga Terra Tissa Tu!\"",
		"outro.valloSchaunffIndianaJonesTalk.2", "\"War nur so'n Gedanke\"",
		"outro.valloSchaunffIndianaJonesTalk.3", "\"Hui, die Säure frißt sich glatt durch das Metall!\"",
		"outro.valloSchaunffIndianaJonesTalk.4", "\"Ach...\"",
		"outro.weUse", "Captainsoft uses",
		"outro.weUseList","Visual Basic 6.0 SP5 <br> " 
				 + "PhotoImpact 5 <br> "
				 + "Winamp 2.77 - 5 <br> "
				 + "Toshiba Laptops <br> "
				 + "Cherry Keyboards <br> "
				 + "Jackson Guitars <br> "
				 + "Joop & Diesel perfumes <br> "
				 + "Pepsi, Heineken, Becks & West <br> ",		
		 "outro.starConversation.1", "Gute Arbeit, Little Star! So wie <br> ich das sehe, sind alle Monster dingfest. <br> Wir haben es mal wieder geschafft.",
		 "outro.starConversation.2", "Und jetzt Chef? Gibt es Urlaub?",
		 "outro.starConversation.3", "Nein. Sie gehen jetzt Donuts kaufen.",		        
		 "outro.starConversation.4", "Ich fliege, Chef!",		 
		 "outro.endAndTeaser", "Visit Captainsoft at <br> "
			   + "www.captainsoft.de <br> "
			   + "and sign theguestbook! <br> <br> <br> <br> <br> "           
			   + "Arkady Renko, Ol'Dirty Blob, Ole Ozelot && Professor Link <br> "
			   + "will return in the TAD 2: arrival of the chili-gods",			   
		"outro.freitagmanLast", "Nach dem erfolgreichem Besuch einer Kaugummientzugsklinik <br> arbeitet Freitagmännchen nun halbtags als Wochenende <br> in der Karibik.",
	    "outro.copyright", "(c) 1996-2013 Captainsoft. <br> www.captainsoft.de",           		
		"quicksave.cannot.isnew", "Quicksave not yet possible - Game is new.",
		"quicksave", "Game  '$1'  saved.",			
		"fitPoints.eat.full.1", "Thanks, I have had enough.",
		"fitPoints.eat.full.2", "Booa.... nope " + np + " now it is good.",
		"fitPoints.eat.full.3", "I am full.",
		"fitPoints.eat.full.4", "No thank you.",
		"fitPoints.eat.thankyou.1", "Yes that is good!",
		"fitPoints.eat.thankyou.2", "Ahhh, mjam, mjam.",
		"fitPoints.eat.thankyou.3", "Thank you very much, Sir " + np,
		"fitPoints.eat.thankyou.4", "Tasty! Thanks, " + np,
		"gameload.welcome.1", "Hello, " + np + "!",
		"gameload.welcome.2", "Hi, " + np +"! How is it hanging?",
		"gameload.welcome.3", "Welcome again " + np + "!",
		"gameload.welcome.4", "Good Day " + np,
		"member.info.overweight.1", "Puh, i have too carry a lot of stuff...",
		"member.info.overweight.2", "Eh, " + np + ", why dont you come down here and help us carry?",
		"member.info.overweight.3", "So for this I usually grind my vassals.",
		"member.info.overweight.4", "Wait, i cannot catch up with all the stuff I have to carry!",		
		"member.info.littleFun.1", "Maybe I will leave and join the force again...",
		"member.info.littleFun.2", "This is no fun!! I just want to go home",
		"member.info.littleFun.3", "I want to go home again soon, to visit my castle.",
		"member.info.littleFun.4", "I now you are busy " + np + ", but i am almost out of FunPoints.",		
		"member.info.noHouse.1", "Ich weiß ja nicht ob dir das aufgefallen ist " + np + ", but i am wearing no pants!",
		"member.info.noHouse.2", "Haha, a lking without pants!",
		"member.info.noHouse.4", "Something is wrong on me...",
		"menu.main.link", "Besuche uns unter www.captainsoft.de",
		"menu.main.copyright", "Copyright 1995-2012 Captainsoft. All rights reserved.",
		"menu.main.version", "Version $1",
		"menu.main.new", "Start new Adventure",
		"menu.main.load", "Load an Adventure",
		"menu.main.save", "Save the Adventure",
		"menu.main.back", "Back to the Adventure",		
		"menu.main.settings", "Forms",
		"menu.main.quit", "End the Game",		
		"menu.save.caption", "Save Adventure",
		"menu.save.button", "Save",			
		"menu.load.caption", "Load Adventure",
		"menu.load.button", "Load",
		"menu.settings.namePlayer", "Name of Player",
		"menu.settings.nameParty", "Name of Party",
		"menu.settings.caption", "Forms",
		"menu.settings.page", "Turn Page",		
		"menu.button.yes", "Yep!",
		"menu.button.no", "No way!",
		"menu.quit.really","Really quit the game, " + np + "!? <br> Did you save?",		
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
		"tile.useAutomation.noChips.3", "Oh, we don't have no more CHips for that.",
		"tile.useAutomation.noChips.4", "We would need $1 Chips!",
		"shop.hello", "\"Buy what you want!\"",
		"shop.dontwantit", "\"I do not want that!\"",
		"shop.solditem", "\"Here you have  $1  Chips!\"",
		"shop.solditemscroll", "$1 sold.",
		"shop.buyitem", "\"Thank you for  $1  Chips!\"",
		"shop.buyitemscroll", "$1 bought.",
		"shop.buyitemnomoney", "\"Unfortunately, you don't not have enough chips!",
		"x", "-",
		};		
		return data;		
	}

}
