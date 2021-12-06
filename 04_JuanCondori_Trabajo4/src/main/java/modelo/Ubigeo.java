package modelo;

import lombok.Data;

@Data

public class Ubigeo {
    
    private String CODUBI;
    private String DEPUBI;
    private String PROUBI;
    private String DISUBI;
    
    public Ubigeo() {
    }

    public Ubigeo(String CODUBI,String DEPUBI,String PROUBI,String DISUBI) {
        this.CODUBI = CODUBI;
        this.DEPUBI = DEPUBI;
        this.PROUBI = PROUBI;
        this.DISUBI = DISUBI;
    }

    @Override
    public String toString() {
        return "Ubigeo{" + "CODUBI=" + CODUBI + ", DEPUBI=" + DEPUBI + ", PROUBI=" + PROUBI + ", DISUBI=" + DISUBI + '}';
    }
    
}
