import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class Labyrint{
  private ArrayList<ArrayList<Rute>> labyrint = new ArrayList<ArrayList<Rute>>();
  private int koloner, rader;

  Labyrint(File fil) throws FileNotFoundException {
    laggLabyrint(fil);
    settNaboer();
  }

  public ArrayList<ArrayList<Rute>> getLabyrint(){
    return labyrint;
  }

  public int getKoloner(){
    return koloner;
  }

  public int getRader(){
    return rader;
  }

  public void laggLabyrint(File fil) throws FileNotFoundException {
    Scanner leser = new Scanner(fil);
    String[] kordinater = leser.nextLine().split(" ");
    rader = Integer.parseInt(kordinater[0]);
    koloner = Integer.parseInt(kordinater[1]);
    for(int i = 0; i < rader; i++){
      ArrayList<Rute> rad = new ArrayList<>();
      String strKol = leser.nextLine();
      for(int j = 0; j < koloner; j++){
        if(strKol.charAt(j) == '.' && (i == 0 || i == rader - 1 || j == 0 || j == koloner - 1)){
          rad.add(new Aapning(i,j));
        } else if(strKol.charAt(j) == '.'){
          rad.add(new HvitRute(i,j));
        } else {
          rad.add(new SortRute(i,j));
        }
      }
      labyrint.add(rad);
    }
  }

  public void settNaboer(){
    for(int i = 0; i < rader; i++){
      for(int j = 0; j < koloner; j++){
        Rute denneRute = labyrint.get(i).get(j);
        // Setter nord, syd, oest, vest naboer
        for(int k = -2; k < 2; k++){
          try{
            Rute nabo = labyrint.get(i+(k%2)).get(j+((k+1)%2));
            denneRute.addNabo(nabo);
          } catch (Exception e){}
        }
      }
    }
  }

  public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int startKol, int startRad){
    //Ugyldig koordinat
    if(!(startKol < koloner && startKol > 0) || !(startRad < rader && startRad > 0)){
      return new ArrayList<ArrayList<Tuppel>>();
    }
    Rute start = labyrint.get(startRad).get(startKol);
    return start.finnUtvei();
  }

  public String toString(){
    String str = "";
    for(int i = 0; i < rader; i++){
      for(int j = 0; j < koloner; j++){
        str += labyrint.get(i).get(j).tilTegn();
      }
      str += "\n";
    }
    return str;
  }
}
