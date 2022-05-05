import java.util.Scanner;



import com.example.repository.AccountRepository;
import com.example.repository.JdbcAccountRepository;
//import com.example.repository.TxnRepository;
//import com.example.repository.TxnRepositoryImpl;
import com.example.service.TxrService;
import com.example.service.TxrServiceImpl;
import com.example.entity.AccDetails;
import com.example.entity.TDetails;

public class App {

	public static void main(String[] args) {
		
		AccountRepository accountRepository = new JdbcAccountRepository();
		TxrService txrService = new TxrServiceImpl(accountRepository);
		Scanner input = new Scanner(System.in);
		while(true)
		{	
		System.out.println("\nSelect 1 for account creation");
		System.out.println("Select 2 for money transfer");
		System.out.println("Select 3 for account details");
		System.out.println("Select 4 for edit account");
		System.out.println("Select 5 to delete account");
		System.out.println("Select 6 to count Accounts");
		System.out.println("Select 7 for transactions");
		System.out.println("Select 8 to count transactions");
		System.out.println("Select 0 to exit");
		int option = input.nextInt();
		if(option!=0) {
		switch(option) {
		case 1:{
			
			System.out.println("Enter your age :");
			int age1 = input.nextInt();
			if(age1>=18) {
		AccountRepository accRepository = new JdbcAccountRepository(); // a.k.a D.A.O
		System.out.println("Select 1 : Savings account");
		System.out.println("Select 2 : Current account");
		int s=input.nextInt();
		if(s==1) {
		System.out.println("Enter Name");
		String name0 = input.next();
		System.out.println("Enter phone");
		long phone = input.nextLong();
		System.out.println("Enter address");
		String address0 = input.next();
		System.out.println("Enter opening balance");
		double balance0 = input.nextDouble();
		String type="Savings Account";
		System.out.println("Enter your age :");
		int age=input.nextInt();
		if(age>=18 && age==age1) {
			System.out.println(age);
		}
		else if(!(age==age1)){
			System.out.println("\nERROR IN THE AGE YOU HAVE MENTIONED");
			break;
		}
		else {
			System.out.println("\nNOT ELIGIBLE TO CREATE AN ACCOUNT");
			break;
		}
		AccDetails accdetails = new AccDetails(name0,phone,address0,balance0,type,age);
		accRepository.save(accdetails);
		}
		else if(s==2) {
			System.out.println("Enter Name");
			String name0 = input.next();
			System.out.println("Enter phone");
			long phone = input.nextLong();
			System.out.println("Enter address");
			String address0 = input.next();
			System.out.println("Enter opening balance");
			double balance0 = input.nextDouble();
			String type="Current Account";
			System.out.println("Enter your age :");
			int age=input.nextInt();
			if(age>=18 && age==age1) {
				System.out.println(age);
			}
			else if(!(age==age1)){
				System.out.println("\nERROR IN THE AGE YOU HAVE MENTIONED");
				break;
			}
			else {
				System.out.println("\nNOT ELIGIBLE TO CREATE AN ACCOUNT");
				break;
			}
			AccDetails accdetails = new AccDetails(name0,phone,address0,balance0,type,age);
			accRepository.save(accdetails);
		}
		break;
		}
			
			else {
				System.out.println("\nNOT ELIGIBLE TO CREATE AN ACCOUNT");
				break;
			}
		}
		case 2:{
		AccountRepository accRepository = new JdbcAccountRepository();
		System.out.println("Enter from account");
		String fid = input.next();
		System.out.println("Enter to account");
		String tid = input.next();
		System.out.println("Enter the amount");
		double am = input.nextDouble();
		boolean f = txrService.transfer(am, fid, tid);
		if(f) {
		TDetails tdetails = new TDetails(fid,tid,am);
		accRepository.tsave(tdetails);
		}
		break;
		}
		case 3:
		{
			System.out.println("Enter Account id (0 For all account )");
			int n1=input.nextInt();
			accountRepository.getAccDetails(n1).forEach(accdetails->System.out.println(accdetails));
			break;
		}
		case 4:
		{
			System.out.println("Enter Account id");
			int n1=input.nextInt();
			accountRepository.edit(n1);
			break;
		}
		case 5:{
			System.out.println("Enter Account id)");
			
			
			
			
			
			
			
			
			int n1=input.nextInt();
			accountRepository.delete(n1);
			break;
		}
		case 6:{
			System.out.println(accountRepository.countAcc());
			break;
		}
		case 7:{
			accountRepository.getTransfer().forEach(tdetails->System.out.println(tdetails));
			break;
		}
		case 8:{
			System.out.println(accountRepository.count());
			break;	
		}
		}
		}
		else {
			break;
		}
		}
		
}
}


