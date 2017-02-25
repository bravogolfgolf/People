package usecase;

import requestor.Request;
import usecase.addperson.AddPersonRequest;
import usecase.deleteperson.DeletePersonRequest;
import usecase.exportfile.ExportRequest;
import usecase.importfile.ImportRequest;
import usecase.refresh.RefreshRequest;
import view.EntryEvent;
import view.RequestBuilder;

import java.io.File;
import java.util.Map;

public class RequestBuilderImpl implements RequestBuilder {

    @Override
    public Request make(String request, Map<Integer, Object> args) {
        if (request.equals("RefreshRequest"))
            return new RefreshRequest();

        if (request.equals("AddPersonRequest")) {
            AddPersonRequest r = new AddPersonRequest();
            EntryEvent entryEvent = (EntryEvent) args.get(1);
            r.fullName = entryEvent.getFullName();
            r.occupation = entryEvent.getOccupation();
            r.ageCategory = entryEvent.getAgeCategory();
            r.employmentStatus = entryEvent.getEmploymentStatus();
            r.uSCitizen = entryEvent.isUsCitizen();
            r.taxId = entryEvent.getTaxId();
            r.gender = entryEvent.getGender();
            return r;
        }

        if (request.equals("DeletePersonRequest")) {
            DeletePersonRequest r = new DeletePersonRequest();
            r.id = (int) args.get(1);
            return r;
        }

        if (request.equals("ExportRequest")) {
            ExportRequest r = new ExportRequest();
            r.file = (File) args.get(1);
            return r;
        }

        if (request.equals("ImportRequest")) {
            ImportRequest r = new ImportRequest();
            r.file = (File) args.get(1);
            return r;
        }

        return null;
    }
}
