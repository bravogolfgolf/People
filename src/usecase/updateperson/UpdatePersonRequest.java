package usecase.updateperson;

import requestor.Request;

public class UpdatePersonRequest implements Request {
    public int id;
    public String fullName;
    public String occupation;
    public int ageCategory;
    public int employmentStatus;
    public boolean uSCitizen;
    public String taxId;
    public String gender;
}
