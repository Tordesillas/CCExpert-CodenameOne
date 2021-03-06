package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.NumericSpinner;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.AttackSpeedProcessor;
import fr.polytech.ccexpert.model.Hero;

import java.util.Collection;

public class AttackSpeedSimulator extends Simulator {
    private AttackSpeedProcessor asp;
    private MultiButton buttonHero;
    private Container ffContainer;
    private Container artifactContainer;
    private Container secondTalentContainer;
    private Container dukeContainer;
    private NumericSpinner hitsSpinner;
    private Container michaelContainer;
    private Container destroyerContainer;
    private Container res;

    public AttackSpeedSimulator(CCExpert main) {
        super(main);
        setTitle("Vitesse d'attaque");
        asp = new AttackSpeedProcessor();
        addCommandListener(this);

        addComponent(new Label("Héros à analyser :"));
        buildSelector();
        addComponent(buttonHero);

        addComponent(new SpanLabel("Le héros possède-t-il Fou Furieux ?"));
        ffContainer = switchWithSpinner(1, 9, "Talent_Berserk.png");
        addComponent(ffContainer);

        addComponent(new SpanLabel("Le héros possède-t-il l'artefact Blitz ?"));
        artifactContainer = switchWithPicture("blitz.png");
        addComponent(artifactContainer);

        addComponent(new SpanLabel("Le héros possède-t-il en second talent Fureur ?"));
        secondTalentContainer = switchWithSpinner(1, 6, "fury.png");
        addComponent(secondTalentContainer);

        addComponent(new SpanLabel("Le héros a-t-il à ses côtés un Duc ?"));
        dukeContainer = switchWithSpinner(1, 11, "Pumpkin_Duke_Icon.png");
        addComponent(dukeContainer);

        addComponent(new SpanLabel("Combien de fois l'éventuel Duc a-t-il utilisé son pouvoir ?"));
        hitsSpinner = createSpinner(0, 5);
        addComponent(hitsSpinner);

        addComponent(new SpanLabel("Le héros a-t-il à ses côtés un Michaël ?"));
        michaelContainer = switchWithSpinner(1, 11, "Michael_Icon.png");
        addComponent(michaelContainer);

        addComponent(new SpanLabel("Le héros a t'il à ses côtés un Destructeur ?"));
        destroyerContainer = switchWithSpinner(1, 11, "Destroyer_Icon.png");
        addComponent(destroyerContainer);

        lookFor.addActionListener(this);
        addComponent(lookFor);

        res = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(res);
    }

    private void buildSelector() {
        Collection<Hero> heroes = main.getSets().getHeroes();

        buttonHero = new MultiButton("Choisissez un héros");
        buttonHero.addActionListener(e -> {
            Dialog d = new Dialog();
            d.setLayout(BoxLayout.y());
            d.getContentPane().setScrollableY(true);
            for (Hero hero : heroes) {
                MultiButton mb = new MultiButton(hero.getFrenchName());
                mb.setIcon(hero.getPicture());
                d.add(mb);
                mb.addActionListener(evt -> {
                    buttonHero.setTextLine1(mb.getTextLine1());
                    buttonHero.setIcon(mb.getIcon());
                    d.dispose();
                    buttonHero.revalidate();
                });
            }
            d.showPopupDialog(buttonHero);
        });
    }

    private void howManyAttackSpeed() {
        Container tmp = asp.printAttackSpeedAmount(null,
                getValueSwitchSpinner(ffContainer),
                getValueSwitch(artifactContainer),
                getValueSwitchSpinner(secondTalentContainer),
                getValueSwitchSpinner(dukeContainer),
                (int)hitsSpinner.getValue(),
                getValueSwitchSpinner(michaelContainer),
                getValueSwitchSpinner(destroyerContainer));
        replace(res, tmp, CommonTransitions.createFade(300));
        res = tmp;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object obj = evt.getSource();

        if (obj == lookFor) {
            howManyAttackSpeed();
            show();
        }
        if (evt.getCommand() == back) {
            main.loadSimulators();
        }
    }
}
