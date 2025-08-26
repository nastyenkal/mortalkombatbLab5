package com.mycompany.mortalcombat.characters;

import javax.swing.ImageIcon;

/**
 * Класс персонажа Sub-Zero.
 *
 */
public class SubZero extends Player {

    /**
     * Конструктор персонажа SubZero.
     *
     * @param level уровень персонажа
     * @param health здоровье
     * @param damage урон
     * @param attack атака
     */
    public SubZero(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        java.net.URL picURL = getClass().getResource("/pictures/Sub_Zero.png");
        picture = new ImageIcon(picURL);
    }

    /**
     * Возвращает имя персонажа.
     *
     * @return имя персонажа
     */
    @Override
    public String getName() {
        return "Sub-Zero";
    }
}
