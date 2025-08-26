package com.mycompany.mortalcombat.characters;

import javax.swing.ImageIcon;

/**
 * Класс персонажа Baraka.
 *
 */
public class Baraka extends Player {

    /**
     * Конструктор персонажа Baraka.
     *
     * @param level уровень персонажа
     * @param health здоровье
     * @param damage урон
     * @param attack атака
     */
    public Baraka(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        java.net.URL picURL = getClass().getResource("/pictures/Baraka.png");
        picture = new ImageIcon(picURL);
    }

    /**
     * Возвращает имя персонажа.
     *
     * @return имя персонажа
     */
    @Override
    public String getName() {
        return "Baraka";
    }
}
