/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.fight.ui;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.gui.boxes.common.BarBox;
import com.captainsoft.TADr.model.fight.Fight;
import com.captainsoft.TADr.model.fight.Monster;
import com.captainsoft.TADr.model.fight.MonsterParty;
import com.captainsoft.TADr.model.fight.PartyFighter;
import com.captainsoft.spark.ui.CDim;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The main window for a fight.
 * 
 * @author mathias fringes
 */
public final class FightWindow extends UiBoxContainer {

	// fields

	public ImageBox giveUpBox;
	public TextBox monsterText;
	public TextBox partyText;
	
	public ActionBox[] actionBoxes = new ActionBox[4];
	public BarBox[] barBox = new BarBox[4];
	public ImageBox[] defenseBox = new ImageBox[4];
	public ImageBox[] hitBox = new ImageBox[4];
	public ImageBox[] hitComicImage = new ImageBox[4];	
	public ImageBox[] puffBox = new ImageBox[4];
	public TextBox[] hitTextBox = new TextBox[4];	
	public UiBoxContainer[] actionPanels = new UiBoxContainer[4];

	public BarBox[] monsterBarBox = new BarBox[5];
	public ImageBox[] hitComicMonsterImage = new ImageBox[5];
	public ImageBox monsterBK;
	public MonsterBox[] monsterBox = new MonsterBox[5];
	public TextBox[] monsterPointsBox = new TextBox[5];

	private final CPos[] points = new CPos[] {new CPos(40, 60), new CPos(160, 65),
										new CPos(270, 67), new CPos(360, 60), new CPos(470, 62)};
	private final float scales[] = { 1f, 0.8f, 0.7f, 1f, 0.9f };
	private final int fonts[] = { 36, 32, 30, 36, 34 };

	// constructors

	public FightWindow(Fight fight, int backgroundScreenNr) {
		super();
		this.name = "FightWindow";
		
		Surface fightScreen = new Surface(TadRepo.inst().ImageLoader().load("fscr", backgroundScreenNr));
		Surface fightBottom = new Surface(TadRepo.inst().ImageLoader().load("fscr", 99));
		
		Surface background = new Surface(625, 394);
		background.blit(fightScreen);
		background.blit(fightBottom, 0, 192);
		
		initBackground(background);

		monsterBK = new ImageBox(background.stamp(9, 49, 607, 143), 9, 49);
		add(monsterBK);
		// 
		// text
		monsterText = new TextBox();		
		monsterText.font = new Font(Fonts.Times, Font.BOLD, 12);
		monsterText.set(30, 7, 560, 34);
		monsterText.stampBackground(background);		
		add(monsterText);
		//
		partyText = new TextBox();		
		partyText.oneLine = true;
		partyText.font = monsterText.font;
		partyText.set(10, 200, 600, 18);
		partyText.stampBackground(background);
		add(partyText);
		//
		// set up monster panels
		for (int i = 0; i < MonsterParty.MAX_PLACED; i++) {
			if (fight.monsterParty.monster(i) != null) {
				Monster monster = fight.monsterParty.monster(i);
				float ratio = scales[i];
				CDim dim = new CDim(117, 125).scale(ratio);
				CPos p = points[i];
				p.add(-9, -49);
				monsterBox[i] = new MonsterBox(dim.width, dim.height, monster);
				monsterBox[i].pos(p.x, p.y);
				monsterBox[i].visible(false);
				monsterBK.add(monsterBox[i]);
				// monster hit
				hitComicMonsterImage[i] = new ImageBox();
				hitComicMonsterImage[i].name = "MonsterHit-ComicBox_" + i;				
				CPos hitPos = new CPos(13, 20).scale(ratio).add(p);
				hitComicMonsterImage[i].pos(hitPos.x, hitPos.y);
				hitComicMonsterImage[i].scale(ratio);
				monsterBK.add(hitComicMonsterImage[i]);
				// points text box
				monsterPointsBox[i] = new TextBox();
				monsterPointsBox[i].oneLine = true;
				monsterPointsBox[i].font = new Font(Fonts.Times, Font.BOLD,
						fonts[i]);
				monsterPointsBox[i].name = "Monster-PointTextBox_" + i;
				monsterPointsBox[i].visible(false);			
				CDim bDim = new CDim(91, 97).scale(ratio);
				CPos tPos = new CPos(bDim.width - 40, bDim.height - 50).scale(0.5f);
				monsterPointsBox[i].set(tPos.x, tPos.y, 40, 40);
				hitComicMonsterImage[i].add(monsterPointsBox[i]);
				// 
				// barbox
				monsterBarBox[i] = new BarBox(monster.hitPoints);
				monsterBarBox[i].visible(false);
				monsterBarBox[i].set(p.x + ((dim.width - 70) / 2), p.y, 70, 5);
				monsterBK.add(monsterBarBox[i]);
			}
		}
		for (int i : new int[] { 0, 3 }) {
			if (monsterBox[i] != null) {
				monsterBK.setToTop(monsterBox[i]);
				monsterBK.setToTop(hitComicMonsterImage[i]);
			}
		}
		//
		// set up action panels
		for (int i = 0; i < 4; i++) {
			actionPanels[i] = new UiBoxContainer("actionPanel [" + i + "]");
			actionPanels[i].visible(false);
			actionPanels[i].set(i * 140 + 40, 228, 123, 155);
			actionPanels[i].initBackground(background.stamp(i * 140 + 40, 228, 123, 155));
			add(actionPanels[i]);
		}
		//
		// action boxes
		for (int i = 0; i < 4; i++) {
			PartyFighter fighter = fight.fighters[i];
			ActionBox ab = new ActionBox(fighter, fight.monsterParty);
			actionBoxes[i] = ab;
			actionPanels[i].add(ab);
		}
		//
		// attack (out of puste)
		Surface as = new Surface(TadRepo.inst().ImageLoader().load("ifc", 14));
		for (int i = 0; i < 4; i++) {
			puffBox[i] = new ImageBox(as);
			puffBox[i].name = "PusteBox [" + i + "]";
			puffBox[i].pos(18, 26);
			puffBox[i].visible(false);

			barBox[i] = new BarBox();
			barBox[i].name = "PusteLine [" + i + "]";
			barBox[i].color = new Color(128, 128, 255);
			barBox[i].set(10, 62, 68, 7);
			puffBox[i].add(barBox[i]);
			barBox[i].stampBackground(as);
			actionPanels[i].add(puffBox[i]);
		}
		// 
		// defense state
		Surface df = new Surface(TadRepo.inst().ImageLoader().load("ifc", 12));
		for (int i = 0; i < 4; i++) {
			defenseBox[i] = new ImageBox(df);
			defenseBox[i].name = "DefenseBox [" + i + "]";
			defenseBox[i].pos(18, 25);
			defenseBox[i].visible(false);
			actionPanels[i].add(defenseBox[i]);
		}
		//
		// hit by monster
		Surface mas = new Surface(TadRepo.inst().ImageLoader().load("ifc", 13));
		for (int i = 0; i < 4; i++) {
			hitBox[i] = new ImageBox(mas);
			hitBox[i].name = "HitBox [" + i + "]";
			hitBox[i].visible(false);
			hitBox[i].pos(1, 1);
			//
			// comic
			hitComicImage[i] = new ImageBox();
			hitComicImage[i].pos(15, 25);
			hitBox[i].add(hitComicImage[i]);
			//
			hitTextBox[i] = new TextBox();
			hitTextBox[i].oneLine = true;
			hitTextBox[i].font = new Font(Fonts.Times, Font.BOLD, 34);
			hitTextBox[i].size(60, 50);
			//
			hitBox[i].add(hitTextBox[i]);
			hitTextBox[i].center();
			hitTextBox[i].y -= 8;
			actionPanels[i].add(hitBox[i]);
		}
		// 
		// give up button
		giveUpBox = new ImageBox(new Surface(TadRepo.inst().ImageLoader().load("ifc", 39)));
		giveUpBox.pos(600, 8);
		giveUpBox.visible(false);
		add(giveUpBox);
	}

	// public

	public void showActionPanels() {
		for (int i = 0; i < actionBoxes.length; i++) {
			actionPanels[i].visible(true);
		}
	}
	
	public void showGiveUpBox() {
		giveUpBox.visible(true);
	}

	public void showMonsterBox(int index) {
		monsterBox[index].visible(true);
	}

	public void showMonsterBarBoxes() {
        for (BarBox aMonsterBarBox : monsterBarBox) {
            if (aMonsterBarBox != null) {
                aMonsterBarBox.visible(true);
            }
        }
	}

	public void hideActionPanels() {
        for (UiBoxContainer actionPanel : actionPanels) {
            actionPanel.visible(false);
        }
	}

	public void toDefense(int index) {
		defenseBox[index].visible(true);
		actionBoxes[index].visible(false);
	}

	public void cancelDefense(int index) {
		defenseBox[index].visible(false);
		actionBoxes[index].visible(true);
	}

}
