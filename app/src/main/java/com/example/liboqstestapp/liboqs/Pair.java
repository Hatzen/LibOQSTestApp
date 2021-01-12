package com.example.liboqstestapp.liboqs;

// https://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples
public class Pair<L, R> {
    private final L left;
    private final R right;

    public Pair(L left, R right) {
        assert left != null;
        assert right != null;

        this.left = left;
        this.right = right;
    }

    public L getLeft() { return left; }
    public R getRight() { return right; }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof de.hartz.software.parannoying.helper.security.liboqs.java.Pair)) return false;
        de.hartz.software.parannoying.helper.security.liboqs.java.Pair pairo = (de.hartz.software.parannoying.helper.security.liboqs.java.Pair) o;
        return this.left.equals(pairo.getLeft()) && this.right.equals(pairo.getRight());
    }

}