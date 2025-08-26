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
 * @author Мария
 */
public class Fight {

    private Player human;
    private Player enemy;

    private ChangeTexts change = new ChangeTexts();
    private int kind_attack[] = {0};
    private int experiences[] = {40, 90, 180, 260, 410};
    private EnemyFabric fabric = new EnemyFabric();
    public int i = 1;
    private int k = -1;
    private int stun = 0;
    private double v = 0.0;

    int locationsCount;
    private int currentLocationsCount = 0;
    private int levelCount;
    private int currentLevelCount;

    int currentEnemyIndex = 0;
    ArrayList<Player> enemiesInLocation = new ArrayList<>();

    /**
     * Обрабатывает возможные комбинации ходов во время боя.
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
     * Обрабатывает атаку игрока.
     *
     * @param human игрок
     * @param enemy противник
     * @param a тип атаки
     * @param label метка здоровья противника
     * @param label2 метка здоровья игрока
     * @param dialog диалоговое окно окончания раунда
     * @param label3 метка результата раунда
     * @param action экземпляр CharacterAction
     * @param pr1 прогресс-бар здоровья игрока
     * @param pr2 прогресс-бар здоровья противника
     * @param dialog1 диалоговое окно победы
     * @param dialog2 диалоговое окно поражения
     * @param frame главное окно
     * @param results список результатов
     * @param label4 метка победы
     * @param label5 метка поражения
     * @param label6 метка информации о ходе
     * @param label7 метка эффектов
     * @param label8 метка информации
     * @param items массив предметов
     * @param rb радио-кнопка предмета
     * @param optionBox комбо-бокс выбора улучшения
     * @param newLevelLabel метка нового уровня
     * @param pointCountLabe метка очков
     * @param expCountLabel метка опыта
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
                if (human.getHealth() <= 0) {
                    EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                            frame, label4, label5, items);
                } else {
                    if (isFinalWinCondition()) {
                        EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                                frame, label4, label5, items);
                    } else {
                        EndRound(human, enemy, dialog, label3, action, items, optionBox, newLevelLabel, pointCountLabe, expCountLabel);
                    }
                }
            }
        }
    }

    /**
     * Завершает раунд, определяет победителя и добавляет предметы.
     *
     * @param human игрок
     * @param enemy противник
     * @param dialog диалоговое окно окончания раунда
     * @param label метка результата
     * @param action экземпляр CharacterAction
     * @param items массив предметов
     * @param optionBox комбо-бокс выбора улучшения
     * @param newLevelLabel метка нового уровня
     * @param pointCountLabe метка очков
     * @param expCountLabel метка опыта
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
            change.updatePointsAndExp(((Human) human), pointCountLabe, expCountLabel);
        } else {
            label.setText(enemy.getName() + " win");
        }

        i = 1;
        k = -1;
        kind_attack = ResetAttack();
    }

    /**
     * Завершает финальный раунд игры.
     *
     * @param human игрок
     * @param action экземпляр CharacterAction
     * @param results список результатов
     * @param dialog1 диалоговое окно победы
     * @param dialog2 диалоговое окно поражения
     * @param frame главное окно
     * @param label1 метка победы
     * @param label2 метка поражения
     * @param items массив предметов
     */
    public void EndFinalRound(Human human, CharacterAction action,
            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
            JLabel label1, JLabel label2, Items[] items) {

        boolean top = false;
        if (results.isEmpty() || results.size() < 10) {
            top = true;
        } else {
            if (human.getPoints() > results.get(results.size() - 1).getPoints() || human.getPoints() > results.get(9).getPoints()) {
                top = true;
            }
        }

        if (human.getHealth() > 0) {
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
            if (top) {
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

        frame.setVisible(false);
    }

    /**
     * Сбрасывает шаблон атак противника.
     *
     * @return массив с начальным шаблоном атак
     */
    public int[] ResetAttack() {
        int a[] = {0};
        return a;
    }

    /**
     * Создает новый раунд и создает врага.
     *
     * @param human игрок
     * @param label метка изображения противника
     * @param pr1 прогресс-бар здоровья игрока
     * @param pr2 прогресс-бар здоровья противника
     * @param label2 метка имени противника
     * @param text метка урона противника
     * @param label3 метка здоровья противника
     * @param action экземпляр CharacterAction
     * @return созданный противник
     */
    public Player NewRound(Player human, JLabel label, JProgressBar pr1,
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {
        this.human = human;

        if (currentEnemyIndex >= enemiesInLocation.size()) {
            prepareLocationAndRounds(action, human);
            if (enemiesInLocation.isEmpty()) {
                return null;
            }
        }

        enemy = enemiesInLocation.get(currentEnemyIndex);
        currentEnemyIndex++;

        action.displayEnemy(enemy, label, label2, text, label3);

        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy.getMaxHealth());
        human.setNewHealth(human.getMaxHealth());
        enemy.setNewHealth(enemy.getMaxHealth());
        action.HP(human, pr1);
        action.HP(enemy, pr2);

        return enemy;
    }

    /**
     * Определяет количество локаций и раундов на игру.
     *
     * @param action экземпляр CharacterAction
     * @param human игрок
     */
    public void prepareLocationAndRounds(CharacterAction action, Player human) {
        if (currentLocationsCount < locationsCount) {
            currentLocationsCount++;
            int enemiesCount = (int) (Math.random() * 3) + human.getLevel() + 1;

            enemiesInLocation.clear();
            currentEnemyIndex = 0;

            for (int i = 0; i < enemiesCount; i++) {
                enemiesInLocation.add(action.createRandomEnemy());
            }
            enemiesInLocation.set(enemiesCount - 1, action.createBoss(human));

            levelCount = enemiesInLocation.size();
        } else {
            levelCount = 0;
            enemiesInLocation.clear();
        }
    }

    /**
     * Возвращает текущее количество локаций.
     *
     * @return количество локаций
     */
    public int getCurrentLocationsCount() {
        return currentLocationsCount;
    }

    /**
     * Сбрасывает счетчик локаций.
     */
    public void resetCurrentLocationsCount() {
        this.currentLocationsCount = 0;
    }

    /**
     * Устанавливает количество локаций на игру.
     *
     * @param locationsCount количество локаций
     */
    public void setLocationsCount(int locationsCount) {
        this.locationsCount = locationsCount;
    }

    /**
     * Устанавливает игрока.
     *
     * @param human игрок
     */
    public void setHuman(Human human) {
        this.human = human;
    }

    /**
     * Устанавливает противника.
     *
     * @param enemy противник
     */
    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    /**
     * Проверяет условие окончательной победы.
     *
     * @return true, если выполнены условия победы
     */
    public boolean isFinalWinCondition() {
        boolean isLastEnemyInLocation = currentEnemyIndex >= enemiesInLocation.size();
        boolean isLastLocation = currentLocationsCount >= locationsCount;

        return isLastEnemyInLocation && isLastLocation;
    }
}
