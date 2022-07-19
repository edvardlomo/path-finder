import java.util.ArrayList;

class HvitRute extends Rute{
  HvitRute(int rad, int kol){
    super(rad, kol);
  }

  @Override
  public void gaa(ArrayList<ArrayList<Tuppel>> stier, ArrayList<Tuppel> sti){
    if(gaatt){
      return;
    }
    gaatt = true;
    sti.add(new Tuppel(KOL,RAD));
    for(Rute nabo : naboer){
      nabo.gaa(stier,sti);
    }
    gaatt = false;
    sti.remove(sti.size()-1);
  }

  public char tilTegn(){
    return '.';
  }
}
