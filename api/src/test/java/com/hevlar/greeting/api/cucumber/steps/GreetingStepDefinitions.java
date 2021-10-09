package com.hevlar.greeting.api.cucumber.steps;

import com.hevlar.greeting.model.Greeting;
import com.hevlar.greeting.service.GreetingService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GreetingStepDefinitions {

    @Autowired
    GreetingService greetingService;

    String greetingAnswer;

    List<Greeting> greetingList = null;
    List<Greeting> savedGreetingList = null;


    @When("greeted with an empty name")
    public void greeted_with_an_empty_name(){
        greetingAnswer = greetingService.greet("");
    }

    @Then("greet back with {string}")
    public void greetBackWith(String greeting) {
        assertThat(greetingAnswer, is(greeting));
    }

    @When("greeted with a name {string}")
    public void greetedWithAName(String name) {
        greetingAnswer = greetingService.greet(name);
    }

    @When("saving the following greeting list")
    public void savingTheFollowingGreetingList(DataTable table) {
        greetingList = new ArrayList<>();
        savedGreetingList = new ArrayList<>();

        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for(Map<String, String> row: rows){
            String name = row.get("name");
            LocalDateTime dateTime = LocalDateTime.parse(row.get("dateTime"));
            String greetingText = row.get("greeting");
            Greeting greeting = new Greeting(name, dateTime, greetingText);
            greetingList.add(greeting);
            savedGreetingList.add(greetingService.saveGreeting(greeting));
        }

    }

    @Then("get back the greeting list")
    public void getBackTheGreetingList(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for(int i = 0; i < rows.size(); i++){
            Map<String, String> row = rows.get(i);
            String name = row.get("name");
            LocalDateTime dateTime = LocalDateTime.parse(row.get("dateTime"));
            String greetingText = row.get("greeting");
            Greeting greeting = new Greeting(name, dateTime, greetingText);
            assertThat(greeting, equalTo(savedGreetingList.get(i)));
        }
    }

    @Given("the following greeting is available")
    public void theFollowingGreetingIsAvailable(DataTable table) {
        Map<String, String> map = table.asMap(String.class, String.class);
        String name = map.get("name");
        LocalDateTime dateTime = LocalDateTime.parse(map.get("dateTime"));
        String greetingText = map.get("greeting");
        Greeting greeting = new Greeting(name, dateTime, greetingText);

        greetingService.saveGreeting(greeting);
    }

    @When("finding the greeting for {string}")
    public void findingTheGreetingFor(String name) {
        savedGreetingList = greetingService.findGreeting(name);
    }
}
