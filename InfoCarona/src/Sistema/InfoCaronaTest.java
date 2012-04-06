package Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import easyaccept.EasyAcceptFacade;

public class InfoCaronaTest {


	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		List<String> files = new ArrayList<String>();
        //Put the us1.txt file into the "test scripts" list
        //files.add("US01.txt");
        //files.add("US02.txt");
        files.add("US03.txt");
        files.add("US04.txt");
        //files.add("US05.txt");
        //Instantiate the sistena
        
        //Instantiate EasyAccept façade
        EasyAcceptFacade eaFacade = new EasyAcceptFacade(sistema, files);
//Execute the tests
        eaFacade.executeTests();
//Print the tests execution results
        System.out.println(eaFacade.getCompleteResults());
      
  }

}
