package dns;

class Utils {

    public static byte[] int16toByteArray(int i) {
        byte[] output = new byte[2];

        output[0] = (byte) ((i & 0xFF00) >> 8);
        output[1] = (byte) (i & 0x00FF);

        return output;
    }

    public static byte[] int32toByteArray(int i) {
        byte[] output = new byte[4];

        output[0] = (byte) ((i & 0xFF000000) >> 24);
        output[1] = (byte) ((i & 0x00FF0000) >> 16);
        output[2] = (byte) ((i & 0x0000FF00) >> 8);
        output[3] = (byte) (i & 0x000000FF);

        return output;
    }

    public static int int16fromByteArray(byte[] val) {
        return (val[0] << 8) + (val[1] & 0xff);
    }

    public static int int32fromByteArray(byte[] val) {
        int value = 0;

        for (int i = 0; i < 4; i++) {
            value = (value << 8) + (val[i] & 0xff);
        }

        return value;
    }
}