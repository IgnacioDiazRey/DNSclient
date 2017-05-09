
import java.io.PrintStream;

import dns.AAAAResourceRecord;
import dns.AResourceRecord;
import dns.NSResourceRecord;
import dns.ResourceRecord;

public class Consultas {
	
		

		 static void dump(PrintStream ps, ResourceRecord r) {

			 {
					ps.print( r.getRRType()+" "+r.getTTL()+" ");
					
					switch(r.getRRType()){
					
					case A: 
						
						dumpA(ps,(AResourceRecord)r);
						break;
					case AAAA:
						dumpAAAA(ps,(AAAAResourceRecord)r);
						break;
					case NS:
						
						dumpNS(ps,(NSResourceRecord)r);
						break;
					case CNAME:
						break;
					default:
						ps.println("Dunno what to do whit a "+r.getRRType()+ " record.");
						return;
					}
				}
		 }
				static void dumpA(PrintStream ps, AResourceRecord r) {
			
					ps.println(r.getAddress().getHostAddress());
					//return r;
					
				}
				static AResourceRecord dumpA_address(AResourceRecord r) {
					
					return r;
					
				}
				
				
				
				
				
				
				static void dumpAAAA(PrintStream ps, AAAAResourceRecord r) {
					ps.println(r.getAddress().getHostAddress());
				}
				

				static AAAAResourceRecord dumpAAAA_address(AAAAResourceRecord r) {
					return r;
				}
				
				
				 static void dumpNS(PrintStream ps, NSResourceRecord r) {
					ps.println(r.getNS());
				}
				
				 
				 static NSResourceRecord dumpNS_address(NSResourceRecord r) {
						
					 return r;
					}
			// TODO Auto-generated method stub
			
		
		
		
		
		
		
		
	}

