package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.Player;

/**
 * Фабрика для создания противников разных типов.
 *
 * @author Настя
 */
public class EnemyFabric {

    /**
     * Создает противника указанного типа и уровня.
     *
     * @param i тип противника (0 - Baraka, 1 - SubZero, 2 - LiuKang, 3 -
     * SonyaBlade, 4 - ShaoKahn)
     * @param j уровень противника
     * @return новый экземпляр противника
     */
    public Player create(int i, int j) {
        EnemyFabricInterface fabric = null;
        switch (i) {
            case 0:
                fabric = new BarakaFabric();
                break;
            case 1:
                fabric = new SubZeroFabric();
                break;
            case 2:
                fabric = new LiuKangFabric();
                break;
            case 3:
                fabric = new SonyaBladeFabric();
                break;
            case 4:
                fabric = new ShaoKahnFabric();
                break;
        }
        Player enemy = fabric.create(j);
        return enemy;
    }
}
