package com.mycompany.mortalcombat;

import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.Human;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

/**
 * Класс для управления текстовыми элементами интерфейса игры.
 *
 * @author Мария
 */
public class ChangeTexts {

    /**
     * Обновляет текстовые элементы интерфейса для нового раунда.
     *
     * @param human игрок
     * @param enemy противник
     * @param pr1 прогресс-бар здоровья игрока
     * @param pr2 прогресс-бар здоровья противника
     * @param label метка для очков
     * @param label2 метка для опыта
     * @param label3 метка уровня игрока
     * @param label4 метка уровня противника
     * @param label5 метка здоровья игрока
     * @param label6 метка здоровья противника
     * @param label7 метка урона игрока
     * @param label8 метка информации о ходе
     * @param label9 метка дополнительной информации
     * @param i номер хода
     * @param items массив предметов
     * @param rb1 радио-кнопка предмета 1
     * @param rb2 радио-кнопка предмета 2
     * @param rb3 радио-кнопка предмета 3
     */
    public void NewRoundTexts(Player human, Player enemy, JProgressBar pr1,
            JProgressBar pr2, JLabel label, JLabel label2, JLabel label3,
            JLabel label4, JLabel label5, JLabel label6, JLabel label7, JLabel label8, JLabel label9,
            int i, Items[] items, JRadioButton rb1, JRadioButton rb2, JRadioButton rb3) {
        label.setText(Integer.toString(((Human) human).getPoints()));
        label2.setText(Integer.toString(((Human) human).getExperience()) + "/" + ((Human) human).getNextExperience());
        label3.setText(Integer.toString(human.getLevel()) + " level");
        label4.setText(Integer.toString(enemy.getLevel()) + " level");
        label5.setText(Integer.toString(human.getMaxHealth()) + "/" + Integer.toString(human.getMaxHealth()));
        label6.setText(Integer.toString(enemy.getMaxHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        label7.setText(Double.toString(human.getDamage()));
        if (i % 2 == 1) {
            label8.setText("Your turn");
        } else {
            label8.setText(enemy.getName() + "'s turn");
        }
        BagText(items, rb1, rb2, rb3);
        label9.setText("");
    }

    /**
     * Обновляет текстовые элементы интерфейса во время раунда.
     *
     * @param human игрок
     * @param enemy противник
     * @param label метка здоровья противника
     * @param label2 метка здоровья игрока
     * @param i номер хода
     * @param label3 метка информации о ходе
     */
    public void RoundTexts(Player human, Player enemy, JLabel label, JLabel label2, int i, JLabel label3) {
        if (enemy.getHealth() >= 0) {
            label.setText(Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMaxHealth()));
        } else {
            label.setText("0/" + Integer.toString(enemy.getMaxHealth()));
        }
        if (human.getHealth() >= 0) {
            label2.setText(Integer.toString(human.getHealth()) + "/" + Integer.toString(human.getMaxHealth()));
        } else {
            label2.setText("0/" + Integer.toString(human.getMaxHealth()));
        }
        if (i % 2 == 1) {
            label3.setText("Your turn");
        } else {
            label3.setText(enemy.getName() + "'s turn");
        }
    }

    /**
     * Отображает текст окончания игры.
     *
     * @param human игрок
     * @param label метка для вывода результата
     */
    public void EndGameText(Human human, JLabel label) {
        if (human.getWin() == 12) {
            label.setText("Победа на вашей стороне");
        } else {
            label.setText("Победа не на вашей стороне");
        }
    }
    
    /**
     * Обновляет текст для отображения предметов в инвентаре.
     *
     * @param items массив предметов
     * @param rb1 радио-кнопка предмета 1
     * @param rb2 радио-кнопка предмета 2
     * @param rb3 радио-кнопка предмета 3
     */
    public void BagText(Items[] items, JRadioButton rb1, JRadioButton rb2, JRadioButton rb3) {
        rb1.setText(items[0].getName() + ", " + items[0].getCount() + " шт");
        rb2.setText(items[1].getName() + ", " + items[1].getCount() + " шт");
        rb3.setText(items[2].getName() + ", " + items[2].getCount() + " шт, (применяется автоматически)");
    }
    
    /**
     * Обновляет отображение очков и опыта игрока.
     *
     * @param human игрок
     * @param pointsLabel метка очков
     * @param expLabel метка опыта
     */
    public void updatePointsAndExp(Human human, JLabel pointsLabel, JLabel expLabel) {
        pointsLabel.setText(Integer.toString(human.getPoints()));
        expLabel.setText(human.getExperience() + "/" + human.getNextExperience());
    }
}
