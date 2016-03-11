package addressvalidationservice;

/**
 * Wrapper class for all the parameters to be set to the CTT service.
 */
public class ServiceParameters {
    private final String DISTRITO_PARAM      = "indistrito";
    private final String CONCELHO_PARAM      = "inconcelho";
    private final String RUA_PARAM           = "inrua";
    private final String NUM_PORTA_PARAM     = "inporta";
    private final String CODIGO_POSTAL_PARAM = "incodpos";
    
    private String distrito;
    private String concelho;
    private String rua;
    private String numPorta;
    private String codigoPostal;
    
    public ServiceParameters(String distrito, String concelho, String rua, String numPorta, String codigoPostal) {
        super();
        
        this.distrito     = distrito;
        this.concelho     = concelho;
        this.rua          = rua;
        this.numPorta     = numPorta;
        this.codigoPostal = codigoPostal;
    }
    
    @Override
    public String toString() {
        return  DISTRITO_PARAM      + "=" + this.distrito    + "&" +
                CONCELHO_PARAM      + "=" + this.concelho    + "&" +
                RUA_PARAM           + "=" + this.rua         + "&" +
                NUM_PORTA_PARAM     + "=" + this.numPorta    + "&" +
                CODIGO_POSTAL_PARAM + "=" + this.codigoPostal;
    }
}