package main;

import domain.Person;
import domain.Response;
import domain.ResponseBuilder;
import ui.RefreshResponse;
import ui.RefreshResponseRecord;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilderImpl implements ResponseBuilder {

    @Override
    public Response make(String response, Map<Integer, Object> args) {
        if (response.equals("RefreshResponse")) {
            RefreshResponse refreshResponse = new RefreshResponse();
            Map map = (HashMap) args.get(1);
            RefreshResponseRecord[] records = new RefreshResponseRecord[map.size()];
            int i = 0;
            for (Object object : map.values()) {
                Person person = (Person) object;
                RefreshResponseRecord record = new RefreshResponseRecord();
                record.id = person.getId();
                record.fullName = person.getFullName();
                record.occupation = person.getOccupation();
                record.ageCategory = person.getAgeCategory();
                record.employmentStatus = person.getEmploymentStatus();
                record.uSCitizen = person.isUsCitizen();
                record.taxId = person.getTaxId();
                record.gender = person.getGender();
                records[i++] = record;
            }
            refreshResponse.responseRecords = records;
            return refreshResponse;
        }
        return null;
    }
}
