package net.pulsir.regions.region.flag;

import lombok.Getter;

@Getter
public enum RegionFlags {

    BLOCK_BREAK(0), BLOCK_PLACE(1), INTERACT(2), ENTITY_DAMAGE(3);

    final int integer;

    RegionFlags(int integer) {
        this.integer = integer;
    }
}
