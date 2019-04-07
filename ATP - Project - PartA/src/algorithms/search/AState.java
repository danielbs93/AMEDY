package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class AState implements Comparable<AState> {

   protected String state;
   protected double cost;
   protected AState cameFrom;

    public AState() {
        cameFrom = null;
    }


    //protected abstract boolean isCell(ISearchable maze);

  // protected abstract ArrayList<AState> getNighbors(ISearchable maze); ////remove

    public abstract void setCost(double cost);

    public abstract void setCameFrom(AState cameFrom);


    public boolean equals (Object o) {

       if (this == o){
           return true;
       }
       if (o == null || getClass() != o.getClass()){
           return false;
       }
       AState state1 = (AState)o;

       if (state != null) {
           return state.equals(state1.state);

       } else {
           return state1.state == null;
       }

   }
    public double getCost() {
        return cost;
    }



    public AState getCameFrom() {
        return cameFrom;
    }
    @Override
    public int compareTo(AState o) {
        if ((o).getCost() > this.getCost())
            return 0;

        return 1;
    }


}
