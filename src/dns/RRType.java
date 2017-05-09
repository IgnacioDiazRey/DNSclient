package dns;

public enum RRType {
    A(1), // a host address
    NS(2), // an authoritative name server
    CNAME(5), // the canonical name for an alias
    SOA(6), // marks the start of a zone of authority
    PTR(12), // a domain name pointer
    HINFO(13), // host information
    MX(15), // mail exchange
    TXT(16), // text strings
    AAAA(28); // an IPv6 host address*/

    static RRType fromByteArray(final byte[] bytes) throws Exception {
        final int val = Utils.int16fromByteArray(bytes);
        
        for (RRType id : values()) {
            if (val == id.id) {
                return id;
            }
        }

        throw (new Exception("Unsupported RRType: " + val));
    }


    private final int id;
    // an IPv6 host address*/
    
    private RRType(int id) {
        this.id = id;
    }
    public byte[] toByteArray() {
        return Utils.int16toByteArray(id);
    }
}