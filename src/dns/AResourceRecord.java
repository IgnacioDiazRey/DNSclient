package dns;

import static dns.RRType.A;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Rodriguez Perez
 */
public class AResourceRecord extends ResourceRecord {
    private final Inet4Address addr;

    public AResourceRecord(DomainName domain, int ttl, Inet4Address addr) {
        super(domain, A, ttl, addr.getAddress());

        this.addr = addr;
    }

    protected AResourceRecord(ResourceRecord decoded) throws Exception {
        super(decoded);        
       
        if (getRDLength() != 4) {
            throw new Exception("Incorrect rdlength for A Resource Records");
        }

        InetAddress ad = InetAddress.getByAddress(getRRData());
        if (!(ad instanceof Inet4Address)) {
            throw new Exception("Address is not a valid IPv4 Address");
        }

        addr = (Inet4Address) ad;
    }

    public final Inet4Address getAddress() {
        return addr;
    }
    
    @Override
    public byte[] toByteArray() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        
        try {                        
            os.write(super.toByteArray());
            os.write(addr.getAddress());                        
        } catch (IOException ex) {
            Logger.getLogger(AResourceRecord.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        
        return os.toByteArray();
    }    
}