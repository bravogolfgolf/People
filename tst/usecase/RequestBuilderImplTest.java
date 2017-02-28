package usecase;

import org.junit.Test;
import requestor.RequestBuilder;
import usecase.addperson.AddPersonRequest;
import usecase.deleteperson.DeletePersonRequest;
import usecase.exportfile.ExportRequest;
import usecase.importfile.ImportRequest;
import usecase.refresh.RefreshRequest;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestBuilderImplTest {
    private final RequestBuilder builder = new RequestBuilderImpl();
    private final HashMap<Integer, Object> args = new HashMap<>();

    @Test
    public void makeMethodShouldReturnRefreshRequest() {
        RefreshRequest request = (RefreshRequest) builder.make("RefreshRequest", args);
        assertTrue(request != null);
    }

    @Test
    public void makeMethodShouldReturnAddPersonRequest() {
        Object[] objects = new Object[]{"Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender"};
        args.put(1, objects);
        AddPersonRequest request = (AddPersonRequest) builder.make("AddPersonRequest", args);
        assertEquals(objects[0], request.fullName);
        assertEquals(objects[1], request.occupation);
        assertEquals(objects[2], request.ageCategory);
        assertEquals(objects[3], request.employmentStatus);
        assertTrue(request.uSCitizen);
        assertEquals(objects[5], request.taxId);
        assertEquals(objects[6], request.gender);
    }

    @Test
    public void makeMethodShouldReturnDeletePersonRequest() {
        int idToBeDeleted = 1;
        args.put(1, idToBeDeleted);
        DeletePersonRequest request = (DeletePersonRequest) builder.make("DeletePersonRequest", args);
        assertEquals(idToBeDeleted, request.id);
    }

    @Test
    public void makeMethodShouldReturnExportRequest() {
        File file = new File("Export.per");
        args.put(1, file);
        ExportRequest request = (ExportRequest) builder.make("ExportRequest", args);
        assertEquals(file, request.file);
    }

    @Test
    public void makeMethodShouldReturnImportRequest() {
        File file = new File("Import.per");
        args.put(1, file);
        ImportRequest request = (ImportRequest) builder.make("ImportRequest", args);
        assertEquals(file, request.file);
    }
}
