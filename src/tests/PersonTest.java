package tests;
import org.junit.Assert;
import org.junit.Test;

import basicas.person.Employee;

import facade.Facade;

public class PersonTest {

	
	@Test
	public void testCRUDEmployee() {		
	
		Employee emp = new Employee();
		emp.setName("teste");
		emp.setLogin("loginteste3");
		emp.setPhone("(99)9999-9999");
		emp.setPassword("aaaa");
		emp.setPosition("teste");
		Facade f = new Facade();
		Employee retorno = null;
		try {
			f.insert(emp);			
			retorno = f.findEmployeeByLogin("loginteste3");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}				
		// Assert that user was added to database
	
		Assert.assertEquals(emp.getLogin(), retorno.getLogin());		
		Assert.assertEquals("Nome não bate", retorno.getName(), "teste");		
		Assert.assertEquals("Login não bate", retorno.getLogin(), "loginteste3");	
		
		
		emp.setName("testeedit");
		
		try {
			retorno = f.update(emp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertEquals(emp.getName(), retorno.getName());		
		Assert.assertEquals("Nome não bate", retorno.getName(), "testeedit");	
		
		try {
			f.delete(retorno);
		} catch (Exception e) {
			System.out.println();
		}
		
		Assert.assertNull(f.findEmployeeByLogin("loginteste3"));
		
		
		
	}
	
	
	
}
