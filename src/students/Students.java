/**
 * 
 */
package students;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * @author kleanthis.mitsioulis
 *
 */
public class Students{
	private String name;
	private String surname;
	private String age;
	private String height;
	private String tuition;
	private String date;
	private String phone;
	private String conduct;

	public  Students(String name, String surname, String age, String height, String tuition, String date, String phone, String conduct) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.height = height;
		this.tuition = tuition;
		this.date = date;
		this.phone = phone;
		this.conduct = conduct;
	}





	public Students(List<String> asList) {
		// TODO Auto-generated constructor stub
	}





	static List<String[]> data = new ArrayList<String[]>();
	static List<Students> students = new ArrayList<Students>();
	static File f = new File("Lab3_Names.txt");
	static BufferedReader br;
	static boolean again = false;
	static String menuChoice = "";
	static Scanner sc = new Scanner(System.in);
	static String[] dataRow = new String[5];
	static boolean alreadyExecuted = false;
	static ArrayList<String[]> headerLine;
	static boolean checkConductRun = false;
	static int countSame = 0;
	static boolean checkD = false;


	public static void main(String[] args) {

		if(!alreadyExecuted) {
			readFile();
			studentToList();
			alreadyExecuted = true;
		}
		
		System.out.println("Welcome!");	
		do {
			
			System.out.println();
			System.out.println("Please select an action:");
			System.out.println();
			System.out.println("1 for Reading the database");
			System.out.println("2 for Searching the database");
			System.out.println("3 for Sorting the database by student surname");
			System.out.println("4 for Sorting the database by student age");
			System.out.println("5 for Sorting the database by student phone");
			System.out.println("6 for Sorting the database by student Name and Age");
			System.out.println("7 for Setting a student's conduct (Default is \"Good\")");
			System.out.println("8 for Exit");



			menuChoice = sc.next();
			

			if (menuChoice.equals("1")) {
				printFile();
				anotherTime();

			}else if (menuChoice.equals("2")) {

				searchDatabase();
				anotherTime();

			}else if (menuChoice.equals("3")) {

				sortBySurname();
				
				anotherTime();
			}else if (menuChoice.equals("4")) {

				sortByAge();
				anotherTime();
			}else if (menuChoice.equals("5")) {

				sortByPhone();
				anotherTime();
			}else if (menuChoice.equals("6")) {

				sortNameAge();
				anotherTime();
			}else if (menuChoice.equals("7")) {
				setNewConduct();
				
			}else {
				again=false;
			}


		}while(again);
		System.out.println("Have a nice Day!");

	}

	static void printFile( ) {
		for (String[] dataRow : data) {
			for(String search : dataRow) {
				System.out.print(search + " ");
			}
			System.out.println();
		}

	}
	static void anotherTime() {
		System.out.println();
		System.out.println("Would you like to perform another action?");
		System.out.println("Press \"Y\" for YES, anything else fot EXIT");
		menuChoice = sc.next();
		if(menuChoice.equalsIgnoreCase("y")) {
			again = true;
		}else {
			again = false;
		}
	}

	static void readFile() {
		try {
			br = new BufferedReader(new FileReader(f));
			String line = "";

			int checkHeader = 0;
			int i = 0;
			while((line = br.readLine())!=null) {
				if (checkHeader == 0) {
					checkHeader++;
					continue;
				}
				String[] dataRow = line.split(",");
				
				LinkedList<String> ll = new LinkedList<String>(Arrays.asList(dataRow));
				ll.add("Good");
				dataRow = ll.toArray(new String[ll.size()]);
				//students.add(new Students(Arrays.asList(dataRow)));
				data.add(dataRow);
				
				i++;
			}
			
			br.close();

		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		alreadyExecuted = true;
	}
	


	static void studentToList () {
		for (int i = 0; i< data.size(); i++) {
			students.add(new Students(data.get(i)[0],data.get(i)[1],data.get(i)[2],data.get(i)[3],data.get(i)[4],data.get(i)[5],data.get(i)[6],data.get(i)[7]));
		}

	}
	static void studentSet () {
		for (int i = 0; i< data.size(); i++) {
			students.get(i).setName(data.get(i)[0]);
			students.get(i).setSurname(data.get(i)[1]);
			students.get(i).setAge(data.get(i)[2]);
			students.get(i).setHeight(data.get(i)[3]);
			students.get(i).setTuition(data.get(i)[4]);
			students.get(i).setDate(data.get(i)[5]);
			students.get(i).setPhone(data.get(i)[6]);
			students.get(i).setConduct(data.get(i)[7]);
		}

	}

	static int searchDatabase() {
		boolean z = false;
		boolean x = false;
		boolean moreSame = false;
		int p = -1;
		System.out.println("Enter \"C\" for CANCEL or");
		System.out.println("Enter searching Criteria:");
		menuChoice = sc.next();
		if (menuChoice.equalsIgnoreCase("c")) {
			z = true;

		}else {
			int i = 0;
			int m = 0;
			for (String[] dataRow : data) {
				for(String search : dataRow) {
					if(search.equalsIgnoreCase(menuChoice)) {
						countSame++;
						x = true;
						p = m;
						if (!moreSame)  {
							System.out.println("Student found:");
							System.out.printf("%-12s %-10s %-5s %-8s %-10s %-13s %-12s %-10s","Name", "Surname", "Age", "Height", "Tuition","Date", "Phone", "Conduct");
							moreSame = true;
							System.out.println();
						}
						System.out.println();
						System.out.println(students.get(i).toString());
						z = false;
					}
				}
				m++;
				i++;
			}

			System.out.println();
			if (!checkConductRun  && z) {
				anotherTime();
			}
		}
		if (z==true) {
			notFound();
			anotherTime();
		}
		if (x==false)  {
			
			notFound();
			anotherTime();
		}
		return p;
	}
	
	static int searchDatabase(String s) {
		boolean z = false;
		boolean x = false;
		boolean moreSame = false;
		int p = -1;
//		System.out.println("Enter \"C\" for CANCEL or");
//		System.out.println("Enter searching Criteria:");
		//menuChoice = sc.next();
		if (menuChoice.equalsIgnoreCase("c")) {
			z = true;

		}else {
			int i = 0;
			int m = 0;
			for (String[] dataRow : data) {
				for(String search : dataRow) {
					if(search.equalsIgnoreCase(s)) {
						countSame++;
						x = true;
						p = m;
						if (!moreSame)  {
							System.out.println("Student found:");
							System.out.printf("%-12s %-10s %-5s %-8s %-10s %-13s %-12s %-10s","Name", "Surname", "Age", "Height", "Tuition","Date", "Phone", "Conduct");
							moreSame = true;
							System.out.println();
						}
						System.out.println();
						
						System.out.println(students.get(i).toString());
						z = false;
						
					}
				}
				m++;
				i++;
			}

			System.out.println();
			if (!checkConductRun  && z) {
				anotherTime();
			}
		}
		if (z==true) {
			System.out.println("zzzzzzzzzz");
			notFound();
			anotherTime();
		}
		if (x==false)  {
			notFound();
			anotherTime();
		}
		return p;
	}
	


	static void notFound() {
		System.out.println("Not found! Try another input");
	}

	static void sortBySurname() {
		students.sort(new Comparator<Students>() {
			
			@Override
			public int compare(Students s1, Students s2) {
				return s1.getSurname().compareTo(s2.getSurname());
			}
		});
		iterateChoice("Surname");
		studentSet();
	}

	static void sortByAge() {
		students.sort(new Comparator<Students>() {
			@Override
			public int compare(Students s1, Students s2) {
				return s1.getAge().compareTo(s2.getAge());
			}
		});
		iterateChoice("Age");
		studentSet();
	}

	static void sortByPhone() {
		students.sort(new Comparator<Students>() {
			@Override
			public int compare(Students s1, Students s2) {
				return s1.getPhone().compareTo(s2.getPhone());
			}
		});
		iterateChoice("Phone");
		studentSet();
	}

	static void setNewConduct() {
		checkConductRun = true;
		boolean stop = false;
		System.out.println("Find the student whose conduct you want to change");
		System.out.println("Enter \"C\" for CANCEL or");
		System.out.println("Enter searching Criteria:");
		menuChoice = sc.next();
		int p = searchDatabase(menuChoice);
		if(p<0) {
			stop= true;
		}
		if (!stop) {
			System.out.println("Enter new conduct");
			menuChoice= sc.next();
			System.out.println(p);
			students.get(p).setConduct(menuChoice);
			iterateChoice();
			anotherTime();
		}
	}

	static void sortNameAge() {

		students.sort(new Comparator<Students>() {
			@Override
			public int compare(Students s1, Students s2) {

				String x1 = s1.getName();
				String x2 = s2.getName();
				int sComp = x1.compareTo(x2);

				if (sComp != 0) {
					return sComp;
				} 
				String x3 = s1.getAge();
				String x4 = s1.getAge();
				return s1.getAge().compareTo(s2.getAge());
			}
		});
		iterateChoice("Name and Age");
	}


	static void iterateChoice(String s) {
		Iterator<Students> it = students.iterator();
		System.out.println("Sorted based on " + s);
		System.out.println();
		System.out.printf("%-12s %-10s %-5s %-8s %-10s %-13s %-12s %-10s","Name", "Surname", "Age", "Height", "Tuition","Date", "Phone", "Conduct");
		System.out.println();
		System.out.println();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		System.out.println();
		
	}
	static void iterateChoice() {
		Iterator<Students> it = students.iterator();
		System.out.println();
		System.out.printf("%-12s %-10s %-5s %-8s %-10s %-13s %-12s %-10s","Name", "Surname", "Age", "Height", "Tuition","Date", "Phone", "Conduct");
		System.out.println();
		System.out.println();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		System.out.println();
	}
	


	public String toString() {
		System.out.printf("%-12s %-10s %-5s %-8s %-10s %-13s %-12s %-10s", this.getName(),this.getSurname(),this.getAge(),this.getHeight(),
				this.getTuition(), this.getDate(), this.getPhone(),this.getConduct());
		return "";
	}

	public String getSurname() {
		return surname;
	}
	public String getAge() {
		return age;
	}
	public String getPhone() {
		return phone;
	}
	public String getName() {
		return name;
	}
	public String getHeight() {
		return height;
	}
	public String getTuition() {
		return tuition;
	}
	public String getDate() {
		return date;
	}
	public String getConduct() {
		return conduct;
	}
	public void setConduct(String conduct) {
		this.conduct = conduct;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setTuition(String tuition) {
		this.tuition = tuition;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}




