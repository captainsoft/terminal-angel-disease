/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.cuts;

import com.captainsoft.spark.TextBox;
import com.captainsoft.spark.anim.MultiScene;
import com.captainsoft.spark.anim.Playable;
import com.captainsoft.spark.anim.Scene;
import com.captainsoft.spark.anim.SceneDSL;
import com.captainsoft.spark.anim.ations.AnimationChain;
import com.captainsoft.spark.anim.ations.Conversation;
import com.captainsoft.spark.anim.ations.LinearWalkAnimation;
import com.captainsoft.spark.anim.ations.NullAnimation;
import com.captainsoft.spark.anim.ations.VisibleAnimation;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The real TAD outro :)
 *
 * @author mathias fringes
 */
public final class Outro extends CutScene {
	
	// fields
	
	private String playerName;
	private String partyName;
	
	// constructors
	
	public Outro(Updater updater, UiBoxContainer backgroundBox, 
			String playerName, String partyName) {
		
		super(updater, backgroundBox, "outro.");
		this.playerName = playerName;
		this.partyName = partyName;
	}
	
	// CutScene
	
	@Override
	public Playable createScene(int index) {		
		switch (index) {			
			case  1: return thatWasTheGame(); 		
			case  2: return madeBy();			
			case  3: return playedBy();
			case  4: return starringScene();
			case  5: return alsoStarringScene();
			case  6: return cakeGuysTalk();
			case  7: return thankYouScene();
		    case  8: return valloSchnauffIndianaJonesTetScene();	
			case  9: return captainsoftEndorsementScene();
			case 10: return starsConversationScene();
			case 11: return endAndTeaserScene();
			case 12: return freitagmanLastThingScene();
			case 13: return copyRightScene();
			default:
				return null;
		}
	}	

	@Override
	public String imageLoaderIdentifier() {
		// this is "intr" for both the intro and the outro!
		return "intr";
	}
	
	@Override
	protected SceneDSL createNewScene(String name) {
		SceneDSL dsl = super.createNewScene(name);
		dsl.centerText();
		return dsl;
	}

	// private methods
	
	private Scene thatWasTheGame() {		
		SceneDSL sd = createNewScene("thatwas");			
		//
		sd.textFade(word("thatWas"), 0, -130, 2.5f, 7);		
		sd.image(image(12), 0, 0, 3.5f, 8);
		//
		sd.pause(0.5f);
		return sd.scene();
	}
	
	private Scene madeBy() {
		SceneDSL sd = createNewScene("mbadeby");		
		//
		sd.textFade(word("madeBy"), 0, -130, 1.5f, 6);				
		TextBox mb = sd.text("Captainsoft", 0, 0); 		
		sd.show(mb, 2.5f, 7);
		//
		sd.pause(0.5f);
		return sd.scene();
	}
	
	private Scene playedBy() {
		SceneDSL sd = createNewScene("playedBy");		
		//
		sd.textFade(word("playedBy"), 0, -130, 1.5f, 6);				
		TextBox pb = sd.text(playerName, 0, 0); 		
		sd.show(pb, 2.5f, 7);
		//
		sd.textFade(word("andPlayedBy"), 0, -130, 7.5f, 12);
		TextBox pb2 = sd.text(partyName, 0, 0); 		
		sd.show(pb2, 8.5f, 13);
		//
		// party members walk in
		float showAt = 9f;
		float hideAt = 14f;
		Surface s1 = image(15);
		s1.flipHorizontally();
		ImageBox p1 = sd.image(s1, -250, 100, showAt, hideAt);
		ImageBox p2 = sd.image(image(16), -170, 100, showAt, hideAt);			
		ImageBox p3 = sd.image(image(17), 170, 100, showAt, hideAt);		
		Surface s4 = image(18);
		s4.flipHorizontally();
		ImageBox p4 = sd.image(s4, 250, 100, showAt, hideAt);
		//
		sd.addAnimation(showAt, new LinearWalkAnimation(p1, 0.1f, 10, 0, 10));
		sd.addAnimation(showAt, new LinearWalkAnimation(p2, 0.1f, 10, 0, 10));
		sd.addAnimation(showAt, new LinearWalkAnimation(p3, 0.1f, -10, 0, 10));
		sd.addAnimation(showAt, new LinearWalkAnimation(p4, 0.1f, -10, 0, 10));
		//
		sd.at(hideAt).pause(1);
		return sd.scene();
	}
	
	private Playable starringScene() {		
		MultiScene ms = new MultiScene();		
		SceneDSL sd = createNewScene("starring");		
		//
		UiBox sb = sd.text(word("starring"), 0, -50);
		sd.hideAt(3, sb);		
		ms.add(sd.scene());		
		//
		ms.add(addStarringAnimation(1, 19, 1));
		ms.add(addStarringAnimation(1, 20, 2));
		ms.add(addStarringAnimation(1, 9, 3));
		ms.add(addStarringAnimation(1, 3, 4));
		ms.add(addStarringAnimation(1, 21, 5));
		ms.add(addStarringAnimation(1, 22, 6));		
		SceneDSL pause = createNewScene("pause");
		pause.length(2);
		ms.add(pause.scene());		
		return ms;
	}
	
	private Playable addStarringAnimation(int at, int image, int number) {
		SceneDSL sd = createNewScene("starring" + image);
		ImageBox b = sd.image(image(image), at, at+3);		
		b.y -= 10;
		b.x -= (b.width / 2) + 335;
		sd.addAnimation(at, new AnimationChain().add( 
				new VisibleAnimation(b, true),
				new LinearWalkAnimation(b, 0.02f, 7, 0, (280 + (b.width / 2)) / 6 ),
				new NullAnimation(5),
				new LinearWalkAnimation(b, 0.02f, 7, 0, (280 + (b.width / 2)) / 6),
				new VisibleAnimation(b, false)
				));
		//
		sd.textFade(word("starring." + number + "1"), 0, -130, at, at+5);		
		sd.textFade(word("starring." + number + "2"), 0, 120, at+1, at+6);
		sd.pause(1.7f);
		return sd.scene();
	}	
	
	private Playable alsoStarringScene() {		
		SceneDSL sd = createNewScene("alsostarring");		
		UiBox sb = sd.text(word("alsoStarring"), 1, -200);
		sd.hideAt(30, sb);
		//		
		float frame = 1;
		for (int i = 0; i < 5; i++) {			
			TextBox tx = sd.textFade(word("alsoStarring." + (i+1)), 0, -30, frame, frame += 5f);
			tx.width += 200;	
			tx.center();			
			sd.at(frame);
			sd.pause(1);
			frame++;
		}		
		return sd.scene();
	}
	
	private Playable cakeGuysTalk() {
		SceneDSL sd = createNewScene("cakeGuysTalk");
		//
		sd.image(image(23), 150, 130);		
		TextBox one = sd.text("", 140, 35);
		one.size(300, 100); 				
		TextBox two = sd.text("",220, 65);
		two.size(300, 100);	
		//
		Conversation c = new Conversation(one, two, 2);
		sd.addAnimation(2, c);		
		int index = 1;
		for (int i = 0; i < 8; i++) {			
			c.say(i % 2 == 0 ? two : one, word("cakeguystalk." + index));			
			if (index == 4) {
				c.sayNothing(2);
			}
			index++;
		}		
		//
		return sd.scene();
	}
	
	private Playable thankYouScene() {
		SceneDSL sd = createNewScene("thankYouScene");
		//
		UiBox sb = sd.text(word("thankYou"), 0, -200);
		sd.show(sb, 2, 15);		
		for (int i = 0; i < 2; i++) {
			TextBox t = sd.textFade(word("thankYou." + (i+1)), 0, 0, i*6 + 3, i*6 + 8.5f);
			t.width += 200;
			t.center();
		}
		sd.at(15);
		sd.clearAll();
		sd.pause(1);
		//
		return sd.scene();
	}
	
	private Playable valloSchnauffIndianaJonesTetScene() {
		SceneDSL sd = createNewScene("valloScene");
		UiBox ib = sd.image(image(3), 152, 150);
		TextBox tx = sd.text("", 154, 130);
		tx.height = 30;	
		//
		Conversation c = new Conversation(tx, 3f);
		c.waitGap(2);
		c.say(tx, wordList(4, "valloSchaunffIndianaJonesTalk"));					
		sd.addAnimation(2, new AnimationChain().add(
				c,
				VisibleAnimation.hide(ib, tx))
				.addWait(1));
		//
		return sd.scene();
	}
			
	private Playable captainsoftEndorsementScene() {
		SceneDSL sd = createNewScene("captainsoftEndorsement");
		UiBox sb = sd.text(word("weUse"), 0, -200);
		//
		sd.hideAt(10, sb);		
		sd.textFade(word("weUseList"), 0, 0, 2, 9);
		sd.at(10);
		sd.pause(2);
		return sd.scene();
	}
	
	private Playable starsConversationScene() {
		SceneDSL sd = createNewScene("starsConversation");	
		//
		UiBox ib = sd.image(image(24), 130, 130);		
		TextBox tx = sd.text("", 200, 70);		
		//
		TextBox one = sd.text("", 110, 35);
		one.size(300, 100); 				
		TextBox two = sd.text("", 220, 75);
		two.size(300, 100);	
		//
		Conversation c = new Conversation(one, two, 3);
		c.conver(wordList(4, "starConversation"));		
		sd.addAnimation(2, new AnimationChain().add(
				c, 
				VisibleAnimation.hide(ib, tx))
				.addWait(1));		
		//
		return sd.scene();
	}
	
	private Playable endAndTeaserScene() {
		SceneDSL sd = createNewScene("endAndTeaser");		
		TextBox tb = sd.textFade(word("endAndTeaser"), 0, 0, 2, 10);
		tb.width += 200;
		tb.center();
		sd.at(11).pause(1);
		return sd.scene();
	}
	
	private Playable freitagmanLastThingScene() {
		SceneDSL sd = createNewScene("FreitagmanLast");		
		sd.image(image(25), 0, 50);		
		TextBox tx = sd.text(word("freitagmanLast"));
		tx.width += 200;
		tx.center();
		tx.y -= 100;		
		//
		sd.at(10);
		sd.clearAll();
		sd.pause(2);
		return sd.scene();
	}
		
	private Playable copyRightScene() {
		SceneDSL sd = createNewScene("copyright");				
		TextBox tb = sd.text(word("copyright"), 0, 0);
		tb.width += 200;
		tb.center();
		sd.hideAt(8, tb);
		sd.pause(2);
		return sd.scene();	
	}
	
}