package pathfinder.model;

public class ArrayCoordinate {
    public int outer;
    public int inner;
    public ArrayCoordinate(int outer, int inner)
    {
        this.outer = outer;
        this.inner = inner;
    }

    public int getOuter()
    {
        return this.outer;
    }
    public int getInner()
    {
        return this.inner;
    }
    
    public void decrementOuter()
    {
        this.outer -= 1;
    }
    public void incrementInner()
    {
        this.inner += 1;
    }
    
    public boolean equals(ArrayCoordinate coordinate)
    {
        boolean equals = true;
        if (this.getOuter() != coordinate.getOuter() || this.getInner() != coordinate.getInner())
        {
            equals = false;
        }
        return equals;
    }
}

