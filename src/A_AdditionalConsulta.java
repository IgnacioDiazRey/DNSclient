import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import dns.AResourceRecord;
import dns.Message;
import dns.NSResourceRecord;
import dns.RRType;
import dns.ResourceRecord;

public class A_AdditionalConsulta {

	
	static String A_AddCons(String ip_consultas1,Message response,ArrayList<ResourceRecord> additional, ArrayList<ResourceRecord> nameserver) throws Exception{
		NSResourceRecord nombreaux;
		AResourceRecord nueva_ip;
		
		ArrayList<ResourceRecord> nameserver1= new ArrayList<ResourceRecord>();
		ArrayList<ResourceRecord> answers1= new ArrayList<ResourceRecord>();
		while(true){
	
			ArrayList<ResourceRecord> additional1= new ArrayList<ResourceRecord>();
				nombreaux=Consultas.dumpNS_address((NSResourceRecord)nameserver.get(0));
		
				String nombre =nombreaux.getNS().toString();
				
				Message question= new Message(nombre,RRType.A,false);
				DatagramSocket s= new DatagramSocket();
				byte [] message= question.toByteArray();
				s.send(new DatagramPacket(message,message.length,InetAddress.getByName(ip_consultas1),53));
				byte[] answer_bytes= new byte[1500];
				DatagramPacket answer= new DatagramPacket(answer_bytes, answer_bytes.length);
				//System.out.println("Q UDP"+" "+ip_consultas1+" "+RRtype_consultas+" "+ nombre);
				
				s.receive(answer);
				Message response1= new Message(answer_bytes);
				
				
				if(((response1.getAnswers()==null)||(response1.getAnswers().isEmpty()))&&((response1.getAdditonalRecords()==null)||(response1.getAdditonalRecords().isEmpty()))
						&&((response1.getNameServers()==null)||(response1.getNameServers().isEmpty()))){System.out.println("No hay respuesta");
						return "fin";}
			
				if((response1.getAnswers()==null)||(response1.getAnswers().isEmpty())){
					
					
				//	if((response.getAdditonalRecords()==null)||(response.getAdditonalRecords().isEmpty())){
				
				
				
				response1.getAdditonalRecords().forEach((rr)->{
					additional1.add(rr);
					//Consultas.dump(System.out,rr);
				});
				
				
				
				
				nueva_ip=Consultas.dumpA_address((AResourceRecord)additional1.get(0));
				
			   ip_consultas1=nueva_ip.getAddress().getHostAddress();
			   //System.out.println("ipppppppp   "+ip_consultas1+"  "+nueva_ip);
			   //return ip;
			}
		//}
				else{ 
					
		response1.getAnswers().forEach((rr)->{
			answers1.add(rr);
		});
		nueva_ip=Consultas.dumpA_address((AResourceRecord)answers1.get(0));
		  String ip=nueva_ip.getAddress().getHostAddress();
		  
		  
		    Consultas.dump(System.out,answers1.get(0));
		  
		  
		return ip;
		} 
				
		}
		
		
		
	}

}
				
				
