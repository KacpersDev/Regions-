package net.pulsir.regions.region.state;

import lombok.Getter;

@Getter
public enum FlagState {

    EVERYONE(0), NONE(1), WHITELIST(2);

    final int integer;

    FlagState(int integer) {
        this.integer = integer;
    }
}
