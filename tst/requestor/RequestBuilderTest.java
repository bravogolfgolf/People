package requestor;

import org.junit.Before;
import org.junit.Test;
import usecase.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestBuilderTest {
    private final Map<String, Class<? extends Request>> requests = new HashMap<>();
    private final RequestBuilder builder = new RequestBuilder(requests);
    private final HashMap<Integer, Object> args = new HashMap<>();

    @Before
    public void setUp() {
        requests.put("RefreshRequest", RefreshRequest.class);
        requests.put("AddPersonRequest", AddPersonRequest.class);
        requests.put("DeletePersonRequest", DeletePersonRequest.class);
        requests.put("ExportRequest", ExportRequest.class);
        requests.put("ImportRequest", ImportRequest.class);
    }

    @Test
    public void makeMethodShouldReturnRefreshRequest() {
        RefreshRequest request = (RefreshRequest) builder.make("RefreshRequest", args);
        assertTrue(request != null);
    }

    @Test
    public void makeMethodShouldReturnAddPersonRequest() {
        args.put(0, "Full Name");
        args.put(1, "Occupation");
        args.put(2, 0);
        args.put(3, 0);
        args.put(4, true);
        args.put(5, "Tax ID");
        args.put(6, "Gender");

        AddPersonRequest request = (AddPersonRequest) builder.make("AddPersonRequest", args);
        assertEquals(args.get(0), request.fullName);
        assertEquals(args.get(1), request.occupation);
        assertEquals(args.get(2), request.ageCategory);
        assertEquals(args.get(3), request.employmentStatus);
        assertTrue(request.uSCitizen);
        assertEquals(args.get(5), request.taxId);
        assertEquals(args.get(6), request.gender);
    }

    @Test
    public void makeMethodShouldReturnDeletePersonRequest() {
        int idToBeDeleted = 1;
        args.put(0, idToBeDeleted);
        DeletePersonRequest request = (DeletePersonRequest) builder.make("DeletePersonRequest", args);
        assertEquals(idToBeDeleted, request.id);
    }

    @Test
    public void makeMethodShouldReturnExportRequest() {
        File file = new File("Export.per");
        args.put(0, file);
        ExportRequest request = (ExportRequest) builder.make("ExportRequest", args);
        assertEquals(file, request.file);
    }

    @Test
    public void makeMethodShouldReturnImportRequest() {
        File file = new File("Import.per");
        args.put(0, file);
        ImportRequest request = (ImportRequest) builder.make("ImportRequest", args);
        assertEquals(file, request.file);
    }
}