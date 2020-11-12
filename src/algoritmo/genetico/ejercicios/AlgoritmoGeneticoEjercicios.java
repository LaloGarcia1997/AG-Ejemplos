/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico.ejercicios;

/**
 *
 * @author lalog
 */
public class AlgoritmoGeneticoEjercicios {
     public static String[] poblacion;
    public static int generacion;
    public static String padre, madre;
    public static double mutacion = .30;
    public static double cruza = .70;
    public static int num_cromosomas;
    public static int tamaniopoblacion;


    public static void main(String[] args) {
        tamaniopoblacion = 50;
        num_cromosomas = 4;
        System.out.println("Ejemplo 1");
        poblacionInicial(tamaniopoblacion, num_cromosomas);
        for (int z = 0; z < tamaniopoblacion; z++) {
            torneo(poblacion);
            cross(padre, madre);
        }
        for (int i = 0; i < poblacion.length; i++) {
            System.out.println("i :"+i+" "+poblacion[i]+" = "+cB(poblacion[i])+" "+fitness1(cB(poblacion[i])) );
        }
        System.out.println("Fitness ="+fitness1(cB(poblacion[tamaniopoblacion-1])));
        
        System.out.println("--------------------------");
        System.out.println("Ejemplo 2");
        num_cromosomas = 6;
        poblacionInicial(tamaniopoblacion, num_cromosomas);
        for (int z = 0; z < tamaniopoblacion; z++) {
            torneo2(poblacion);
            cross(padre, madre);
        }
        for (int i = 0; i < poblacion.length; i++) {
            System.out.println("i :"+i+" "+poblacion[i]+" = "+cB(poblacion[i])+" "+fitness2(cB(poblacion[i]))+" Pasajeros ="+fitness21(cB(poblacion[i])));
        }
        System.out.println("Fitness ="+fitness2(cB(poblacion[tamaniopoblacion-1])));
        
        System.out.println("--------------------------");
        System.out.println("Ejemplo 3");
        poblacionInicial(tamaniopoblacion, num_cromosomas);
        for (int z = 0; z < tamaniopoblacion; z++) {
            torneo3(poblacion);
            cross(padre, madre);
        }
        for (int i = 0; i < poblacion.length; i++) {
            System.out.println("i :"+i+" "+poblacion[i]+" = "+cB(poblacion[i])+" "+fitness3(cB(poblacion[i])));
        }
        System.out.println("Fitness ="+fitness3(cB(poblacion[tamaniopoblacion-1])));
        
        System.out.println("--------------------------");
        System.out.println("Ejemplo 4");
        num_cromosomas = 12;
        poblacionInicial4(tamaniopoblacion, num_cromosomas);
        for (int z = 0; z < tamaniopoblacion; z++) {
            torneo3(poblacion);
            cross(padre, madre);
        }
        for (int i = 0; i < poblacion.length; i++) {
            System.out.println("i :"+i+" "+poblacion[i]+" = "+cB(poblacion[i])+" "+fitness4(cB(poblacion[i])));
        }
        System.out.println("Fitness ="+fitness4(cB(poblacion[tamaniopoblacion-1])));
        
        System.out.println("--------------------------");
        System.out.println("Ejemplo 5");
        num_cromosomas = 7;
        poblacionInicial(tamaniopoblacion, num_cromosomas);
        for (int z = 0; z < tamaniopoblacion; z++) {
            torneo5(poblacion);
            cross(padre, madre);
        }
        for (int i = 0; i < poblacion.length; i++) {
            System.out.println("i :"+i+" "+poblacion[i]+" = "+cB(poblacion[i])+" "+fitness5(cB(poblacion[i])));
        }
        System.out.println("Fitness ="+fitness5(cB(poblacion[tamaniopoblacion-1]))); 
    }
    
    public static double fitness1(int x) {
        return -50 * (x * x) + 500* x;
    }
    
    public static double fitness2(int x) {
        return 10000 * x  - 125 * (x*x);
    }
    
    public static double fitness21(int x) {
        return 10000 - 125 * x;
    }
    
    public static double fitness3(int x) {
        try {
            return (4860 / x) + 15 * x + 750000;
        } catch (Exception e) {
            return (4860 / 1) + 15 * 1 + 750000;
        } 
    }
    
    public static double fitness4(int x) {
        return (100000 / x)  + 1500 + 0.2 * x;
    }
    
    public static double fitness5(int x) {
        return -12.5 * (x * x) + 1375 * x - 1500;
    }
    

    public static int cB(String cadena) {
        int num = 0;
        int num1 = 1;
        for (int i = cadena.length() - 1; i >= 0; i--) {
            if (num == 0) {
                if (cadena.charAt(i) == '1') {
                    num = num1;
                } else {
                    num1 *= 2;
                }
            } else {
                if (cadena.charAt(i) == '1') {
                    num += num1 * 2;
                    num1 *= 2;
                } else {
                    num1 *= 2;
                }
            }
        }
        return num;
    }
    
    public static void poblacionInicial(int tamanio, int numcromosomas) {
        poblacion = new String[tamanio];
        double num;
        for (int i = 0; i < poblacion.length; i++) {
            String cadena = "";
            for (int j = 0; j < numcromosomas; j++) {
                num = Math.random();
                if (num < 0.25 || num> 0.75) {
                    cadena += "0";
                } else {
                    cadena += "1";
                }
            }
            poblacion[i] = cadena;
        }
    }
    
    public static boolean buscaString(String[] miarray, String valor) {
        for(String s: miarray){
            if(s.equals(valor))
                return true;
        }
        return false;
    }
    
    public static String changeCharInPosition(int position, char ch, String str) {
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }
    //-----------------------------------------------------------------------------------//
    //Ejemplo 1
     public static void cross(String pad, String mad) {
        String padr = pad;
        String madr = mad;
        String partePadre = "";
        String parteMadre = "";
        int div = 1;
        if (num_cromosomas >= 2) {
            div = num_cromosomas / 2;
        }
        for (int i = 0; i < div; i++) {
            partePadre += String.valueOf(padr.charAt(i));
            parteMadre += String.valueOf(madr.charAt(i));
        }
        String hijo = partePadre + parteMadre;
        double num2 = Math.random();
        if(num2 < .5) {
            if (hijo.charAt(0) == '0') {
                hijo = changeCharInPosition(0, '1', hijo);
            }else{
                 hijo = changeCharInPosition(0, '0', hijo);
            }
        }else if (num2 > 0.5) {
            if (hijo.charAt(1) == '0') {
                hijo = changeCharInPosition(1, '1', hijo);
            }else{
                hijo = changeCharInPosition(1, '0', hijo);
            }
        }
        poblacion[0] = hijo;
        
    }

    

    public static void torneo(String[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1; j++) {
                if (fitness1(cB(arreglo[j])) > fitness1(cB(arreglo[j + 1]))) {
                    String tmp = arreglo[j + 1];
                    arreglo[j + 1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        for (int i = 0; i < arreglo.length; i++) {
            if (i == arreglo.length-1) {
                padre = arreglo[i];
            } else if (i == arreglo.length-2) {
                madre = arreglo[i];
            }
        }
    }
    //------------------------------------------------------------------------------//
    //EJEMPLO 2
    public static void torneo2(String[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1; j++) {
                if (fitness2(cB(arreglo[j])) > fitness2(cB(arreglo[j + 1]))) {
                    String tmp = arreglo[j + 1];
                    arreglo[j + 1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        for (int i = 0; i < arreglo.length; i++) {
            if (i == arreglo.length-1) {
                padre = arreglo[i];
            } else if (i == arreglo.length-2) {
                madre = arreglo[i];
            }
        }
    }
    //------------------------------------------------------------------------------//
    //EJEMPLO 3
      public static void torneo3(String[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1; j++) {
                if (fitness3(cB(arreglo[j])) < fitness3(cB(arreglo[j + 1]))) {
                    String tmp = arreglo[j + 1];
                    arreglo[j + 1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        for (int i = 0; i < arreglo.length; i++) {
            if (i == arreglo.length-1) {
                padre = arreglo[i];
            } else if (i == arreglo.length-2) {
                madre = arreglo[i];
            }
        }
    }
          //------------------------------------------------------------------------------//
    //EJEMPLO 4
    public static void torneo4(String[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1; j++) {
                if (fitness4(cB(arreglo[j])) < fitness4(cB(arreglo[j + 1]))) {
                    String tmp = arreglo[j + 1];
                    arreglo[j + 1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        for (int i = 0; i < arreglo.length; i++) {
            if (i == arreglo.length-1) {
                padre = arreglo[i];
            } else if (i == arreglo.length-2) {
                madre = arreglo[i];
            }
        }
    }
      public static void poblacionInicial4(int tamanio, int numcromosomas) {
        poblacion = new String[tamanio];
        double num;
        for (int i = 0; i < poblacion.length; i++) {
            String cadena = "";
            for (int j = 0; j < numcromosomas; j++) {
                num = Math.random();
                if (num < 0.20 || num> 0.80) {
                    cadena += "0";
                } else {
                    cadena += "1";
                }
            }
            poblacion[i] = cadena;
        }
    }
    //----------------------------------------------------------------------//
    //EJEMPLO 5
        public static void torneo5(String[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1; j++) {
                if (fitness5(cB(arreglo[j])) > fitness5(cB(arreglo[j + 1]))) {
                    String tmp = arreglo[j + 1];
                    arreglo[j + 1] = arreglo[j];
                    arreglo[j] = tmp;
                }
            }
        }
        for (int i = 0; i < arreglo.length; i++) {
            if (i == arreglo.length-1) {
                padre = arreglo[i];
            } else if (i == arreglo.length-2) {
                madre = arreglo[i];
            }
        }
    }
      
      
}
