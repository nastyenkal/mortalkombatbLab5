package com.mycompany.mortalcombat;

import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.Human;
import com.mycompany.mortalcombat.characters.*;
import com.mycompany.mortalcombat.fabrics.*;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 *
 */
public class CharacterAction {

    private int experience_for_next_level = 40;
    private final int kind_fight[][] = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};
    private Player enemyes[] = new Player[6];
    EnemyFabric fabric = new EnemyFabric();
    private Player enemyy = null;

    /**
     * создание массива противников.
     *
     */
    public void setEnemyes() {
        enemyes[0] = fabric.create(0, 0);
        enemyes[1] = fabric.create(1, 0);
        enemyes[2] = fabric.create(2, 0);
        enemyes[3] = fabric.create(3, 0);
        enemyes[4] = fabric.create(4, 0);
        enemyes[5] = fabric.create(4, 0);
    }

    /**
     * геттер
     *
     * @return список противников
     */
    public Player[] getEnemyes() {
        return this.enemyes;
    }

    /**
     * метод, формирующий противника
     *
     * @return противник
     */
//Старый
//    public Player ChooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3) {
//        enemyy = createRandomEnemy();
//        int i = (int) (Math.random() * 4);
//        ImageIcon icon1 = null;
//        switch (i) {
//            case 0:
//                enemyy = enemyes[0];
//                break;
//            case 1:
//                enemyy = enemyes[1];
//                break;
//            case 2:
//                enemyy = enemyes[2];
//                break;
//            case 3:
//                enemyy = enemyes[3];
//
//                break;
//        }
//        label.setIcon(enemyy.getPicture());
//        label2.setText(enemyy.getName());
//        text.setText(String.valueOf(enemyy.getDamage()));
//        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
//        return enemyy;
//    }
//новый 
    public Player ChooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3) {
        enemyy = createRandomEnemy(); // Используем новый метод для создания
        displayEnemy(enemyy, label, label2, text, label3); // Используем новый метод для отображения
        return enemyy;
    }

    /**
     * метод, формирующий босса
     *
     * @return противник (босс)
     */
//старый
//    public Player ChooseBoss(JLabel label, JLabel label2, JLabel text, JLabel label3, int i, Player human) {
//
//        label2.setText("Shao Kahn (босс)");
//        enemyy = enemyes[4];
//        for (int j = 0; j < human.getLevel() - enemyy.getLevel() + 2; i++) {
//            enemyy.setLevel();
//        }
//        enemyy.setDamage(enemyy.getLevel());
//        enemyy.setMaxHealth(5 * enemyy.getLevel());
//        enemyy.setHealth(enemyy.getMaxHealth() - enemyy.getHealth());
//        label.setIcon(enemyy.getPicture());
//        text.setText(String.valueOf(enemyy.getDamage()));
//        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
//        return enemyy;
//    }
//новый
    public Player ChooseBoss(JLabel label, JLabel label2, JLabel text, JLabel label3, int i, Player human) {
        enemyy = createBoss(human); // Используем новый метод для создания
        if (enemyy.getName().equals("Shao Kahn")) { // Добавим проверку на имя босса для корректного текста
            label2.setText("Shao Kahn (босс)");
        }
        displayEnemy(enemyy, label, label2, text, label3); // Используем новый метод для отображения
        return enemyy;
    }

    /**
     * метод, определяющий поведение противника
     *
     * @return массив со стратегией поведения
     */
    public int[] EnemyBehavior(int k1, int k2, int k3, int k4, double i, Boolean isWizard) {
        int arr[] = null;
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            arr = kind_fight[1];
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[2];
        }
        if (i >= (k1 + k2 + k3) * 0.01 & i < 1) {
            arr = kind_fight[3];
        }
        return arr;
    }

    public int[] ChooseBehavior(Player enemy, CharacterAction action) {
        int arr[] = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.EnemyBehavior(15, 15, 60, 10, i, false);
        }
        if (enemy instanceof SubZero) {
            arr = action.EnemyBehavior(25, 25, 0, 50, i, true);
        }
        if (enemy instanceof LiuKang) {
            arr = action.EnemyBehavior(13, 13, 10, 64, i, false);
        }
        if (enemy instanceof SonyaBlade) {
            arr = action.EnemyBehavior(25, 25, 50, 0, i, false);
        }
        if (enemy instanceof ShaoKahn) {
            arr = action.EnemyBehavior(10, 45, 0, 45, i, false);
        }
        return arr;
    }

    public void HP(Player player, JProgressBar progress) {

        if (player.getHealth() >= 0) {
            progress.setValue(player.getHealth());
        } else {
            progress.setValue(0);
        }
    }

    /**
     * метод для вызова дебаффа.
     *
     */
    public void useDebuff() {
    }

    public Boolean AddPoints(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 0:
                human.setExperience(20);
                human.setPoints(25 + human.getHealth() / 4);
                break;
            case 1:
                human.setExperience(25);
                human.setPoints(30 + human.getHealth() / 4);
                break;
            case 2:
                human.setExperience(30);
                human.setPoints(35 + human.getHealth() / 4);
                break;
            case 3:
                human.setExperience(40);
                human.setPoints(45 + human.getHealth() / 4);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(55 + human.getHealth() / 4);
                break;
        }
        Boolean isLevelUp = false;

        if (experience_for_next_level <= human.getExperience()) {

            human.setLevel();
            isLevelUp = true;
            experience_for_next_level += human.getExperience();
            human.setNextExperience(experience_for_next_level);
            NewHealthHuman(human);
            for (int j = 0; j < 4; j++) {
                NewHealthEnemy(enemyes[j], human);
            }

        }
        return isLevelUp;
    }

    public void AddPointsBoss(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 2:
                human.setExperience(30);
                human.setPoints(45 + human.getHealth() / 2);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(65 + human.getHealth() / 2);
                break;
        }
        if (experience_for_next_level <= human.getExperience()) {
            human.setLevel();

            experience_for_next_level += human.getExperience();
            human.setNextExperience(experience_for_next_level);
            NewHealthHuman(human);
            for (int j = 0; j < 4; j++) {
                NewHealthEnemy(enemyes[j], human);
            }
        }

    }

    public void AddItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }

    public void NewHealthHuman(Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 25;
                damage = 3;
                break;
            case 2:
                hp = 30;
                damage = 3;
                break;
            case 3:
                hp = 30;
                damage = 4;
                break;
            case 4:
                hp = 40;
                damage = 6;
                break;
        }
        human.setMaxHealth(hp);
        human.setDamage(damage);
    }

    public void NewHealthEnemy(Player enemy, Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 32;
                damage = 25;
                break;
            case 2:
                hp = 30;
                damage = 20;
                break;
            case 3:
                hp = 23;
                damage = 24;
                break;
            case 4:
                hp = 25;
                damage = 26;
                break;
        }
        enemy.setMaxHealth((int) enemy.getMaxHealth() * hp / 100);
        enemy.setDamage((int) enemy.getDamage() * damage / 100);
        enemy.setLevel();
    }

    public void UseItem(Player human, Items[] items, String name, JDialog dialog, JDialog dialog1) {
        boolean itemUsedSuccessfully = false;

        switch (name) {
            case "jRadioButton1":
                if (items[0].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.25));
                    items[0].setCount(-1);
                    itemUsedSuccessfully = true;
                } else {
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                    //dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton2":
                if (items[1].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.5));
                    items[1].setCount(-1);
                    itemUsedSuccessfully = true;
                } else {
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                    //dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton3":
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                //dialog.setBounds(300, 200, 400, 300);
                break;
        }

        if (itemUsedSuccessfully) {
            dialog1.dispose();
        }
    }
//НОВЫЕ
    public Player createRandomEnemy() {
        int i = (int) (Math.random() * 4);
        Player newEnemy = null;
        switch (i) {
            case 0:
                newEnemy = enemyes[0];
                break;
            case 1:
                newEnemy = enemyes[1];
                break;
            case 2:
                newEnemy = enemyes[2];
                break;
            case 3:
                newEnemy = enemyes[3];
                break;
        }
        return newEnemy;
    }
    
    public Player createBoss(Player human) {
        Player boss = enemyes[4]; // Предполагается, что босс всегда под индексом 4
        for (int j = 0; j < human.getLevel() - boss.getLevel() + 2; j++) { // была ошибка, j++ вместо i++
            boss.setLevel();
        }
        boss.setDamage(boss.getLevel());
        boss.setMaxHealth(5 * boss.getLevel());
        boss.setHealth(boss.getMaxHealth()); // исправлено, чтобы здоровье было полным
        return boss;
    }
    
    public void displayEnemy(Player enemyToDisplay, JLabel pictureLabel, JLabel nameLabel, JLabel damageLabel, JLabel hpLabel) {
        if (enemyToDisplay == null) {
            return; // Защита от ошибок
        }
        if (pictureLabel != null) {
            pictureLabel.setIcon(enemyToDisplay.getPicture());
        }
        if (nameLabel != null) {
            nameLabel.setText(enemyToDisplay.getName());
        }
        if (damageLabel != null) {
            damageLabel.setText(String.valueOf(enemyToDisplay.getDamage()));
        }
        if (hpLabel != null) {
            hpLabel.setText(enemyToDisplay.getHealth() + "/" + enemyToDisplay.getMaxHealth());
        }
    }
}
