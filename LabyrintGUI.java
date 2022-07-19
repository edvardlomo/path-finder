import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class LabyrintGUI {
  private JFrame vindu = new JFrame();
  private JPanel panel = new JPanel();
  private Labyrint labyrint;
  private ArrayList<ArrayList<JButton>> celler;

  LabyrintGUI(){
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    lagMenu();
    vindu.add(panel);
    JLabel startLabel = new JLabel("Velg fil i fil -> Velg fil");
    panel.add(startLabel);
    vindu.pack();
    vindu.setVisible(true);
  }

  public void velgFil(){
    JFileChooser velger = new JFileChooser("labyrinter");
    int resultat = velger.showOpenDialog(null);
    File f;
    if(resultat == JFileChooser.APPROVE_OPTION){
      f = velger.getSelectedFile();
      try {
        labyrint = new Labyrint(f);
      } catch (FileNotFoundException e){}
      lagLabyrintGUI();
    }
  }

  public void lagMenu(){
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("Fil");
    menuFile.addMenuListener(new MenuListener(){
      @Override
      public void menuSelected(MenuEvent e){
        velgFil();
      }

      @Override
      public void menuCanceled(MenuEvent e){}

      @Override
      public void menuDeselected(MenuEvent e){}
    });
    menuBar.add(menuFile);
    vindu.setJMenuBar(menuBar);
  }

  public void lagLabyrintGUI(){
    int kol, rad;
    kol = labyrint.getKoloner();
    rad = labyrint.getRader();
    vindu.remove(panel);
    panel = new JPanel();
    JPanel grid = new JPanel();
    panel.add(grid);
    grid.setLayout(new GridLayout(rad,kol,0,0));
    ArrayList<ArrayList<Rute>> arrayLabyrint = labyrint.getLabyrint();

    // Stoerrelse paa celler i GUI
    celler = new ArrayList<>();
    int celleSize = 750/(rad > kol ? rad : kol);
    celleSize = celleSize < 50 ? celleSize : 50;
    for(int i = 0; i < rad; i++){
      ArrayList<JButton> radCeller = new ArrayList<>();
      for(int j = 0; j < kol; j++){
        JButton celle = new JButton();
        Rute labyrintCelle = arrayLabyrint.get(i).get(j);
        celle.addActionListener(new BtnListener(i,j, labyrintCelle instanceof SortRute));
        celle.setBorderPainted(false);
        celle.setPreferredSize(new Dimension(celleSize,celleSize));
        Color farge = labyrintCelle instanceof SortRute ? Color.BLACK : Color.WHITE;
        celle.setBackground(farge);
        grid.add(celle);
        radCeller.add(celle);
      }
      celler.add(radCeller);
    }
    vindu.add(panel);
    vindu.pack();
  }

  class BtnListener implements ActionListener {
    private final int RAD, KOL;
    private final boolean vegg;

    BtnListener(int rad, int kol, boolean vegg){
      this.RAD = rad;
      this.KOL = kol;
      this.vegg = vegg;
    }

    @Override
    public void actionPerformed(ActionEvent e){
      lagLabyrintGUI();
      visSti(labyrint.finnUtveiFra(KOL,RAD));
    }

    public void visSti(ArrayList<ArrayList<Tuppel>> stier){
      if(vegg){
      } else if(stier.size() == 0){
        celler.get(RAD).get(KOL).setBackground(Color.GRAY);
      } else {
        for(Tuppel cell : finnKortesteSti(stier)){
          celler.get(cell.Y).get(cell.X).setBackground(Color.GRAY);
        }
      }
      panel.add(new JLabel("Stier: "+stier.size()));
      vindu.pack();
    }

    public ArrayList<Tuppel> finnKortesteSti(ArrayList<ArrayList<Tuppel>> stier){
      ArrayList<Tuppel> kortesteSti = stier.get(0);
      int lengde = kortesteSti.size();
      for(ArrayList<Tuppel> sti : stier){
        if(sti.size() < lengde){
          kortesteSti = sti;
          lengde = sti.size();
        }
      }
      return kortesteSti;
    }
  }
}
