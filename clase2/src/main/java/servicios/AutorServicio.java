package servicios;

import entidades.Autor;
import excepciones.MiExcepciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositorios.AutorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {

    @Autowired
    AutorRepository autorRepositorio;
    @Transactional
    public void crearAutor(String nombre) throws MiExcepciones {
        Autor autor = new Autor();
        validarAutor(nombre);
        autor.setNombre(nombre);
        autorRepositorio.save(autor);

    }

    public List<Autor> listarAutor(){
        List<Autor> lista = new ArrayList<>();
        lista = autorRepositorio.findAll();
        return lista;
    }

    @Transactional
    public void modificarAutor(String id, String nombre) throws MiExcepciones{
        validarAutor(nombre);
        if (id == null) {// aquí solo creo la exception pero en el controlador la lanzo y atrapo
            throw new MiExcepciones("El id no puede ser nulo...");
        }
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if(respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }

    }

    public Autor getOne(String id){
        return autorRepositorio.getOne(id);//utilizamos el metodo getOne para retornar el autor en base al id
        //            este get one, es propio de Spring
    }

    public void validarAutor(String nombre) throws MiExcepciones{

        if(nombre.isEmpty() || nombre == null){
            throw new MiExcepciones("El nombre del autor no puede ser nulo o vacio");
        }
    }
}
