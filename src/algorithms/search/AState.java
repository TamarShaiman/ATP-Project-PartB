package algorithms.search;

public class AState {
    private String state;
    private double cost = 0;
    private AState cameFrom;

    public AState(String state){
        this.state = state;
       // this.cameFrom = null ;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if ( o == null || this.getClass() != o.getClass()) return false;

        AState state1 = (AState) o;

        return this.state != null ? this.state.equals(state1.state) : state1.state.equals(null);
    }

    @Override
    public int hashCode(){
        return this.state != null ? this.state.hashCode() : 0;
    }

    public String getState() {
        return state;
    }

/*    public void setState(String state) {
        this.state = state;
    }*/

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public String toString()
    {
        return this.state;
    }
}
