import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Optional;

import dns.ResourceRecord;

public class dnsclient{

	public static void main(String[] args) throws Exception {
		
		String protocolo,ip_servidor,RRtype,nombre,ip_consultas,RRtype_consultas,consulta,consulta1;
		protocolo=args[0];
		ArrayList<String> nombres= new ArrayList<String>();
		ArrayList<String> ips= new ArrayList<String>();
		ArrayList<String> ips2= new ArrayList<String>();
		ArrayList<String> nombresns= new ArrayList<String>();
		ArrayList<String> ipsns= new ArrayList<String>();
		ArrayList<ArrayList> indice= new ArrayList<ArrayList>();
		ArrayList<String> ttl= new ArrayList<String>();
		ArrayList<String> ttlns= new ArrayList<String>();
		ArrayList<ResourceRecord> aux1=new ArrayList<ResourceRecord>();
		String ip_aux=null;
		int aux=0;
		
		//if(protocolo.equals("-u")){
			
			try{
			String linea;
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			while(true){
				ip_servidor=(args[1]);
				ip_consultas=ip_servidor;
				
				linea=br.readLine();
				String[] partes= linea.split(" ");
				RRtype=partes[0];
				nombre=partes[1];
				RRtype_consultas=RRtype;
				if(protocolo.equals("-t")) System.out.println("No se halla implementado el uso de TCP. Se usara UDP");
				consulta="f";
				switch(RRtype){
				case "A": 
					consulta=cache.cache_cons(ips2,ip_aux,aux,nombres,ips,ttl,"ip", RRtype_consultas, nombre, 0);
					break;
				case "NS":
					consulta=cache.cache_NScons(indice,aux,nombresns ,ipsns,ttlns,"ip", RRtype_consultas, nombre, 0, aux1);
					break;
				case "AAAA": 
					consulta=cache.cache_cons(ips2,ip_aux,aux,nombres,ips,ttl,"ip", RRtype_consultas, nombre, 0);
					break;
				}
				
				
				
				if(consulta.equals("fin")){  continue;}
				
				else{
				
				
				
				String ip="ini";
				do{
				
				switch(RRtype){
				case "A": 
					
					ip=AConsulta.ACons(ips2,nombre,ip_servidor,ip_consultas,RRtype,nombres,ips,ttl);
					break;
					
				case "NS":
					ip=NSConsulta.NSCons(indice,nombre,ip_servidor,ip_consultas,RRtype,nombresns,ipsns,ttlns);
					break;
				case "AAAA":
					ip=AAAAConsulta.AAAACons(ips2,nombre,ip_servidor,ip_consultas,RRtype,nombres,ips,ttl);
					break;
				}
				ip_servidor=ip;
				
				}while(!ip.equals("fin"));
				
			
				
			}
			}
			}catch(Exception e){
				
			}
				
				//System.out.println("Answers:");
				//if((response.getAnswers()==null)||(response.getAnswers().isEmpty())){
				//System.out.println("esta vacio");}
			//	response.getAnswers().forEach((rr)->{
					//Consultas.dump(System.out,rr);
				//});
				
			
				//System.out.println("NS:");
				//response.getNameServers().forEach((rr)->{
					//Consultas.dump(System.out,rr);
					//nameserver.add(rr);
					
				//});
				
			
					
				
				//System.out.println("Additional:");
			//	response.getAdditonalRecords().forEach((rr)->{
					//Consultas.dump(System.out,rr);
				//});
				
			
			}

	
			
			
		
		//}
		
		

	

	}
