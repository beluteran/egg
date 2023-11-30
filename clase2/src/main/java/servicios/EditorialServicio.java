package servicios;

import entidades.Editorial;
import excepciones.MiExcepciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositorios.EditorialRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepository editorialRepositorio;
    //si se produce un cambio en la base de datos usamos transactional
    // si lanza excepcion hace un rol back
    // si no lanza hace un commit y emite el cambio
    @Transactional
    public void crearEditorial(String nombre){
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);
    }

    public List<Editorial> listaEditorial(){
        List<Editorial> lista = new ArrayList<>();
        lista = editorialRepositorio.findAll();
        return lista;
    }

    @Autowired
    public void modificarEditorial(String id, String nombre) throws MiExcepciones{
        validarEditorial(id,nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }
    }

    public void validarEditorial(String id, String nombre) throws MiExcepciones {
        if(id.isEmpty() || id == null){
            throw new MiExcepciones("El id del autor no puede ser nulo o vacio");
        }
        if(nombre.isEmpty() || nombre == null){
            throw new MiExcepciones("El nombre del autor no puede ser nulo o vacio");
        }
    }
}

