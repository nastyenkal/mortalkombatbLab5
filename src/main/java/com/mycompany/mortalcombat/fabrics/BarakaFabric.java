package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.Baraka;

/**
 * Фабрика для создания персонажа Baraka.
 *
 * @author Настя
 */
public class BarakaFabric implements EnemyFabricInterface {

    /**
     * Создает экземпляр персонажа Baraka с заданным уровнем.
     *
     * @param i уровень создаваемого персонажа
     * @return новый экземпляр персонажа Baraka
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new Baraka(1, 100, 12, 1);
        return enemy;
    }
}
