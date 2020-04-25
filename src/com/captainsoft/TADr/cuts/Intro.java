/*
 * Copyright Captainsoft 2010-2012.
 * All rights reserved.
 */
package com.captainsoft.TADr.cuts;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.cuts.Scene;
import com.captainsoft.spark.cuts.SceneDSL;
import com.captainsoft.spark.cuts.ations.AbstractBoxAnimation;
import com.captainsoft.spark.cuts.ations.AnimationChain;
import com.captainsoft.spark.cuts.ations.FrameAnimation;
import com.captainsoft.spark.cuts.ations.LinearWalkAnimation;
import com.captainsoft.spark.cuts.ations.MoveAnimation;
import com.captainsoft.spark.cuts.ations.NullAnimation;
import com.captainsoft.spark.cuts.ations.PosAnimation;
import com.captainsoft.spark.cuts.ations.TextAnimation;
import com.captainsoft.spark.cuts.ations.VisibleAnimation;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.BoxUtils;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
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
    public Animation createAnimation(int index) {
        switch (index) {
            case 1:
                return captainsoftLogoScene().createAnimation();
            case 2:
                return thisIsTheStory().createAnimation();
            case 3:
                return formerHomeOf().createAnimation();
            case 4:
                return fatHeroesDrinking().createAnimation();
            case 5:
                return untilThisDay().createAnimation();
            case 6:
                return monsterDance().createAnimation();
            case 7:
                return verhaltensweisenTalk().createAnimation();
            case 8:
                return partyIntroducing().createAnimation();
            case 9:
                return tadLogo().createAnimation();
            case 10:
                return irrlichtMove().createAnimation();
            case 11:
                return goOnToActionWorld().createAnimation();
            default:
                return null;
        }
    }

    @Override
    public String imageLoaderIdentifier() {
        return "intr";
    }

    // private

    @SuppressWarnings("unused")
    private Scene createPauseScene() {
        SceneDSL sd = createNewScene("PAUSE");
        sd.pause(2);
        return sd.scene();
    }

    private Scene captainsoftLogoScene() {
        SceneDSL sd = createNewScene("CAPTAINSOFT LOGO");
        //
        sd.image(image(13), 0, -30, 2.5f, 6.5f);
        //
        sd.pause(1.5f);
        return sd.scene();
    }

    private Scene thisIsTheStory() {
        SceneDSL sd = createNewScene("THIS IS THE STORY");
        //
        sd.textFade(word("thisisthestory.1"), txtXoff, txtYoff, 1, 7);
        sd.image(image(1), 170, 70, 1, 8);
        //
        sd.pause(1.5f);
        return sd.scene();
    }

    private Scene formerHomeOf() {
        SceneDSL sd = createNewScene("FORMER HOME OF");
        //
        sd.textFade(word("formerHomeOf.1"), txtXoff, txtYoff, 1, 12);
        ImageBox deepDungeonBox = sd.image(image(2), 150, 70, 1, 8);
        sd.addAnimation(7, new LinearWalkAnimation(deepDungeonBox, 0.01f, 0, 30, 10));
        //
        sd.textFade(word("formerHomeOf.2"), txtXoff, 20, 8.5f, 14);
        ImageBox justinBox = sd.image(image(3), 170, 80, 8.5f, 16);
        sd.addAnimation(15.5f, new LinearWalkAnimation(justinBox, new FrameMx(0.01f), 30, 0, 11));
        //
        sd.pause(1.5f);
        return sd.scene();
    }

    private Scene fatHeroesDrinking() {
        SceneDSL sd = createNewScene("FAT HEROES DRINKING");
        //
        sd.textFade(word("fatHeroesDrinking.1"), txtXoff, txtYoff, 1, 8);
        sd.textFade(word("fatHeroesDrinking.2"), txtXoff, txtYoff, 9, 20);
        sd.image(image(4), 150, 100, 9f, 21f);
        //
        sd.pause(1.5f);
        return sd.scene();
    }

    private Scene untilThisDay() {
        SceneDSL sd = createNewScene("UNTIL THIS DAY");
        //
        sd.textFade(word("untilThisDay.1"), txtXoff, txtYoff, 1, 9);
        sd.textFade(word("untilThisDay.2"), txtXoff, 40, 6, 9);
        //
        sd.pause(1.5f);
        return sd.scene();
    }

    private Scene monsterDance() {
        SceneDSL sd = createNewScene("MONSTER DANCE");
        //
        //sd.textFade("Hier tanzen dann die Monster aus dem FLASH FILM!", txtXoff, txtYoff, 1, 3);
        ImageBox m1 = sd.image(image(5), -200, -200);
        ImageBox m2 = sd.image(image(6), -70, -210);
        ImageBox m3 = sd.image(image(7), 70, -205);
        ImageBox m4 = sd.image(image(8), 200, -200);
        sd.addAnimation(1, new LinearWalkAnimation(m1, 0.04f, 0, 10, 20));
        sd.addAnimation(1, new LinearWalkAnimation(m2, 0.01f, 0, 11, 19));
        sd.addAnimation(1, new LinearWalkAnimation(m3, 0.02f, 0, 10, 20));
        sd.addAnimation(1, new LinearWalkAnimation(m4, 0.03f, 0, 9, 21));
        sd.hideAt(8, m1, m2, m3, m4);
        //
        TextBox tb = sd.textFade(word("monsterDance.1"), 0, 120, 2, 8.5f);
        tb.alignCenter();
        tb = sd.textFade(word("monsterDance.2"), 0, -220, 10, 15);
        tb.alignCenter();
        //
        sd.pause(1.5f);
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
        int[] flow = new int[]{0, 1, 2, 0, 1, 0, 2, 0, 1, 2, 0, 1, 0};
        int[] wait = new int[]{3, 3, 3, 2, 2, 2, 2, 3, 2, 3, 3, 2, 3};
        CPos[] pos = new CPos[]{new CPos(0, -100), new CPos(-130, -70), new CPos(130, -70)};
        //
        float lastFrame = 2.5f;
        for (int i = 0; i < flow.length; i++) {
            sd.addAnimation(lastFrame, new VisibleAnimation(tb, true));
            sd.addAnimation(lastFrame, new MoveAnimation(tb, pos[flow[i]]));
            sd.addAnimation(lastFrame, new TextAnimation(tb, word("vhtalk." + (i + 1))));
            lastFrame += wait[i] + 1f;
            sd.addAnimation(lastFrame, new VisibleAnimation(tb, false));
            lastFrame += 0.4f;
        }
        sd.addAnimation(lastFrame, new TextAnimation(tb, ""));
        sd.pause(0.5f);
        // irrlicht moves up
        UiBox irrlichtBox = sd.image(image(11), 0, 30, sd.last(), 0);
        LinearWalkAnimation irrlichtUp = new LinearWalkAnimation(irrlichtBox, new FrameMx(0.02f), 0, -3, 120);
        irrlichtUp.hideWhenOver = true;
        sd.append(irrlichtUp);
        sd.hideAt(sd.last() + 3f, verhaltensweisenBox);
        //
        sd.pause(1.5f);
        return sd.scene();
    }

    private Scene partyIntroducing() {
        //
        SceneDSL sd = createNewScene("PARTY INTRODUCING");
        int[] yMoves = {350, 70, 270, 470};
        CPos[] txtPos = {new CPos(20, 70), new CPos(40, 270), new CPos(500, 20), new CPos(380, 180)};
        String trPrefix = "drucing.";
        List<FrameAnimation> partyAnimations = new ArrayList<FrameAnimation>();
        //
        // arkady
        final ImageBox arkadyBox = sd.image(image(15).createScaled(2));
        arkadyBox.visible(false);
        arkadyBox.move(190, 40);
        partyAnimations.add(new AbstractBoxAnimation(arkadyBox) {
            // looks at irrlicht, jumps over it and looks back.
            public FrameMx play(int step) {
                super.play(step);
                showAt(1);
                hideAt(56);
                if (step > 11 && step < 14) {
                    box.x -= 4;
                    return wait(0.4f);
                } else if (step == 56) {
                    return over();
                }
                if (step >= 20 && step < 32) {
                    box.x -= 13;
                    if (step > 25) {
                        box.y += 34;
                    } else {
                        box.y -= 34;
                    }
                    return wait(0.04f);
                }
                if (step == 36) {
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
        final Surface flipped = new Surface(blobBox.imageSurface());
        flipped.flipHorizontally();
        //
        partyAnimations.add(new AbstractBoxAnimation(blobBox) {
            // makes a little dance and moon-walk
            public FrameMx play(int step) {
                super.play(step);
                showAt(1);
                hideAt(65);
                if (step == 1) {
                    return wait(1f);
                }
                if (step > 1 && step < 7) {
                    blobBox.imageSurface(toggle() ? flipped : original);
                    return wait(0.2f);
                }
                if (step >= 7 && step < 34) {
                    box.x -= (step >= 20) ? 14 : -16;
                    if (step == 20) {
                        blobBox.imageSurface(original);
                        return wait(0.5f);
                    }
                    return wait(0.06f);
                }
                if (step >= 34 && step < 66) {
                    if (step > 36 && step < 56) {
                        box.y -= toggle() ? -5 : 5;
                    }
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
        partyAnimations.add(new AbstractBoxAnimation(ritterBox) {
            public FrameMx play(int step) {
                super.play(step);
                showAt(1);
                // runs after the irrlicht but gets "ausser puste"
                if (step < 45) {
                    box.x += (step < 15) ? 8 : (step < 22) ? 6 : 4;
                    return wait(step < 15 ? 0.05f : 0.1f);
                } else if (step >= 45 && step < 49) {
                    return wait(0.5f);
                } else if (step == 49) {
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
        partyAnimations.add(new AbstractBoxAnimation(zettBox) {
                                public FrameMx play(int step) {
                                    super.play(step);
                                    showAt(1);
                                    hideAt(23);
                                    //
                                    if (time(2) | time(4) || time(6)) {
                                        zettBox.scale(1.8f);
                                        BoxUtils.posMiddle(zettBox, middle);
                                        return wait(0.2f);
                                    }
                                    if (time(3) || time(5) || time(7)) {
                                        zettBox.scale(1);
                                        BoxUtils.posMiddle(zettBox, middle);
                                        return (step == 7 ? wait(0.9f) : wait(0.2f));
                                    }
                                    if (time(8)) {
                                        zettBox.imageSurface().flipHorizontally();
                                        return wait(0.9f);
                                    }
                                    if (step >= 9 && step < 24) {
                                        box.x -= 10;
                                        return wait(0.08f);
                                    }
                                    if (time(24)) {
                                        return over();
                                    }
                                    return wait(1.5f);
                                }
                            }
        );

        // playing....
        for (int i = 0; i < 4; i++) {

            UiBox ib = sd.image(image(10));
            final TextBox tb = sd.text("");
            tb.alignCenter();

            sd.append(new IrrlichtAnimation(ib, yMoves[i]));
            sd.append(new TextAnimation(tb, word(trPrefix + (i + 1))));
            sd.append(new PosAnimation(tb, txtPos[i]));

            sd.addAnimation(sd.last() + 0.5f, new FrameAnimation() {
                @Override
                public FrameMx play(int step) {
                    tb.color(new Color(255, 255, 255));
                    return null;
                }
            });

            sd.addAnimation(sd.last(), partyAnimations.get(i));
            sd.textFade(tb, sd.last(), sd.last() + 5.5f);
            sd.hideAt(sd.last(), tb);
            sd.pause(1);
        }
        //
        sd.pause(1.5f);
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
        }).add(new NullAnimation(2.5f))
                .add(new VisibleAnimation(ibox, false))
                .add(new NullAnimation(1.5f)));
        //
        return sd.scene();
    }

    private Scene irrlichtMove() {
        SceneDSL sd = createNewScene();
        //
        int[] flowImage = {100, 250, 190, 260, 390, 290};
        CPos[] pos = {new CPos(10, 160), new CPos(400, 350), new CPos(40, 20),
                new CPos(340, 280), new CPos(500, 30), new CPos(310, 340)};
        //
        addIrrlichtMove(sd, flowImage, pos, "irrlicht.");
        //
        return sd.scene();
    }

    private Scene goOnToActionWorld() {
        SceneDSL sd = createNewScene();
        //
        UiBox box = sd.image(image(14), 760, -30);
        box.visible(false);
        sd.addAnimation(1.5f, new AbstractBoxAnimation(box, new FrameMx(0.05f)) {
            public FrameMx play(int step) {
                if (step == 1) {
                    box.visible(true);
                }
                box.x -= 20;
                return (box.x < 25) ? null : defaultGap;
            }
        });
        sd.length(3.5f);
        //
        return sd.scene();
    }

    // private helpers

    private UiBox addIrrlichtMove(SceneDSL sd, int[] yMoves, CPos[] txtPos, String trPrefix) {
        return addIrrlichtMove(sd, yMoves, txtPos, trPrefix, null);
    }

    private UiBox addIrrlichtMove(SceneDSL sd, int[] yMoves, CPos[] txtPos, String trPrefix, List<FrameMx> starts) {
        UiBox ib = sd.image(image(10));
        final TextBox tb = sd.text("");
        tb.alignCenter();
        //
        for (int i = 0; i < yMoves.length; i++) {
            if (starts != null) {
                starts.add(new FrameMx(sd.last()));
            }

            IrrlichtAnimation ia = new IrrlichtAnimation(ib, yMoves[i]);
            if (i == yMoves.length - 1) {
                ia.stopInMiddle = true;
            }
            sd.append(ia);
            sd.append(new TextAnimation(tb, word(trPrefix + (i + 1))));
            sd.append(new PosAnimation(tb, txtPos[i]));

            sd.addAnimation(sd.last() + 0.5f, new FrameAnimation() {
                @Override
                public FrameMx play(int step) {
                    tb.color(new Color(255, 255, 255));
                    return null;
                }
            });

            sd.textFade(tb, sd.last() + 0.1f, sd.last() + 5.4f);
            sd.pause(1);
        }
        return ib;
    }

}
