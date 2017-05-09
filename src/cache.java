import java.util.ArrayList;
import java.util.Iterator;

import dns.RRType;
import dns.ResourceRecord;

public class cache {
	
	
	
	static String cache_cons(ArrayList<String> ips2,String ip, int ttl,ArrayList<String> nombres,ArrayList<String> ips,ArrayList<String> tiempo,String ip_consultas,String rrtype_consultas, String nombre,int i) throws Exception{
		
	
		String RRtypenombre;
		RRtypenombre=rrtype_consultas+" "+nombre;
	   String ip_consultas1= ip_consultas+" "+rrtype_consultas;
		String ip_consultas2=ip;
		if(i==0){
			
			if(nombres.contains(RRtypenombre)){
				
				
				
				int j=nombres.indexOf(RRtypenombre);
				
				if((ips2.get(j).isEmpty())||(ips2.get(j)==null)){System.out.println("entra");return "fin";}
			
				if(Integer.parseInt(HoraSistema.HoraSistema_ttl())>Integer.parseInt(tiempo.get(j))){
					
					tiempo.remove(j);
					ips.remove(j);
					ips2.remove(j);
					nombres.remove(j);
					
					
					return "f";}
				else{
					System.out.println("Q"+" "+"Cache  Cache"+" "+RRtypenombre);
				System.out.print("A"+" ");
				System.out.print(ips.get(j));
				int ttldevida= Integer.parseInt(tiempo.get(j))-Integer.parseInt(HoraSistema.HoraSistema_ttl());
				System.out.print(" "+ttldevida+" ");
				System.out.println(ips2.get(j));
				
				return "fin";
			}
				}
			//else {
				
				//nombres.add(RRtypenombre);
	
			return "f";
			//}
		}
		else if(i==1){
			nombres.add(RRtypenombre);
			int j=nombres.indexOf(RRtypenombre);

			ips.add(j,ip_consultas1);
			ips2.add(j,ip_consultas2);
		
			int tiempototal=(ttl+Integer.parseInt(HoraSistema.HoraSistema_ttl()));
	
			tiempo.add(j,Integer.toString(tiempototal));

			return "fin";
		}
		else if(i==2){
			nombres.add(RRtypenombre);
			int j=nombres.indexOf(RRtypenombre);

			ips.add(j,ip_consultas);
			ips2.add(j," "+"CNAME");
			
			int tiempototal=(ttl+Integer.parseInt(HoraSistema.HoraSistema_ttl()));

			tiempo.add(j,Integer.toString(tiempototal));

			return "fin";
		}
	 return "fin";
	}
	
	
	
static String cache_NScons(ArrayList<ArrayList> serv, int ttl,ArrayList<String> nombresns,ArrayList<String> ipsns,ArrayList<String> tiempons,String ip_consultas,String rrtype_consultas, String nombre,int i,ArrayList<ResourceRecord> answers) throws Exception{
		
		String RRtypenombre;
		RRtypenombre=rrtype_consultas+" "+nombre;
	   String ip_consultas1= ip_consultas;
		if(i==0){
		
			if(nombresns.contains(RRtypenombre)){
				

				
				int j=nombresns.indexOf(RRtypenombre);
				
				if((ipsns.get(j).isEmpty())||(ipsns.get(j)==null)){return "fin";}
				
				if(Integer.parseInt(HoraSistema.HoraSistema_ttl())>Integer.parseInt(tiempons.get(j))){

				
					tiempons.remove(j);
					ipsns.remove(j);
						nombres.remove(j);
					
					
					return "f";}
				else{
					
				
				try{
					System.out.println("Q"+" "+"Cache  Cache"+" "+RRtypenombre);
				for(int h=0;h<serv.get(j).size();h++){
					
					System.out.print("A"+" "+ipsns.get(j)+" ");
					ResourceRecord x= (ResourceRecord)serv.get(j).get(h);
					Consultas.dump(System.out,x);
				
					}
				}catch(Exception e){
					System.out.println("A"+" "+ipsns.get(j));
				}
				
				return "fin";
			}}
			else {
				
				
		
			return "f";
			}
		}
		else if(i==1){
			nombresns.add(RRtypenombre);
			int j=nombresns.indexOf(RRtypenombre);
			
			int numero=j+1;
	

			ipsns.add(j,ip_consultas1);

			serv.add(j,answers);

			int tiempototal=(ttl+Integer.parseInt(HoraSistema.HoraSistema_ttl()));

			tiempons.add(j,Integer.toString(tiempototal));
			
		
			return "fin";
		}
		else if(i==2){
			nombresns.add(RRtypenombre);
			int j=nombresns.indexOf(RRtypenombre);

			ipsns.add(j,ip_consultas+" "+"CNAME");
	
			int tiempototal=(ttl+Integer.parseInt(HoraSistema.HoraSistema_ttl()));
		
			tiempons.add(j,Integer.toString(tiempototal));
	
			return "fin";
		}
	 return "fin";
	}
	
	
	

	
}
