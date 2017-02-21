package main;

import domain.Person;
import ui.RefreshResponse;
import domain.Response;
import domain.ResponseBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ResponseBuilderImpl implements ResponseBuilder {
    @Override
    public Response make(String response, Map<Integer, Object> args) {
        if (response.equals("RefreshResponse")) {
            Map<Integer, Person> people = new HashMap<>((HashMap<Integer, Person>) args.get(1));
            RefreshResponse r = new RefreshResponse();
            String[] responses = new String[people.size()];
            int i = 0;
            for (Person person : people.values()) {
                StringJoiner message = new StringJoiner("|");
                message.add(String.valueOf(person.getId()));
                message.add(person.getFullName());
                message.add(person.getOccupation());
                message.add(String.valueOf(person.getAgeCategory()));
                message.add(String.valueOf(person.getEmploymentStatus()));
                message.add(String.valueOf(person.isUsCitizen()));
                message.add(person.getTaxId());
                message.add(person.getGender());
                responses[i++] = message.toString();
            }
            r.people = responses;
            return r;
        }
        return null;
    }
}
