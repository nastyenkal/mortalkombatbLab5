package com.mycompany.mortalcombat.characters;

import javax.swing.ImageIcon;

/**
 * Класс персонажа Shao Kahn.
 *
 */
public class ShaoKahn extends Player {

    /**
     * Конструктор персонажа ShaoKahn.
     *
     * @param level уровень персонажа
     * @param health здоровье
     * @param damage урон
     * @param attack атака
     */
    public ShaoKahn(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        java.net.URL picURL = getClass().getResource("/pictures/Shao_Kahn.png");
        picture = new ImageIcon(picURL);
    }

    /**
     * Возвращает имя персонажа.
     *
     * @return имя персонажа
     */
    @Override
    public String getName() {
        return "Shao Kahn";
    }
}
