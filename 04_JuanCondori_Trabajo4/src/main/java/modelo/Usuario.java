package modelo;

import java.util.UUID;
import lombok.Data;

@Data

public class Usuario {
    
    String pass1 = UUID.randomUUID().toString().toUpperCase().substring(2, 5);
    String pass2 = UUID.randomUUID().toString().toLowerCase().substring(2, 5);
    String pass3 = "#+";
    String password = pass1 + pass2 + pass3;
    
    private int IDUSU;
    private String USUUSU;
    private String PWDUSU;
    private int NIVUSU;
    private int IDCLI;
    private int IDEMP;
    private String NOMCLI;
    private String APECLI;
    private String EMACLI;
    private String NOMEMP;
    private String APEEMP;
    private String EMAEMP;
    private String ROLUSU;
    private String ESTUSU;
    
    private String EMAIL;
    
    private Cliente cliente = new Cliente();
    
    public Usuario() {
    }
    
    public Usuario(int IDUSU, String USUUSU, int NIVUSU, int IDCLI, int IDEMP, String NOMCLI, String APECLI, String EMACLI, String NOMEMP, String APEEMP, String EMAEMP, String ROLUSU, String ESTUSU, Cliente cliente) {
        this.IDUSU = IDUSU;
        this.USUUSU = USUUSU;
        this.NIVUSU = NIVUSU;
        this.IDCLI = IDCLI;
        this.IDEMP = IDEMP;
        this.NOMCLI = NOMCLI;
        this.APECLI = APECLI;
        this.EMACLI = EMACLI;
        this.NOMEMP = NOMEMP;
        this.APEEMP = APEEMP;
        this.EMAEMP = EMAEMP;
        this.ROLUSU = ROLUSU;
        this.ESTUSU = ESTUSU;
        this.cliente = cliente;
    }
    
}
