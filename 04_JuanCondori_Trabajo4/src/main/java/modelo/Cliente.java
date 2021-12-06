package modelo;

import java.util.UUID;
import lombok.Data;

@Data

public class Cliente {
    
    String pass1 = UUID.randomUUID().toString().toUpperCase().substring(2, 5);
    String pass2 = UUID.randomUUID().toString().toLowerCase().substring(2, 5);
    String pass3 = "#+";
    String password = pass1 + pass2 + pass3;

    private Integer IDCLI;
    private String NOMCLI;
    private String APECLI;
    private String DIRCLI;
    private String EMACLI;
    private String DNICLI;
    private String CELCLI;
    private String CODUBI;
    private String DISUBI;
    private String PROUBI;
    private String DEPUBI;
    private String ESTCLI;
    private String PWDCLI = password;
    private Ubigeo ubigeo = new Ubigeo();

    public Cliente() {
    }
    
    public Cliente(Integer IDCLI, String NOMCLI, String APECLI, String DIRCLI, String EMACLI, String DNICLI, String CELCLI, String CODUBI, String DISUBI, String PROUBI, String DEPUBI, String ESTCLI, Ubigeo ubigeo) {
        this.IDCLI = IDCLI;
        this.NOMCLI = NOMCLI;
        this.APECLI = APECLI;
        this.DIRCLI = DIRCLI;
        this.EMACLI = EMACLI;
        this.DNICLI = DNICLI;
        this.CELCLI = CELCLI;
        this.CODUBI = CODUBI;
        this.DISUBI = DISUBI;
        this.PROUBI = PROUBI;
        this.DEPUBI = DEPUBI;
        this.ESTCLI = ESTCLI;
        this.ubigeo = ubigeo;
    }
    
}
