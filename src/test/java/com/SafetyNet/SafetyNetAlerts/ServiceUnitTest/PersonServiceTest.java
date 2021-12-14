package com.SafetyNet.SafetyNetAlerts;

import com.SafetyNet.SafetyNetAlerts.dto.CalculateNumberOfAdultAndChildren;
import com.SafetyNet.SafetyNetAlerts.dto.ChildrenDTO;
import com.SafetyNet.SafetyNetAlerts.model.Firestation;
import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.service.DataService;
import com.SafetyNet.SafetyNetAlerts.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PersonServiceTest {


    @Autowired
    PersonService personService;
    @Autowired
    DataService dataService;

    @Test
    void getPersonList() {
        List<Person> personList1 = personService.getPersonList();
        Assertions.assertNotNull(personList1);
    }

    @Test
    void deletePersonTest() {

    }
    @Test
    void getPersonByStationTest() {
        String station = "2";
        CalculateNumberOfAdultAndChildren calculateNumberOfAdultAndChildren = personService.getPersonByStation(station);
        int adult = calculateNumberOfAdultAndChildren.getAdult();
        int children = calculateNumberOfAdultAndChildren.getChildren();
        Assertions.assertEquals(adult, 4);
        Assertions.assertNotNull(calculateNumberOfAdultAndChildren);
    }
    @Test
    void getChildrenListTest(){
        List<Firestation> firestationList = dataService.getFirestations();
        for(Firestation firestation : firestationList){
            String address = firestation.getAddress();
            List<ChildrenDTO> childrenDTOList = personService.getChildrenList(address);
        }
    }
}
