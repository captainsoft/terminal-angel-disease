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
 * German i18n gui messages.
 *
 * @author mathias fringes
 */
public final class GermanMessages implements GuiMessages {
	
	public String[] data() {
		String[] data = {
		"chest.needsKeyNoDietrich.1", "Seltsam... der Dientrich funktionert hier nicht!",
		"chest.needsKey.1", "Diese Kiste bekomme ich beim besten Willen nicht auf!",
		"chest.cannotOpen.1", "Puh diese Truhe bekomme ich noch nicht geknackt!",
		"chest.found", "Ihr findet",
		"chiliStuff.dontEat.1", "Bäh. Das riecht ja widerlich!",
		"chiliStuff.dontEat.2", "Ich esse ja fast alles. Aber das hier nie im Leben!",
		"chiliStuff.dontEat.3", "Nein " + np + ", das esse ich auch nicht dir zuliebe.",
		"chiliStuff.dontEat.4", "Uh. Nein, danke, das möchte ich wirklich nicht.",
		"item.info.type." + CurrentFunPoints.id(), "$itm! Das macht Laune für  $p  FunPoints!",
		"item.info.type." + HeadArmor.id(), "$itm! Das hat  $p  Rp! Tragbar von: $suit ($cp Cp).",
		"item.info.type." + BodyArmor.id(), "$itm! Das hat  $p  Rp! Tragbar von: $suit ($cp Cp).",
		"item.info.type." + FeetArmor.id(), "$itm! Das hat  $p  Rp! Tragbar von: $suit ($cp Cp).",
		"item.info.type." + Protection.id(), "$itm! Das hat  $p  Rp! Tragbar von: $suit ($cp Cp).",
		"item.info.type." + Weapon.id(), "$itm! Das hat  $p  Ap! Benutzbar von: $suit ($cp Cp)!",
		"item.info.type." + MagicSingle.id(), "$itm! Magie, die einem Monster  $p  FunPoints raubt!",
		"item.info.type." + MagicMultiple.id(), "$itm! Magie, die mehreren Monstern  $p  FunPoints raubt!",
		"item.info.type." + MagicFireSingle.id(), "$itm! Feuerzauber, der einem Monster  $p  FunPoints raubt!",
		"item.info.type." + MagicFireMultiple.id(), "$itm! Feuerzauber, der mehreren Monstern  $p  FunPoints raubt!",
		"item.info.type." + MagicWaterSingle.id(), "$itm! Wasserzauber, der einem Monster  $p  FunPoints raubt!",
		"item.info.type." + MagicWaterMultiple.id(), "$itm! Wasserzauber, der mehreren Monstern  $p  FunPoints raubt!",
		"item.info.type." + Shaolin.id(), "$itm! Pulver dass uns fliegen lässt!",
		"item.info.type." + SlimeBomb.id(), "$itm! Das verlangsamt manche Monster!",
		"item.info.type." + Puzzle.id(), "$itm! Das sieht ganz nützlich aus.",
		"item.info.type." + FunPointsMax.id(), "$itm! Das bringt dauerhaften Spaß!",
		"item.info.type." + FitPointsMax.id(), "$itm! Das macht einen fit!",
		"item.info.type." + FoxPointsMax.id(), "$itm! Das macht müde Geister munter!",
		"item.use.cannot.1", "Das wird nicht funktioneren...",
		"item.use.cannot.2", "Geht nicht...",
		"item.use.cannot.3", "Es ist nichts geschehen.",
		"item.use.cannot.4", "Das klappt so nicht...",
		"item.use.wrong.1", "Mhmm, merkwürdig, es ist nichts passiert.",
		"item.use.wrong.2", "Was denkst Du dir da immer aus, " + np + " ?",
		"item.use.wrong.3", "Es ist nichts geschehen...",
		"item.use.wrong.4", "Also, " + np + " da mußt Du nochmal nachdenken!",
		"item.use.fightOnly.1", "Das bewahre ich mir für einen Kampf auf.",
		"item.use.fightOnly.2", "Ich möchte zwar, aber ich sollte lieber noch warten!",
		"item.use.fightOnly.3", "Das kann ich nur im Gefecht benutzen.",
		"item.use.fightOnly.4", "Nein, das wäre jetzt so zu gefährlich!",
		"item.use.funPointsMax.1", "Du machst einen tollen Job, " + np + "!",
		"item.use.funPointsMax.2", "Schleck. Mampf.",
		"item.use.funPointsMax.3", "Mhmm. Los Jungs, laßt uns weiterziehen!",
		"item.use.funPointsMax.4", "Danke sehr, " + np,
		"item.use.fitPointsMax.1", "Besser als Training!",
		"item.use.fitPointsMax.2", "Ich bin der Allerstärkste!",
		"item.use.fitPointsMax.3", "Armdrücken, " + np + "?",
		"item.use.fitPointsMax.4", "Ja, ich fühle mich nun stärker.",
		"item.use.foxPointsMax.1", "Nun kann ich noch besser ermitteln!",
		"item.use.foxPointsMax.2", "Die Wurzel aus $1 ist $2.",
		"item.use.foxPointsMax.3", "Nun ist der Groschen gefallen!",
		"item.use.foxPointsMax.4", "Wer unterhält sich mit mir über Astrophysik?",		
		"item.take.wrongPlace.1", "Also da paßt das nun wirklich nicht hin, " + np + ".",
		"item.take.wrongPlace.2", "So soll ich hier 'rumlaufen? Keine Chance!",
		"item.take.wrongPlace.3", "Ich bin nicht sicher ob das da hin paßt!",
		"item.take.wrongPlace.4", "Nein, also das kann ich da nicht tragen, " + np + ".",		
		"item.take.cannotUseIt.1", "Also $1 steht mir nun wirklich nicht!",
		"item.take.cannotUseIt.2", "Nein " + np + ", ich habe auch ein Gefühl für Mode!",
		"item.take.cannotUseIt.3", "Das kann ich beim besten Willen nicht tragen, " + np + ".",
		"item.take.cannotUseIt.4", "Mit $1 kann ich aber wirklich nicht umgehen.",		
		"inventory.carry", "Zu schleppen:  $1 / $2",		
		"ifc.itemTaken", "$1 genommen.",
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
		"intro.vhtalk.5", "Jawoll!",
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
		"telep.prisma.funny.1", "Hihi, das kitzelt!", 
		"telep.prisma.funny.2", "Whoooaa... das macht Spaß!",
		"telep.prisma.funny.3", "Wusch....",
		"telep.prisma.funny.4", np + ", wie funktioniert das eigentlich genau?",
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
		"fight.attack.metzel.noWeapon", "$1 hat gar kine Waffe in der Hand!",
		"fight.attack.metzel.hit", "$1 stürzt sich mutig auf die Gegner, und trifft  $2  Monster!",
		"fight.attack.metzel.fail", "$1 stürzt sich mutig auf die Gegner, aber kann nichts bewirken!",				
		"fight.attack.police.hit", "$1 greift rigoros ein, und verhaftet  $2  Monster!",
		"fight.attack.police.fail", "$1 greift rigoros ein, kann aber nichts bewirken.",		
		"fight.attack.rempel.hit", "$1 rempelt sich gegen die Monster, und trifft  $2  Monster!",
		"fight.attack.rempel.fail", "$1 rempelt sich gegen die Monster, kann aber nichts bewirken.",		
		"fight.attack.slime.hit", "$1 zündet die Schleim-Bombe, und verklebt  $2  Monster!",
		"fight.attack.slime.fail", "$1 zündet die Schleim-Bombe, kann aber keine Monster verkleben!",		
		"fight.weapon.attack.hit", "$1 attackiert mit $2, und trifft für  $3  FunPoints!",
		"fight.weapon.attack.miss", "$1 attackiert mit $2, aber haut ins Leere!",
		"fight.weapon.attack.fail", "$1 attackiert mit $2, kann aber keine FunPoints rauben!",
		"fight.weapon.attack.noWeapon", "$1 hat gar keine Waffe in der Hand!",		
		"fight.use.shaolin.ok", "$1 versprüht das Shaolin-Pulver, und die Party fängt an zu fliegen!",
		"fight.use.shaolin.twice", "$1 versprüht das Shaolin-Pulver, aber die Wirkung kommt nicht mehr zum tragen!",
		"fight.use.incrFunPoints", "$1 nimmt  $2  zu sich und ist mit mehr Spaß bei der Sache!",		
		"fight.win", "$1 <br> besiegen die Monster und erhalten <br> $2 <br> Chips!",		
		"fight.title.against", "$1 kämpfen gegen",		
		"fight.defaultAttack.hitsWithDefense", "und trifft trotz Gegenwehr für   $1   FunPoints!",
		"fight.defaultAttack.hits", "und trifft für   $1   FunPoints!",
		"fight.defaultAttack.canDefense", "aber wird erfolgreich abgewehrt!",
		"fight.defaultAttack.notHitCharm", "aber kann ihm nicht den Spaß verderben!",		
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
		"quicksave.cannot.isnew", "Noch kein Schnellspeichern möglich - Spiel ist neu.",
		"quicksave", "Spiel  '$1'  gespeichert.",			
		"fitPoints.eat.full.1", "Danke, ich habe genug.",
		"fitPoints.eat.full.2", "Booa.... nee " + np + " jetzt is echt mal gut.",
		"fitPoints.eat.full.3", "Ich bin voll.",
		"fitPoints.eat.full.4", "Nein Danke.",
		"fitPoints.eat.thankyou.1", "Ja, das ist gut!",
		"fitPoints.eat.thankyou.2", "Ahhh, mjam, mjam.",
		"fitPoints.eat.thankyou.3", "Vielen Dank, Sir " + np,
		"fitPoints.eat.thankyou.4", "Lecker! Danke, " + np,
		"gameload.welcome.1", "Hallo, " + np + "!",
		"gameload.welcome.2", "Hi, " + np +"! Alles klar?",
		"gameload.welcome.3", "Willkommen " + np + "!",
		"gameload.welcome.4", "Guten Tag " + np,
		"member.info.overweight.1", "Puh, ich muß echt viel mit mir rumtragen...",
		"member.info.overweight.2", "Ey, " + np + ", kannst du nicht runterkommen und mitragen?",
		"member.info.overweight.3", "Also für diese Plackerei habe ich normalerweise meine Vasallen.",
		"member.info.overweight.4", "Wartet, ich habe kann nicht so schnell mit meinem vielen Gepäck!",		
		"member.info.littleFun.1","Vielleicht mach' ich mich bald selbständig!",
		"member.info.littleFun.2","Ich hab' bald keine Lust mehr, wenn das hier so weitergeht!",
		"member.info.littleFun.3","Ich könnte mal wieder mein Königreich besuchen.",
		"member.info.littleFun.4","Ich will ja nicht stören " + np + ", aber ich habe bald keine FunPoints mehr.",		
		"member.info.noHouse.1", "Ich weiß ja nicht ob dir das aufgefallen ist " + np + ", aber ich habe keine Hose an!",
		"member.info.noHouse.2", "Haha, ein König ohne Hosen!",
		"member.info.noHouse.4", "Irgendetwas stimmt an mir nicht...",
		"menu.main.link", "Besuche uns unter www.captainsoft.de",
		"menu.main.copyright", "Copyright 1995-2012 Captainsoft. All rights reserved.",
		"menu.main.version", "Version $1",
		"menu.main.new", "Neues Abenteuer beginnen",
		"menu.main.load", "Abenteuer laden",
		"menu.main.save", "Abenteuer abspeichern",
		"menu.main.back", "Zurück zum Abenteuer",		
		"menu.main.settings", "Formulare",
		"menu.main.quit", "Spiel beenden",		
		"menu.save.caption", "Abenteuer speichern",
		"menu.save.button", "speichern",			
		"menu.load.caption", "Abenteuer laden",
		"menu.load.button", "laden",
		"menu.settings.namePlayer", "Name des Spielers",
		"menu.settings.nameParty", "Name der Party",
		"menu.settings.caption", "Formulare",
		"menu.settings.page", "umblättern",		
		"menu.button.yes", "Ja!",
		"menu.button.no", "Nein!",
		"menu.quit.really","Spiel wirklich beenden, " + np + "!? <br> Hast Du gespeichert?",		
		"menu.settings.playMusic", "Musik abspielen",
		"menu.settings.playSounds", "Sounds abspielen",
		"menu.settings.walkSpeed", "Laufgeschwindigkeit",
		"menu.settings.walkSlow", ">>",
		"menu.settings.walkFast", "Schnell",				
		"teleporter.misuse.4", "Hier unten funktioniert der Teleporter nicht!",
		"teleporter.misuse.5", "Hier unten funktioniert der Teleporter nicht!",
		"teleporter.misuse.18", "Wir sind wohl zu weit oben für den Teleporter...",
		"teleporter.misuse.19", "Hier funktioniert der Teleporter wohl nicht!",
		"teleporter.misuse.20", "Hier funktioniert der Teleporter bestimmt nicht.",				
		"tile.coffeeAutomaton.1", "Ahh, Kaffee wie bei uns auf dem Revier!",
		"tile.coffeeAutomaton.2", "Das Zeug könnt' ich mir den ganzen Tag 'reinkippen!",
		"tile.coffeeAutomaton.3", "Da macht die Sache doch gleich mehr Spaß!",
		"tile.coffeeAutomaton.4", np + ", eine hervorragende Idee von Dir!",
		"tile.useAutomation.noChips.1", "Zu wenig Chips...",
		"tile.useAutomation.noChips.2", "Nanu " + np + ", wo sind denn alle Chips geblieben?",
		"tile.useAutomation.noChips.3", "Och, dafür haben wir keine Chips mehr.",
		"tile.useAutomation.noChips.4", "Dafür bräuchten wir $1 Chips!",
		"shop.hello", "\"Was wollt ihr kaufen?\"",
		"shop.dontwantit", "\"Das möchte ich nicht!\"",
		"shop.solditem", "\"Hier habt ihr  $1  Chips!\"",
		"shop.solditemscroll", "$1 verkauft.",
		"shop.buyitem", "\"Danke für die  $1  Chips!\"",
		"shop.buyitemscroll", "$1 gekauft.",
		"shop.buyitemnomoney", "\"Leider habt ihr zu wenig Chips!",
		"x", "-",
		};		
		return data;
	}

}
