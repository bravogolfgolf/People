package usecase;

import builderfactory.Request;

public class UpdatePersonRequest extends Request {
    public int id;
    public String fullName;
    public String occupation;
    public int ageCategory;
    public int employmentStatus;
    public boolean uSCitizen;
    public String taxId;
    public String gender;
}
