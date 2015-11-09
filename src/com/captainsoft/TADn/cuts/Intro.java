/*
 * Copyright Captainsoft 2010-2012. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.cuts;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.spark.TextBox;
import com.captainsoft.spark.anim.FrameMx;
import com.captainsoft.spark.anim.Scene;
import com.captainsoft.spark.anim.SceneDSL;
import com.captainsoft.spark.anim.ations.AbstractBoxAnimation;
import com.captainsoft.spark.anim.ations.AnimationChain;
import com.captainsoft.spark.anim.ations.LinearWalkAnimation;
import com.captainsoft.spark.anim.ations.MoveAnimation;
import com.captainsoft.spark.anim.ations.NullAnimation;
import com.captainsoft.spark.anim.ations.PosAnimation;
import com.captainsoft.spark.anim.ations.TextAnimation;
import com.captainsoft.spark.anim.ations.VisibleAnimation;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.BoxUtils;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The real TAD intro !:)
 *
 * @author mathias fringes
 */
public final class Intro extends CutScene {
	
	// fields	
		
	private final int txtXoff = -140;
	private final int txtYoff = -90;
	
	// constructors
	
	public Intro(Updater updater, UiBoxContainer backgroundBox) {
		super(updater, backgroundBox, "intro.");		
	}
	
	// CutScene
	
	@Override
	public Scene createScene(int index) {	
		switch (index) {
			case  1: return captainsoftLogoScene(); 
			case  2: return thisIsTheStory();
			case  3: return formerHomeOf();
			case  4: return fatHeroesDrinking();
			case  5: return untilThisDay();
			case  6: return monsterDance();
			case  7: return verhaltensweisenTalk();		
			case  8: return partyIntroducing();
			case  9: return tadLogo();
			case 10: return irrlichtMove();
			case 11: return goOnToActionWorld();	
			default:
				return null;
		}
	}
	
	@Override
	public String imageLoaderIdentifier() {
		return "intr";
	}

	// private methods
	
	@SuppressWarnings("unused")
	private Scene createPauseScene() {
		SceneDSL sd = createNewScene("PAUSE");
		sd.pause(2);
		System.out.println(sd.getAnimationTable());
		return sd.scene();
	}
	
	private Scene captainsoftLogoScene() {		
		SceneDSL sd = createNewScene("CAPTAINSOFT LOGO");		
		//
		sd.image(image(13), 0, -30, 1.5f, 4.5f);
		//
		sd.pause(0.5f);
		return sd.scene();
	}
	
	private Scene thisIsTheStory() {
		SceneDSL sd = createNewScene("THIS IS THE STORY");		
		//		
		sd.textFade(word("thisisthestory.1"), txtXoff, txtYoff, 1, 6);
		sd.image(image(1), 170, 70, 2, 7);		
		//
		sd.pause(0.5f);
		return sd.scene();
	}	
	
	private Scene formerHomeOf() {
		SceneDSL sd = createNewScene("FORMER HOME OF");		
		//
		sd.textFade(word("formerHomeOf.1"), txtXoff, txtYoff, 1, 11);
		ImageBox deepDungeonBox = sd.image(image(2), 150, 70, 2, 7);
		sd.addAnimation(6, new LinearWalkAnimation(deepDungeonBox, 0.01f, 0, 30, 9));
		//
		sd.textFade(word("formerHomeOf.2"), txtXoff, 20, 6.5f, 12);
		ImageBox justinBox = sd.image(image(3), 170, 80, 7.5f, 13);		
		sd.addAnimation(12.5f, new LinearWalkAnimation(justinBox, new FrameMx(0.01f), 30, 0, 11));
		//
		sd.pause(0.5f);
		return sd.scene();
	}
	
	private Scene fatHeroesDrinking() {
		SceneDSL sd = createNewScene("FAT HEROES DRINKING");		
		//
		sd.textFade(word("fatHeroesDrinking.1"), txtXoff, txtYoff, 1, 6);	
		sd.textFade(word("fatHeroesDrinking.2"), txtXoff, txtYoff, 7, 16);
		sd.image(image(4), 150, 100, 7.5f, 17);
		//
		sd.pause(0.5f);
		return sd.scene();
	}
	
	private Scene untilThisDay() {
		SceneDSL sd = createNewScene("UNTIL THIS DAY");
		//
		sd.textFade(word("untilThisDay.1"), txtXoff, txtYoff, 1, 8);	
		sd.textFade(word("untilThisDay.2"), txtXoff, 40, 5, 8);
		//
		sd.pause(1f);
		return sd.scene();
	}
	
	private Scene monsterDance() {
		SceneDSL sd = createNewScene("MONSTER DANCE");
		//
		//sd.textFade("Hier tanzen dann die Monster aus dem FLASH FILM!", txtXoff, txtYoff, 1, 3);
		ImageBox m1 = sd.image(image(5), -200,-200);
		ImageBox m2 = sd.image(image(6), -70, -210);
		ImageBox m3 = sd.image(image(7), 70, -205);
		ImageBox m4 = sd.image(image(8), 200, -200);
		sd.addAnimation(1, new LinearWalkAnimation(m1, 0.04f, 0, 10, 20));
		sd.addAnimation(1, new LinearWalkAnimation(m2, 0.01f, 0, 11, 19));
		sd.addAnimation(1, new LinearWalkAnimation(m3, 0.02f, 0, 10, 20));
		sd.addAnimation(1, new LinearWalkAnimation(m4, 0.03f, 0, 9, 21));
		sd.hideAt(7, m1, m2, m3, m4);
		//
		TextBox tb = sd.textFade(word("monsterDance.1"), 0, 120, 2, 7);
		tb.alignCenter();
		tb = sd.textFade(word("monsterDance.2"), 0, -220, 8, 11);
		tb.alignCenter();
		//
		sd.pause(1f);
		return sd.scene();
	}

	private Scene verhaltensweisenTalk() {
		SceneDSL sd = createNewScene("VERHALTENSWEISEN TALK");			
		//
		final UiBox verhaltensweisenBox = sd.image(image(9), 0, 100);		
		TextBox tb = sd.text("", 0, 0);
		tb.height = 100;
		tb.alignCenter();
		//
		int[] flow = new int[] {0,1,2,0,1,0,2,0,1,2,0,1,0}; 
		int[] wait = new int[] {3,3,3,2,2,2,2,3,2,3,3,2,3};
		CPos[] pos = new CPos[] {new CPos(0,-100), new CPos(-130,-70), new CPos(130,-70)};
		//
		float lastFrame = 1.5f;
		for (int i = 0; i < flow.length; i++) {
			sd.addAnimation(lastFrame, new MoveAnimation(tb, pos[flow[i]]));
			sd.addAnimation(lastFrame, new TextAnimation(tb, word("vhtalk." + (i+1))));
			lastFrame += wait[i];
		}
		sd.addAnimation(lastFrame, new TextAnimation(tb, ""));
		sd.pause(0.5f);		
		// irrlicht moves up
		UiBox irrlichtBox = sd.image(image(11), 0, 0, sd.last(), 0);		
		LinearWalkAnimation irrlichtUp = new LinearWalkAnimation(irrlichtBox, new FrameMx(0.02f),0, -5, 50);
		irrlichtUp.hideWhenOver = true;
		sd.append(irrlichtUp);
		sd.hideAt(sd.last() + 0.7f, verhaltensweisenBox);		
		//
		sd.pause(1.2f);
		return sd.scene();
	}
	
	private Scene partyIntroducing() {
		SceneDSL sd = createNewScene("PARTY INTRODUCING");
		int[] flowImage = {350, 70, 270, 470};
		CPos[] pos = {new CPos(20,70), new CPos(40,270), new CPos(500, 20), new CPos(380,180)};
		//
		List<FrameMx> starts = new ArrayList<FrameMx>();
		addIrrlichtMove(sd, flowImage, pos, "drucing.", starts);
		//
		// arkady			
		final ImageBox arkadyBox = sd.image(image(15).createScaled(2));
		arkadyBox.visible(false);
		arkadyBox.move(190, 40);
		sd.addAnimation(starts.get(0).add(0.5f), new AbstractBoxAnimation(arkadyBox) {	
			// looks at irrlicht, jumps over it and looks back.
			public FrameMx play(int step) {
				super.play(step);
				showAt(1);
				hideAt(20);
				if (step < 4) {
					box.x -= 4;
					return wait(0.4f);				
				} else if (step == 20) {					
					return over();
				}
				if (step >= 4 && step < 14) {					
					box.x -= 13;
					if (step > 8) {
						box.y += 34;
					} else {
						box.y -= 34;
					}
					return wait(0.04f);
				}
				if (step == 15) {
					arkadyBox.imageSurface().flipHorizontally();
				}
				return wait(0.1f);
			}
		});
		//
		// blob		
		final ImageBox blobBox = sd.image(image(16).createScaled(2));
		final Surface original = blobBox.imageSurface();
		blobBox.move(100, 40);
		blobBox.visible(false);		
		final Surface flipped = new ImageSurface(blobBox.imageSurface());
		flipped.flipHorizontally();
		//
		sd.addAnimation(starts.get(1).add(0.5f), new AbstractBoxAnimation(blobBox) {
			// makes a little dance and moon-walk
			public FrameMx play(int step) {
				super.play(step);
				showAt(1);
				hideAt(38);
				if (step == 1) {
					return wait(0.3f);
				}
				if (step > 1 && step < 7) {
					blobBox.imageSurface(toggle() ? flipped : original);
					return wait(0.1f);
				}
				if (step > 6 && step < 29) {
					box.x -= (step >= 18) ? 15 : -13;
					if (step == 18) {
						blobBox.imageSurface(original);
					}
					return wait(0.04f);
				}
				if (step >= 29 && step < 38) {
					box.y -= toggle() ? -5 : 5;
					return wait(0.05f);
				}
				return over();				
			}
		});
		//
		// ritter		
		ImageBox ritterBox = sd.image(image(17).createScaled(2).flipHorizontally());
		ritterBox.visible(false);		
		ritterBox.pos(30, 250);
		//
		sd.addAnimation(starts.get(2).add(0.5f), new AbstractBoxAnimation(ritterBox) {						
			public FrameMx play(int step) {
				super.play(step);
				showAt(1);
				// runs after the irrlicht but gets "ausser puste"
				if (step < 25) {					
					box.x += (step < 15) ? 10 : (step < 22) ? 8 : 6;
					// box.y -= toggle() ? 2 : -2;
					return wait(step < 15 ? 0.05f : 0.1f);
				} else if (step == 25) {
					return wait(0.5f);
				} else if (step == 26) {
					hide();
					return wait(1f);
				} else {
					return over();
				}							
			}
			
		});
		//
		// professor		
		final ImageBox zettBox = sd.image(image(18).createScaled(2));
		zettBox.visible(false);
		final CPos middle = new CPos(210, 260);
		BoxUtils.posMiddle(zettBox, middle);
		sd.addAnimation(starts.get(3).add(0.5f), new AbstractBoxAnimation(zettBox) {						
			public FrameMx play(int step) {
				super.play(step);
				showAt(1);
				hideAt(18);				
				//
				if (time(2) | time(4) || time(6)) {
					zettBox.scale(1.8f);
					BoxUtils.posMiddle(zettBox, middle);					
					return wait(0.1f);
				}
				if (time(3) || time(5) || time(7)) {
					zettBox.scale(1);
					BoxUtils.posMiddle(zettBox, middle);
					return (step == 7 ? wait(0.4f) : wait(0.1f));
				}
				if (time(8)) {
					zettBox.imageSurface().flipHorizontally();
					return wait(0.4f);
				}
				if (step >= 9 && step < 18) {
					box.x -= 15;
					return wait(0.05f);
				}				
				if (time(18)) {
					return over();
				}							
				return wait(0.5f);
			}}
		);		
		return sd.scene();
	}
			
	private Scene tadLogo() {
		SceneDSL sd = createNewScene();		
		final ImageBox ibox = sd.image(image(12));			
		ibox.scale(2.5f);
		sd.addAnimation(1, new AnimationChain().add(new AbstractBoxAnimation(ibox, new FrameMx(0.1f)) {			
			@Override
			public FrameMx play(int step) {			
				super.play(step);
				ibox.scale(ibox.scale() - ((step < 18) ? 0.08f : 0.01f)); 				
				ibox.center();
				ibox.move(0, -25);				
				return (step < 42) ? defaultGap : null;									 				
			}
		}).add(new NullAnimation(0.5f))
		.add(new VisibleAnimation(ibox, false))
		.add(new NullAnimation(0.5f)));		
		//
		return sd.scene();
	}	

	private Scene irrlichtMove() {
		SceneDSL sd = createNewScene();
		//
		int[] flowImage = {100, 250, 190, 260, 390, 90, 290};
		CPos[] pos = {new CPos(10, 160), new CPos(400, 350), new CPos(40, 20), 
					  new CPos(340, 280), new CPos(500, 30), new CPos(120, 300), new CPos(310, 340)};
		//
		UiBox irrlichtBox = addIrrlichtMove(sd, flowImage, pos, "irrlicht.");		
		IrrlichtAnimation ia = new IrrlichtAnimation(irrlichtBox, 200);
		ia.stopInMiddle = true;
		sd.append(ia);
		//
		return sd.scene();
	}	
	
	private Scene goOnToActionWorld() {
		SceneDSL sd = createNewScene();
		//
		UiBox box = sd.image(image(14), 760, -30);
		sd.addAnimation(1, new AbstractBoxAnimation(box, new FrameMx(0.05f)) {			
			public FrameMx play(int step) {
				box.x -= 20;
				return (box.x < 25) ? null : defaultGap;
			}
		});
		sd.length(2.5f);
		//
		return sd.scene();
	}
	
	// private helpers
	
	private UiBox addIrrlichtMove(SceneDSL sd, int[] yMoves, CPos[] txtPos, String trPrefix) {
		return addIrrlichtMove(sd, yMoves, txtPos, trPrefix, null);
	}
	
	private UiBox addIrrlichtMove(SceneDSL sd, int[] yMoves, CPos[] txtPos, String trPrefix, List<FrameMx> starts) {
		UiBox ib = sd.image(image(10));
		TextBox tb = sd.text("");
		tb.alignCenter();				
		//
		for (int i = 0; i < yMoves.length; i++) {			
			if (starts != null) {
				starts.add(new FrameMx(sd.last()));
			}
			sd.append(new IrrlichtAnimation(ib, yMoves[i]));			
			sd.append(new TextAnimation(tb, word(trPrefix + (i+1))));
			sd.append(new PosAnimation(tb, txtPos[i]));		
			sd.textFade(tb, sd.last() + 0.1f, sd.last() + 2.4f);
			sd.pause(1);			
		}
		return ib;
	}
	
}
