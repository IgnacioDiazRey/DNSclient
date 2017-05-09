package dns;

public enum RRClass {
    IN(1), // the Internet
    CH(3), // the CHAOS class
    HS(4); // Hesiod      

    static RRClass fromByteArray(final byte[] bytes) throws Exception {
        final int val = Utils.int16fromByteArray(bytes);

        for (RRClass id : values()) {
            if (val == id.id) {
                return id;
            }
        }

        throw (new Exception("Unsupported RRClass: " + val));
    }


    private final int id;
    // Hesiod
    
    private RRClass(int id) {
        this.id = id;
    }
    public byte[] toByteArray() {
        return Utils.int16toByteArray(id);
    }
}