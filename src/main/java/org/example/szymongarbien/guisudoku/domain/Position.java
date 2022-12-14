package org.example.szymongarbien.guisudoku.domain;

import java.awt.*;

public class Position extends Button {
    final int i;
    final int j;
    int val;
    final boolean blocked;

    Position(int i, int j, int val, boolean blocked) {
        this.i = i;
        this.j = j;
        this.val = val;
        this.blocked = blocked;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
