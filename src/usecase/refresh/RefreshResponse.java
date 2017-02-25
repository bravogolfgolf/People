package usecase.refresh;

import respondor.Response;

import java.util.ArrayList;
import java.util.List;

public class RefreshResponse implements Response {
    public final List<RefreshResponseRecord> records = new ArrayList<>();
}
