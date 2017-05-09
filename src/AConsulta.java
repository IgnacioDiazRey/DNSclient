
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

public class AConsulta {
	
	
	
	static String ACons(ArrayList<String> ips2,String nombre,String ip_servidor,String ip_consultas1, String RRtype_consultas, ArrayList nombres,ArrayList ips,ArrayList<String> ttl) throws Exception{
	
		ArrayList<ResourceRecord> nameserver= new ArrayList<ResourceRecord>();
		ArrayList<ResourceRecord> answers= new ArrayList<ResourceRecord>();
		ArrayList<ResourceRecord> additional= new ArrayList<ResourceRecord>();
		AResourceRecord nueva_ip;
		String ip_consultas;
		ip_consultas=ip_servidor;
		String consulta;
	
		
		
		try{   
			int i=0;
			 
		Message question= new Message(nombre,RRType.A,false);
		DatagramSocket s= new DatagramSocket();
		byte [] message= question.toByteArray();
		s.send(new DatagramPacket(message,message.length,InetAddress.getByName(ip_servidor),53));
		byte[] answer_bytes= new byte[1500];
		DatagramPacket answer= new DatagramPacket(answer_bytes, answer_bytes.length);
		System.out.println("Q UDP"+" "+ip_consultas+" "+RRtype_consultas+" "+ nombre);
		
		try{
			s.setSoTimeout(4000);
		s.receive(answer);
		
		}catch (Exception e) {
			System.out.println("No hay respuesta");
			return "fin";
		}
		
		Message response= new Message(answer_bytes);
		
		/*
		System.out.println("---------------------------------------------------");
		System.out.println("Answers:");

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
	
		*/
	
		if(((response.getAnswers()==null)||(response.getAnswers().isEmpty()))&&((response.getAdditonalRecords()==null)||(response.getAdditonalRecords().isEmpty()))
				&&((response.getNameServers()==null)||(response.getNameServers().isEmpty()))){System.out.println("No hay respuesta");
				return "fin";}
		

		
		if((response.getAnswers()==null)||(response.getAnswers().isEmpty())){
			
			//System.out.println("Q UDP"+" "+ip_consultas+" "+RRtype_consultas+" "+ nombre);
			
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
					Consultas.dump(System.out,nameserver.get(i));
					RRType rtype=nameserver.get(i).getRRType();
					System.out.print("A"+" "+ip_consultas+" ");
					
					String ip=A_AdditionalConsulta.A_AddCons(ip_consultas1,response, additional, nameserver);

					return ip;
				
				
			}
			
		
			
			
			else if(nameserver.get(i).getRRType().equals("CNAME")){
				{
				AAAAResourceRecord a=Consultas.dumpAAAA_address((AAAAResourceRecord)additional.get(i));
				String ip=a.getAddress().getHostAddress();
				System.out.println("CNAME");
			
				//consulta=cache.cache_cons(ips2,ip,additional.get(0).getTTL(),nombres,ips,ttl,ip_consultas, RRtype_consultas, nombre,2);

				return "fin";
				}}
				else{
			
					
				
					int jdk=0;
					do{
						try{
						   
					nueva_ip=Consultas.dumpA_address((AResourceRecord)additional.get(i));
			
					String ip=nueva_ip.getAddress().getHostAddress();
		    
					Consultas.dump(System.out,nameserver.get(i));
					System.out.print("A"+" "+ip_consultas+" ");
					Consultas.dump(System.out,additional.get(i));
					return ip;
						}catch(Exception e){
						    
							jdk=1;
							i++;
					
						}
		    
					
		     
				}while(jdk==1);
					}
				//
				
				
			}
		
		
			
			
		else{
			//System.out.println("Q UDP"+" "+ip_consultas+" "+RRtype_consultas+" "+ nombre);
			response.getAnswers().forEach((rr)->{
				answers.add(rr);
			});
			System.out.print("A"+" "+ip_consultas+" ");
			
			
			
			if(answers.get(0).getRRType().toString().equals("CNAME")){
				System.out.println("CNAME");
				
				cache.cache_cons(ips2,"fin",answers.get(0).getTTL(),nombres,ips,ttl,ip_consultas, RRtype_consultas, nombre,2);
			
				return "fin";
			}
			
			
			
			Consultas.dump(System.out,answers.get(0));
			
		
			nueva_ip=Consultas.dumpA_address((AResourceRecord)answers.get(0));
		
		    String ip=nueva_ip.getAddress().getHostAddress();
			
		
			consulta=cache.cache_cons(ips2,ip,answers.get(0).getTTL(),nombres,ips,ttl,ip_consultas, RRtype_consultas, nombre, 1);
			
			return "fin";
		}
			

		
	}catch(Exception e2){
		
		System.out.println("Q UDP"+" "+ip_consultas+" "+RRtype_consultas+" "+ nombre);
		String ip="fin";
		return ip;
	}	return "fin";
		}

	}
		

