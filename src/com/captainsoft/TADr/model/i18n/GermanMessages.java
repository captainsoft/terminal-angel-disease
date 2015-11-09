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
 * German i18n gui messages.
 *
 * @author mathias fringes
 */
public final class GermanMessages implements GuiMessages {
	
	public String[] data() {
		String[] data = {
        "press.f1.forHelp", "Drücke F1 für Hilfe.",
		"chest.needsKeyNoDietrich.1", "Seltsam... der Dietrich funktioniert hier nicht!",
		"chest.needsKey.1", "Diese Kiste bekomme ich beim besten Willen nicht auf!",
		"chest.cannotOpen.1", "Puh, diese Truhe bekomme ich noch nicht geknackt!",
		"chest.found", "Ihr findet",
		"chiliStuff.dontEat.1", "Bäh. Das riecht ja widerlich!",
		"chiliStuff.dontEat.2", "Ich esse ja fast alles. Aber das hier nie im Leben!",
		"chiliStuff.dontEat.3", "Nein " + np + ", das esse ich auch nicht Dir zuliebe.",
		"chiliStuff.dontEat.4", "Uh. Nein, danke, das möchte ich wirklich nicht.",
		"equiq.left.1", "Kopf",
		"equiq.left.2", "Körper",
		"equiq.left.3", "Beine",
		"equiq.right.1", "Waffe",
		"equiq.right.2", "Gürtel",
		"equiq.right.3", "Schutz",				
		"party.special.1.a", "Gefecht",
	    "party.special.1.b", "Sichern",
	    "party.special.1.c", "Polizeigriff",
	    "party.special.1.d", "Schlösser knacken",	   
	    "party.special.2.a", "Prügeln",
	    "party.special.2.b", "Zurückprügeln",
	    "party.special.2.c", "Umrempeln",
	    "party.special.2.d", "Zaubersprüche",	    
	    "party.special.3.a", "Duellieren",
	    "party.special.3.b", "Parieren",
	    "party.special.3.c", "Berserker",
	    "party.special.3.d", "Magische Formeln",	    
	    "party.special.4.a", "Scharmützeln",
	    "party.special.4.b", "Raushalten",
	    "party.special.4.c", "Chem. Reaktion",
	    "party.special.4.d", "Schriften",		
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
		"item.info.type." + Shaolin.id(), "$itm! Pulver, das uns fliegen lässt!",
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
		"item.use.fightOnly.4", "Nein, das wäre jetzt zu gefährlich!",
		"item.use.funPointsMax.1", "Du machst einen tollen Job, " + np + "!",
		"item.use.funPointsMax.2", "Schleck. Mampf.",
		"item.use.funPointsMax.3", "Mhmm. Los Jungs, laßt uns weiterziehen!",
		"item.use.funPointsMax.4", "Danke sehr, " + np + "!",
		"item.use.fitPointsMax.1", "Besser als Training!",
		"item.use.fitPointsMax.2", "Ich bin der Allerstärkste!",
		"item.use.fitPointsMax.3", "Armdrücken, " + np + "?",
		"item.use.fitPointsMax.4", "Ja, ich fühle mich nun stärker.",
		"item.use.foxPointsMax.1", "Nun kann ich noch besser ermitteln!",
		"item.use.foxPointsMax.2", "Die Wurzel aus $1 ist $2.",
		"item.use.foxPointsMax.3", "Nun ist der Groschen gefallen!",
		"item.use.foxPointsMax.4", "Wer unterhält sich mit mir über Astrophysik?",		
		"item.take.wrongPlace.1", "Also da passt das nun wirklich nicht hin, " + np + ".",
		"item.take.wrongPlace.2", "So soll ich hier 'rumlaufen? Keine Chance!",
		"item.take.wrongPlace.3", "Ich bin nicht sicher, ob das da hin paßt!",
		"item.take.wrongPlace.4", "Nein, also das kann ich da nicht tragen, " + np + ".",		
		"item.take.cannotUseIt.1", "Also, $1 steht mir nun wirklich nicht!",
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
		"intro.vhtalk.13", "Lasst uns ihnen sofort ein E-Rrlicht schicken!",
		"intro.drucing.1", "Detective Summerkamp",
		"intro.drucing.2", "Kurtis der Blob",
		"intro.drucing.3", "Ritter Ole Ozelot",
		"intro.drucing.4", "Professor Zett",
		"intro.irrlicht.1", "Ein Spiel von Captainsoft.",
		"intro.irrlicht.2", "Programmiert abends und an den Wochenenden.",
		"intro.irrlicht.3", "Grafiken sind heimgewerkelt.",		
		"intro.irrlicht.4", "Sound-Effects von DJ Fresh Tzaziki.",
		"intro.irrlicht.5", "Musik (nicht im Spiel enthalten) von Captain Burschi and the Pogo Jumpers",
		"intro.irrlicht.6", "Written and directed by Captainsoft",
		"telep.prisma.funny.1", "Hihi, das kitzelt!", 
		"telep.prisma.funny.2", "Whoooaa... das macht Spaß!",
		"telep.prisma.funny.3", "Wusch...",
		"telep.prisma.funny.4", np + ", wie funktioniert das eigentlich genau?",
		"fight.learn.weapon.1", "Ich werde im Gefecht immer besser mit  $1  Punkten!",
		"fight.learn.weapon.2", "Verkloppen macht mit  $1  Punkten noch mehr Spaß!",
		"fight.learn.weapon.3", "Ich kann mich nun mit  $1  Punkten duellieren!",
		"fight.learn.weapon.4", "Die Wissenschaft des Scharmützelns erschliesst sich mir nun mit  $1  Punkten!",
		"fight.learn.magicItem.2", "Diese Schriftrollen kann ich nun  $1  Punkten einsetzen!",
		"fight.learn.magicItem.3", "Mit Zaubersprüche kann ich nun mit  $1  Punkten umgehen!",
		"fight.learn.magicItem.4", "Schriftrollen kann ich nun mit  $1  Punkten benutzen!",
		"fight.learn.magicItemChem.4", "Ich habe im Fach chemische Reaktionen nun  $1  Punkte!",				
		"fight.learn.special.1", "Ich hab  $1  Punkte im Fach Polizeigriff!",
		"fight.learn.special.2", "Haha! Ich habe beim Umrempeln die  $1  Punktemarke geknackt!",
		"fight.learn.special.3", "Ich habe im Berserken nun  $1  Punkte!",		
		"fight.attack.magic.multi.hit", "$1 zaubert $2, und trifft  $3  Monster!",
		"fight.attack.magic.multi.fail", "$1 zaubert $2, aber trifft kein Monster!",
		"fight.attack.magic.single.hit", "$1 zaubert $2, und trifft für  $3  FunPoints!",
		"fight.attack.magic.single.fail", "$1 zaubert $2, kann aber keine Wirkung erzielen!",		
		"fight.attack.chem.multi.hit", "$1 mischt $2, und trifft  $3  Monster!",
		"fight.attack.chem.multi.fail", "$1 mischt $2, aber trifft kein Monster!",
		"fight.attack.chem.single.hit", "$1 mischt $2, und trifft für  $3  FunPoints!",
		"fight.attack.chem.single.fail", "$1 mischt $2, kann aber keine Wirkung erzielen!",		
		"fight.attack.bomb.multi.hit", "$1 wirft $2, und trifft  $3  Monster!",
		"fight.attack.bomb.multi.fail", "$1 wirft $2, aber trifft kein Monster!",
		"fight.attack.bomb.single.hit", "$1 wirft $2, und trifft für  $3  FunPoints!",
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
		"fight.defaultAttack.hitsWithDefense", "und trifft trotz Gegenwehr für  $1  FunPoints!",
		"fight.defaultAttack.hits", "und trifft für  $1  FunPoints!",
		"fight.defaultAttack.canDefense", "aber wird erfolgreich abgewehrt!",
		"fight.defaultAttack.notHitCharm", "aber kann ihm nicht den Spaß verderben!",
		"fight.defaultAttack.intoTheVoid", "aber haut ins Leere!",
        "fight.gameover", "Leider hat <br> $1 <br> die Gruppe aufgrund kreativer Differenzen verlassen.",
		"outro.thatWas", "Und das war",
		"outro.madeBy", "Erdacht und geschaffen von",
		"outro.playedBy", "Gespielt von",
		"outro.andPlayedBy", "und",
		"outro.starring", "mit dabei:",		
		"outro.starring.11", "Chili-Tschong Kahn",
		"outro.starring.12", "als der böse Meister des Dungeons",
		"outro.starring.21", "Freitagmännchen",
		"outro.starring.22", "als der psychopatische Helfer des bösen Meisters",
		"outro.starring.31", "Die Verhaltensweisen",
		"outro.starring.32", "als die drei Weisen",
		"outro.starring.41", "Justin Chamberlain",
		"outro.starring.42", "als der Held des anderen Spiels",
		"outro.starring.51", "The Bruder von 'Chuck, die Pflanze'",
		"outro.starring.52", "als ein Rhododendron",
		"outro.starring.61", "Jutta die Prinzessin",
		"outro.starring.62", "nicht wirklich mit dabei",
		"outro.alsoStarring", "auch mit dabei",
		"outro.alsoStarring.1", "Ampel-Männchen <br> Big Star <br> Bobby <br> Bürgermeister Schulze <br> Captain N! <br> The Cavel Guys <br> Commander Blop <br> Dienstagsmännchen <br> Dipl.Ing Ringring",
		"outro.alsoStarring.2", "Donnerstagsmännchen <br> Mister Sparkel <br> Fräulein Honig <br> Hubert <br> Sonntagsmännchen <br> General Fu-Huh-Sang <br> Grimaldi <br> Mistel Ling <br> Ein paar Emus",
		"outro.alsoStarring.3", "Eyk, der Emu-Mann <br> Flying Zett <br> GIGANTUS <br> Holla <br> Goldie <br> Kurle Wosch <br> Little Star <br> Misses Storck <br> Get-a-Quest Computer <br> Mister Klaar <br> Jeffrey <br> Tee Schobber",
		"outro.alsoStarring.4", "Mister Pani <br> Mister Tar <br> Mittwochsmännchen <br> Monsieur Bradfish <br> Montagsmännchen <br> Muck, der Magier <br> Nowsed <br> Script <br> Mister Pizza <br> Herr Fred <br> Renko",
		"outro.alsoStarring.5", "Marvin <br> Samstagsännchen <br> Owsed <br> Hans <br> The friendly Saur <br> TimeShop Clerk <br> Türsteher <br> Wilbur <br> E-Rrlicht <br> <br> and the <br> JoJo-Jazz Ensemble",
		"outro.cakeguystalk.1", "Meinst Du die bringen uns die Torte noch vorbei?",
		"outro.cakeguystalk.2", "Vielleicht.",
		"outro.cakeguystalk.3", "Die müßten doch schon längst wieder da sein!",
		"outro.cakeguystalk.4", "Ich weiß.",
		"outro.cakeguystalk.5", "Mir ist kalt.",
		"outro.cakeguystalk.6", "Mir auch.",		
		"outro.cakeguystalk.7", "Ich hab Hunger.",
		"outro.cakeguystalk.8", "Halt die Klappe!",			
		"outro.thankYou", "Captainsoft möchte den folgenden Personen danken:",
        "outro.thankYou.1", "Fringes Familie, allen Berlin Freunden, allen Münster Freunden, GM <br> "
                + "Sana Gesundheitszentrum Berlin <br> "
                + "FH Trier, University of Westminster <br> "
                + "Wizardry 7, Ultima 7, Indiana Jones <br> "
                + "Pepsi-Cola, Heineken, Galeria Kaufhof, Pizza XXL <br> "
                + "PC Player, Kultpower.de <br> "
                + "Robert DeNiro, Jeff Hanneman <br> "
                + "Tempo90, Berghain <br> "
                + "At The Gates - für den Titel <br> ",
        "outro.thankYou.2", "Alexander Krug & the DUS <br> "
                + "Painkilla & GameDev Page <br> "
                + "Developia, TIGF, Gamejolt, Java Gaming Community <br> "
                + "EgonOlsen, Karl Wessel, Thorsten Lüders, Bene Heying, Toby Bürkle, Arun + Steffi <br> "
                + " <br> <br> <br> <br> <br> "
                + "... und allen die hier bestimmt vergessen wurden! <br> ",
		"outro.valloSchaunffIndianaJonesTalk.1", "\"Ah, Bunga Terra Tissa Tu!\"",
		"outro.valloSchaunffIndianaJonesTalk.2", "\"Hi, Ich verkaufe diese tollen Lederjacken.\"",
		"outro.valloSchaunffIndianaJonesTalk.3", "\"Hinter Dir, ein dreiköpfiger Affe!\"",
		"outro.valloSchaunffIndianaJonesTalk.4", "\"Ach...\"",
		"outro.weUse", "Captainsoft verwendet",
        "outro.weUseList","IntelliJ IDEA Community Edition 13.1 <br> "
                + "Ulead PhotoImpact 12 <br> "
                + "Samsung Laptops <br> "
                + "Microsoft Tastaturen <br> "
                + "Logitech Mäuse <br> "
                + "Jackson Gitarren <br> "
                + "Replay Perfüm <br> "
                + "Pepsi, Heineken, Pizza XXL, Balisto <br> ",
		 "outro.starConversation.1", "Gute Arbeit, Little Star! So wie <br> ich das sehe, sind alle Monster dingfest. <br> Wir haben es mal wieder geschafft.",
		 "outro.starConversation.2", "Und jetzt Chef? Gibt es Urlaub?",
		 "outro.starConversation.3", "Nein. Sie gehen jetzt Donuts kaufen.",		        
		 "outro.starConversation.4", "Ich fliege, Chef!",		 
		 "outro.endAndTeaser", "Besuche Captainsoft unter <br> "
			   + "www.captainsoft.de <br> "
			   + "und trag Dich ins Gästebuch ein (oder facebook)! <br> <br> <br> <br> <br> "
			   + "Summerkamp, Kurtis, Ole & der Professor <br> "
			   + "kommen wieder in TAD 2: Ankunft der Chili-Götter",
		"outro.freitagmanLast", "Nach dem erfolgreichem Besuch einer Kaugummientzugsklinik <br> arbeitet Freitagmännchen nun halbtags als Wochenende <br> in der Karibik.",
	    "outro.copyright", "(c) " + TAD.Copyright + " Captainsoft. <br> www.captainsoft.de",
	    "party.protect", "Rüstung:  ",
		"quicksave.cannot.isnew", "Noch kein Schnellspeichern möglich - Spiel ist neu.",
		"quicksave", "Spiel  '$1'  gespeichert.",			
		"fitPoints.eat.full.1", "Danke, ich habe genug.",
		"fitPoints.eat.full.2", "Booa... nee " + np + " jetzt is echt mal gut.",
		"fitPoints.eat.full.3", "Ich bin voll.",
		"fitPoints.eat.full.4", "Nein Danke.",
		"fitPoints.eat.thankyou.1", "Ja, das ist gut!",
		"fitPoints.eat.thankyou.2", "Ahhh, mjam, mjam.",
		"fitPoints.eat.thankyou.3", "Vielen Dank, Sir " + np + ".",
		"fitPoints.eat.thankyou.4", "Lecker! Danke, " + np + ".",
		"gameload.welcome.1", "Hallo, " + np + "!",
		"gameload.welcome.2", "Hi, " + np +"! Alles klar?",
		"gameload.welcome.3", "Willkommen " + np + "!",
		"gameload.welcome.4", "Guten Tag " + np + ".",
		"member.info.overweight.1", "Puh, ich muss echt viel mit mir rumtragen...",
		"member.info.overweight.2", "Ey, " + np + ", kannst du nicht runterkommen und mitragen?",
		"member.info.overweight.3", "Also für diese Plackerei habe ich normalerweise meine Vasallen.",
		"member.info.overweight.4", "Wartet, ich habe kann nicht so schnell mit meinem vielen Gepäck!",		
		"member.info.littleFun.1", "Vielleicht mach' ich mich bald selbständig!",
		"member.info.littleFun.2", "Ich hab' bald keine Lust mehr, wenn das hier so weitergeht!",
		"member.info.littleFun.3", "Ich könnte mal wieder mein Königreich besuchen.",
		"member.info.littleFun.4", "Ich will ja nicht stören " + np + ", aber ich habe bald keine FunPoints mehr.",
		"member.info.noHouse.1", "Ich weiß ja nicht ob dir das aufgefallen ist " + np + ", aber ich habe keine Hose an!",
		"member.info.noHouse.2", "Haha, ein König ohne Hosen!",
		"member.info.noHouse.4", "Irgendetwas stimmt an mir nicht...",
		"menu.main.link", "Besuche uns unter www.captainsoft.de",
		"menu.main.copyright", "Copyright " + TAD.Copyright + " Captainsoft. All rights reserved.",
		"menu.main.version", "Version $1",
		"menu.main.new", "Neues Abenteuer beginnen",
		"menu.main.load", "Abenteuer laden",
		"menu.main.save", "Abenteuer abspeichern",
		"menu.main.back", "Zurück zum Abenteuer",		
		"menu.main.settings", "Formulare",
		"menu.main.quit", "Spiel beenden",
        "menu.main.about", "Über",
        "menu.main.linkToHomepage", "http://www.captainsoft.de",
        "menu.main.about.thankYou", "Dankeschön",
        "menu.main.about.txt", "The Terminal Angel Disease wurde gemacht von" + br + "Captainsoft (Mathias Fringes)" + br +
                "während 1996 bis 2015." + br2 +
                "Unterstütze' uns via:" + br +
                "www.captainsoft.de" + br +
                "www.facebook.com/TheTerminalAngelDisease" + br +
                "twitter.com/KurtisTheBlob" + br +
                "indiedb.com/games/the-terminal-angel-disease" + br2 +
                "Drücke F1 im Spiel für Hilfe.",
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
        "helpWindow.title", "Hilfe!",

		"helpWindow.1",
			"Hallo " + np + ", hier sind ein paar Tipps für Dich und " + prt + ":" + br2 +
		    "Die Action-Welt:" + br +
		    " * Linker Klick: Laufen, Truhen öffnen, mit Leuten reden, Schalter betätigen..." + br +
		   "  * Rechter Klick: Gegenstände aufnehmen und ablegen." + br +
		   "  * Leg einen Gegenstand auf dem Partymitglied ab, um es im nächsten verfügbaren Platz im Rucksack abzulegen!" + br2 +
		   			     
		   " Rucksack:" + br +
		   " * Linker Klick: Profunde Gegenstand-Analyse" + br +
           " * Doppelklick: Essen, Schlüssel und andere Gegenstände benutzen, Bücher lesen." + br +
		   " * Rechter Klick: Gegenstände aufnehmen und ablegen." + br2 +

            " Unterhalten:" + br +
            "  * Klicke auf den Kopf einer Person, um sich mit ihr zu unterhalten." + br +
            "  * Einige Personen verhalten sich entsprechend, wenn im Rucksack ein für sie interessanter Gegenstand vorhanden ist." + br2 +

            " Geschäfte:" + br +
            "  * Einen Gegenstand aus dem Shop aufnehmen um zu kaufen." + br +
            "  * Einen Gegegenstand auf den \"Verkaufen\" Knopf ... verkauft!" + br2

		   ,
	  "helpWindow.2",
			   " Kämpfen:" + br +
			   "  * Rechter Klick auf dem kleinen Monster-Icon: gesamte Party konzentriert sich auf dieses Monster." + br +
			   "  * Rechter Klick auf \"X\" oben rechts: Aufgeben :o" + br +
			   "  * Ist ein Monster getroffen, kann es Euch nicht angreifen ;)" + br +
			   "  * Der Einsatz von Kampftaktiken verbessert die Fähigkeiten!" + br2 +

               " Schnellzugriff:" + br +
               "  * 1-4: Rucksäck anzeigen." + br +
               "  * F1: Hilfe!" + br +
               "  * F2: Das Quest-Hühnchen" + br +
               "  * F3: Spiel-Information." + br +
               "  * F5: Schnellspeichern." + br +
               "  * F12: Chef Taste (benutz' sie verantwortungsbewusst!)." + br +
               "  * awsd, Pfeiltasten: Herumlaufen." + br +
               "  * Space: Stoppen, nächstes Gespräch." + br +
               "  * ESC: Diskmenu",

      "infoWindow", 
			"Captainsoft's" + br +
			"The Terminal Angel Disease" + sep +			
			"* $1  -  $2 " + sep +				
			"Besuche unsere Seite für Neuigkeiten" + sep +
			"*www.captainsoft.de" + sep +
			"Bitte unterstütze uns und werde Fan auf Facebook" + sep +			  
			"*www.facebook.com/TheTerminalAngelDisease" + sep +			  
			"Bitte auch mit Euren Freunden teilen! Je mehr Fans wir bekommen," + br +
			"umso mehr können wir an The Terminal Angel Disease arbeiten!" + sep +			  
			"Viel Spaß beim Spielen!" + br +
			"Speichern früh, speichern oft :)",


        "questWindow.title",       "Der Report vom Quest-Hühnchen",
        "quest.m1.WiseGuys",       "Gehen wir erstmal zu den Verhaltensweisen um herauszufinden was wir in diesem Spiel überhaupt zu tun haben. Deren Büro befindet sich im Nordwesten der Stadt, jenseits der großen Brücke.",
        "quest.m1.EmuEggBradfish", "Ein klassiches Quest: Besorgen wir ein Emu-Ei und bringen es zu dem Koch mit dem komischen Akzent. Das Ei bekommen wir auf Eyks Emu-Farm am östlichen Stadtrand!",
        "quest.m1.TransitAmpel",   "Eine sprechende Ampel? Ist das nicht ein Cocktail? Aber wir brauchen wohl das Transitvisum um die große Brücke überqueren zu können.",
        "quest.m1.searchForEvidence","Bürgermeister Schulze hat also was mit der Nummer zu tun. Wer hätte das gedacht? ;) Wir müssen unbedingt Beweismittel sammeln und ihn damit konfrontieren.",
        "quest.m1.HarborShipLost", "Der Ausflugsdampfer mit ein paar Touristen an Bord ist nicht zurückgekehrt. Vielleicht haben sie ein Spiel mit besserer Grafik entdeckt? Hinweis: In diesem Spiel gibt es eigentlich KEIN Boot.",

        "quest.m2.NoPassGrimaldi", "Natürlich brauchen wir wieder ein Passwort um an dem waffenstarrendem Stadtwächter vorbeizukommen.",
        "quest.m2.NoPassAtStars",  "Die Polizeisterne wollen uns das Passwort der Stadtwache nicht mitteilen (weil Kurtis Unfug getrieben hat). Naja, sie wollen es ja eh ändern wenn wir sie nicht mehr belauschen.",
        "quest.m2.findJustinCave", "Nur mit dem heiligen Code-Wheel können wir den Deep Deep Dungeon betreten. Das ist in der großen Höhle unter dem Justin Chamberlain Denkmal versteckt. Bitte lasst uns aber Proviant einkaufen bevor wir da runter gehen! :)",
        "quest.m2.findRingRing",   "Sprechen wir nun mit Dipl.Ing Ringring. Er war der Level-Designer des Deep Deep Dungeon, und kann uns sicher mehr darüber erzählen.",

        "quest.m3.schulzTelporter","Mit dem Schlüssel des Bürgermeisters können wir den Teleporter zu Chili-Tschong Kahn verwenden. Dieser befindet sich auf einem lauschigem Plateu ganz am Ende dieses Spiels - wo auch sonst?",

        "quest.m5.Gigantus",       "Die armen - aber igendwie süßen - Cavel Guys wollen dass wir GIGANTUS erledigen. Eigentlich ist das hier kein Killerspiel, aber sie lassen uns sonst nicht aus dieser schrecklichen Höhle raus.",

        "quest.m6.Waldfgee",       "Was für eine wunderschöne Waldbraut! Aber leider besteht sie darauf, dass wir zunächst irgend'nen paar heilige Schlüssel oder was finden. Alternativ könnten wir sie ja auch mit einer Stange Zigaretten bestechen!?",

        "quest.m7.getBook",        "Der kleine Waldschrat hat sein Buch in den westlichen Bergen verlegt. Also holen wir das Ding, bringen es ihm, kassieren die Belohnung, Finito.",
        "quest.m7.toTehDungeon",   "Wir haben die Eintrittskarte für 3 Erwachsenen und einen Blob. Die zeigen wir dann an der Kasse des Deep Deep Dungeon vor. Könnte nicht alles so einfach sein?",

        "quest.m9.Justin1",        "Justin Chamberlain! Was für ein netter Kerl! Er denkt ein gewisser Chili-Tschong Kahn ist der Endboss in diesem Spiel. Mit seinem Schlüssel können wir ihm entgegentreten! (Warum macht er das nicht selber?)",
        "quest.m9.Justin2",        "Das original Deep Deep Dungeon hatte natürlich einen Kopierschutz - wie alle Spiele zur damaligen Zeit. Ohne kommen wir nicht mal in das erste Level!",

        "quest.m13.cold",          "Der Typ hat sich aber eine gehörige Erkältung zugezogen. Kein Wunder hier im feuchtem Dungeon-Klima. Er braucht wohl ein richtig großen Taschentuch.",
        "quest.m13.keysToDungeonCells", "Von der Triefnase haben wir den Schlüssel zum Dungeon-Eingang hinter den Gefängniszellen bekommen! Was wird uns dort erwarten? Mehr Monster, mehr Schokolade?",
        "quest.m13.FridayGum",     "Freitagmännchen braucht wohl Kaugummi um normal sprechen zu können. Die alte Zuckerschnute.",
        "quest.m13.FindBeweisBunga","Anscheinend hat Chili-Tschong Kahn einen Komplizen. Er - oder sie? - lebt in einer Villa in Bunga Terra Tissa Tu. Wir steigen durch die Hintertür ein und nehmen das Haus auseinander.",

        "quest.m15.questMachineTalk","Wir müssen das schäbige Mikrofon von unseren Absichten überzeugen. Wo bekommen wir ein stichfestes, passendes Quest her?",

        "quest.m16.chcolata",      "Die beiden Hohlpfosten wollen eine Schokoladentorte von uns. Hier unten?!? Die komische Barriere sieht eh etwas wackelig aus.",

        "quest.m18.lattenGame",    "Oha, eine Action-Adventure Einlage? Wir sollen das Lattenlabyrinth in 10 Sekunden durchqueren.",

        "quest.m19.saltTheChiliKing", "Was wir in diesem Spiel gelernt haben: Konzernbosse sind immer böse, gemein und sadistisch. Dem Typen versalzen wir jetzt seinen Kaffee und schicken ihn in Rente.",

        "quest.m20.toTheOfficeRoom", "Warum sollten sie auch die Büros für eine Truppe wie uns öffnen. Wir brauchen mehr Bürokratie! Jetzt!",


		"x", "-",		
		};		
		return data;
	}

}
