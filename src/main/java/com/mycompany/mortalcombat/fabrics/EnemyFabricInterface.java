package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.Player;

/**
 * Интерфейс для фабрик создания противников.
 *
 * @author Настя
 */
public interface EnemyFabricInterface {

    /**
     * Создает противника указанного уровня.
     *
     * @param i уровень противника
     * @return новый экземпляр противника
     */
    public Player create(int i);
}
