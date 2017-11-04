package com.epicodus.djmusicmanager.util;


public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
