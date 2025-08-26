package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.SubZero;

/**
 * Фабрика для создания персонажа SubZero.
 *
 * @author Настя
 */
public class SubZeroFabric implements EnemyFabricInterface {

    /**
     * Создает экземпляр персонажа SubZero с заданным уровнем.
     *
     * @param i уровень создаваемого персонажа
     * @return новый экземпляр персонажа SubZero
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new SubZero(1, 60, 16, 1);
        return enemy;
    }
}
