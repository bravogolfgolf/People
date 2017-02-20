package main;

import domain.AddPersonRequest;
import domain.DeletePersonRequest;
import domain.ExportRequest;
import domain.ImportRequest;
import org.junit.Before;
import org.junit.Test;
import ui.EntryEvent;
import ui.RequestBuilder;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestBuilderImplTest {

    private final RequestBuilder builder = new RequestBuilderImpl();
    private HashMap<Integer, Object> args;

    @Before
    public void setUp() {
        args = new HashMap<>();
    }

    @Test
    public void makeMethodShouldReturnAddPersonRequest() {
        EntryEvent formEvent = new EntryEvent(new Object(), "Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender");
        args.put(1, formEvent);
        AddPersonRequest request = (AddPersonRequest) builder.make("AddPersonRequest", args);
        assertEquals(formEvent.getFullName(), request.fullName);
        assertEquals(formEvent.getOccupation(), request.occupation);
        assertEquals(formEvent.getAgeCategory(), request.ageCategory);
        assertEquals(formEvent.getEmploymentStatus(), request.employmentStatus);
        assertTrue(request.uSCitizen);
        assertEquals(formEvent.getTaxId(), request.taxId);
        assertEquals(formEvent.getGender(), request.gender);
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
