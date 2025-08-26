package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.SonyaBlade;

/**
 * Фабрика для создания персонажа SonyaBlade.
 *
 * @author Настя
 */
public class SonyaBladeFabric implements EnemyFabricInterface {

    /**
     * Создает экземпляр персонажа SonyaBlade с заданным уровнем.
     *
     * @param i уровень создаваемого персонажа
     * @return новый экземпляр персонажа SonyaBlade
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new SonyaBlade(1, 80, 16, 1);
        return enemy;
    }
}
