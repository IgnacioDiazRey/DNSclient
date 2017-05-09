package dns;
import static dns.RRType.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Rodriguez Perez
 */
public class NSResourceRecord extends ResourceRecord {
    private final DomainName ns;

    public NSResourceRecord(DomainName domain, int ttl, DomainName ns) {
        super(domain, NS, ttl, ns.toByteArray());
        
        this.ns = ns;
    }

    protected NSResourceRecord(ResourceRecord decoded, final byte[] message) {
        super(decoded);

        ns = new DomainName(getRRData(), message);
    }

    public final DomainName getNS() {
        return ns;
    }
    
    @Override
    public byte[] toByteArray() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        
        try {        
            os.write(super.toByteArray());
            os.write(ns.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(NSResourceRecord.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }        
        
        return os.toByteArray();
    }
}