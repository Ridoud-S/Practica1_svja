package org.svja.top.practica1_sevj.repository;
import org.svja.top.practica1_sevj.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepository {

    private static UserRepository instance;
    private final ArrayList<User> historial = new ArrayList<>();

    private UserRepository() {}

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void agregar(User user) {
        historial.add(user);
    }

    public List<User> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public void limpiar() {
        historial.clear();
    }

    /** Elimina la sesión en el índice indicado. */
    public void eliminar(int index) {
        if (index >= 0 && index < historial.size()) {
            historial.remove(index);
        }
    }

    public int size() {
        return historial.size();
    }
}
