package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.Player;

/**
 *
 * @author lihac
 */
public class EnemyFabric {
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
