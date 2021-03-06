package fr.polytech.ccexpert.view.simulator;

import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.StatsGuildWar;

public class GuildWar extends Simulator {
    private Button lookForFame;
    private Container stats;
    private Container stats2;
    private StatsGuildWar sgw;
    private TextField userPower;
    private TextField userScore;
    private TextField userScore2;
    private ButtonGroup group;

    public GuildWar(CCExpert main) {
        super(main);
        setTitle("Guerre de guilde");
        sgw = new StatsGuildWar();
        addCommandListener(this);

        containerPower();
        containerFame();
    }

    private void containerPower() {
        Label labelPower = new Label("Puissance :");
        userPower = new TextField("", "Insérer la puissance du joueur", 6, TextArea.NUMERIC);
        Label labelScore = new Label("Score :");
        userScore = new TextField("", "Insérer le score du joueur", 4, TextArea.NUMERIC);

        lookFor = new Button("Analyser");
        lookFor.addActionListener(this);

        addComponent(labelPower);
        addComponent(userPower);
        addComponent(labelScore);
        addComponent(userScore);
        addComponent(lookFor);

        stats = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(stats);
    }

    private void containerFame() {
        Label labelPosition = new Label("Position :");
        Container radio = new Container(new BoxLayout(BoxLayout.X_AXIS));
        group = new ButtonGroup();
        RadioButton p1 = new RadioButton("1");
        RadioButton p2 = new RadioButton("2");
        RadioButton p3 = new RadioButton("3");
        RadioButton p4 = new RadioButton("4");
        RadioButton p5 = new RadioButton("5");
        group.add(p1);
        group.add(p2);
        group.add(p3);
        group.add(p4);
        group.add(p5);
        radio.addComponent(p1);
        radio.addComponent(p2);
        radio.addComponent(p3);
        radio.addComponent(p4);
        radio.addComponent(p5);
        p1.setSelected(true);
        Label labelScore2 = new Label("Score :");
        userScore2 = new TextField("", "Insérer la puissance du joueur", 6, TextArea.NUMERIC);

        lookForFame = new Button("Analyser");
        lookForFame.addActionListener(this);

        addComponent(labelPosition);
        addComponent(radio);
        addComponent(labelScore2);
        addComponent(userScore2);
        addComponent(lookForFame);

        stats2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(stats2);
    }

    private void loadStatistics() {
        int power = parseInteger(userPower.getText().trim());
        int score = parseInteger(userScore.getText().trim());
        userPower.clear();
        userScore.clear();
        Container tmp = sgw.printStats(power, score);
        replace(stats, tmp, CommonTransitions.createFade(300));
        stats = tmp;
    }

    private void loadFameStats() {
        int score = parseInteger(userScore2.getText().trim());
        int position = group.getSelectedIndex();
        userScore2.clear();
        Container tmp = sgw.printFameStats(score, position);
        replace(stats2, tmp, CommonTransitions.createFade(300));
        stats2 = tmp;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Command cmd = evt.getCommand();
        Object obj = evt.getSource();

        if (obj == lookFor) {
            loadStatistics();
            show();
        } else if (obj == lookForFame) {
            loadFameStats();
            show();
        }

        if (cmd == back) {
            main.loadSimulators();
        }
    }
}
