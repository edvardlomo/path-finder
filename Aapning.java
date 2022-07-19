import java.util.ArrayList;

class Aapning extends HvitRute{
  Aapning(int rad, int kol){
    super(rad, kol);
  }

  @Override
  public void gaa(ArrayList<ArrayList<Tuppel>> stier, ArrayList<Tuppel> sti){
    sti.add(new Tuppel(KOL,RAD));
    stier.add(new ArrayList<Tuppel>(sti));
    sti.remove(sti.size()-1);
  }
}
