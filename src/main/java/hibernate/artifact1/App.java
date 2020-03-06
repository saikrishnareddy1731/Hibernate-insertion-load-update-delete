package hibernate.artifact1;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class App 
{
    public static void main( String[] args )
    {
    	Configuration config= new Configuration();
        config.configure("hibernate.xml");
        
        SessionFactory factory = config.buildSessionFactory();
        
        
       storeObject(factory);
       loadObject(factory);
       delete(factory);
       update(factory);
        
    }

	private static void update(SessionFactory factory) 
	{
		try(Session session= factory.openSession(); Scanner scanner= new Scanner(System.in))
		{
			  Transaction tx = session.beginTransaction();
			
			Query qry = session.createQuery("update Employee s set s.firstName=:t where id=:p");
			                 System.out.println("Enter the first name what you want to change:");
                             String editFirstName=scanner.nextLine();
			                 System.out.println("enter the id you want to you want to update first name: ");
			                 int s=scanner.nextInt();
			                 qry.setParameter("t", editFirstName);
			                 qry.setParameter("p",s);
			                 
						     int res = qry.executeUpdate();
                             System.out.println("Command successfully executed....");
						     System.out.println("Numer of records effected due to update  query="+res);
                             tx.commit();
						     session.close();
				       		 factory.close();
			
			
			
			
		
			 
			//   System.out.println("****************************************Deleted successfully broh*******************************************");
			 
		
	}
		 catch(HibernateException ex) 
		{
			   ex.printStackTrace();
		   }
		
		
	}

	private static void delete(SessionFactory factory) 
	{
		try(Session session= factory.openSession(); Scanner scanner= new Scanner(System.in))
		{

			  Transaction tx = session.beginTransaction();
			
			Query qry = session.createQuery("delete from Employee p where p.id=:sai");
			             System.out.println("enter the id you want to delete ");
			                    int s=scanner.nextInt();
						        qry.setParameter("sai",s);
						        int res = qry.executeUpdate();

						        System.out.println("Command successfully executed....");
						        System.out.println("Numer of records effected due to delete query"+res);
                                 tx.commit();
						        session.close();
								factory.close();
			
			
			
			
		
			 
			//   System.out.println("****************************************Deleted successfully broh*******************************************");
			 
		
	}
		 catch(HibernateException ex) 
		{
			   ex.printStackTrace();
		   }
		
	}

	private static void loadObject(SessionFactory factory) 
	{
		try(Session session= factory.openSession(); Scanner scanner= new Scanner(System.in))
		{

	  
			
			
			
//****************************************************where to get the  particular employe id from table ************************************************************************** 
			
			Query query=session.createQuery("select emp.id,emp.lastName from Employee emp where emp.firstName=:s");
			
			    System.out.println("Enter the first name u want to check  :");
			  String s=scanner.nextLine();	
		     query.setParameter("s",s);
		     List <Object[]>list=(List<Object[]>)query.getResultList();
		   
		     Iterator<Object[]> iter=list.iterator();
			  
		     
		   	 
		     while(iter.hasNext())
		     {
					
					  Object []objCategory=iter.next(); 
					  System.out.println(objCategory[0]);
					  System.out.println(objCategory[1]);
					
			}
			
		     
		      
		      
  	
			 
			 
			 

			     
			
// *********************************its use similar to the object type only  , here we can get all the details of the person by using the getter setter methods***********************
			
			
			/*Query query = session.createQuery("from Employee");       
	    	 List<Employee> list = (List<Employee>) query.getResultList();
	    	 Iterator<Employee> iter = list.iterator();

	    	 
     while(iter.hasNext())
    {

	    	 
	    	 Employee emp = iter.next();
	    	 System.out.println(emp.getId());
	    	 System.out.println(emp.getFirstName());
	    	 System.out.println(emp.getLastName());
	    	  System.out.println(emp.getSalary());
	     }
	     */
  
//***************************************Object type array it consist of all the details of an employee array in the form of array so we call them by index  "best method to call"***************************
		
		/*	Query query=session.createQuery("select emp.id,emp.firstName,emp.lastName,emp.salary from Employee emp");
              //above two ways we can write the query 1st one is the best one.
			
			List<Object[]>list=(List<Object[]>)query.getResultList();
			 Iterator<Object[]> iter=list.iterator();
			  
			     
			   	 
			     while(iter.hasNext())
			     {
						
						  Object []objCategory=iter.next(); 
						  System.out.println((objCategory[0]));
						  System.out.println(objCategory[1]);
						  System.out.println(objCategory[2]);
						  System.out.println(objCategory[3]);
				}
     */
    

		
	}
		 catch(HibernateException ex) 
		{
			   ex.printStackTrace();
		   }
	}

	private static void storeObject(SessionFactory factory) {
		
		try(Session session= factory.openSession(); Scanner scanner= new Scanner(System.in))
		{

	    	  System.out.println("Enter ID : ");
			   int id =  scanner.nextInt();
			   System.out.println("Enter first name : ");
			   String firstName= scanner.next();
			   System.out.println("Enter last name : ");
			   String lastName = scanner.next();
			   System.out.println("Enter salary : ");
			   int salary=  scanner.nextInt();
	         Employee employee = new Employee(id, firstName,lastName,salary);
			   
			   Transaction tx = session.beginTransaction();
			   session.save(employee);
			   tx.commit();
			   System.out.println("inserted : ");
					   
		   }
		   catch(HibernateException ex) 
		   {
			   ex.printStackTrace();
		   }
		
		
	}
}
