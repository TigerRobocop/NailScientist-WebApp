package program;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import basicas.polish.Polish;
import facade.Facade;


public class Program {


	static EntityManagerFactory factory;
	static EntityManager manager;

	public static void main(String[] args) {
		
	/*	Facade f = new Facade();
		Brand b = new Brand();
		b.setName("name3455");
		try {
			f.insert(b);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
		
		Facade f = new Facade();
			Polish p = new Polish();
			p.setColor("azul goiaba");
			p.setName("coisa linda");
			
			
			Polish teste = null;
			try {
				p.setFinish(f.findByNameFinish("aaa"));
				p.setBrand(f.findByNameBrand("teste"));
			
				f.insert(p);
				teste = f.findByNamePolish("coisa linda");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println(p.getColor());
			System.out.println(teste.getColor());
	}

}
