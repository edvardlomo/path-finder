import java.util.ArrayList;

abstract class Rute{
  protected final int RAD, KOL;
  protected ArrayList<Rute> naboer = new ArrayList<>();
  protected boolean gaatt = false;

  Rute(int rad, int kol){
    this.RAD = rad;
    this.KOL = kol;
  }

  public boolean getGaatt(){
    return gaatt;
  }

  public void addNabo(Rute nabo){
    naboer.add(nabo);
  }

  public ArrayList<ArrayList<Tuppel>> finnUtvei(){
    ArrayList<ArrayList<Tuppel>> stier = new ArrayList<>();
    gaa(stier, new ArrayList<Tuppel>());
    return stier;
  }

  public abstract void gaa(ArrayList<ArrayList<Tuppel>> stier, ArrayList<Tuppel> sti);
  public abstract char tilTegn();
}
