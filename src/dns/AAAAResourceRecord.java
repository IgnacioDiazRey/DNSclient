package dns;

import static dns.RRType.AAAA;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AAAAResourceRecord extends ResourceRecord {
    private final Inet6Address addr;    

    public AAAAResourceRecord(DomainName domain, int ttl, Inet6Address addr) {
        super(domain, AAAA, ttl, addr.getAddress());

        this.addr = addr;        
    }

    protected AAAAResourceRecord(ResourceRecord decoded) throws Exception {
        super(decoded);

        int index = commonSize();

        if (getRDLength() != 16) {
            throw new Exception("Incorrect rdlength for A Resource Records");
        }

        InetAddress ad = InetAddress.getByAddress(getRRData());
        if (!(ad instanceof Inet6Address)) {
            throw new Exception("Address is not a valid IPv6 Address");
        }

        addr = (Inet6Address) ad;
    }

    public final Inet6Address getAddress() {
        return addr;
    }
    
    @Override
    public byte[] toByteArray() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            os.write(super.toByteArray());
            os.write(addr.getAddress());
        } catch (IOException ex) {
            Logger.getLogger(AAAAResourceRecord.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }                

        return os.toByteArray();
    }
}