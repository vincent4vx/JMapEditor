/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.common;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Pair<F, S> implements IPair<F, S>{
    final private F first;
    final private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public F getFirst() {
        return first;
    }

    @Override
    public S getSecond() {
        return second;
    }
    
}
