package tests;

import org.junit.Assert;
import org.junit.Test;

import basicas.polish.Polish;
import facade.Facade;

public class PolishTest {
	
	@Test
	public void testCRUDPolish(){
		Facade f = new Facade();
		Polish p = new Polish();
		p.setColor("azul goiabaaa");
		p.setName("coisa lindaaa");
		
		
		Polish teste = null;
		try {
			p.setFinish(f.findByNameFinish("aaa"));
			p.setBrand(f.findByNameBrand("teste"));
		
			f.insert(p);
			teste = f.findByNamePolish("coisa lindaaa");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		
		Assert.assertEquals(p.getName(), teste.getName());
		Assert.assertEquals("Nome não bate", teste.getName(), "coisa lindaaa");		
		Assert.assertEquals("cor não bate!", teste.getColor(), p.getColor());
		Assert.assertEquals("cor não bate", teste.getColor(), "azul goiabaaa");
		
		p.setColor("cor da burra");
		
		try {
			teste = f.update(p);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertEquals(p.getColor(), teste.getColor());		
		Assert.assertEquals("Cor não bate", teste.getColor(), "cor da burra");	
		
		try {
			f.delete(teste);
		} catch (Exception e) {
			System.out.println();
		}
		
		Assert.assertNull(f.findByNamePolish("coisa lindaaa"));
	}

}
