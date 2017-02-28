package usecase;

import requestor.Request;
import requestor.RequestBuilder;
import usecase.addperson.AddPersonRequest;
import usecase.deleteperson.DeletePersonRequest;
import usecase.exportfile.ExportRequest;
import usecase.importfile.ImportRequest;
import usecase.refresh.RefreshRequest;

import java.io.File;
import java.util.Map;

public class RequestBuilderImpl implements RequestBuilder {

    @Override
    public Request make(String request, Map<Integer, Object> args) {
        if (request.equals("RefreshRequest"))
            return new RefreshRequest();

        if (request.equals("AddPersonRequest")) {
            AddPersonRequest r = new AddPersonRequest();
            Object[] objects = (Object[]) args.get(1);
            r.fullName = (String) objects[0];
            r.occupation = (String) objects[1];
            r.ageCategory = (int) objects[2];
            r.employmentStatus = (int) objects[3];
            r.uSCitizen = (boolean) objects[4];
            r.taxId = (String) objects[5];
            r.gender = (String) objects[6];
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
