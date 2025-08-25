package com.mycompany.mortalcombat;

import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.Human;
import com.mycompany.mortalcombat.characters.Player.ActionType;
import com.mycompany.mortalcombat.characters.ShaoKahn;
import com.mycompany.mortalcombat.characters.SubZero;
import com.mycompany.mortalcombat.fabrics.EnemyFabric;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

/**
 * Класс, реализующий механику боя между игроком и противником.
 *
 */
public class Fight {

    // игрок и противник
    private Player human;
    private Player enemy;

    //параметры игровых механик
    private ChangeTexts change = new ChangeTexts();
    private int kind_attack[] = {0};
    private int experiences[] = {40, 90, 180, 260, 410};
    private EnemyFabric fabric = new EnemyFabric();
    public int i = 1;
    private int k = -1;
    private int stun = 0;
    private double v = 0.0;

    // счетчики для локаций и уровней
    int locationsCount;
    private int currentLocationsCount = 0;
    private int levelCount;
    private int currentLevelCount;

    //противники на текущей локации
    int currentEnemyIndex = 0;
    ArrayList<Player> enemiesInLocation = new ArrayList<>();

    /**
     * метод, который обрабатывает возможные комбинации ходов во время боя между
     * игроками
     *
     * @param p1 первый игрок
     * @param p2 второй игрок
     * @param l метка для вывода текста хода первого игрока
     * @param l2 метка для вывода текста хода второго игрока
     */
    public void Move(Player p1, Player p2, JLabel l, JLabel l2) {
        if (stun == 1) {
            p1.setAttackType(-1);
        }
        switch (Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack())) {
            case "10":
                v = Math.random();
                if (p1 instanceof ShaoKahn & v < 0.15) {
                    p2.setHealth(-(int) (p1.getDamage() * 0.5));
                    l2.setText("Your block is broken");

                } else {
                    p1.setHealth(-(int) (p2.getDamage() * 0.5));
                    l2.setText(p2.getName() + " counterattacked");
                }
                break;
            case "11":
                p2.setHealth(-p1.getDamage());
                l2.setText(p1.getName() + " attacked");
                break;
            case "00":
                v = Math.random();
                if (v <= 0.5) {
                    stun = 1;
                }
                l2.setText("Both defended themselves");
                break;
            case "01":
                l2.setText(p1.getName() + " didn't attacked");
                break;
            case "-10":
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " didn't attacked");
                break;
            case "-11":
                p1.setHealth(-p2.getDamage());
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " attacked");
                break;
            case "20":
            case "2-1":
                if (Math.random() < 0.75) {
                    p2.setDebuff(p2.getLevel());
                    l.setText(p1.getName() + "used debuff");
                    l2.setText(p2.getName() + " are debuffed");
                }
                break;
            case "02":
            case "-12":
                if (Math.random() < 0.75) {
                    p1.setDebuff(p1.getLevel());
                    l2.setText(p2.getName() + "used debuff");
                    l.setText(p1.getName() + " are debuffed");
                }
                break;
            case "12":
                p2.setHealth(-p1.getDamage() * 1.15);
                l.setText(p1.getName() + " attacked");
                l2.setText("Failed to debuff oppponent");
                break;
            case "21":
                p1.setHealth(-p2.getDamage() * 1.15);
                l.setText(p1.getName() + " attacked");
                l.setText("Failed to debuff oppponent");
                break;
            case "30":
            case "3-1":
                p1.setHealth((p1.getMaxHealth() - p1.getHealth()) * 0.5);
                l.setText(p1.getName() + " regenerated");
                break;
            case "03":
            case "-13":
                p2.setHealth((p2.getMaxHealth() - p2.getHealth()) * 0.5);
                l2.setText(p2.getName() + " regenerated");
                break;
            case "13":
                p2.setHealth(-p1.getDamage() * 2);
                l.setText(p1.getName() + " attacked");
                l2.setText("Failed to regenerate");
                break;
            case "31":
                p1.setHealth(-p2.getDamage() * 2);
                l2.setText(p2.getName() + " attacked");
                l.setText("Failed to regenerate");
                break;
            case "23":
                p2.setDebuff(p1.getLevel());
                p2.setHealth((p2.getMaxHealth() - p2.getHealth()) * 0.5);
                l.setText(p1.getName() + " used debuff");
                l2.setText(p2.getName() + " regenerated");
                break;
            case "32":
                p1.setDebuff(p2.getLevel());
                p1.setHealth((p1.getMaxHealth() - p1.getHealth()) * 0.5);
                l2.setText(p2.getName() + " used debuff");
                l.setText(p1.getName() + "regenerated");
                break;
        }
    }

    /**
     * метод, которых обрабатывает возможные комбинации атаки.
     *
     */
    public void Hit(Player human, Player enemy, int a, JLabel label,
            JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
            JProgressBar pr1, JProgressBar pr2, JDialog dialog1,
            JDialog dialog2, JFrame frame, ArrayList<Result> results,
            JLabel label4, JLabel label5, JLabel label6, JLabel label7,
            JLabel label8, Items[] items, JRadioButton rb, JComboBox optionBox, JLabel newLevelLabel, JLabel pointCountLabe, JLabel expCountLabel) {
        label7.setText("");
        human.setAttackType(a);

        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = action.ChooseBehavior(enemy, action);
            k = 0;
        }

        if (enemy instanceof SubZero && Math.random() < 0.15) {
            enemy.setAction(ActionType.WEAKEN);
        } else if (enemy instanceof ShaoKahn && Math.random() < 0.2) {
            enemy.setAction(ActionType.REGENERATE);
        } else {
            int atk = kind_attack[k];
            switch (atk) {
                case 0 ->
                    enemy.setAction(ActionType.DEFEND);
                case 1 ->
                    enemy.setAction(ActionType.ATTACK);
                default ->
                    enemy.setAction(ActionType.NONE);
            }
            human.deleteDebuff();
            enemy.deleteDebuff();
            if (i % 2 == 1) {
                Move(human, enemy, label7, label8);
            } else {
                Move(enemy, human, label7, label8);
            }
            i++;
            change.RoundTexts(human, enemy, label, label2, i, label6);
            action.HP(human, pr1);
            action.HP(enemy, pr2);
            if (human.getHealth() <= 0 & items[2].getCount() > 0) {
                human.setNewHealth((int) (human.getMaxHealth() * 0.05));
                items[2].setCount(-1);
                action.HP(human, pr1);
                label2.setText(human.getHealth() + "/" + human.getMaxHealth());
                rb.setText(items[2].getName() + ", " + items[2].getCount() + " шт");
                label7.setText("Вы воскресли");
            }
            if (human.getHealth() <= 0 || enemy.getHealth() <= 0) {
                // Случай 1: Игрок проиграл. Всегда конец игры.
                if (human.getHealth() <= 0) {
                    EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                            frame, label4, label5, items);
                } // Случай 2: Игрок выиграл. Теперь нужно проверить, была ли это финальная победа.
                else {
                    if (isFinalWinCondition()) {
                        // Да, это была финальная победа! Сразу показываем финальное окно.
                        EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                                frame, label4, label5, items);
                    } else {
                        // Нет, это обычная победа. Показываем промежуточное окно.
                        EndRound(human, enemy, dialog, label3, action, items, optionBox, newLevelLabel, pointCountLabe, expCountLabel);
                    }
                }
            }

//            if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
//                if (levelCount == 0 || human.getHealth() <= 0) {
//                    EndFinalRound(((Human) human), action, results, dialog1, dialog2,
//                            frame, label4, label5, items);
//
//                } else {
//                    EndRound(human, enemy, dialog, label3, action, items, optionBox, newLevelLabel, pointCountLabe, expCountLabel);
//                }
//            }
        }
    }

    /**
     * метод, победителя в конце раунда, добавляющий предметы по итогам раунда и
     * поднимающий уровень игрока.
     *
     */
    public void EndRound(Player human, Player enemy, JDialog dialog, JLabel label,
            CharacterAction action, Items[] items, JComboBox optionBox, JLabel newLevelLabel, JLabel pointCountLabe, JLabel expCountLabel) {

        dialog.setSize(700, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        //dialog.setBounds(300, 150, 700, 600);
        if (human.getHealth() > 0) {

            label.setText("You win");
            ((Human) human).setWin();

            if (enemy instanceof ShaoKahn) {
                action.AddItems(38, 23, 8, items);
                action.AddPointsBoss(((Human) human), action.getEnemyes());

            } else {
                action.AddItems(25, 15, 5, items);
                Boolean isLevelUp = action.AddPoints(((Human) human), action.getEnemyes());
                if (isLevelUp) {
                    optionBox.setSelectedIndex(0);
                    optionBox.setVisible(true);
                    newLevelLabel.setVisible(true);

                }

            }
        change.updatePointsAndExp(((Human) human), pointCountLabe, expCountLabel);// новое
        } else {
            label.setText(enemy.getName() + " win");
        }

        i = 1;
        k = -1;
        kind_attack = ResetAttack();
    }

    /**
     * метод, заврешающий финальный раунд игры, отображает соответствующее
     * диалоговое окно с результатом , обновляет очки и проверяет, попал ли
     * игрок в топ.
     *
     */
    public void EndFinalRound(Human human, CharacterAction action,
            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
            JLabel label1, JLabel label2, Items[] items) {

        // Проверяем, попал ли игрок в топ-10
        boolean top = false;
        if (results.isEmpty() || results.size() < 10) {
            top = true;
        } else {
            // Проверяем, больше ли очков у игрока, чем у последнего в топе
            if (human.getPoints() > results.get(results.size() - 1).getPoints() || human.getPoints() > results.get(9).getPoints()) {
                top = true;
            }
        }

        // Разделяем логику для победы и поражения
        if (human.getHealth() > 0) {
            // --- ЛОГИКА ПОБЕДЫ ---
            human.setWin();
            action.AddPoints(human, action.getEnemyes());

            if (top) {
                label1.setText("Победа на вашей стороне");
                dialog1.pack();
                dialog1.setLocationRelativeTo(null);
                dialog1.setVisible(true);
            } else {
                label2.setText("Победа на вашей стороне");
                dialog2.pack();
                dialog2.setLocationRelativeTo(null);
                dialog2.setVisible(true);
            }
        } else {
            // --- ЛОГИКА ПОРАЖЕНИЯ ---
            if (top) {
                // Даже при поражении игрок может попасть в топ по очкам
                label1.setText("Вы проиграли");
                dialog1.pack();
                dialog1.setLocationRelativeTo(null);
                dialog1.setVisible(true);
            } else {
                label2.setText("Вы проиграли");
                dialog2.pack();
                dialog2.setLocationRelativeTo(null);
                dialog2.setVisible(true);
            }
        }

        frame.setVisible(false); // Скрываем основное окно, вместо dispose()
    }
//    public void EndFinalRound(Human human, CharacterAction action,
//            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
//            JLabel label1, JLabel label2, Items[] items) {
//        String text = "Вы проиграли";
//
//        if (human.getHealth() > 0) {
//            human.setWin();
//            action.AddPoints(human, action.getEnemyes());
//            text = "Победа на вашей стороне";
//        }
//        boolean top = false;
//        if (results == null) {
//            top = true;
//        } else {
//            int i = 0;
//            for (int j = 0; j < results.size(); j++) {
//                if (human.getPoints() < results.get(j).getPoints()) {
//                    i++;
//                }
//            }
//            if (i < 10) {
//                top = true;
//            }
//        }
//
//        if (top) {
//            dialog1.pack();
//            dialog1.setLocationRelativeTo(null);
//            dialog1.setVisible(true);
//            //dialog1.setBounds(150, 150, 600, 500);
//            label1.setText(text);
//        } else {
//            dialog2.pack();
//            dialog2.setLocationRelativeTo(null);
//            dialog2.setVisible(true);
//            //dialog2.setBounds(150, 150, 470, 360);
//            label2.setText(text);
//        }
//        frame.dispose();
//    }

    /**
     * Сброс шаблона атак противника
     *
     * @return массив с начальным шаблоном атак
     */
    public int[] ResetAttack() {
        int a[] = {0};
        return a;
    }

    /**
     * метод, формирующий новый раунд и создающий врага
     *
     * @return объект созданного врага
     */
//старая версия
//    public Player NewRound(Player human, JLabel label, JProgressBar pr1,
//            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {
//        this.human = human;
//
//        Player enemy1 = null;
//        if (levelCount == 1) {
//            enemy = action.ChooseBoss(label, label2, text, label3, human.getLevel(), human);
//        } else if (levelCount > 1) {
//
//            enemy = action.ChooseEnemy(label, label2, text, label3);
//
//        }
//        levelCount--;
//        pr1.setMaximum(human.getMaxHealth());
//        pr2.setMaximum(enemy.getMaxHealth());
//        human.setNewHealth(human.getMaxHealth());
//        enemy.setNewHealth(enemy.getMaxHealth());
//        action.HP(human, pr1);
//        action.HP(enemy, pr2);
//
//        if (levelCount == 0) {
//            prepareLocationAndRounds();
//        }
//
//        return enemy;
//    }

// Новая, исправленная версия
    // Замените старый метод на этот
    public Player NewRound(Player human, JLabel label, JProgressBar pr1,
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {
        this.human = human;

        // Если враги в локации закончились, готовим новую
        if (currentEnemyIndex >= enemiesInLocation.size()) {
            prepareLocationAndRounds(action, human);
            // Если и после этого врагов нет - игра окончена
            if (enemiesInLocation.isEmpty()) {
                return null;
            }
        }

        enemy = enemiesInLocation.get(currentEnemyIndex); // Берем следующего врага из списка
        currentEnemyIndex++;

        // Отображаем его
        action.displayEnemy(enemy, label, label2, text, label3);

        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy.getMaxHealth());
        human.setNewHealth(human.getMaxHealth());
        enemy.setNewHealth(enemy.getMaxHealth()); // У врага тоже должно быть полное здоровье
        action.HP(human, pr1);
        action.HP(enemy, pr2);

        return enemy;
    }

    /**
     * метод, определяющий количество локаций и раундов на игру.
     *
     */
// Старая версия
/*
public void prepareLocationAndRounds() {
    if (currentLocationsCount < locationsCount) {
        currentLocationsCount++;
        levelCount = (int) (Math.random() * 3) + human.getLevel() + 1;
    }
}
     */
// Новая, исправленная версия
    // Замените старый метод на этот
    public void prepareLocationAndRounds(CharacterAction action, Player human) {
        if (currentLocationsCount < locationsCount) {
            currentLocationsCount++;
            // Определяем количество врагов + 1 босс в конце
            int enemiesCount = (int) (Math.random() * 3) + human.getLevel() + 1;

            enemiesInLocation.clear();
            currentEnemyIndex = 0;

            // Создаем обычных врагов, не отображая их
            for (int i = 0; i < enemiesCount; i++) {
                enemiesInLocation.add(action.createRandomEnemy());
            }
            // Заменяем последнего врага на босса
            enemiesInLocation.set(enemiesCount - 1, action.createBoss(human));

            levelCount = enemiesInLocation.size(); // Сохраняем общее число врагов
        } else {
            levelCount = 0;
            enemiesInLocation.clear();
        }
    }

    /**
     * геттер, определяющий количество локаций и раундов на игру.
     *
     * @return количество локаций
     */
    public int getCurrentLocationsCount() {
        return currentLocationsCount;
    }

    /**
     * метод для сброса кол-ва локаций на игру
     */
    public void resetCurrentLocationsCount() {
        this.currentLocationsCount = 0;
    }

    /**
     * сеттер кол-ва локаций на игру.
     *
     */
    public void setLocationsCount(int locationsCount) {
        this.locationsCount = locationsCount;
    }

    /**
     * сеттер.
     *
     */
    public void setHuman(Human human) {
        this.human = human;
    }

    /**
     * сеттер.
     *
     */
    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }
// новые
    public boolean isFinalWinCondition() {
        boolean isLastEnemyInLocation = currentEnemyIndex >= enemiesInLocation.size();
        boolean isLastLocation = currentLocationsCount >= locationsCount;

        return isLastEnemyInLocation && isLastLocation;
    }
}
