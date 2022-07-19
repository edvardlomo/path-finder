import java.util.ArrayList;

class SortRute extends Rute{
  SortRute(int rad, int kol){
    super(rad, kol);
  }

  @Override
  public void gaa(ArrayList<ArrayList<Tuppel>> stier, ArrayList<Tuppel> sti){
    return;
  }

  public char tilTegn(){
    return '#';
  }
}
