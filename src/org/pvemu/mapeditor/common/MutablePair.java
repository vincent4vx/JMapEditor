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
public class MutablePair<F, S> implements IPair<F, S>{
    private F first;
    private S second;

    public MutablePair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    @Override
    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }
    
}
