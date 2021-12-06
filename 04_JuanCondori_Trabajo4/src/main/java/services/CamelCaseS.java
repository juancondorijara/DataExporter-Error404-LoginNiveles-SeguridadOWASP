package services;

public class CamelCaseS {
    
    public static String camelMayuscula(String camelcase) {
        camelcase = camelcase.toLowerCase();
        char[] listaCadena = camelcase.toCharArray();
        boolean espacioBlanco = true;
        for (int i = 0; i < listaCadena.length; i++) {
            // Detecta si es letra nuestra cadena en cada posición
            if (Character.isLetter(listaCadena[i])) {
                // Si hay un espacio en blanco
                if (espacioBlanco == true) {
                    listaCadena[i] = Character.toUpperCase(listaCadena[i]);
                    espacioBlanco = false;
                }
            } else {
                espacioBlanco = true;
            }
        }
        camelcase = String.valueOf(listaCadena);
        return camelcase;
    }
    
    //METODO TODO MINUSCULA PARA EMAIL
    public static String camelMinuscula(String camelcase) {
        char ch[] = camelcase.toCharArray();
        for (int i = 0; i < camelcase.length(); i++) {
            if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {  // Si se encuentra el primer carácter de una palabra
                if (ch[i] >= 'A' && ch[i] <= 'Z') {      // Si está en mayúsculas
                    ch[i] = (char) (ch[i] - 'A' + 'a');  // Convertir en minúsculas
                }
            } // Si aparte del primer carácter cualquiera está en mayúsculas
            else if (ch[i] >= 'A' && ch[i] <= 'Z') {     // Convertir en minúsculas
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        String st = new String(ch);
        camelcase = st;
        return camelcase;
    }
    
    //METODO CAMEL CASE
    public static String camelCase(String camelcase) {
        char ch[] = camelcase.toCharArray();
        for (int i = 0; i < camelcase.length(); i++) {
            if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {  // Si se encuentra el primer carácter de una palabra
                if (ch[i] >= 'a' && ch[i] <= 'z') {      // Si está en minúsculas
                    ch[i] = (char) (ch[i] - 'a' + 'A');  // Convertir en mayúsculas
                }
            } // Si aparte del primer carácter cualquiera está en mayúsculas
            else if (ch[i] >= 'A' && ch[i] <= 'Z') {     // Convertir en minúsculas
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        String st = new String(ch);
        camelcase = st;
        return camelcase;
    }
    
    //METODO CAMELCASE INVERSO
    public static String camelInverso(String camelcase) {
        char ch[] = camelcase.toCharArray();
        for (int i = 0; i < camelcase.length(); i++) {
            if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {  // Si se encuentra el primer carácter de una palabra
                if (ch[i] >= 'A' && ch[i] <= 'Z') {      // Si está en minúsculas
                    ch[i] = (char) (ch[i] - 'A' + 'a');  // Convertir en mayúsculas
                }
            } // Si aparte del primer carácter cualquiera está en mayúsculas
            else if (ch[i] >= 'a' && ch[i] <= 'z') {     // Convertir en minúsculas
                ch[i] = (char) (ch[i] + 'A' - 'a');
            }
        }
        String st = new String(ch);
        camelcase = st;
        return camelcase;
    }

    //METODO CAMEL CASE COMPUESTO POR 2 METODOS
    public static String toProperCase(String camelcase) {
        String temp = camelcase.trim();
        String spaces = "";
        if (temp.length() != camelcase.length()) {
            int startCharIndex = camelcase.charAt(temp.indexOf(0));
            spaces = camelcase.substring(0, startCharIndex);
        }
        temp = temp.substring(0, 1).toUpperCase()
                + spaces + temp.substring(1).toLowerCase() + " ";
        return temp;
    }

    public static String toCamelCase(String camelcase) {
        String[] parts = camelcase.split(" ");
        String camelCaseString = "";
        for (String part : parts) {
            if (part != null && part.trim().length() > 0) {
                camelCaseString = camelCaseString + toProperCase(part);
            } else {
                camelCaseString = camelCaseString + part + " ";
            }
        }
        return camelCaseString;
    }

    public static void main(String[] args) throws Exception {
        String camel = "jUaN gAbRiEl CoNdOrI jArA";
        System.out.println(camel.toLowerCase());
        System.out.println(camel.toUpperCase());
//        System.out.println(camelMayuscula(camel));
//        System.out.println(camelMinuscula(camel));
//        System.out.println(toCamelCase(camel));
//        System.out.println(camelInverso(camel));
    }
    
}
