package org.example.user;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class FileUserService implements UserService {

    private String archivo;

    public FileUserService(String userFile) {
        archivo = userFile;
    }

    @Override
    public Optional<Usuario> validate(String usuario, String password) {

        var props = new Properties();
        try {
            props.load( new FileReader(archivo));
            if( props.containsKey("usuario") && props.containsKey("password") ) {
                if( props.getProperty("usuario").equals(usuario) && props.getProperty("password").equals(password) ) {
                    Usuario user =  new Usuario();
                    user.setUsuario(props.getProperty("usuario"));
                    user.setPassword(props.getProperty("password"));
                    return Optional.of(user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
