package ui;

import java.util.ArrayList;

import business.Person;
import business.PersonDB;
import util.Console;

public class JpaCreateDemoApp {

	public static void main(String[] args) {
		System.out.println("JPA Create Schema Demo");
		String choice = "";
		while (!choice.equalsIgnoreCase("exit")) {
			System.out.println("Options:");
			System.out.println("all     - get all person records");
			System.out.println("get     - get person by id");
			System.out.println("add     - add person");
			System.out.println("del     - delete person by id");
			System.out.println("update  - update person");
			System.out.println("exit    - exit app");
			System.out.println();
			choice = Console.getString("Option?:  ");
			
			if (choice.equals("get")) {
				getPerson();
			}
			else if (choice.equals("all")) {
				getAllPersons();
			}
			else if (choice.equals("add")) {
				addPerson();
			}
			else if (choice.equals("del")) {
				deletePerson();
			}
			else if (choice.equals("update")) {
				updatePerson();
			}
		}
		System.out.println("Bye!");
	}

	private static void getPerson() {
		int personId = Console.getInt("Enter personID to retrieve:  ");
		Person u = PersonDB.getPersonById(personId);
		System.out.println("Person details:  "+u);
		System.out.println();
	}

	private static void getAllPersons() {
		ArrayList<Person> persons = PersonDB.getAllPersons();
		System.out.println("All persons:");
		for (Person u:persons) {
			System.out.println(u);
		}
		System.out.println();
	}

	private static void addPerson() {
		String userName = Console.getString("Username:  ");
		String password = Console.getString("Pwd:  ");
		String firstName = Console.getString("FName:  ");
		String lastName = Console.getString("LName:  ");
		Person p = new Person(userName, password, firstName, lastName);
		
		if (PersonDB.addPerson(p)) {
			System.out.println("Person '"+p.getFirstName()+" "+p.getLastName()+"', id="+p.getId()+" successfully added!");
		}
		System.out.println();
	}

	private static void deletePerson() {
		int personId = Console.getInt("Enter personID to delete:  ");
		Person p = PersonDB.getPersonById(personId);
		if(PersonDB.deletePerson(p)) {
			System.out.println("Person '"+p.getFirstName()+" "+p.getLastName()+"' successfully deleted!");
		}
		System.out.println();
	}

	private static void updatePerson() {
		int personId = Console.getInt("Enter personID to update:  ");
		Person p = PersonDB.getPersonById(personId);
		String fName = Console.getString("Enter new value for first name:  ");
		p.setFirstName(fName);
		if(PersonDB.updatePerson(p)) {
			System.out.println("Person '"+p.getFirstName()+" "+p.getLastName()+"' successfully updated!");
		}
		System.out.println();
	}
	
}
