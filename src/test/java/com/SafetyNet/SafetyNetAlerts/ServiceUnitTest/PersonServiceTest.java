package com.SafetyNet.SafetyNetAlerts.ServiceUnitTest;

import com.SafetyNet.SafetyNetAlerts.config.ReadAndConvertJsonFileToObject;
import com.SafetyNet.SafetyNetAlerts.dto.*;
import com.SafetyNet.SafetyNetAlerts.model.Person;
import com.SafetyNet.SafetyNetAlerts.service.DataService;
import com.SafetyNet.SafetyNetAlerts.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Autowired
    DataService dataService;

    @Autowired
    ReadAndConvertJsonFileToObject readAndConvertJsonFileToObject;

    @BeforeEach
    public void initData() throws IOException {
        readAndConvertJsonFileToObject.initData();
    }

    //ajouter une nouvelle personne test
    @Test
    void ajouterUnePersonTest() {
        Person person = new Person("Norhene", "Boublenza", "54 rue jonquoy", "Paris", "54878", "2155454551", "boublenza.n@gmail.com");
        personService.savePerson(person);
        List<Person> personList = dataService.getPersons();
        Assertions.assertEquals(personList.get(23).firstName, "Norhene");
        Assertions.assertEquals(personList.get(23).lastName, "Boublenza");
        Assertions.assertNotNull(personList.get(23));
    }

    // mettre à jour une personne test
    @Test
    void personUpdateTest() {
        String firstName = "John";
        String lastName = "Boyd";
        Person personUpdated = new Person("Norhene", "Boublenza", "54 rue jonquoy", "Paris", "54878", "2155454551", "boublenza.n@gmail.com");
        personService.updatePerson(firstName, lastName, personUpdated);
        List<Person> personList = dataService.getPersons();
        Assertions.assertEquals(personList.get(0).firstName, "Norhene");
    }

    //supprimer une personne test
    @Test
    void deletePersonTest() {
        String firstName = "John";
        String lastName = "Boyd";
        personService.deletePerson(firstName,lastName);
        List<Person> personList = dataService.getPersons();
        Assertions.assertNotEquals(personList.get(0).firstName,"John");
    }

    @Test
    void getPersonByStationTest() {
        String station = "2";
        CalculateNumberOfAdultAndChildren calculateNumberOfAdultAndChildren = personService.getPersonByStation(station);
        Assertions.assertEquals(calculateNumberOfAdultAndChildren.adult, 4);
        Assertions.assertEquals(calculateNumberOfAdultAndChildren.children, 1);
        Assertions.assertNotNull(calculateNumberOfAdultAndChildren);
    }

    @Test
    void getChildrenListTest() {
        String address = "1509 Culver St";
        List<ChildrenDTO> childrenDTOList = personService.getChildrenList(address);
        Assertions.assertEquals(childrenDTOList.get(0).firstName,"Tenley");
        Assertions.assertEquals(childrenDTOList.get(0).age,9);
        Assertions.assertNotNull(childrenDTOList);
    }
    @Test
    void getPhoneNumberTest() {
        String stationNumber = "1";
        List<String> phoneNumber = personService.getPhoneNumber(stationNumber);
        Assertions.assertFalse(phoneNumber.isEmpty());
        Assertions.assertTrue(phoneNumber.contains("841-874-7462"));


    }
    @Test
    void getInhabitantByAddressTest() {
        String address = "29 15th St";
        List<FireDTO> fireDTOS = personService.getInhabitantByAddress(address);
        Assertions.assertEquals(fireDTOS.get(0).lastName,"Marrack");
        Assertions.assertEquals(fireDTOS.get(0).stationNumber,"2");
        Assertions.assertFalse(fireDTOS.isEmpty());
    }
    @Test
    void getFoyerByStationListTest() {
        List<String> stations = Arrays.asList("2","3");
        List<FloodDTO> floodDTOList = personService.getFoyerByStationList(stations);
        Assertions.assertEquals(floodDTOList.get(0).foyer.get(0).age,37);
        Assertions.assertEquals(floodDTOList.get(0).address,"1509 Culver St");
        Assertions.assertFalse(floodDTOList.isEmpty());
    }
    @Test
    void getPersonByFirstNameAndLastNameTest() {
        String firstName = "John";
        String lastName = "Boyd";
        List<PersonInfoDTO> personInfoDTOS = personService.getPersonByFirstNameAndLastName(firstName, lastName);
        Assertions.assertEquals(personInfoDTOS.get(0).lastName,"Boyd");
        Assertions.assertEquals(personInfoDTOS.get(0).age,37);
        Assertions.assertFalse(personInfoDTOS.isEmpty());
    }
    @Test
    void getAddressMailByCityTest() {
        String city = "Culver";
        List<String> phoneNumber = personService.getAddressMailByCity(city);
        Assertions.assertEquals(phoneNumber.get(5), "drk@email.com");
        Assertions.assertFalse(phoneNumber.isEmpty());
    }

}
