
public class Teste {
public static void main(String[] args) {
	String origem = "-";
	
	if (((origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%�&*0-9].*")))) {
         System.out.println("entrou");
	 }else{
		 System.out.println("nao");
	 }
 	
}
}
