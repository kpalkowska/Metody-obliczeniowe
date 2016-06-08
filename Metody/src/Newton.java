import java.util.Scanner;
import static java.lang.Math.*;
     
public class Newton {
     
    static double[][] funkcja(double x,double y)
    {
        double[][] macierz = new double[1][2];
        macierz[0][0]=x*x+4*y*y-4;
        macierz[0][1]=x*x-2*x-y+1;
        return macierz;
    }
     
    static double[][] pochodna(double x,double y)
    {
        double[][] macierz = new double[2][2];
        macierz[0][0]=2*x;
        macierz[0][1]=8*y;
        macierz[1][0]=2*x-2;
        macierz[1][1]=-1;
        return macierz;
    }
     
    static boolean sprawdz(double[][] macierz)
    {
        double a,b,c,d,wynik;
        a=macierz[0][0];
        b=macierz[0][1];
        c=macierz[1][0];
        d=macierz[1][1];
     
        wynik=a*d-b*c;
     
        if(wynik!=0){
        	return true;
        }
        else{
        	System.out.println("Nieprawidlowe dane. Macierz nieodwracalna!");
        	return false;
        }
    }
    
    static double[][] odwroc(double[][] macierz2)
    {
        double a,b,c,d,wynik;
        a=macierz2[0][0];
        b=macierz2[0][1];
        c=macierz2[1][0];
        d=macierz2[1][1];
     
        wynik=1/(a*d-c*b);
     
        macierz2[0][0]=wynik*d;
        macierz2[0][1]=wynik*(-b);
        macierz2[1][0]=wynik*(-c);
        macierz2[1][1]=wynik*a;
     
        return macierz2;
    }

    public static void newton(){

        Scanner in = new Scanner(System.in);
        double x, y; // punkty startowe
        int kroki = 1; //ilosc powtorzonych prob
        int MAX = 50; //maksymalna liczba iteracji
        double p1 = 1;
        double p2 = 1;
        double PRZYB = 0.01;
        
	    System.out.println("\nRownania:");
	    System.out.println("x^2+4y^2-4=0");
	    System.out.println("x^2-2x-y+1=0 ");
     
   	    double[][] funkcja = new double[1][2];
   	    double[][] pochodna = new double[2][2];
   	    double[][] odwrotnosc = new double[2][2];
   	    double[] wynikMnozenia = new double[2];
   	    double[] poprzedniWynik = new double[2];
   	    boolean sprawdz = true;

    	do{
	    System.out.print("\nPodaj x: ");
	    x = in.nextDouble();
	    System.out.print("Podaj y: ");
	    y = in.nextDouble();
	    sprawdz = sprawdz(pochodna(x,y));
    	}
    	while(sprawdz == false);
    	
    	while(kroki<=MAX && sprawdz == true && p1 > PRZYB && p2 > PRZYB)
	    {
			funkcja = funkcja(x,y);
		    pochodna = pochodna(x,y);
		    sprawdz = sprawdz(odwroc(pochodna(x,y)));
		    odwrotnosc = odwroc(pochodna(x,y));
			     
			for(int i=0;i<2;i++)
		        wynikMnozenia[i] = 0;     
			
		    //mnozenie f'^-1*f
		    for(int i=0;i<2;i++)
		    {
		        for(int j=0;j<2;j++)
		            wynikMnozenia[i]=wynikMnozenia[i]+odwrotnosc[i][j]*funkcja[0][j];
		    }
		    
		    poprzedniWynik[0] = x;
		    poprzedniWynik[1] = y;
		    
		    //odejmowanie macierzy wynikowej funkcji powyzej od macierzy[x0,y0]
		    x=x-wynikMnozenia[0];
		    y=y-wynikMnozenia[1];

		    if(kroki>=1){
			    p1 = abs(poprzedniWynik[0] - x);
			    p2 = abs(poprzedniWynik[1] - y);
		    }
		    
		    kroki++;
	    }
    	
    	System.out.printf("\nLiczba wykonanych iteracji: %d", --kroki);
	    System.out.printf("\nWynik dla przyblizenia %f:", PRZYB);
	    System.out.format("\n| %f |\n", x);
	    System.out.format("| %f |\n", y);

    }
     
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	char odp = 't';
	    	
	 do{
		newton();
		System.out.printf("\nCzy chcesz podac nowe punkty startowe? (t/n)");
		odp = in.next("[tn]").charAt(0); 
	    }while(odp == 't');
    }
}
