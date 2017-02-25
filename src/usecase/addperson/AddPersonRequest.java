package usecase.addperson;

import requestor.Request;

public class AddPersonRequest implements Request {
    public String fullName;
    public String occupation;
    public int ageCategory;
    public int employmentStatus;
    public Boolean uSCitizen;
    public String taxId;
    public String gender;
}
