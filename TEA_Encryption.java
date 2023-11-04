import java.util.Scanner;

public class TEA_Encryption {
    public static void main(String[] args) {
        final int DeltaOne = 0x11111111;
        final int DeltaTwo = 0x22222222;
        int[] K = new int[4];
        Scanner s = new Scanner(System.in);
        boolean valid = false;
        for (int i = 0; i < K.length; i++) {
            valid = false;
            while (!valid) {
                System.out.println("Please input K[" + i + "] as a Hex String (without \"0x\")");
                String kIndex = s.nextLine();
            
                try {
                    int kIndexInt = Integer.parseUnsignedInt(kIndex, 16);
                    K[i] = kIndexInt;
                    System.out.println("K[" + i + "] = 0x" + Integer.toHexString(K[i]));
                    valid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid hexidecimal value e.g. \"12345678\"");
                    
                }
            }
        }
        
        int[] L = new int[3];
        int[] R = new int[3];
        valid = false;
        while (!valid) {
            System.out.println("Please input L[0] as a Hex String (without \"0x\")");
            String lIndex = s.nextLine();
            
            try {                    
                int lIndexInt = Integer.parseUnsignedInt(lIndex, 16);
                L[0] = lIndexInt;
                System.out.println("L[0] = 0x" + Integer.toHexString(L[0]));
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please input a valid hexidecimal value e.g. \"12345678\"");
            }
        }
        valid = false;
        while (!valid) {
            System.out.println("Please input R[0] as a Hex String (without \"0x\")");
            String rIndex = s.nextLine();
            
            try {                    
                int rIndexInt = Integer.parseUnsignedInt(rIndex, 16);
                R[0] = rIndexInt;
                System.out.println("R[0] = 0x" + Integer.toHexString(R[0]));
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please input a valid hexidecimal value e.g. \"12345678\"");
            }
        }
        s.close();

        L[1] = 0x00000000;L[2] = 0x00000000;
        R[1] = 0x00000000;R[2] = 0x00000000;

        // Test values
        // K[0] = 0x90001C55;
        // K[1] = 0x1234ABCD;
        // K[2] = 0xFEDCBA98;
        // K[3] = 0xE2468AC0;
        
        // L[0] = 0xA0000009;
        // R[0] = 0x8000006B;
        
        L[1] = R[0];
        //R[1] = L[0] + F(R[0], K[0], K[1], DeltaOne);
        R[1] = L[0] + (((R[0] << 4) + K[0]) ^ (R[0] + DeltaOne) ^ ((R[0] >>> 5) + K[1]));
        //L[2] = L[0] + F(R[0], K[0], K[1], DeltaOne);
        L[2] = R[1];
        //R[2] = L[2] + F(R[0], K[2], K[3], DeltaTwo);
        R[2] = L[1] + (((R[1] << 4) + K[2]) ^ ((R[1] >>> 5) + K[3]) ^ (R[1] + DeltaTwo));

        for(int i = 0 ; i < L.length; i++) {
            System.out.println("L[" + i + "]: " + Integer.toHexString(L[i]).toUpperCase() + "    R[" + i + "]: " + Integer.toHexString(R[i]).toUpperCase());
        }
        
    }

}
