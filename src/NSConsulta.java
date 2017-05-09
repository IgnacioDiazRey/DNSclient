
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import dns.AAAAResourceRecord;
import dns.AResourceRecord;
import dns.Message;
import dns.NSResourceRecord;
import dns.RRType;
import dns.ResourceRecord;

public class NSConsulta {
	
	static String NSCons(ArrayList<ArrayList> indice,String nombre,String ip_servidor, String ip_consultas1,String RRtype_consultas, ArrayList nombres,ArrayList ips,ArrayList<String> tiempons) throws Exception{
		

		ArrayList<ResourceRecord> nameserver= new ArrayList<ResourceRecord>();
		ArrayList<ResourceRecord> answers= new ArrayList<ResourceRecord>();
		ArrayList<ResourceRecord> additional= new ArrayList<ResourceRecord>();
		AResourceRecord nueva_ip;
		String ip_consultas;
		ip_consultas=ip_servidor;
		String consulta;
	
		try{
		    	int z=0;
		
		Message question= new Message(nombre,RRType.NS,false);
		DatagramSocket s= new DatagramSocket();
		byte [] message= question.toByteArray();
		s.send(new DatagramPacket(message,message.length,InetAddress.getByName(ip_servidor),53));
		byte[] answer_bytes= new byte[1500];
		DatagramPacket answer= new DatagramPacket(answer_bytes, answer_bytes.length);
		System.out.println("Q UDP"+" "+ip_consultas+" "+RRtype_consultas+" "+ nombre);
		s.receive(answer);
		Message response= new Message(answer_bytes);
		
		
		if(((response.getAnswers()==null)||(response.getAnswers().isEmpty()))&&((response.getAdditonalRecords()==null)||(response.getAdditonalRecords().isEmpty()))
				&&((response.getNameServers()==null)||(response.getNameServers().isEmpty()))){System.out.println("No hay respuesta");
				return "fin";}
		
/*		

	System.out.println("---------------------------------------------------");
		System.out.println("Answers:");
		//if((response.getAnswers()==null)||(response.getAnswers().isEmpty())){
		//System.out.println("esta vacio");}
		response.getAnswers().forEach((rr)->{
			Consultas.dump(System.out,rr);
		});
		
	
		System.out.println("NS:");
		response.getNameServers().forEach((rr)->{
			Consultas.dump(System.out,rr);
		//	nameserver.add(rr);
			
		});
		
		System.out.println("Additional:");
	response.getAdditonalRecords().forEach((rr)->{
		Consultas.dump(System.out,rr);
		});
	
	System.out.println("---------------------------------------------------");
		
	System.out.println("---------------------------------------------------");	
		*/
		
		
		
		if(((response.getAnswers()==null)||(response.getAnswers().isEmpty()))&&((response.getAdditonalRecords()==null)||(response.getAdditonalRecords().isEmpty()))
				&&((response.getNameServers()==null)||(response.getNameServers().isEmpty()))){System.out.println("No hay respuesta");
				return "fin";}
		
		
		
		
		if((response.getAnswers()==null)||(response.getAnswers().isEmpty())){
		
			
			
			

			response.getNameServers().forEach((rr)->{
				//Consultas.dump(System.out,rr);
				nameserver.add(rr);
				
			});
			
			response.getAdditonalRecords().forEach((rr)->{
				additional.add(rr);
				
			});
			
			System.out.print("A"+" "+ip_consultas+" ");
			
			
			
			
			if(response.getAdditonalRecords().isEmpty()||(response.getAdditonalRecords()==null)){
				PrintStream ps= System.out;
				Consultas.dump(System.out,nameserver.get(z));
				RRType rtype=nameserver.get(z).getRRType();
				System.out.print("A"+" "+ip_consultas+" ");
				String ip=A_AdditionalConsulta.A_AddCons(ip_consultas1,response, additional, nameserver);
				
				
				
				
				return ip;
				
				
		}
			
			
		
			
			
			
			
			else if(nameserver.get(z).getRRType().equals("CNAME")){
				
					//
					//Consultas.dumpAAAA(ps,(AAAAResourceRecord)additional.get(0));
				
					AAAAResourceRecord a=Consultas.dumpAAAA_address((AAAAResourceRecord)additional.get(z));
					String ip=a.getAddress().getHostAddress();
					System.out.println("CNAME");
					
					consulta=cache.cache_NScons(indice,additional.get(z).getTTL(),nombres,ips,tiempons,ip_consultas, RRtype_consultas, nombre, 2, answers);
					
					
					return "fin";
					}
		
			else{
				
				
				
				int jdk=0;
				do{
					try{
			nueva_ip=Consultas.dumpA_address((AResourceRecord)additional.get(z));
			
		    String ip=nueva_ip.getAddress().getHostAddress();
		    
		    Consultas.dump(System.out,nameserver.get(z));
			System.out.print("A"+" "+ip_consultas+" ");
		    Consultas.dump(System.out,additional.get(z));
		    
		    
		    return ip;
		    }catch(Exception e){
				jdk=1;
				z++;
			
			}

		
 
	}while(jdk==1);
		}
		     
			
			
	
		}
		
		
			
			
		else{
			
			//System.out.println("Q UDP"+" "+ip_consultas+" "+RRtype_consultas+" "+ nombre);
			
			response.getAnswers().forEach((rr)->{
				answers.add(rr);

			
			});
			if(answers.get(0).getRRType().toString().equals("CNAME")){
				System.out.print("A"+" "+ip_consultas+" ");
				System.out.println("CNAME");
				
				cache.cache_NScons(indice,answers.get(0).getTTL(),nombres,ips,tiempons,ip_consultas, RRtype_consultas, nombre,2,answers);
			
				return "fin";
			}
			for(int i=0;i<answers.size();i++){
			System.out.print("A"+" "+ip_consultas+" ");
			Consultas.dump(System.out,answers.get(i));
		
			}
			

			
			//nueva_ip=Consultas.dumpA_address((AResourceRecord)answers.get(0));
		
		    //String ip=nueva_ip.getAddress().getHostAddress();
			
		
			consulta=cache.cache_NScons(indice,answers.get(0).getTTL(),nombres,ips,tiempons,ip_consultas, RRtype_consultas, nombre, 1, answers);
			
			
			return "fin";
		}
			
	
	}catch(Exception e2){
	
		System.out.println("Q UDP"+" "+ip_consultas+" "+RRtype_consultas+" "+ nombre);
		String ip="fin";
		return ip;
	}
		
		
		
	return "fin";
	
	
		
		
		
		
		
	}

}
