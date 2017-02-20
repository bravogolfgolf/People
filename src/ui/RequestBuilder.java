package ui;

import domain.Request;

import java.util.Map;

public interface RequestBuilder {
    Request make(String request, Map<Integer, Object> args);
}
